<template>
    <div class="loop" >   <div class="carousel">  <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in loopImgs" :key="item">
       <div class="imgdiv"> <img :src="require('@/assets/img/Loop/'+item)" alt=""  style=" width: 100%; height: 100%;"></div>
      </el-carousel-item>
    </el-carousel>
  </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            loopImgs:['1-1.jpg','1-2.jpg','1.jpg','3.jpg'],
        }
    }

}
</script>

<style>

.loop{
    
  }
</style>