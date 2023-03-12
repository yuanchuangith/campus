<template>
  <div class="content">
  <div style="height:300px">   <div ref="vantaRef"></div></div>

<div>    <el-row :gutter="10">
  <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content divH"></div></el-col>
  <el-col  :xs="16" :sm="16" :md="16" :lg="16" :xl="16"><div class="grid-content bg-purple-light divH">
<div class="top">
 
    <el-row>
  <el-button>识别4位数验证码</el-button>
  <el-button type="primary">识别旋转验证码</el-button>

 
</el-row>

    <!-- <el-carousel indicator-position="outside">
    <el-carousel-item v-for="item in 4" :key="item">
      <h3>{{ item }}</h3>
    </el-carousel-item>
  </el-carousel> -->
</div>
    <div class="test">
      <el-form ref="form"  label-width="80px">
        <info :postData="postData" :getToken="getToken"></info>
        <el-form-item label="识别结果">
        <div> <el-input placeholder="识别结果" v-model='result'   clearable></el-input></div>
      </el-form-item>
      </el-form>
    </div>


  </div></el-col>
  <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
    <div class=" divH">
      <div class="zhanweiIDnv"></div>
      <div class="divfour grid-content ">
        <div v-show="isLogin">
          <userView :userInfo="userInfo" />
        </div>
        <router-link active-class="active" to="/login"> <el-empty :image-size="200" v-show="!isLogin" > <el-button
              type="primary">去登录</el-button></el-empty></router-link>
  
      </div>
    </div>
  </el-col>

</el-row></div>
  </div>
</template>

<script>
import * as THREE from 'three'
import NET from 'vanta/src/vanta.net'
import info from '@/components/checkCode/info.vue'
import {uploadPostHeaderApi,postApi} from "@/utils/model/api";
import userView from '@/components/workPrimary/userView.vue';
import {getUserInfoByt } from "@/utils/model/getInfo";
import pubsub from 'pubsub-js'
export default {
    components:{info,userView},
    data() {
    return {
      result:'',
      userInfo:{},
     isLogin:false,
   
    }

}
,
methods:{
    postData(param,token){
      let expir = window.localStorage.getItem("expir");
    let dates = new Date().getTime();
  
    if (expir > dates && this.userInfo) {
        if(token)
        {
            let hander = {
          'tokenCheckCode': token
        };
            uploadPostHeaderApi("testCode",param,hander).then(res=>{
                if(res.code==200)
                {
                 this.result= res.data.code;
                }
            })

        }
      }
      else
    {
      const h = this.$createElement;

        this.$notify({
          title: '请登录',
          message: h('i', { style: 'color: teal'}, '登录过期请重新登录')
        });
    }


    },
      getToken(){

    let expir = window.localStorage.getItem("expir");
    let dates = new Date().getTime();
  
    if (expir > dates && this.userInfo) {
   
      postApi('checkCodeGetToken', { "userID": this.userInfo.userID,"userName":this.userInfo.userName }).then(res => {
        if (res.data) {
       
        
          pubsub.publish("getToken", res.data)
       
        }
      })
        ;
    }
    else
    {
      const h = this.$createElement;

        this.$notify({
          title: '请登录',
          message: h('i', { style: 'color: teal'}, '登录过期请重新登录')
        });
    }
  },
    getUser()
    {
     var getUserinfo=getUserInfoByt();
     if(getUserinfo)
     {
      getUserinfo.then(res=>{
         this.userInfo=res.data;
         this.isLogin=true;
        })
     }
     
      



    },

},
mounted() {

    this.vantaEffect = NET({
      el: this.$refs.vantaRef,
      THREE: THREE,
      mouseControls:true,touchControls:true,gyroControls:false,minHeight:1300.00,scale:1.00,scaleMobile:1.00,  color: 0xf0f0f0,
  backgroundColor: 0x0
    })
    this.getUser();
  },
beforeDestroy() {
    if (this.vantaEffect) {
      this.vantaEffect.destroy()
    }
  },
}
</script>

<style>

</style>
<style src="@/assets/css/captureCodecss/captureCode.scss" lang="scss" scoped />
<style  src="@/assets/css/userLoginInfo.scss" lang="scss" scoped></style>
