<template>
    <div class="center_bottom">
      <Echart
        :options="options"
        id="bottomLeftChart"
        class="echarts_bottom"
        style="height: 550px;  "
      ></Echart>
    </div>
  </template>
  
  <script>
import { currentGET } from "@/utils/model/api";
  import { graphic } from "echarts";
  export default {
    data() {
      return {
        options: {},
      };
    },
    props: {},
    mounted() {
      this.getData();
    },
    methods: {
        dealData(data){
            let positionBigClassid={};
            let positionBigClass=data.positionBigClass;
         
            for(var i =0 ; i < positionBigClass.length;i++)
            {
             
                positionBigClassid[positionBigClass[i]['classificationID']]={'title':positionBigClass[i]['title'],'count':positionBigClass[i]['count']};
            }
            
           let positionData={};
           let barData=[];
           let category=[];
           let rateData=[];
           let lineData=[];

           let positionNumRanks=data.positionNumRanks;
           for(var i =0 ; i < positionNumRanks.length;i++)
            {
                var temp=positionNumRanks[i];
                var tempId=temp['classificationID'];
                if (positionBigClassid.hasOwnProperty(tempId) )
                {   
                    barData.push(temp['middleIDCount']);
                    category.push(temp['title']);
                    var rate = temp['middleIDCount']/positionBigClassid[tempId]['count']*1000;
                    rate=Math.round(rate)/10
                    rateData.push(rate);
                    lineData.push(positionBigClassid[tempId]['count']);

                }
               
            }
 

            positionData['barData']=barData;
            positionData['category']=category;
            positionData['rateData']=rateData;
            positionData['lineData']=lineData;

        
            return positionData;
        },
      getData() {
        this.pageflag = true;
        currentGET("getPosiyionRate", { companyName: this.companyName }).then((res) => {

          if (res.code==200) {
          
            let data=this.dealData(res.data)
            console.log("安装计划", data);
            this.init(data);
          } 
        });
      },
      init(newData) {
      this.options = {
        tooltip: {
          trigger: "axis",
          backgroundColor: "rgba(0,0,0,.6)",
          borderColor: "rgba(147, 235, 248, .8)",
          textStyle: {
            color: "#FFF",
          },
          formatter: function (params) {
            // 添加单位
            var result = params[0].name + "<br>";
            console.log('params',params)
            params.forEach(function (item) {
              if (item.value) {
                if (item.seriesName == "占比率") {
                  result +=
                    item.marker +
                    " " +
                    item.seriesName +
                    " : " +
                    item.value +
                    "%</br>";
                } else {
                  result +=
                    item.marker +
                    " " +
                    item.seriesName +
                    " : " +
                    item.value +
                    "种</br>";
                }
              } else {
                result += item.marker + " " + item.seriesName + " :  - </br>";
              }
            });
            return result;
          },
        },
        legend: {
          data: ["细分行业", "归类行业", "占比率"],
          textStyle: {
            color: "#B4B4B4",
          },
          top: "0",
        },
        grid: {
          left: "50px",
          right: "40px",
          bottom: "30px",
          top: "20px",
        },
        xAxis: {
          data: newData.category,
          axisLine: {
            lineStyle: {
              color: "#B4B4B4",
            },
          },
          axisTick: {
            show: false,
          },
        },
        yAxis: [
          {
            splitLine: { show: false },
            axisLine: {
              lineStyle: {
                color: "#B4B4B4",
              },
            },

            axisLabel: {
              formatter: "{value}",
            },
          },
          {
            splitLine: { show: false },
            axisLine: {
              lineStyle: {
                color: "#B4B4B4",
              },
            },
            axisLabel: {
              formatter: "{value}% ",
            },
          },
          
        ],
        series: [
          {
            name: "细分行业",
            type: "bar",
            barWidth: 10,
            itemStyle: {
              borderRadius: 5,
              color: new graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: "#956FD4" },
                { offset: 1, color: "#3EACE5" },
              ]),
            },
            data: newData.barData,
          },
          {
            name: "归类行业",
            type: "bar",
            barGap: "-100%",
            barWidth: 10,
            itemStyle: {
              borderRadius: 5,
              color: new graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: "rgba(156,107,211,0.8)" },
                { offset: 0.2, color: "rgba(156,107,211,0.5)" },
                { offset: 1, color: "rgba(156,107,211,0.2)" },
              ]),
            },
            z: -12,
            data: newData.lineData,
          },
          {
            name: "占比率",
            type: "line",
            smooth: true,
            showAllSymbol: true,
            symbol: "emptyCircle",
            symbolSize: 8,
            yAxisIndex: 1,
            itemStyle: {
              color: "#F02FC2",
            },
            data: newData.rateData,
    },
    // {
    //         name: "占1率",
    //         type: "line",
    //         legend: {
    //     show:false,
     
    // },
    //         smooth: true,
    //         showAllSymbol: true,
    //         symbol: "emptyCircle",
    //         symbolSize: 8,
    //         yAxisIndex: 1,
    //         itemStyle: {
    //           color: "#F02FC2",
    //         },
    //         data: newData.rateData,
    //       },
        ],
      };
    },
  },
};
</script>
<style lang="scss" scoped>
.center_bottom {
  width: 100%;
  height: 100%;

  .echarts_bottom {
    width: 100%;
    height: 100%;
  }
}
</style>
