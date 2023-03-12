<template>
         <div class="content">


    <div>    <el-row :gutter="10">
    <el-col :xs="2" :sm="2" :md="2" :lg="2" :xl="2"><div class="grid-content divH "></div></el-col>
    <el-col  :xs="21" :sm="21" :md="21" :lg="21" :xl="21"><div class="grid-content  divH">
      
      
        <el-row :gutter="16">
    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5"><div class="grid-content  divH  shadow">
        <div class="left_top"> 
            <div class="avatar">  <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></div>
           
        </div>
        <div class="menu"><ul>
            <router-link active-class="active" :to="`/userSettingIndex/userDetailInfo/`+userInfo.userID">   <li class="item"> <i class="el-icon-c-scale-to-original c_icon"></i> 详细信息</li></router-link>
            <router-link active-class="active" :to="`/userSettingIndex/avatar/`+userInfo.userID">   <li class="item"> <i class="el-icon-user c_icon"></i> 修改头像</li></router-link>
           <router-link active-class="active" :to="`/userSettingIndex/updatepw/`+userInfo.userID"> <li class="item"> <i class="el-icon-bank-card c_icon"></i> 修改密码</li></router-link>
                  <router-link active-class="active" :to="`/userSettingIndex/updateUserInfo/`+userInfo.userID">   <li class="item"> <i class="el-icon-user-solid c_icon"></i> 修改个人信息</li></router-link>
                  <router-link active-class="active" :to="`/userSettingIndex/order/1/`+userInfo.userID"> <li class="item"> <i class="el-icon-shopping-cart-full c_icon"></i> 所有订单</li></router-link>
                  <router-link active-class="active" :to="`/userSettingIndex/order/2/`+userInfo.userID"><li class="item"> <i class="el-icon-shopping-cart-2 c_icon"></i> 已付订单</li></router-link>
                  <router-link active-class="active" :to="`/userSettingIndex/order/3/`+userInfo.userID">   <li class="item"> <i class="el-icon-shopping-cart-1 c_icon"></i> 未付订单</li></router-link>
                    <router-link active-class="active" :to="`/userSettingIndex/updatepw/`+userInfo.userID">   <li class="item"> <i class="el-icon-receiving c_icon"></i> 发表帖子</li></router-link>
                        <router-link active-class="active" :to="`/userSettingIndex/content/`+userInfo.userID">  <li class="item"> <i class="el-icon-chat-line-round c_icon"></i> 发表评论</li></router-link>
                            <router-link active-class="active" :to="`/userSettingIndex/updatepw/`+userInfo.userID">  <li class="item"> <i class="el-icon-star-on c_icon"></i> 收藏</li></router-link>
        </ul></div>

        <div></div>


        
    </div></el-col>
    <el-col  :xs="19" :sm="19" :md="19" :lg="19" :xl="19 ">
        <div class="grid-content shadow  divH">
<div class="inner_content">
            <router-view :key="$route.fullPath"></router-view>
        </div>


    </div></el-col>
  

    </el-row>



    </div></el-col>
    <el-col :xs="2" :sm="2" :md="2" :lg="2" :xl="2">
 
    </el-col>

    </el-row>
</div>
    </div>
</template>

<script>
import {getUserInfoByt } from "@/utils/model/getInfo";
export default {

    data() {
    return {
      result:'',
      userInfo:{},
     isLogin:false,
  
    }

}
,
methods:{
    getUser()
    {
     var getUserinfo=getUserInfoByt();
     if(getUserinfo)
     {
      getUserinfo.then(res=>{
         this.userInfo=res.data;

            
        })
     }
     
      



    },
},
created(){
    this.getUser();
   
}
}
</script>

<style lang="scss" scoped src="@/assets/user/userSettingIndex.scss">

</style>