<template>
    <div class="content">
<loopImg></loopImg>
  
  
  <div>    <el-row :gutter="10">
    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content divH"></div></el-col>
    <el-col  :xs="16" :sm="16" :md="16" :lg="16" :xl="16"><div class="grid-content bg-purple-light divH">
  
    <div class="center">
      <div id="car_content"></div>
        
      <div class="top">
        <h2><span class="bname">充值</span></h2>
      </div>
      <div class="top">
          <div class="selectMoney">
            <el-button type="primary"  @click="rechargeMone(1)">1</el-button>
            <el-button type="primary"  @click="rechargeMone(2)">2</el-button>
            <el-button type="primary"  @click="rechargeMone(5)">5</el-button>
    
            <el-button type="primary"  @click="rechargeMone(10)">10</el-button>
            <el-button type="primary"  @click="rechargeMone(20)">20</el-button>
            <el-button type="primary"   @click="rechargeMone(50)">50</el-button>
            <el-button type="primary"  @click="rechargeMone(100)">100</el-button>
        
     
          </div>
          <div class="money inp">    <el-input v-model="money" placeholder="请输入你充值的金额" ></el-input></div>
          <div class="money but">   <el-button type="primary" @click="rechargeMone(-1)" >充值</el-button></div>
      </div>
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
  
  <script lang="js">

  import info from '@/components/checkCode/info.vue'
  import {postApi} from "@/utils/model/api";
  import userView from '@/components/workPrimary/userView.vue';
  import {getUserInfoByt } from "@/utils/model/getInfo";
  import {Message} from 'element-ui'
  import loopImg from "@/components/loopImg.vue"

  export default {
      components:{info,userView,loopImg},
      data() {
      return {
        result:'',
        userInfo:{},
       isLogin:false,
       key:'',
       token:'',
       rquestTokenNum:0,
     
       money:'',
     
      }
  
  }
  ,
  methods:{
    rechargeMone(money)
    {
     
      if(!this.userInfo.userID)
        return;
      
      if(money<0)
      {
        if(this.money>0)
        {
          console.log(1)
          money=this.money;

        }
        else
        {
        return;
        }

      }

      postApi("alipay",{
        'userID':this.userInfo.userID,
        'userName':this.userInfo.userName,
        'totalAmount':money

      }).then(res=>{
        console.log(res)
     if(res.code==200)
     {
                let form = res.data;
            
              
                  console.log(form);
                  const div = document.createElement('div');
                  div.innerHTML = form;
                  document.getElementById('car_content').appendChild(div);
                  document.forms[0].submit();

    }

      })
      

    },
    check(){
   if(this.$route.query.total_amount>0)
   {
 Message.success({message:"充值成功"})
   }
    }
    ,
    
  
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
  
      
      this.getUser();
      this.check();
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
  
  <style src="@/assets/css/captureCodecss/reCharge.scss" lang="scss" scoped />
  <style src="@/assets/css/userLoginInfo.scss" lang="scss" scoped />