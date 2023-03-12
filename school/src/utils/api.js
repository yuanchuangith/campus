import axios from 'axios'
import {Message} from 'element-ui'
import route from '../router/index'
import {UtilVar} from "./config/setHeader";
let configs = {
    headers: { 'Content-Type': 'multipart/form-data' },

}
//请求拦截器
axios.interceptors.request.use(config => {
    let token = window.localStorage.getItem("token");
    let expir = window.localStorage.getItem("expir");
    // let userID = window.localStorage.getItem("tag");
    //如果存在token,请求就携带
    if (token) {
        config.headers['token'] = token;
    }
    if (expir) {
        config.headers['expir'] = expir;
    }
    // if (userID) {
    //     config.headers['userID'] = userID;
    // }
    
    return config
}, error => {
    console.log('request:', error)
})

axios.interceptors.response.use(success=>{
    if(success.status||success.status==200|| success.code==200)
    {
        if(success.data.code==500 || success.code==401||success.code==403)
        {
            // Message.error({message:success.data.message})
            return;
        }
        if (success.data.message)
        {
            // Message.success({message:success.data.message})

        }
        return success.data;
    }


},error=>{
console.log(error.response)
    Message.error({message:error.response.code })
    // route.replace();
})

const baseUrl=''
const testUrl='http://localhost:8081'
export const axiosRequest={
    post(url,params){
        return axios({
            method:'post',
            url:`${baseUrl}${url}`,
            data:params
        })
        
    }
    ,
    get(url,params){
        return axios({
            method:'get',
            url:`${baseUrl}${url}`,
            data:params
        })
    }
}
export const postRequest = (url, data) => {
    return axios({
        method: 'POST',
        url: `${baseUrl}${url}`,
        data: data
    })
}
export const getRequest=(url,params)=>{
    return  axios({
        method:'get',
        url:`${baseUrl}${url}`,
        data:params
    })
}
export const url={
    captcha: {
        url: '/api/Account/verifyCode',
        des: '获取验证码image流'
    },
    login:{
        url: '/api/Account/login',
        des: '用户登录'
    },
    register:{
        url: '/api/Account/register',
        des: '用户注册'
    }
}
export const GETNOBASE = async (url, params) => {
    try {
        const data = await axios.get(url, {
            params: params,
        });
        return data;
    } catch (error) {
        return error;
    }
}
let configs_ENC = {
    headers: { 'enc': UtilVar.ENC }
}
let isEncryptionParam = (params) => {
    return params

}
export const GET = async (url, params) => {
  
    try {
        params = isEncryptionParam(params)
      
        const data = await axios.get(`${baseUrl}${url}`, {
            params: params,
        });
        return data;
    } catch (error) {
        return error;
    }
}
export const POST = async (url, params,headers) => {
  
    try {
        params = isEncryptionParam(params)
       
      
        const data = await axios({
            method:'post',
            url:`${baseUrl}${url}`,
            data:params,
           
      
        })
        return data;
    } catch (error) {
        return error;
    }
}
export const POSTUPLOAD = async (url, params,headers) => {
  
    try {
        params = isEncryptionParam(params)
       
      
        const data = await axios({
            method:'post',
            url:`${baseUrl}${url}`,
            data:params,
            headers:headers
      
        })
        return data;
    } catch (error) {
        return error;
    }
}
/**
 * 下载文档流
 * @param {config.responseType} 下载文件流根据后端 配置   arraybuffer || blod
 */
export const FILE = async (config = {}, body, params) => {
    try {
        const data = await axios({
            method: config.method || 'get',
            url: `${baseUrl}${config.url}`,
            data: body,
            params: params,
            responseType: config.responseType || 'blob',
            onDownloadProgress: (e) => {
                // console.log(e,e.currentTarget)
                // if (e.currentTarget.response.size > 0) {
                //     e.percent = e.loaded / e.currentTarget.response.size * 100;
                // }
                // event.srcElement.getResponseHeader('content-length')
                config.onProgress && config.onProgress(e)
            },
        });
        return data;
    } catch (err) {
        return err;
    }
}
