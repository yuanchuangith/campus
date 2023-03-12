<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5"
        >
        <div class="grid-content">
          <!-- <div style="    min-height: 188px;"></div>
        <div class="divleftFixed"></div> -->
         </div>
        </el-col>
      <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14">
        <div class="divheight">
          <div style="">
            <div
          
              style="min-height: 75px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)"
            >
            <Search :getSearchKey="getSearchKey" titleOne="根据工作名称" titleTow="根据公司名称" :model="1"/>
          </div>

            <div class="selectInfo div_min_height">
              <div class="provice_div">
                <span
                  ><el-button type="text" @click="dialogVisible = true">
                    工作地点：</el-button
                  ></span
                ><span class="mspan"><el-button plain>全国</el-button></span
                ><span class="mspan"></span
                ><provivceDia :getClickProvice="getClickProvice"></provivceDia>
                <el-button
                  type="text"
                  @click="dialogVisible = true"
                  v-show="this.requestData.provice"
                  style="margin-left: 40px"
                >
                  选择省份：{{ this.requestData.provice }}</el-button
                >

                <span class="remove">
                  <el-button class="el-icon-error" plain
                  @click="removeProvice"
                    >&nbsp;&nbsp;清除</el-button
                  ></span
                >
              </div>
              <div class="provice_div">
                <span
                  ><el-button type="text" @click="dialogVisible = true">
                    所属行业  ：</el-button
                  ></span
                ><span class="mspan"><el-button plain>不限</el-button></span
                ><span class="mspan"></span>
                <industryDialog
                  :getClickIndustry="getClickIndustry"
                  dialogName="选择行业"
                ></industryDialog>
                <el-button type="text" @click="dialogVisible = true" v-show="this.title" style="margin-left:40px"> 选择行业：{{ this.title }}</el-button>

                <span class="remove">
                  <el-button class="el-icon-error" plain @click="removeIndustry"
                    >&nbsp;&nbsp;清除</el-button
                  ></span
                >
              </div>
            </div>
          </div>

          <div
            class="position_div provice_item infinite-list"
            style="overflow: auto"
          >
            <div
              class="position_item infinite-list-item" v-for="item in postitonData" :key="item.jobID"   >
              <div class="checkPosition">
                <span><el-checkbox></el-checkbox></span>
              </div>

              <div class="position">
                <div class="position_top">
                  <router-link active-class="active" :to="`/work/position/`+item.jobID" target="_blank">  <div class="position_tag tag_conpanyName_color">{{ item.tag }}</div></router-link>
                  <div class="position_date dataColor">{{ item.publishData|timeFormater }}</div>
                  <div class="position_conpany tag_conpanyName_color"><a :href=item.addressUrl target="_blank">{{ item.companyName }}</a></div>
                </div>
                <div class="position_center">
                  <div class="position_top">
                    <div class="position_tag wage_color">{{ item.wage }}</div>
                    <div class="position_date description_colo">
                      <!-- <el-tag type="warning" v-for="(text,index) in descriptionSplict(item.description)" :key="index"  class="wekare_eltag">{{ text }}</el-tag> -->
                      <span class="textColorWar">{{ item.description }}</span>
                    </div>
                    <div class="position_conpany textPosition">
                                 <span v-if="item.provice"> <span >{{ item.provice }}</span><span>|</span></span>
                      <span  >{{ item.companyCharacter }}</span
                      ><span  >|</span><span>{{ item.companySize }}</span>
                    </div>
                  </div>
                </div>
                <div class="position_buttom">
                  <div class="position_top">
                    <div class="position_tag"  >
                      <!-- <span v-for="(text,index) in descriptionSplict(item.wekare)" :key="index" class="wekare_text">{{ text }}</span> -->
                    
                      <el-tag type="success" v-for="(text,index) in descriptionSplict(item.wekare)" :key="index"  class="wekare_eltag">{{ text }}</el-tag></div>

                    <div class="position_conpany textchiyaoPosition">
                      {{ item.title | replcaeTitle }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div></el-col
      >

      <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
        <div class="zhanweiIDnv"></div>
        <div class="divfour ">
        <div v-show="isLogin"><userView :userInfo="userInfo" /></div>
        <router-link active-class="active" to="/login">     <el-empty :image-size="200"  v-show="!isLogin"> <el-button type="primary">去登录</el-button></el-empty></router-link>
          
          </div
      ></el-col>
      <el-col
        :xs="14"
        :sm="14"
        :md="14"
        :lg="14"
        :xl="14"
        class="buttompafintion"
        ><div>
          <pagination
            :postGetData="postGetData"
            :total="total"
          ></pagination></div
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import Pagination from "@/components/workPrimary/pagination .vue";
import provivceDia from "@/components/workPrimary/proviceDialog.vue";
import industryDialog from "@/components/workPrimary/industryDialog.vue";
import userView from '@/components/workPrimary/userView.vue';
import Search from "@/components/workPrimary/search.vue";
import { getApi, postApi } from "@/utils/model/api";
import {getUserInfoByt } from "@/utils/model/getInfo";
import dayjs from 'dayjs'
export default {
  name: "workIndex",
  components: { Pagination, provivceDia, industryDialog ,Search,userView},
  data() {
    return {
      postitonData: [],
      checked: true,
      total: 0,
      title:'',
      requestData: {
        provice: null,
        industry: null,
        endPage: 40,
        startPage: 0,
        keyWord:'',
        dibIsChange:true
      },
      isLogin:false,
      userInfo:{},
    };
  },
  filters: {
    replcaeTitle(value) {
      return value.replace(/\,/g, "/");
    },
    timeFormater(value)
    {
      return dayjs(value).format('YYYY年MM月DD日 ')
    },
  },
  methods: {
    descriptionSplict(value){
   
        let strs=value.split(/\|/)
        if(strs[strs.length-1]==='')
        {
          strs.pop();
        }
     
      
        return strs;
    },
    getClickProvice(provice) {
      this.startPage=0;
      this.requestData.provice = provice;
      this.getPostionData();
    },
    getClickIndustry(industry,title) {
      this.startPage=0;
      this.requestData.industry = industry;
      this.title=title;
      this.getPostionData();
    },
    removeProvice(){
      this.startPage=0;
      this.requestData.provice = null;
      this.getPostionData();
    },
    removeIndustry(){
      this.startPage=0;
      this.requestData.industry = null;
      this.title='';
     this.getPostionData();
    },
    getSearchKey(item)
    {
      if(item.keyWord)
      {
        this.requestData.keyWord=item.keyWord;
        this.requestData.dibIsChange=item.dibIsChange;
        this.byKeyGetPostionData();
      }

    },
    
    byKeyGetPostionData() {
      
      postApi("getIndexSearchPosition", this.requestData).then((res) => {
        if (res.code == 200) {

          this.postitonData = res.data.positionSimplies;
          this.total = res.data.positionTotal;
        }
      });
    },
    postGetData(num) {
   
      this.requestData.startPage = num * 40 - 40;

      this.getPostionData();
    },
    getPostionData() {
      
      postApi("getPosition", this.requestData).then((res) => {
        if (res.code == 200) {
     
          this.postitonData = res.data.positionSimplies;
          this.total = res.data.positionTotal;
        }
      });
    },
    getUser()
    {
     var getUserinfo=getUserInfoByt();
     if(getUserinfo)
     {
      getUserinfo.then(res=>{
         this.userInfo=res.data;
         this.isLogin=true;
        })
     }
     
      



    },

  },
  mounted() {
    this.getPostionData();
    this.getUser();
  },
};
</script>
<style  src="@/assets/css/work/workindex.css" scoped></style>
<style  src="@/assets/css/userLoginInfo.scss" lang="scss" scoped></style>
<style lang="scss" scoped>
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
*{
  font-family: microsoft yahei!important;

}
</style>

  