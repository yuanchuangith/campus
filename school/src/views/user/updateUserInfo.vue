<template>
  
<div class="update_info">
    <div>修改用户信息:</div>
    <el-form ref="form" class="from" :model="form" label-width="80px">
  <el-form-item label="用户名：">
    <el-input v-model="form.realName"></el-input>
  </el-form-item>
  <el-form-item label="性别：">
    <el-select v-model="form.sex" placeholder="请选择性别">
      <el-option label="男" value="1"></el-option>
      <el-option label="女" value="0"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="修改生日">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday" style="width: 100%;"  value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
    </el-col>
    <!-- <el-col class="line" :span="2">-</el-col>
    <el-col :span="11">
      <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
    </el-col> -->
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
      return {
        form: {
          realName: '',
          sex: '',
          birthday: '',
          userID:'',
         
        }
      }
    },
    methods: {
      onSubmit() {
        console.log(this.form)
        userUpdatePostApi('updateUserInfo',this.form).then(res=>{

          try{
            if (res.code==200)
            {
              message(res.code,res.message)
            }
            else
            message(res.code,res.message)


          }
          catch{
            message("","发生错误")

          }
        })
      }
    }
    ,
    created(){
      this.form.userID = this.$route.params.userID;
    }
  }
</script>
<style lang="scss" scoped>
.update_info{
    width: 40%;
}
.from{
    margin-top: 15px;
}
</style>