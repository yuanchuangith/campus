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
            <Search :getSearchKey="getSearchKey" titleOne="根据标题" :model="3" :removeKey="removekey" num="回复数"  title="点赞数：" :ID="requestData.contentId" />
          </div>
    
       
          </div>
    
        
          <div class="obedient position_div provice_item ">

<div class="obedient_discuss infinite-list" v-infinite-scroll="loadContent"  style="overflow: auto">
        <div class="discuss_content infinite-list-item" v-for="(item) in obedient" :key="item.obedientID">
          <div class="discuss_content_item infinite-list-item">
            <div class="protrait_photo">
              <!-- <span v-if="item=-1"><img :src="item.protraitPhoto" alt=""></span> -->
              <span v-if="item.protraitPhoto"><img :src="require('@/assets/img/userAvatar/'+item.protraitPhoto)" alt="" class="protraitPhoto"></span>
              <span v-else><el-avatar
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></span>
            </div>

            <div class="content">
              <div class="content_top">
                <div class="content_user "><span>{{ item.userName }}</span></div>
         <div class="content_data ">          <router-link active-class="active" :to="`/obedientDetail/`+item.obedientID" target="_blank"> <span>标题：{{ item.title |tagStr }}</span></router-link></div>
               
                <div class="content_user tag_count">
                  <span class=""><el-badge :value="item.praiseNum" class="item " type="primary">
                      <el-button size="small" >点赞</el-button>
                    </el-badge></span>
                  <span class=""><el-badge :value="item.replyCount" class="item " type="primary">
                      <el-button size="small" >回复数</el-button>
                    </el-badge></span>
                </div>

              </div>
              <div class="Occupy"></div>
              <div class="position_center">
                <div class="content_top">
                   <div class="content_user  "><span>{{ item.title }}</span></div>
                  <div class="content_data "><span>{{ item.createTime | timeFormater }}</span></div>
                  <!-- <div class="content_data "><span>{{ item.userName }}</span></div> -->

                  <!-- <div class="content_user tag_conpanyName_color">1</div> -->
                </div>
              </div>
              <div class="Occupy"></div>
              <div class="content_images">

                <!-- 图片加载后期修改 -->
                <div class="images">
                  <div v-if="item.images=='' || item.images==null" class="images">

                  </div>
                  <div v-else class="images">
                    <div class="block images_item" v-for=" (imagesrc, i) in  dealImagesStr(item.images)" :key="i">
                     
                      <el-image :src="require('@/assets/img/obedient/'+item.obedientID+'/'+imagesrc)" lazy :preview-src-list="[require('@/assets/img/obedient/'+item.obedientID+'/'+imagesrc)]">
                        <div slot="placeholder" class="image-slot">
                          加载中<span class="dot">...</span>
                        </div>
                      </el-image>
                    </div>
                  </div>



                </div>

              </div>
              <!-- ()=>loadReplyContent(item.content) -->
         
          
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
 
    </el-row>
    </div>
    </template>
      
    <script>
 
    import userView from '@/components/workPrimary/userView.vue';
    import Search from "@/components/workPrimary/searchConpany.vue";
    import { obedientPostApi } from "@/utils/model/api";
    import {getUserInfoByt } from "@/utils/model/getInfo";
    import dayjs from 'dayjs'
    import {message} from '@/utils/model/usullyUtils'
    export default {
    name: "workIndex",
    components: { Search,userView},
    data() {
    return {
        obedient: [],
     
      checked: true,
      
      title:'',
      requestData: {
        contentId:  "",
        endPage: 15,
        startPage: 0,
        keyWord:'',
    
      },
      search:false,
      isLogin:false,
      userInfo:{},
      init:0
    };
    },
    filters: {
        tagStr(value)
    {

      return  value.length <= 23? value:value.substring(0,23)+'.....';
    },
      timeFormater(value) {
      return dayjs(value).format('MM月DD日 ')
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

       this. getPostionData()
    },
    getSearchKey(item)
    {
       
      if(item.keyWord)
      {
        this.requestData.startPage=0;
        this.requestData.keyWord=item.keyWord;
        this. getPostionData();
      this.obedient
        // console.log(item)
      }
     
    },
    
    loadContent(){
 
        if(this.init==0)
            return;
          
        this.requestData.startPage+=15;

        obedientPostApi("getplateObedient", this.requestData).then((res) => {
            try {
         if (res.code == 200) {
          message(res.code, res.message)
    
        res.data.forEach(element => {
            this.obedient.push(element);
        });
        
         }
         else
          message(res.code, res.message)
       } catch (error) {
         message(null, "失败")

       }
      });

},
dealImagesStr(imageStr){
      if (imageStr==null||imageStr=="")
      return null
    return  imageStr.split("|")

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
      
        obedientPostApi("getplateObedient", this.requestData).then((res) => {
            try {
         if (res.code == 200) {
          message(res.code, res.message)
         this.obedient=res.data;
         this.init=1;

        
         }
         else
          message(res.code, res.message)
       } catch (error) {
         message(null, "失败")

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
        this.requestData.contentId = this.$route.params.classificationID;

    this.getUser();
    this.getPostionData();
    },
    };
    </script>
    <style  src="@/assets/css/discuss/obident.scss"  lang="scss" scoped></style>
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
      
    