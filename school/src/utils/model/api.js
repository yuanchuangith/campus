import * as API from '../api'


export const paramType ={
   
    'getPosiyionRate':"/api/work/getPosiyionRank", //站比率
    'newWorkContent':"/api/work/newWorkContent", //获取新评论
    'posititonContent':'/api/work/positContent',//工作的评论 
    'scrollPosition':'/api/work/scrollPosition',// 滚动工作 展示
    'educationRequire':'/api/work/JobRequier',// 工作要求
    'map':'/api/work/getProviceInfo',// //中间地图

}
export const workParam ={
   'getPosition':"/api/work/getIndexPosition",
   'getProvice':"/api/work/getWorkProvices" ,//获取省份
   'getIndustrykind':"/api/work/getIndustrykind" ,//获取行业
   'getSearchTips':"/api/work/getSearchTips",
   'getIndexSearchPosition':"/api/work/getIndexSearchPosition",
   "getUser":"/api/Account/getUserInfo",
   'getJobDetail':"/api/work/getJobDetail",
   'getReplyContent':"/api/work/getReplyContent",
   'getSearchKeyByConpanyName':"/api/conpany/getSearchKeyByConpanyName",
   'getConapnyAndrate':"/api/conpany/getConapnyAndrate",
   'getSearchByconpanyName':"/api/conpany/getSearchByconpanyName",
   'getConpanyDetail':"/api/conpany/getConpanyDetail",
   'getconpantContentPage':"/api/conpany/getconpantContentPage",
   'ratepraise':"/api/conpany/praise",
   "replyConten":"/api/conpany/replyConten",
   "alipay":"/api/aply/alipay",
   'checkCodeGetToken':'/api/serverCode/getCheckToken',


}
const updateUser={
    'updatePassWord':"/api/Account/updatePassW",
    'avatar':"/api/upload/avatar",
    'updateUserInfo':"/api/Account/updateUserInfo",
    'userDetailInfo':"/api/Account/userDetailInfo",
    'getOrder':"/api/Account/getOrders",
    'getContent':"/api/Account/getContent",

}
const obedient={
    'getObedient':"/api/obedient/getObedient",
    'getplate':"/api/obedient/getplate",
    'getplateObedient':"/api/obedient/getplateObedient",
 'getObedientDetial':"/api/obedient/getObedientDetial",
}
const uploadUrl={
    'uploadImg':"/api/upload/uploadImg",
    'uploadtest':"/api/upload/postTest",
    'saveContent':'/api/content/saveContent',
    'praise':'/api/content/praise',
    'testCode':'/check/checkCode',
    'chatGpt':'/check/chatGpt',
    
}
export const currentGET=  (key,param)=> {
 
    return API.GET(paramType[key], param)
}

export const getApi=(key,param)=> {
 
    return API.GET(workParam[key], param)
}
export const postApi=(key,data)=> {

    return API.POST(workParam[key], data)
}
export const uploadGetApi=(key,param,headers)=> {
 
    return API.GET(uploadUrl[key], param,headers)
}
export const uploadPostApi=(key,data)=> {

    return API.POST(uploadUrl[key], data)
}
export const uploadPostHeaderApi=(key,data,headers)=> {

    return API.POSTUPLOAD(uploadUrl[key], data,headers)
}
export const userUpdatePostApi=(key,data)=> {

    return API.POST(updateUser[key], data)
}
export const userUpdateGetApi=(key,data,headers)=> {

    return API.GET(updateUser[key], data,headers)
}
export const obedientPostApi=(key,data)=> {

    return API.POST(obedient[key], data)
}
export const obedientGetApi=(key,data,headers)=> {

    return API.GET(obedient[key], data,headers)
}

