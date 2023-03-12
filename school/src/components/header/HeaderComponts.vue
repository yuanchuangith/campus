<template>
<div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">  <router-link active-class="active" to="/">首页</router-link></el-menu-item>

  <el-submenu index="2">
  <template slot="title">   <router-link active-class="active" to="/index"> 论坛</router-link></template>
  <router-link active-class="active" to="/index">  <el-menu-item index="2-1">首页</el-menu-item></router-link>
  <router-link active-class="active" to="/index">  <el-menu-item index="2-1">x</el-menu-item></router-link>
    <el-menu-item index="2-3">选项3</el-menu-item>
    
    <!-- <el-submenu index="2-4">
      <template slot="title">选项4</template>
      <el-menu-item index="2-4-1">选项1</el-menu-item>
      <el-menu-item index="2-4-2">选项2</el-menu-item>
      <el-menu-item index="2-4-3">选项3</el-menu-item>
    </el-submenu> -->
  </el-submenu>
  <el-submenu index="2-4">
 <template slot="title">   <router-link active-class="active" to="/work"> work</router-link></template>
 <router-link active-class="active" to="/indexWork">   <el-menu-item index="2-4-1">indexWork</el-menu-item></router-link>
      <!-- <el-menu-item index="2-4-2">选项2</el-menu-item> -->
      <router-link active-class="active" to="/work/conpany">   <el-menu-item index="2-4-3">conpany</el-menu-item></router-link>
    </el-submenu>
    <el-submenu index="2-5">
      <template slot="title">   <router-link active-class="active" to="/checkCode"> 验证码识别</router-link></template>
      <router-link active-class="active" to="/chatGpt"> <el-menu-item index="2-4-2">CHATGPT</el-menu-item></router-link>

      <router-link active-class="active" to="/reCharge"> <el-menu-item index="2-4-2">充值</el-menu-item></router-link>

  </el-submenu>
  <!-- <router-link active-class="active" to="/checkCode">  <el-menu-item index="4">订单管理</el-menu-item></router-link> -->
  <el-submenu index="4">
    <template slot="title"> <router-link active-class="active" to="/login">登录</router-link></template>

    <router-link active-class="active" to="/register"><el-menu-item index="4-1">注册</el-menu-item></router-link>
  </el-submenu>
  <el-submenu index="5" id="seeting" >
    <template slot="title"> <i class="el-icon-s-tools"></i> </template>

    <router-link active-class="active" to="/userSettingIndex"><el-menu-item index="4-1">用户设置</el-menu-item></router-link>
    <router-link active-class="active" to="/reCharge"> <el-menu-item index="2-4-2">充值</el-menu-item></router-link>
    <router-link active-class="active" to="/"> <el-menu-item index="2-4-2">联系我们</el-menu-item></router-link>
    <router-link active-class="active" to="/"><el-menu-item index="4-1">登出</el-menu-item></router-link>
  </el-submenu>
</el-menu>
<div class="line"></div>

</div>


</template>

<script>
  export default {
    name:'HeaderComponts',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        // console.log(key, keyPath);
      }
    }
  }
</script>
<style lang="scss" scoped>


a{
  text-decoration-line: none;
  color: #000;
}
#seeting{
 margin-left: 64%;
}

</style>