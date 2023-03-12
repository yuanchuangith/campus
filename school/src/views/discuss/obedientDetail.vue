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
            <div >
           
              <div>    <span class="name">{{obedientDetial.userName}}</span><!-- <span v-if="item=-1"><img :src="item.protraitPhoto" alt=""></span> -->
                      <span v-if="obedientDetial.protraitPhoto"><img :src="require('@/assets/img/userAvatar/'+obedientDetial.protraitPhoto)" alt="" class="protraitPhoto"></span>
                      <span v-else><el-avatar
                          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></span></div>
                        </div>
            <div class="center_top_header_info">
        
              <div class="info_top">
                <div class="job_tag">
                  <h1>{{ obedientDetial.title }}</h1>
                </div>
                <div class="job_wage"><strong>{{obedientDetial.createTime |timeFormater }}</strong></div>
              </div>
              <!-- <p class="info_center"> <span class="textColor"> {{ obedientDetial.title }} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{ obedientDetial.title }}</span></p> -->
              
              <div class="info_buttom">
                <!-- <div > <el-tag type="success"
                    
                    class="wekare_eltag">{{ obedientDetial.title }}</el-tag></div> -->
            </div>
            <div class="info_buttom textColor">
              <div style="margin-top:10px; width: 10% ; margin-left: auto;"><span class=""><el-badge :value="1" class="item " type="danger">
                        <el-button size="small">热度</el-button>
                      </el-badge></span>
                </div>
            </div>
            </div>
            <div class="info_buttom textColor"></div>
       
              
            </div>
            <div class="center_contents div_flex">
                <div style="width:100%;">
                    <div class="jobDetailInfo">
                    <div>
                        <h2><span class="bname">内容：</span></h2>
                    </div>
                    <div class="job_content">
                        <div >
                            {{obedientDetial.textContent  }}
                        </div>
                       
                                  <!--   标记 -->
                                  <div v-if="obedientDetial.images=='' || obedientDetial.images==null" class="images">
                                    </div>
                                    <div v-else class="images">
                                      <div class="block images_item" v-for="  (imagesrc, i)  in descriptionSplict(obedientDetial.images)" :key="i">
                                    
                                        <el-image :src="require('@/assets/img/obedient/'+obedientDetial.obedientID+'/'+imagesrc)" lazy :preview-src-list="[require('@/assets/img/obedient/'+obedientDetial.obedientID+'/'+imagesrc)]">
                                          <div slot="placeholder" class="image-slot">
                                            加载中<span class="dot">...</span>
                                          </div>
                                        </el-image>
                                      </div>
                                    </div>
                                    
                                    
                              
                     
                    </div>
        
                </div>
            </div>
          </div>
          <div class="center_address div_flex">
            <div class="letf_width">
              <h2><span>评论区</span></h2>
              
            </div>
            <div class=" conpany_praise">
              <div class="conpany_num">

                <div class="title text_border">感兴趣人数和点赞</div>
                <div class="info_buttom ">


                  <div>
                    <span class=""><el-badge :value="obedientDetial.contenrtNum" class="item " type="primary">
                        <el-button size="small">热度</el-button>
                      </el-badge></span>
                    <span class="tag_left"><el-badge :value="obedientDetial.praiseNum" class="item" type="warning">
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
                  <el-badge :value="obedientDetial.numCount" class="item">
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
                <div class="discuss_content infinite-list-item" v-for="(item,index) in replay" :key="item.replyID">
                  <div class="discuss_content_item infinite-list-item">
                    <div class="protrait_photo">
                      <span v-if="item.protraitPhoto"><img :src="require('@/assets/img/userAvatar/'+item.protraitPhoto)" class="protraitPhoto" alt=""></span>
                      <span v-else><el-avatar
                          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></span>
                    </div>

                    <div class="content">
                      <div class="content_top">
                        <div class="content_user " v-if="item.realName"><span>{{ item.realName }}</span></div>
                        <div class="content_user " v-else><span>{{ item.userName }}</span></div>
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

                      
                       
                       
                          
                        <div class="content_images">
                                  <!--   标记 -->
                                  <div v-if="item.images=='' || item.images==null" class="images">
                                    </div>
                                    <div v-else class="images">
                                      <div class="block images_item" v-for="  (imagesrc, i)  in descriptionSplict(item.images)" :key="i">
                                    
                                        <el-image :src="require('@/assets/img/obedient/'+obedientDetial.obedientID+'/'+item.replyID+'/'+imagesrc)" lazy :preview-src-list="[require('@/assets/img/obedient/'+obedientDetial.obedientID+'/'+item.replyID+'/'+imagesrc)]">
                                          <div slot="placeholder" class="image-slot">
                                            加载中<span class="dot">...</span>
                                          </div>
                                        </el-image>
                                      </div>
                                    </div>
                                    
                                    
                                    </div>
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
    import userView from '@/components/workPrimary/userView.vue';
   
    import { obedientPostApi } from "@/utils/model/api";
    import {getUserInfoByt } from "@/utils/model/getInfo";
    import dayjs from 'dayjs'
    import {message} from '@/utils/model/usullyUtils'
    import reply from '@/components/content/publishContent.vue'

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

     imagesUrl:[],
     
      init:0,
      replay:[],
      isLogin:false,
      userInfo:{},
      publish_content_show:false,
      contentID:'',
      obedientDetial:{},
     
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

     
    },
    // '@/assets/img/obedient/'+obedientDetial.obedientID+'/'+imgurl
   
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
        return
        this.requestJobInfo.model=2;
        this.requestJobInfo.startPage+=10;
        this.getobedientDetail();
      

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
       
    

     
    } ,
   
   
    dealContent() {
     

    },
    dealJob() {
     
      
     
    },

    getobedientDetail() {
      obedientPostApi('getObedientDetial', this.requestJobInfo).then(res => {

        try {
         if (res.code == 200) {
          message(res.code, res.message)
          if(res.data.model==1)
          {this.obedientDetial=res.data.obedientDetial;
          this.replay=res.data.replys;
          this.init=1;
        }
        else if(res.data.model==2)
        {
          res.data.replys.forEach(element => {
            this.replay.push(element);
                    });
        }
        

   
         }
         else
          message(res.code, res.message)
       } catch (error) {
         message(null, "失败")

       }
      })
    }

  },
  mounted() {
   

  },
  updated   (){
   
  },
  created(){
  

    this.requestJobInfo.contentId = this.$route.params.obedientID;
   
  

   
    this.getobedientDetail();
    this.getUser();
    
  }
}
</script>


<style src="@/assets/css/discuss/obedientDetial.scss" lang="scss" scoped >

</style>


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