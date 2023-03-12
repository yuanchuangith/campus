<template>
  <div id="app">
      <HeaderComponts></HeaderComponts>
        <router-view></router-view>
  </div>
</template>
<script>
import  HeaderComponts  from './components/header/HeaderComponts.vue';
export default{
  components:{HeaderComponts}


}

</script>
<style>
#app{
  min-height: 100%;
  width: 100%;
}
</style>
