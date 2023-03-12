<template>
  <div>
    <el-row :gutter="10">
  <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7"><div class="grid-content bg-purple-dark"><div><Eduction  /></div>
  <div><WorkTitle title="工作展示" /> <Scorllwork class="itemPosContent"></Scorllwork></div></div></el-col>
  <el-col :xs="10" :sm="10" :md="10" :lg="10" :xl="10"><div class="divheight bg-purple-light"><div><Map  /></div>
   

  </div></el-col>
  <el-col :xs="7" :sm="7" :md="7" :lg="7" :xl="7"><div class="divheight bg-purple  "> <div><WorkTitle title="对于工作的评论"  class="titlework" /> 
  
  <Item class="itemPosContent" ><posititonContent  class="posititonContent"/></Item>
    <!-- Scroll down to see the bottom-right button.
  <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop> -->
</div></div></el-col>

<!-- buttom -->
<el-col :xs="17" :sm="17" :md="17" :lg="17" :xl="17"><div class="buttomheight bg-purple-light"><div><WorkTitle title="行业占比" />
  <ButtomRate></ButtomRate>


</div>
   

  </div></el-col>
</el-row>
  </div>
</template>

<script>
import Map from '@/components/work/map.vue'
import Eduction from '@/components/work/educationRequire.vue'
import Scorllwork from '@/components/work/scroll/scrollWork.vue'
import posititonContent from '@/components/work/posititonContent.vue'
import Item from '@/components/work/item.vue'
import WorkTitle from '@/components/work/workTitle.vue'
import ButtomRate from '@/components/work/buttomRate.vue'


export default {
  name:'work',
  components: { Map ,Eduction,Scorllwork,posititonContent,Item,WorkTitle,ButtomRate},

  data(){
    return{

    }
  }

}
</script>

<style lang="css" scoped>
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
  

  }
  .titlework{
    margin-top: 2%;
  }
  .itemPosContent
  {
    margin-top: 4%;
  }
  .posititonContent{
    padding-bottom:5%;
  }
  .buttomheight{
    border-radius: 4px;
    height: 585px; 
    margin-top: 5px;
  }
  .divheight{
    border-radius: 4px;
    height: 610px; 
  }
  .bg-purple {
  }
  .bg-purple-light {
    
  }
  .grid-content {
    border-radius: 4px;
    min-height: 1200px;
  }
  .el-row{
    background-image: url("@/assets/img/pageBg.png");
        background-size: cover;
        background-position: center center;
    padding-top: 5px;

  }
  .divPosition{
    height: 1200px;

  }
</style>