<template>
  <div>
    <!-- <ul class="infinite-list"  v-infinite-scroll="load" style="overflow:auto;height:600px">
         <template  v-if="item.protraitPhoto">
              <el-avatar :src="item.protraitPhoto"></el-avatar>
            </template>
    </ul> -->
    <ul
      class="right_center infinite-list"
      v-infinite-scroll="load"
      infinite-scroll-distance="20px"
      style="overflow: auto; height: 509px"
    >
      
      <li
        class="right_center_item infinite-list-item"
        v-for="item in ContentDatas"
        :key="item.contentID"
      >
        <span class="orderNum">
          <div>
            <router-link to="/edu">
            <template v-if="item.protraitPhoto">
              <el-avatar :src="item.protraitPhoto"></el-avatar>
            </template>
            <el-avatar
              v-else
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar>
            </router-link>
            </div></span>
        <div class="inner_right">
          <router-link to="/edu">
            <div class="dibu"></div>
            <div class="flex">
              <div class="info">
                <span class="labels"></span>
                <span class="contents zhuyao"> {{ item.userName }}</span>
                <span class="labels"> &nbsp; &nbsp; &nbsp; &nbsp; </span>
                <span class="labels">点赞人数：</span>
                <span class="contents"> {{ item.praiseNum }}个</span>
              </div>
              <div class="info">
                <span class="labels">岗位：</span>
                <span class="contents warning"> {{ item.tag | tagStr }}</span>
              </div>

              <div class="info">
                <span class="labels">评论：</span>
                <span class="contents warning"> {{ item.content }}</span>
              </div>
            </div>

            <div class="flex">
              <div class="info">
                <span class="labels"> 工资：</span>
                <span class="contents ciyao" style="font-size: 12px">
                  {{ item.wage }}</span
                >
              </div>
              <div class="info time">
                <span class="labels">公司：</span>
                <span class="contents warning" style="font-size: 12px">
                  {{ item.companyName }}</span
                >
              </div>
            </div>
            <div class="flex">
              <span class="labels">职位描述:</span>
              <div class="info">
                
                <span class="contents ciyao"> {{ item.textContent |subStr}}</span>
              </div>
            </div>
          </router-link>
        </div>
      </li>
    </ul>
  </div>
</template>
  
  <script>

import { currentGET } from "@/utils/model/api";
export default {
  data() {
    return {
      count: 0,
      ContentDatas: [],
      
    };
  },
  methods: {
    unique(arr) { // 根据唯一标识id来对数组进行过滤
  const res = new Map();//定义常量 res,值为一个Map对象实例
  //返回arr数组过滤后的结果，结果为一个数组   过滤条件是，如果res中没有某个键，就设置这个键的值为1
  return arr.filter((arr) => !res.has(arr.contentID) && res.set(arr.contentID, 1))
},
    load() {
      if (this.count==0)
      {
        this.count+=1;
        return;
      }
      currentGET("newWorkContent").then((res) => {

      if (res.code == 200) {


        let data=res.data;
        // this.ContentDatas =Array.from(new Set( this.ContentDatas.concat(data)));
        this.ContentDatas=this.unique( this.ContentDatas.concat(data));
        // console.log("posititonContent",     this.ContentDatas);

      }
    });
    },
  },
  filters: {
    subStr(value) {
      return value.length <= 55? value : value.substring(0, 55) + ".....";
    },
    tagStr(value) {
      return value.length <= 23 ? value : value.substring(0, 23) + ".....";
    },
  },
  mounted() {
    currentGET("posititonContent").then((res) => {
      // console.log("posititonContent", res);
      if (res.code == 200) {
        this.ContentDatas = res.data;
      }
    });
  },
};
</script>
<style lang="scss" scoped>

::-webkit-scrollbar {
    width: 0 !important;
  }
  ::-webkit-scrollbar {
    width: 0 !important;
    height: 0;
  }
  a {
    color: #fff;
  }
  .right_center {
    width: 100%;
    height: 100%;
  
    .right_center_item {
      display: flex;
      align-items: center;
      justify-content: center;
      height: auto;
      padding: 10px;
      font-size: 14px;
      color: #fff;
  
      .orderNum {
        margin: 0 20px 0 -20px;
      }
  
      .inner_right {
        position: relative;
        height: 100%;
        width: 400px;
        flex-shrink: 0;
        line-height: 1.5;
        color: #fff;
        .dibu {
          position: absolute;
          height: 2px;
          width: 104%;
          background: #d3dce6;
          background-size: cover;
          background-position: center center;
          bottom: -12px;
          left: -2%;
          background-size: cover;
        }
      }
  
      .info {
        margin-right: 10px;
        display: flex;
     
        align-items: center;
  
        .labels {
          flex-shrink: 0;
          font-size: 12px;
          color: rgb(255, 255, 255);
        }
  
        .zhuyao {
          font-size: 15px;
        }
  
        .ciyao {
          color: rgba(255, 255, 255, 0.8);
        }
  
        .warning {
          color: #e6a23c;
          font-size: 15px;
        }
      }
    }
  }
  
  .right_center_wrap {
    overflow: hidden;
    width: 100%;
    height: 500px;
  }
  
  .overflow-y-auto {
    overflow-y: auto;
  }
</style>
<!-- <style  src="@/assets/css/work/workPosition.scss" lang="scss" scoped></style> -->