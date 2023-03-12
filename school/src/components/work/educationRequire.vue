<!--
 * @Author: daidai
 * @Date: 2022-02-28 16:16:42
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2022-10-25 09:18:22
 * @FilePath: \web-pc\src\pages\big-screen\view\indexs\left-center.vue
-->
<template>
  <div class="centermap">
    <div class="maptitle">
      <div class="zuo"></div>
      <span class="titletext">工作要求占比率</span>
      <div class="you"></div>
    </div>
    <div class="mapwrap">
      <dv-border-box-13>
        <Echart
          id="leftCenter"
          :options="options"
          style="height: 600px; "
          ref="charts"
        />
      </dv-border-box-13>
    </div>
  </div>

  <!-- <Reacquire v-else @onclick="getData" style="line-height:200px">
      重新获取
    </Reacquire> -->
</template>
  
  <script>
import { currentGET } from "@/utils/model/api";
export default {
  data() {
    return {
      options: {},
      educationData: {
        TotoSum: 0,
        educationRequire: [],
        legenddata: [],
      },
      pageflag: true,
      timer: null,
    };
  },
  created() {
    this.getData();
  },
  mounted() {},
  beforeDestroy() {
    this.clearData();
  },
  filters: {},
  computed: {
    getEducationRequireInfo() {},
  },
  methods: {
    clearData() {
      if (this.timer) {
        clearInterval(this.timer);
        this.timer = null;
      }
    },
    dealData() {
      let tempData = [];
      for (var i = 0; i < this.educationData.educationRequire.length; i++) {
        this.educationData.legenddata.push(
          this.educationData.educationRequire[i].compeducationRequire
        );
        tempData.push({
          name: this.educationData.educationRequire[i].compeducationRequire,
          value: this.educationData.educationRequire[i].compeducationRequireSum,
        });
      }
      this.educationData.educationRequire = tempData;
    },
    getData() {
      this.pageflag = true;
      // this.pageflag =false

      currentGET("educationRequire").then((res) => {
        //只打印一次
          if (!this.timer) {
            // console.log("工作要求", res);
          }
    
        if (res.code == 200) {
          // this.countUserNumData = res.data

          this.educationData.educationRequire = res.data.educationRequire;
          this.educationData.TotoSum = res.data.TotoSum;
          // for (var i =0;i<educationRequire.length;i++)
          // {
          //     console.log(educationRequire[i])
          // }
          this.dealData();
          // console.log("temp", this.educationData.educationRequire);
          this.$nextTick(() => {
            this.init(this.educationData.educationRequire);
          });
        } else {
          // this.pageflag = false
          // this.$Message({
          //   text: res.msg,
          //   type: 'warning'
          // })
        }
      });
    },
    //轮询
    switper() {
      if (this.timer) {
        return;
      }
      let looper = (a) => {
        this.getData();
      };
      this.timer = setInterval(
        looper,
        this.$store.state.setting.echartsAutoTime
      );
      let myChart = this.$refs.charts.chart;
      myChart.on("mouseover", (params) => {
        this.clearData();
      });
      myChart.on("mouseout", (params) => {
        this.timer = setInterval(
          looper,
          this.$store.state.setting.echartsAutoTime
        );
      });
    },
    init(infodata) {
      let total = this.educationData.TotoSum;
      // console.log("total", total);
      let colors = ["#ECA444", "#33A1DB", "#56B557"];
      let piedata = {
        name: "工作学历或者经验要求",
        type: "pie",
        radius: ["40%", "50%"],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: "rgba(0,0,0,0)",
          borderWidth: 2,
        },

        data: infodata,
      };
      this.options = {
        title: {
          // zlevel: 0,
          text: ["{value|" + total + "}", "{name|总数}"].join("\n"),
          top: "center",
          left: "center",
          textStyle: {
            rich: {
              value: {
                color: "#ffffff",
                fontSize: 24,
                fontWeight: "bold",
                lineHeight: 20,
              },
              name: {
                color: "#ffffff",
                lineHeight: 20,
              },
            },
          },
        },
        tooltip: {
          trigger: "item",
          backgroundColor: "rgba(0,0,0,.6)",
          borderColor: "rgba(147, 235, 248, .8)",
          textStyle: {
            color: "#FFF",
          },
        },
        legend: { type: "scroll", show: true, top: "4%" , left: '2%', // orient: 'vertical'垂直
          textStyle: {
            color: "#FFF",
          }},
        series: [
      


          //展示圆点
          {
            ...piedata,
            labelLayout: {
              hideOverlap: false,//不隐藏显示全部数据
            },
            avoidLabelOverlap:true,//重叠错开
            tooltip: { show: true, },
            label: {
              show: true,
              position: "outer",
              formatter: "   {b|{b}}   \n {c|{c}个}  \n {per|{d}%}  ",
              //       formatter(v) {
              // 	let text =  v.name + ' :  ' + v.value + '个  '+(Math.min(v.value/total, 1) * 100).toFixed(0)+'%'
              // 	if(text.length <= 8){
              // 		return text;
              // 	}else if(text.length > 8 && text.length <= 16){
              // 		return text = `${text.slice(0,8)}${text.slice(8)}`
              // 	}else if(text.length > 16 && text.length <= 24){
              // 		return text = `${text.slice(0,8)}\n${text.slice(8,16)}${text.slice(16)}`
              // 	}else if(text.length > 24 && text.length <= 30){
              // 		return text = `${text.slice(0,8)}\n${text.slice(8,16)}${text.slice(16,24)}${text.slice(24)}`
              // 	}else if(text.length > 30){
              // 		return text = `${text.slice(0,8)}\n${text.slice(8,16)}${text.slice(16,24)}${text.slice(24,30)}${text.slice(30)}`
              // 	}
              // },
              //   position: "outside",
              rich: {
                b: {
                  color: "#fff",
                  fontSize: 12,
                  lineHeight: 26,
                },
                c: {
                  color: "#31ABE3",
                  fontSize: 14,
                },
                per: {
                  color: "#31ABE3",
                  fontSize: 14,
                },
              },
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 50, // 第二段线 长度
              show: true,
            },
            emphasis: {
              show: false,
            },
          },
          {
            ...piedata,
            tooltip: { show: true },
            itemStyle: {},
            label: {
              backgroundColor: "inherit", //圆点颜色，auto：映射的系列色
              height: 0,
              width: 0,
              lineHeight: 0,
              borderRadius: 2.5,
              shadowBlur: 8,
              shadowColor: "auto",
              // padding: [2.5, -2.5, 2.5, -2.5],// 取掉不显示
            },
            labelLine: {
              length: 20, // 第一段线 长度
              length2: 50, // 第二段线 长度
              show: false,
            },
          },
          
        ],
      };
    },
  },
};
</script>
  <style lang='scss' scoped>
 .centermap {
    margin-bottom: 30px;

  
    .maptitle {
      height: 60px;
      display: flex;
      justify-content: center;
      padding-top: 10px;
      box-sizing: border-box;
  
      .titletext {
        font-size: 28px;
        font-weight: 900;
        letter-spacing: 6px;
        background: linear-gradient(
          92deg,
          #0072ff 0%,
          #00eaff 48.8525390625%,
          #01aaff 100%
        );
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        margin: 0 10px;
      }
  
      .zuo,
      .you {
        background-size: 100% 100%;
        width: 29px;
        height: 20px;

      }
  
      .zuo {
        background: url("@/assets/Login/img/xieyou.png") no-repeat;
      }
  
      .you {
        background: url("@/assets/Login/img/xieyou.png") no-repeat;
      }
    }
  
    .mapwrap {
      height: 548px;
      width: 100%;
      // padding: 0 0 10px 0;
      box-sizing: border-box;
      position: relative;
  
      .quanguo {
        position: absolute;
        right: 20px;
        top: -46px;
        width: 80px;
        height: 28px;
        border: 1px solid #00eded;
        border-radius: 10px;
        color: #00f7f6;
        text-align: center;
        line-height: 26px;
        letter-spacing: 6px;
        cursor: pointer;
        box-shadow: 0 2px 4px rgba(0, 237, 237, 0.5),
          0 0 6px rgba(0, 237, 237, 0.4);
      }
    }
  }
  </style>
  