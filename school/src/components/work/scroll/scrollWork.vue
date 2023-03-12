
<template>
  <div v-if="pageflag" class="right_center_wrap beautify-scroll-def" :class="{ 'overflow-y-auto': !sbtxSwiperFlag }">
    <component :is="components" :data="list">
      <ul class="right_center ">
        <li class="right_center_item" v-for="(item, i) in list" :key="i">
          <span class="orderNum">{{ i + 1 }}</span>
        <div class="inner_right"> 
          <router-link  to="/edu" >
            <div class="dibu"></div>
            <div class="flex">
              <div class="info">
                <span class="labels ">工作名称：</span>
                <span class="contents zhuyao"> {{ item.tag }}</span>
                
              </div>
              <div class="info">
                <span class="labels">公司类型：</span>
                <span class="contents "> {{ item.companyCharacter }}</span>
                <span class="labels">  &nbsp;   &nbsp; &nbsp;   &nbsp;   </span>
                <span class="labels">感兴趣人数：</span>
                <span class="contents "> {{ item.interestedNum }}个</span>
              </div>
        
              <div class="info">
                <span class="labels">公司名字：</span>
                <span class="contents warning"> {{ item.companyName|tagStr  }}</span>
              </div>
            </div>


            <div class="flex">

              <div class="info">
                <span class="labels"> 工作要求：</span>
                <span class="contents ciyao" style="font-size:12px"> {{ item.description }}</span>
              </div>
              <div class="info time">
                <span class="labels">发布时间：</span>
                <span class="contents" style="font-size:12px"> {{ item.publishData |timeFormater}}</span>
              </div>

            </div>
            <div class="flex">

              <div class="info">
                <span class="labels">职位描述:</span>
                <span class="contents ciyao" > {{   item.textContent | subStr}}</span>
              </div>
            </div>
          </router-link>
          </div>
        </li>
      </ul>
    </component>
  </div>


</template>
  
  <script>
import { currentGET } from "@/utils/model/api";
  import vueSeamlessScroll from 'vue-seamless-scroll'  // vue2引入方式
  import Kong from './kong.vue'
  import dayjs from 'dayjs'
  export default {
    components: { vueSeamlessScroll, Kong },
  
    data() {
      return {
        list: [],
        pageflag: true,
        defaultOption: {
          direction: 1,hoverStop: true ,limitMoveNum: 4,fopenWatch: true,singleHeight: 0,singleWidth: 0,step: 4.4,waitTime
  : 
  3000,
          limitMoveNum: 3, 
          singleHeight: 250, 
          step:0,
        }
  
      };
    },
    computed: {
      sbtxSwiperFlag() {
        let ssyjSwiper =true;//控制是否滚动
        if (ssyjSwiper) {
          this.components = vueSeamlessScroll
        } else {
          this.components = Kong
        }
        return ssyjSwiper
      }
    }, 
    
   
    created() {
      this.getData()
    },
  
    mounted() { },
    methods: {
      getData() {
        this.pageflag = true
       
        currentGET('scrollPosition', { limitNum: 50 }).then(res => {
          // console.log('实时预警', res);
         
          if (res.code==200) {
            this.list = res.data
            let timer = setTimeout(() => {
                clearTimeout(timer)
          
                // this.defaultOption.step=4.4
            }, this.defaultOption.waitTime);
          } else {
            this.pageflag = false
   
          }
        })
      },
  
    },
    filters:{
    subStr(value)
    {

      return  value.length <= 40? value:value.substring(0,20)+'.....';
    },
    timeFormater(value)
    {
      return dayjs(value).format('YYYY年MM月DD日 ')
    },
    tagStr(value)
    {

      return  value.length <= 23? value:value.substring(0,23)+'.....';
    }
  },
  };
  </script>
  <style lang='scss' scoped>
  a{
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
          color: #E6A23C;
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