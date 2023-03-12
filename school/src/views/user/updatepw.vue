<template>
  <div id="updatepw">
    <el-form :rules="rules" ref="pw" :model="pw" label-width="80px">
      <el-form-item label="修改密码">

  </el-form-item>
  <el-form-item label="旧密码">
    <el-input type="password" v-model="pw.oldPassWord"></el-input>
  </el-form-item>
  <el-form-item label="新密码" prop="pass">
    <el-input type="password" v-model="pw.newPassWord" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="pw.checkPass" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">更改</el-button>
    <el-button>取消</el-button>
  </el-form-item>
  
</el-form>
  </div>
</template>

<script>
import {userUpdatePostApi} from '@/utils/model/api'
import {message} from '@/utils/model/usullyUtils'

 export default {
    data() {
        var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.pw.newPassWord) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
      
        pw: {
          oldPassWord: '',
          newPassWord: '',
          checkPass: '',
          userID:''
          
        }
        ,
        rules: {
            checkPass: [
            { validator: validatePass, trigger: 'blur' }
          ],
        }
      }
    },
    methods: {
      onSubmit() {
      if(this.pw.newPassWord==this.pw.checkPass)
      {

        userUpdatePostApi("updatePassWord",this.pw).then(res=>{
       try {
          if(res.code==200)
          {
            message(res.code,res.message)
            this.pw.oldPassWord='';
            this.pw.newPassWord='';
            this.pw.checkPass='';
          }
       } catch (error) {
        message(null,"失败")
        
       }

        })
      }
      }
    },
    created(){
      this.pw.userID = this.$route.params.userID;
     
    }

  }
</script>

<style lang="scss" scoped>
#updatepw{
    width: 40%;
}

</style>