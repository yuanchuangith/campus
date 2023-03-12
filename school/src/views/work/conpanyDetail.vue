<template>
  <div class="divheight">
    <el-row :gutter="10">
      <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
        <div class="grid-content  divheight"></div>
      </el-col>
      <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">
        <div class="grid-content  divheight">
          <div class="top_reserve"></div>
          <div class="center_top_header">
            <div class="center_top_header_info">
              <div class="info_top">
                <div class="job_tag">
                  <h1>{{ conpanyInfo.conpanyName }}</h1>
                </div>
                <div class="job_wage"><strong>{{ conpanyInfo.conpanyCharacter }}</strong></div>
              </div>
              <p class="info_center"> <span class="textColor"> {{ conpanyInfo.conpanySize }} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{ conpanyInfo.createTime |timeFormater}}</span></p>
              
              <div class="info_buttom">
                <div > <el-tag type="success"
                    
                    class="wekare_eltag">{{ conpanyInfo.conpanyIndustry }}</el-tag></div>
            </div>
            <div class="info_buttom textColor">
                <!-- <p><span v-if="primaryPositionInfo.kind">{{
                        primaryPositionInfo.kind
            
                        }}</span><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>地区：{{
                        conpanyInfo.provice
                        }}</span></p> -->
            </div>
            </div>
            
            <div class="center_top_header_apply" v-if="rate>0"><el-rate v-model="rate" disabled show-score text-color="#ff9900"
                    score-template="企业评分">
                </el-rate>
 <div style="margin-top:10px;"> <el-badge  class="item">
                    <el-button size="small" @click="reply" >评价</el-button>
                  </el-badge></div>

              </div>
              
            </div>
            <div class="center_contents div_flex">
                <div style="width:100%;">
                    <div class="jobDetailInfo">
                    <div>
                        <h2><span class="bname">在招职位</span></h2>
                    </div>
                    <div class="job_content">
                        <div >
                            <div class="position_div provice_item infinite-list" v-infinite-scroll="loadJob"  style="overflow: auto">
                                <div class="position_item infinite-list-item" v-for="item in positions" :key="item.jobID">
                                    <div class="checkPosition">
                                        <span><el-checkbox></el-checkbox></span>
                                    </div>
        
                                    <div class="position">
                                        <div class="position_top">
                                            <router-link active-class="active" :to="`/work/position/`+item.jobID"
                                                target="_blank">
                                                <div class="position_tag tag_conpanyName_color">{{ item.tag }}</div>
                                            </router-link>
                                            <div class="position_date dataColor">{{ item.publishData|timeFormater }}</div>
                                            <div class="position_conpany tag_conpanyName_color"><a :href=item.url
                                                    target="_blank">{{ conpanyInfo.conpanyName }}</a></div>
                                        </div>
                                        <div class="position_center">
                                            <div class="position_top">
                                                <div class="position_tag wage_color">{{ item.wage }}</div>
                                                <div class="position_date description_colo">
                                                    <!-- <el-tag type="warning" v-for="(text,index) in descriptionSplict(item.description)" :key="index"  class="wekare_eltag">{{ text }}</el-tag> -->
                                                    <span class="textColorWar">{{ item.description }}</span>
                                                </div>
                                                <div class="position_conpany textPosition">
                                                    <span v-if="item.provice"> <span>{{ conpanyInfo.provice
                                                            }}</span><span>|</span></span>
                                                    <span>{{
                                                        item.kind }}</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="position_buttom">
                                            <div class="position_top">
                                                <div class="position_tag">
                                                    <!-- <span v-for="(text,index) in descriptionSplict(item.wekare)" :key="index" class="wekare_text">{{ text }}</span> -->
        
                                                    <el-tag type="success"
                                                        v-for="(text,index) in descriptionSplict(item.wekare)" :key="index"
                                                        class="wekare_eltag">{{ text }}</el-tag>
                                                </div>
        
                                                <div class="position_conpany textchiyaoPosition">
                                                    {{ item.title }}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- <div class="positionDescriptions position_text">
                              <p v-for="(text, index) in positionDescriptions" :key="index">{{ text }}</p>
                            </div>
                            <div v-if="positionRequire.length>0" class="positionRequire position_text">
                              <div>任职资格：</div>
                             <div > <p v-for="(text, index) in positionRequire" :key="index">{{ text }}</p></div>
                         
                            </div> -->
                        </div>
                    </div>
        
                </div>
            </div>
          </div>
          <div class="center_address div_flex">
            <div class="letf_width">
              <h2><span>工作地址</span></h2>
              <div class="job_address"><span></span><span class="addressMap"><a
                    target="_blank" v-if="positions.length>0" :href="positions[0].addressUrl">查看地图</a></span></div>
            </div>
            <div class=" conpany_praise">
              <div class="conpany_num">

                <div class="title text_border">感兴趣人数和点赞</div>
                <div class="info_buttom ">


                  <div>
                    <span class=""><el-badge :value="conpanyInfo.interestedNum" class="item " type="primary">
                        <el-button size="small">感兴趣</el-button>
                      </el-badge></span>
                    <span class="tag_left"><el-badge :value="conpanyInfo.praiseNum" class="item" type="warning">
                        <el-button size="small" >点赞</el-button>
                      </el-badge></span>

                  </div>
                </div>

              </div>
            </div>
          </div>
          <div class="rewriter_center_buttom div_flex">
            <div class="letf_width">
              <div class="top">
                <div>
                  <el-badge :value="conpantContentCount" class="item">
                    <el-button size="small" @click="reply" >评价</el-button>
                  </el-badge>
                </div>
                <div>
                  <div class="top_text">
                    <h3>评价区</h3>
                  </div>
                </div>
              </div>
              <div class="job_discuss infinite-list"  v-infinite-scroll="loadConpanycontents"  style="overflow: auto;height:800px;">
                <div class="discuss_content infinite-list-item" v-for="(item,index) in conpanycontents" :key="item.rateID">
                  <div class="discuss_content_item infinite-list-item">
                    <div class="protrait_photo">
                      <span v-if="item.protraitPhoto"><img :src="item.protraitPhoto" alt=""></span>
                      <span v-else><el-avatar
                          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></span>
                    </div>

                    <div class="content">
                      <div class="content_top">
                        <div class="content_user "><span>{{ item.userName }}</span></div>
                        <div class="content_data "><span>{{ item.createTime | timeFormater }}</span></div>
                        <div class="content_user tag_count">
                          <span class=""><el-badge :value="item.praiseNum" class="item " type="primary">
                              <el-button size="small" @click="praise(item,index)">点赞</el-button>
                            </el-badge></span>
                         
                        </div>

                      </div>
                      <div class="Occupy"></div>
                      <div class="position_center">
                        <div class="content_top">
                          <div class="content_user  "><span>{{ item.content }}</span></div>
                          <!-- <div class="content_data "><span>{{ item.userName }}</span></div> -->

                          <!-- <div class="content_user tag_conpanyName_color">1</div> -->
                        </div>
                      </div>
                      <div class="Occupy"></div>
                      <div class="content_images">

                      
                       
                       
                          
                            <el-rate v-model="item.rate" disabled show-score text-color="#ff9900" score-template="评分">
                        </el-rate>
                           
                        <div class="Occupy"></div>



                  

                      </div>
                      <!-- ()=>loadReplyContent(item.content) -->
                      
                     
                    </div>
              
                  </div>
                </div>
              </div>
            </div>
            
          </div>
        </div>
      </el-col>
      <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
        <div class="grid-content  divheight">
          <div class="zhanweiIDnv"></div>
        <div class="divfour ">
        <div v-show="isLogin"><userView :userInfo="userInfo" /></div>
        <router-link active-class="active" to="/login">     <el-empty :image-size="200"  v-show="!isLogin"> <el-button type="primary">去登录</el-button></el-empty></router-link>
          
          </div
      >
        </div>
      </el-col>

    </el-row>
    <div class="publish_content" ref="publish_content" v-show="publish_content_show"><reply :model="2" :closef="closeReply" :uploadReply="uploadReply"  /></div>
  </div>
