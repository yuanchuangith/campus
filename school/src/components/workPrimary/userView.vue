<template>
  <div class=""> 
    <div class="avatar"> <div> <div><template v-if="this.userInfo.protraitPhoto">
              <el-avatar :src="require(`@/assets/img/userAvatar/`+this.userInfo.protraitPhoto)"  :size="100"></el-avatar>
            </template>
            <el-avatar
              v-else
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar></div></div></div> 
    <div class="info"><ul><li>
        <span> <el-tag  ><i class="el-icon-user" closable></i>用户名：</el-tag></span>
        <span>{{ this.userInfo.userName }}</span>
    
    </li>
        <li><span> <el-tag  ><i class="el-icon-ice-cream-round" closable></i>性别 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</el-tag></span>

            <span v-if=" this.userInfo.sex==1">男</span>
            <span v-else>女</span>
        
        </li>
    
        <li><span> <el-tag  ><i class="
el-icon-milk-tea" closable></i>登录IP&nbsp;&nbsp;&nbsp;&nbsp;:</el-tag></span>
<span>{{ this.userInfo.loginIp }}</span>
        
        
        </li>
      
    </ul></div>
      
    </div>
</template>

<script>
export default {
    props:['userInfo'],

}
</script>

<style scoped lang="css">
.user{
 
}
ul,li{
  list-style: none;
  margin: 0;
  
}
.avatar{
  min-height: 100px;
  display: flex;
  justify-content: center;
  align-content: center;

}
.avatar>div{
  width: 20%;
  margin: auto;
}
.info>ul{
  width: 85%;
  margin: auto;
  padding-top: 20px;

  
}
.info>ul>li{
  margin-top: 10px;
}

</style>