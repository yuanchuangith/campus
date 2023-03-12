<template>
  <div
    class="userLogin"
    v-loading="loading"
    element-loading-text="登录中..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.6)"
  >
    <el-form
      :model="loginFrom"
      :rules="rules"
      ref="loginFrom"
      label-width="100px"
      class="loginFrom"
    >
      <h3 class="title">用户登录</h3>
      <el-form-item label="用户名：" prop="username">
        <el-input
          v-model="loginFrom.username"
          placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input
          v-model="loginFrom.password"
          placeholder="请输入密码"
        ></el-input>
      </el-form-item>
      <el-form-item label="验证码：">
        <el-input
          type="text"
          v-model="loginFrom.code"
          placeholder="请输入验证码"
        ></el-input>
        <img
          :src="captchaUrl"
          @click="updateCaptcha"
          style="width: 100px; height: 25px; margin-top: 10px"
          alt=""
        />
      </el-form-item>

      <el-form-item label="keep Login" prop="isKeep">
        <el-switch v-model="loginFrom.isKeep"></el-switch>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('loginFrom')"
          >登录</el-button
        >
        <el-button @click="resetForm('loginFrom')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {message} from '@/utils/model/usullyUtils'
export default {
  name: "Login",
  data() {
    return {
      captchaUrl: this.$requestUrl.captcha.url + "?time=" + new Date(),
      loginFrom: {
        username: "",
        password: "",

        isKeep: false,

        code: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { message: "", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 4, message: "密码最少是4位", trigger: "blur" },
        ],
        code: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          { min: 4, message: "验证码是4位", trigger: "blur" },
        ],
      },
      loading: false,
    };
  },
  methods: {
    updateCaptcha() {
      this.captchaUrl = this.$requestUrl.captcha.url + "?time=" + new Date();
    },
    // getYear(){},
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$axiosRequest.post(this.$requestUrl.login.url, this.loginFrom)
            .then((res) => {
              console.log(res);
              if (res.code == 200) {
                window.localStorage.setItem("token", res.data.token);
                window.localStorage.setItem("expir", res.data.expir);
                // window.localStorage.setItem("tag", res.data.UserID);
                //跳转首页
                // this.$router.replace('/home')
       
                let path = this.$route.query.redirect;
                this.$router.replace(
                  path === "/" || path === undefined ? "/register" : path
                );
               
              }
              else
              {
              this.loading = false;
           
                message(res.code,res.message)

              }

            });

          // alert('submit!');
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  computed: {},
};
</script>

<style lang="css" scoped>
.loginFrom {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 188px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: #fff;
  border: 10px solid #eaeaee;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.userLogin {
  height: 950px;
  width: 100%;
  overflow: hidden;
  background-image: url("@/assets/Login/img/bg2.jpg");
  background-repeat: no-repeat;
  background-size: cover; /*背景图基于容器大小伸缩*/
  background-attachment: fixed; /*固定，不随其余部分滚动*/
  background-clip: border-box; /*设置元素的背景延伸到边框下面*/
  /*background-position: 为image设置初始位置*/
  /*background-origin:   基于什么进行定位*/
}
.el-input {
  display: inline-block;
  height: 47px;
  width: 100%;
}

.title {
  margin-left: 43%;
  margin-bottom: 20px;
}
</style>