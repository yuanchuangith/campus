<template>
<div>
    <el-descriptions v-if="infoData" class="margin-top" title="用户信息" :column="3" direction="vertical">
  <el-descriptions-item label="用户名">{{infoData.user.userName}}</el-descriptions-item>
  <el-descriptions-item label="手机号">{{infoData.user.telephone}}</el-descriptions-item>
  <el-descriptions-item label="昵称" :span="2">{{infoData.user.realName}}</el-descriptions-item>
  <el-descriptions-item label="余额">
    <el-tag size="small">{{infoData.userContentInfo.money}}</el-tag>
  </el-descriptions-item>
  <el-descriptions-item label="评论数">{{infoData.userContentInfo.contentPiraise}}</el-descriptions-item>
  <el-descriptions-item label="获得赞">{{infoData.userContentInfo.contentPiraise+infoData.userContentInfo.replyPraiseNumSum}}</el-descriptions-item>
  <el-descriptions-item label="帖子数">{{infoData.userContentInfo.contentNum}}</el-descriptions-item>
  <!-- <el-descriptions-item label="是否可以修改性别" :span="2">{{infoData.user.realName}}</el-descriptions-item> -->
 
</el-descriptions>
</div>
</template>

<script>
import {userUpdateGetApi} from '@/utils/model/api'
import {message} from '@/utils/model/usullyUtils'
  export default {
    data() {
      return {
        userID:'',
        infoData:'',
      }
    },
    methods: {
      getDta() {
        userUpdateGetApi("userDetailInfo",{'userID':this.userID}).then(res=>{
          try {
            if(res.code)
            {
              this.infoData=res.data;
            console.log(this.infoData)
              message(res.code,res.message)
            }
          } catch (error) {
            message(null,"失败")
            
          }
        })
      }
    }
    ,
    mounted(){
      this.userID = this.$route.params.userID;
      this.getDta();
    }
  }
</script>

<style>

</style>