#coding=utf-8

from fastapi  import FastAPI,Form,Header,File,UploadFile
import uvicorn
from typing import Union
from starlette.requests import Request,http_cookies
import cv2
from model.code.getCheckCode import *
from model.code.ResultUtil import *
from model.code.JwtToken import *
from model.code.connectMysql import *
import requests
import openai
import json

openai.api_key = "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"
app = FastAPI()


@app.get("/")
async def main():
    return {'message':'hellow'}
@app.post("/checkCode")
async def checkCode(request:Request,file:UploadFile=File(...),tokenCheckCode: Union[str, None] = Header(default=None)):
    global jwt
    global sqlconnect

    try:

        userInfo=jwt.get_payload_from_token(tokenCheckCode)
        if userInfo:

            userID,userName=userInfo.split('|')
            accountInfo=sqlconnect.selectSql('SELECT  chargeMoney    ,trialCode FROM rechargeMoney WHERE userID="{}"  and delTag="1" '.format(userID))[0]
            if accountInfo['trialCode']>0:





                content = file.file.read()


                getCheck=getCheckCode.getCode(content)
                sql= 'UPDATE rechargeMoney set trialCode={} WHERE userID="{}"'.format(
                        accountInfo['trialCode'] - 1,userID)
                sqlconnect.executeSql(sql)

                return ResultUtil.success(code=200,message='成功',data={"code":getCheck})

            elif accountInfo['chargeMoney']>0.1:
                content = file.file.read()

                getCheck = getCheckCode.getCode(content)
                sqlconnect.executeSql(
                    'UPDATE rechargeMoney set chargeMoney={} WHERE userID="{}"'.format(
                        accountInfo['chargeMoney']-0.1,userID))

                return ResultUtil.success(code=200, message='成功', data={"code": getCheck})
            else:
                return ResultUtil.fail(code=50003, message='余额不足')
        else:
            return ResultUtil.fail(code=50002, message='错误')




    except Exception as e:

        print(e)
        return ResultUtil.fail(50001,message='失败')


    print(len(file))
    pass
@app.post("/chatGpt")
async def chatGpt(request:Request,key:str = Form(...),tokenCheckCode: Union[str, None] = Header(default=None)):
    try:

        userInfo = jwt.get_payload_from_token(tokenCheckCode)
        if userInfo:

            userID, userName = userInfo.split('|')
            accountInfo = sqlconnect.selectSql(
                'SELECT  chargeMoney    ,trialChat FROM rechargeMoney WHERE userID="{}"  and delTag="1" '.format(
                    userID))[0]
            if accountInfo['trialChat'] > 0:


                response = openai.Completion.create(
                    model="text-davinci-003",
                    prompt=key,
                    temperature=0,
                    max_tokens=2048,
                    top_p=1,
                    frequency_penalty=0.3,
                    presence_penalty=0.15,
                    stop=None
                )


                if(response):
                    data=response["choices"]
                    sql = 'UPDATE rechargeMoney set trialChat={} WHERE userID="{}"'.format(
                        accountInfo['trialChat'] - 1, userID)
                    sqlconnect.executeSql(sql)
                    return  ResultUtil.success(code=200, message='成功', data=data)
            elif  accountInfo['chargeMoney'] > 0.5:
                response = openai.Completion.create(
                    model="text-davinci-003",
                    prompt=key,
                    temperature=1 ,
                    max_tokens=2048,
                    top_p=1,
                    frequency_penalty=0.3,
                    presence_penalty=0.15,
                    stop=None
                )

                # respone= requests.post(url=url,data=data,headers=headers)

                if (response):
                    data = response["choices"]
                    sqlconnect.executeSql(
                        'UPDATE rechargeMoney set chargeMoney={} WHERE userID="{}"'.format(
                            accountInfo['chargeMoney'] - 0.5, userID))
                    return ResultUtil.success(code=200, message='成功', data=data)
            else:
                return ResultUtil.fail(code=50014, message='请充值')
        else:
            return ResultUtil.fail(code=50013, message='错误')



    except Exception as e:
        print(e)
        return ResultUtil.fail(code=50012, message='错误')


    pass

if __name__ == '__main__':
    jwt = JwtToken(3600 * 24 * 1)

    sqlconnect = connectMysql('campus')
    uvicorn.run(app,host="localhost",port=9000)
    pass