</template>

<script>
import { getApi, postApi ,uploadGetApi} from "@/utils/model/api";
import dayjs from 'dayjs'
import {getUserInfoByt } from "@/utils/model/getInfo";
import userView from '@/components/workPrimary/userView.vue';
import reply from '@/components/content/publishContent.vue'
import itemVue from '@/components/work/item.vue';

export default {
  components:{userView,reply},
  data() {
    return {

      requestJobInfo: {
        jobID: '',
        endPage: 10,
        startPage: 0,
        model: 1,
        contentId:''
      },

     
     
      init:0,
      replayGetData:[],
      isLogin:false,
      userInfo:{},
      publish_content_show:false,
      contentID:'',
      conpanyInfo:{},
      conpanycontents:[],
      positions:[],
      
      conpanycontents:[],
      conpantContentCount:0,
      rate:0,
      conpanycontentStartPage:0,
    
 

    }
  },
  filters: {
    replcaeTitle(value) {
      return value.replace(/\,/g, "/");
    },
    timeFormater(value) {
      return dayjs(value).format('MM月DD日 ')
    },
  },
  methods: {
    uploadReply(text,rate){

      if ( JSON.stringify(this.userInfo) == "{}" )
      {
        console.log('请登录')
       
        return
      }
      if(rate==0)
      {
       
        return
      }
      const data={
        'content':text,
        'userID':this.userInfo.userID,
        'userName':this.userInfo.userName,
        'rate':rate,
        'conpanyID':this.requestJobInfo.contentId
      }

      postApi("replyConten",data).then(res=>{
        if(res.code==200)
        {
         
          this.conpanycontents.unshift(res.data)
          this.publish_content_show=false;
        }
      })
     
    },
 
      descriptionSplict(value) {
      
        if (value==null)
        return
          let strs = value.split(/\|/)
          if (strs[strs.length - 1] === '') {
              strs.pop();
          }


          return strs;
      },
      praise(item,index) {
        getApi('ratepraise',{"rateID":item.rateID,'index':index}).then(res=>{
          if(res.code==200){
            item.praiseNum+=1;
          }
        })

      },


      loadConpanycontents() {
        if(this.init==0)
     {
        
        return;
     }
     if(this.conpanycontentStartPage<this.conpantContentCount)
     {
      postApi('getconpantContentPage', {'contentId':this.requestJobInfo.contentId,'startPage':this.conpanycontentStartPage+10}).then(res => {
          if(res.code==200)
          {
           
            res.data.forEach(item=>{
              this.conpanycontents.push(item);
            })
          }


      })
     }

      },
      reply(){
        this.publish_content_show=true;
      },
    closeReply(){
        this.publish_content_show=false;
    },
    formaterTextContent() {
    

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
  
    loadJob(){
       
     if(this.init==0)
     {
        
        return;
     }
     if(this.requestJobInfo.startPage<  this.conpanyInfo.jobNum)
     {
        this.requestJobInfo.model=2;
      this.requestJobInfo.startPage= this.requestJobInfo.startPage+10;
      this.getJobDetal();
     }

     
    } ,
   
   
    dealContent() {
     

    },
    dealJob() {
     
      
     
    },

    getJobDetal() {
      postApi('getConpanyDetail', this.requestJobInfo).then(res => {

        if (res.code == 200) {
          if (this.requestJobInfo.model == 1) {
     
            this.conpanyInfo=res.data.conpanyInfo;
          this.positions=res.data.positions;
          this.conpanycontents=res.data.conpanycontents;
          this.conpantContentCount=res.data.conpanContentCount.count;
         
          this.init=1;
          }
          else{
        
         
           res.data.positions.forEach(item=>{
                this.positions.push(item);
            })
     
          
          }
    

          // this.ContentCounts = res.data.ContentCounts;
          // this.PositionContents = res.data.PositionContents;
          // this.ReplyPositionContents = res.data.ReplyPositionContents;
      
          // this.dealContent();

        }
      })
    }

  },
  mounted() {
   

  },
  updated   (){
   
  },
  created(){
  

    this.requestJobInfo.contentId = this.$route.params.conpanyid;
    this.rate=Number(this.$route.params.rate);
   
    this.getJobDetal();
    this.getUser();
    
  }
}
</script>


<style src="@/assets/css/work/workDetail.scss" lang="scss" scoped >

</style>
<style src="@/assets/css/work/conpanyDetail.scss" lang="scss" scoped />

<style  src="@/assets/css/userLoginInfo.scss" lang="scss" scoped></style>

<style lang="scss" scoped>
::-webkit-scrollbar {
  width: 0 !important;
}

::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}

* {
  font-family: microsoft yahei !important;

}
</style>