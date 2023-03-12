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
        <Search :getSearchKey="getSearchKey" titleOne="根据公司名称" :model="2" :removeKey="removekey" num="工作数"  title="省份" :ID="null"  />
      </div>

   
      </div>

      <div
        class="position_div provice_item infinite-list"
        style="overflow: auto"
      >
        <div
          class="position_item infinite-list-item" v-for="item in conpanyData" :key="item.conpanyID"   >
          <div class="checkPosition">
            <span><el-checkbox></el-checkbox></span>
          </div>

          <div class="position">
            <router-link active-class="active" :to="{
              name:'conpanyDetail',
              params:{
                conpanyid:item.conpanyID,
                rate:item.rate
              }
            }" target="_blank">
              <div class="position_top">
                <div class="position_tag tag_conpanyName_color">{{ item.conpanySize }}</div>
                <div class="position_date dataColor">时间：{{ item.createTime|timeFormater }}</div>
                <div class="position_conpany tag_conpanyName_color"><a :href=item.addressUrl target="_blank">{{ item.conpanyName
                    }}</a></div>
              </div>
            </router-link>
            <div class="position_center">
              <div class="position_top">
                <div class="position_tag wage_color">{{ item.conpanyCharacter }}</div>
                <div class="position_date description_colo">
                  <!-- <el-tag type="warning" v-for="(text,index) in descriptionSplict(item.description)" :key="index"  class="wekare_eltag">{{ text }}</el-tag> -->
                  <span class="textColorWar">岗位数量：{{ item.jobNum }}</span>
                </div>
                <div class="position_conpany textPosition">
                             <span v-if="item.provice"> <span >{{ item.provice }}</span></span>
                 
                </div>
              </div>
            </div>
            <div class="position_buttom">
                <div class="position_top">
                    <div class="position_tag">
                        <!-- <span v-for="(text,index) in descriptionSplict(item.wekare)" :key="index" class="wekare_text">{{ text }}</span> -->
                        <el-rate v-model="item.rate" disabled show-score text-color="#ff9900" score-template="企业评分">
                        </el-rate>
                    </div>

                <div class="position_conpany textchiyaoPosition">
                  {{ item.conpanyIndustry |replcaeTitle}}
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
import Search from "@/components/workPrimary/searchConpany.vue";
import { getApi, postApi } from "@/utils/model/api";
import {getUserInfoByt } from "@/utils/model/getInfo";
import dayjs from 'dayjs'
export default {
name: "workIndex",
components: { Pagination, provivceDia, industryDialog ,Search,userView},
data() {
return {
  conpanyData: [],
  checked: true,
  total: 0,
  title:'',
  requestData: {
   
    endPage: 40,
    startPage: 0,
    keyWord:'',
    model:1,
  },
  search:false,
  isLogin:false,
  userInfo:{},
};
},
filters: {
replcaeTitle(value) {
  value=value.replace(/\,/g, "/");

  return value
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


removekey(){
  this.search=false;
    this.requestData.startPage=0;
    this.requestData.model=1;
   this. getPostionData()
},
getSearchKey(item)
{
  if(item.keyWord)
  {
    this.requestData.startPage=0;
    this.requestData.keyWord=item.keyWord;
 
    this.byKeyGetPostionData();
    // console.log(item)
  }
  else
  {
    this.search=false;
    this.requestData.startPage=0;
    this.requestData.model=1;
   this. getPostionData()
  }

},

byKeyGetPostionData() {
  
    postApi("getSearchByconpanyName", this.requestData).then((res) => {
    if (res.code == 200) {
   
      this.conpanyData = res.data.conpany;
        if (res.data.tatol) {
 
            this.total = res.data.tatol;
        }
    }
  });
},
postGetData(num) {
 
  this.requestData.startPage = num * 40 - 40;
  this.requestData.model=2;

 if(this.search)
 {

 }
 else
 {
    this.getPostionData();
 }

},
getPostionData() {
  
  postApi("getConapnyAndrate", this.requestData).then((res) => {
    if (res.code == 200) {
 
      this.conpanyData = res.data.conpany;
        if (res.data.tatol) {
 
            this.total = res.data.tatol;
        }
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
  <style  src="@/assets/css/userLoginInfo.scss" lang="scss" scoped></style>
  
