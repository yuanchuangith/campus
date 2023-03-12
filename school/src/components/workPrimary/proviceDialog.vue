<template>
  <div class="provicemain">
    <el-button type="text" @click="dialogVisible = true"> 全部省份></el-button>

<el-dialog
  title="工作地点选择"
  :visible.sync="dialogVisible"
  width="40%"
  :before-close="handleClose">
  <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6" ><div class=" grid-content bg-purple-dark select">
    <div style="height: 1px;"></div><ul><li><span class="text">选择省份</span></li></ul></div></el-col>
  <el-col :xs="18" :sm="18" :md="18" :lg="18" :xl="18"><div class="grid-content bg-purple-dark">

    <div class="provice_item infinite-list" style="overflow:auto;">
   
      <div class="infinite-list-item" v-for="(item, i) in proviceData " :key="i"><el-button @click="clickProvice(item.provice)" >{{ item.provice }}</el-button></div>
      
    </div>
       <!-- <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto ;height: 300px;">
    <li v-for="i in count" class="infinite-list-item" :key="i">{{ i }}</li>
  </ul> -->
  </div></el-col>
  <span slot="footer" class="dialog-footer ">


    <el-button type="primary" @click="dialogVisible = false"  class="dialog__footer">确 定</el-button>
  </span>
</el-dialog>


  </div>
</template>
<script>
import { getApi } from "@/utils/model/api";
  export default {
    props:['getClickProvice'],
    data() {
      return {
        dialogVisible: false,
        count:0,
        proviceData:[],
       
      };
    },filters:{
      formatterStr(value){
        return  value.length <= 4? value:value.substring(0,4)+'..';
      }
    },
    methods: {
      handleClose(done) {
       
        done();
      }
  
      ,
      getProvice(){
        getApi("getProvice").then(res=>{
          
          if(res.code==200)
          {
            this.proviceData=res.data;
          }
        })
      },
      clickProvice(value)
      {
        this.dialogVisible=false;
       
       
        this.getClickProvice(value);
        
      }
    },
    mounted(){
      this.getProvice();
    }
  };
</script>

<style lang="scss" scoped>

.el-row{
  background-attachment:fixed;
}
.el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    // background: #99a9bf;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }

  .grid-content {
    border-radius: 4px;
    min-height: 400px;
  }
.provicemain{
    display: inline-block;
    margin-left: 10px;
}

.textPosition{
color: #606266;
}
.textchiyaoPosition{
color: #909399;
}
.select{
  box-sizing: border-box;
}
.select>ul, ul>li{
  margin: 0;
  padding: 0;
  list-style: none;
}
.select>ul{
  margin-top: 10px;;
}
.select>ul>li{
  color: white;
  // width: 90%;
  // margin: auto;
  
  transition: 0.6s;
  height: 50px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);


  display: flex;
    /*水平居中*/
    justify-content: center;
    /*垂直居中*/
    align-items: center;  
}
.select>ul>li:hover{
  text-shadow: 0 0 4px #409EFF;
}

// .select>ul>li::before{
//   content: '';
//   // position:absolute;
//   position: absolute; 
//   width: 100%;
//   height: 100%;
//   top: 0;
//   left: 0;
//   border-radius: 50%;
//   background: #409EFF;
//   transition: 0.6s;
//   transform: scale(0.8);
//   z-index: -1;

// }
.text {
 

  transition: 0.6s;
  filter: invert(1);
}
// .select>ul>li:hover::before{
//   background: #409EFF;
//   transform: scale(1.05);
// }

.dialog__footer{
  margin-top: 20px;
  
}
.provice_item{
  height: 400px;
  display: flex;
  width: 100%;
 
 
  flex-wrap: wrap;
  flex-grow:0;//是否自动增长空间
flex-shrink:0;//是否自动缩小空间


  align-content: space-evenly;

  flex: 1;
  
}
.provice_item>div{
  width: 20%;
  overflow: hidden;
  margin-top: 15px;
   margin-left: 15px;
   margin-right: 10px;
    justify-content: center;
    
  }
  .provice_item>div>button{
    // overflow: hidden;
    width: 90px;
  overflow: hidden;
  }
  
</style>