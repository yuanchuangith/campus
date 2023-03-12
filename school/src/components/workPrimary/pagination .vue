<template>

<div>
    <div class="block pag pagination">
        <el-pagination 
      
     
  
      :page-size="40"
      layout="prev, pager, next, jumper"

  @current-change="current"
  :total="this.total">
</el-pagination>
</div>
</div>
</template>
<!-- @prev-click="prev" -->
<!-- @next-click="next" -->
<script>
export default {
    props:['postGetData','total'],
    data(){
    return{
        currentPage2: 5,
    }
  },
  methods:{
    prev(num){
        console.log(num)
    },
    next(num){
        console.log(num)
    }
    ,
    current(num){
       this.postGetData(num);
    }
  }

}
</script>

<style  scoped>
.pag{
  
    width: 100%;
}
.pagination {
  padding-top: 35px;
  text-align: center;
  padding: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}



.pagination li {
  /* width: 32px;
  height: 32px !important;
  line-height: 32px; */
  /* font-family: ".PingFang SC"; */
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  text-align: center;
  background-color: #fff;
  color: #000000;
  border: 1px solid #e5e5ea;
}
/* 激活后的样式 */



</style>