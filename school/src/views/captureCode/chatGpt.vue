<template>
  <div class="content">
  <div >   <div ref="vantaRef"></div></div>

<div>    <el-row :gutter="10">
  <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content divH"></div></el-col>
  <el-col  :xs="16" :sm="16" :md="16" :lg="16" :xl="16"><div class="grid-content bg-purple-light divH">

  <div class="center">
    <h2>ChatGpt聊天</h2>
    <div class="chat_content  infinite-list" >
      <div class="chat infinite-list-item"  v-for="(item, index) in chatData" :key="index">
          <div class="info" v-if="item.model==1">
       <span><el-avatar v-if="userInfo.protraitPhoto" :src="userInfo.protraitPhoto"></el-avatar>
        <el-avatar v-else src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      </span>
       <span style="margin-left: 5px;margin-top: 15px;"> {{ userInfo.userName }}</span>
          </div>
          <div class="info" v-if="item.model==0">
       <span><el-avatar :src="require('@/assets/img/chatGpt/chatGpt.jpg')"></el-avatar></span>
       <span style="margin-left: 5px;margin-top: 15px;"> ChatGpt</span>
          </div>
          <div class="chat_str">
            <vue-typed-js :strings="[item.content]" :shuffle="true" :typeSpeed="80"
            :fadeOutClass="'fadeOutClass'" :loop="true" :loopCount="1" :startDelay="500" :showCursor="false" :smartBackspace="true"
            :contentType="'html'">
            <p class="typing"></p>
          </vue-typed-js>
          </div>
      
      
        </div></div>
    <div class="inputdiv"><div class="data"><el-input type="textarea"  :autosize="{ minRows: 2, maxRows: 5}" placeholder="请输入内容" v-model="key">
        </el-input> </div> <div class="publish"><el-button type="primary" @click="getData">发送</el-button></div></div>
    </div>


  </div></el-col>
  <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
    <div class=" divH">
      <div class="zhanweiIDnv"></div>
      <div class="divfour user_info grid-content ">
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
import {tip} from "@/utils/model/usullyUtils"
import pubsub from 'pubsub-js'
export default {
    components:{info,userView,},
    data() {
    return {
      result:'',
      userInfo:{},
     isLogin:false,
     key:'',
     token:'',
     rquestTokenNum:0,
   idPost:true,
   chatData:[

  ],
    }

}
,
methods:{

  getData(){
  

    if(!this.idPost)
    {
      tip(this,"请稍等","等待服务器返回结果",null)
      return;
    }
    let expir = window.localStorage.getItem("expir");
    let dates = new Date().getTime();

    if (expir > dates && this.userInfo) {
      if (this.rquestTokenNum == 0 || !this.token) {
    
        this.getToken();
       
        tip(this,"请稍等","正在请求token，请再次提交",null);
        
        return
      }
      if (this.token) {
       
        let hander = {
          'tokenCheckCode': this.token
        };
        const param  = new FormData();
        param .append("key", this.key);
        this.idPost=false;
        this.chatData.push({"content":this.key,'model':1})
       try {
        uploadPostHeaderApi("chatGpt",param,hander).then(res=>{
          try {
            if(res.code==200)
            {
             
              this.idPost=true;
          
             console.log(res.data)
             const text=res.data[0].text;
             this.key='';

             this.chatData.push({'content':text.toString(),'model':0})
            
            }
            else
            {
              this.chatData.push({'content':"网络繁忙",'model':0})
              this.idPost=true;
            }
          } catch (error) {
            console.log(error)
            this.idPost=true;
            this.chatData.push({'content':"网络繁忙",'model':0})
          }

        })
       } catch (error) {
       
        tip(this,"错误","服务器错误", color='color: red')
       }

      }
    }
    else {
      const h = this.$createElement;

      this.$notify({
        title: '请登录',
        message: h('i', { style: 'color: teal' }, '登录过期请重新登录')
      });
    }

    },
      getToken(){

    let expir = window.localStorage.getItem("expir");
    let dates = new Date().getTime();
  
    if (expir > dates && this.userInfo) {
   
      postApi('checkCodeGetToken', { "userID": this.userInfo.userID,"userName":this.userInfo.userName }).then(res => {
        if (res.code==200) {
        
        
         this.token=res.data;
         this.rquestTokenNum+=1;
       
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
      mouseControls:true,touchControls:true,gyroControls:false,minHeight:900.00,scale:1.00,scaleMobile:1.00, color: 0x823030,
backgroundColor: 0xe1e1ff
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

<style src="@/assets/css/captureCodecss/chatGpt.scss" lang="scss" scoped />
<style src="@/assets/css/userLoginInfo.scss" lang="scss" scoped />