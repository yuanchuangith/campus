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
                  <h1>{{ primaryPositionInfo.tag }}</h1>
                </div>
                <div class="job_wage"><strong>{{ primaryPositionInfo.wage }}</strong></div>
              </div>
              <p class="info_center"> <span class="textColor">{{ primaryPositionInfo.description }}| {{
                primaryPositionInfo.publishData | timeFormater
              }}</span></p>
              <div class="info_buttom">
                <div v-if="this.primaryPositionInfo.wekare"> <el-tag type="success"
                    v-for="(text, index) in descriptionSplict(primaryPositionInfo.wekare)" :key="index"
                    class="wekare_eltag">{{ text }}</el-tag></div>
              </div>
              <div class="info_buttom textColor">
                <p><span v-if="primaryPositionInfo.kind">{{
                  primaryPositionInfo.kind
                    | replcaeTitle
                }}</span><span>&nbsp;&nbsp;&nbsp;&nbsp;</span><span>来源于：{{
  primaryPositionInfo.source
}}</span></p>
              </div>
            </div>

            <div class="center_top_header_apply">11111</div>
          </div>
          <div class="center_contents div_flex">
            <div class="letf_width">
              <div class="jobDetailInfo">

                <div>
                  <h2><span class="bname">职位信息</span></h2>
                </div>
                <div class="job_content">
                  <div v-if="primaryPositionInfo.textContent">
                    <div class="positionDescriptions position_text">
                      <p v-for="(text, index) in positionDescriptions" :key="index">{{ text }}</p>
                    </div>
                    <div v-if="positionRequire.length>0" class="positionRequire position_text">
                      <div>任职资格：</div>
                     <div > <p v-for="(text, index) in positionRequire" :key="index">{{ text }}</p></div>
                 
                    </div>
                  </div>
                </div>

              </div>
            </div>
            <div class="rigth_width">
              <div class="conpany_info">
                <div class="title text_border">
                  <h2 class="bname">公司信息</h2>
                </div>
                <div class="info text_border">
                  <div class="conpanyName"><strong>{{ primaryPositionInfo.companyName }}</strong></div>
                  <div class="Occupy"></div>
                  <div class=""><span><i class="el-icon-s-home"></i></span><span>{{
                    primaryPositionInfo.companyLocation + primaryPositionInfo.companyLocarionArea
                  }}</span></div>
                  <div class="Occupy"></div>
                  <div class=""><span><i class="el-icon-user"></i></span><span>{{
                    primaryPositionInfo.companySize
                  }}</span></div>
                  <div class="Occupy"></div>
                  <div class=""><span><i class="el-icon-s-flag"></i></span><span>{{
                    primaryPositionInfo.companyIndustry
                  }}</span></div>
                  <div class="Occupy"></div>
                  <div class=""><span><i class="el-icon-notebook-2"></i></span><span>{{
                    primaryPositionInfo.companyCharacter
                  }}</span></div>

                </div>
                <div class="info_buttom ">
                  <div><el-badge :value="this.morePosition.length + 1" class="item" type="warning">
                      <el-button size="small">公司在招聘岗位数</el-button>
                    </el-badge></div>
                </div>
              </div>
            </div>
          </div>
          <div class="center_address div_flex">
            <div class="letf_width">
              <h2><span>工作地址</span></h2>
              <div class="job_address"><span>{{ primaryPositionInfo.jobaddress }}</span><span class="addressMap"><a
                    target="_blank" :href="primaryPositionInfo.addressUrl">查看地图</a></span></div>
            </div>
            <div class=" conpany_praise">
              <div class="conpany_num">

                <div class="title text_border">感兴趣人数和点赞</div>
                <div class="info_buttom ">


                  <div>
                    <span class=""><el-badge :value="primaryPositionInfo.interestedNum" class="item " type="primary">
                        <el-button size="small">感兴趣</el-button>
                      </el-badge></span>
                    <span class="tag_left"><el-badge :value="primaryPositionInfo.praiseNum" class="item" type="warning">
                        <el-button size="small" >点赞</el-button>
                      </el-badge></span>

                  </div>
                </div>

              </div>
            </div>
          </div>
          <div class="center_buttom div_flex">
            <div class="letf_width">
              <div class="top">
                <div>
                  <el-badge :value="PositionContentCount" class="item">
                    <el-button size="small" @click="reply">评论</el-button>
                  </el-badge>
                </div>
                <div>
                  <div class="top_text">
                    <h3>评论区</h3>
                  </div>
                </div>
              </div>
              <div class="job_discuss infinite-list" v-infinite-scroll="loadContent"  style="overflow: auto">
                <div class="discuss_content infinite-list-item" v-for="(item,index) in content" :key="item.contentID">
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
                              <el-button size="small" @click="praise(item.contentID,index)">点赞</el-button>
                            </el-badge></span>
                          <span class=""><el-badge :value="item.replyCount" class="item " type="primary">
                              <el-button size="small" >回复数</el-button>
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

                        <!-- 图片加载后期修改 -->
                        <div class="images">
                          <div v-if="item.images=='' || item.images==null" class="images">
     
                          </div>
                          <div v-else class="images">
                            <div class="block images_item" v-for=" (imagesrc, i) in  dealImagesStr(item.images)" :key="i">
                 
                              <el-image :src="'./campusUploadImg/'+imagesrc" lazy :preview-src-list="['./campusUploadImg/'+imagesrc]">
                                <div slot="placeholder" class="image-slot">
                                  加载中<span class="dot">...</span>
                                </div>
                              </el-image>
                            </div>
                          </div>



                        </div>

                      </div>
                      <!-- ()=>loadReplyContent(item.content) -->
                      <div class="content_buttom infinite-list"  v-infinite-scroll=" ()=>loadReplyContent(item.contentID,item.replyStatrtPage,item.replyCount)"  style="overflow: auto">
                        <div class="discuss_content_item infinite-list-item" v-for="(replyItem,replyItemindex) in item.replyItem"
                          :key="replyItem.replyID">
                          <div class="protrait_photo">
                            <span v-if="replyItem.protraitPhoto"><img :src="item.protrait_photo" alt=""></span>
                            <span v-else><el-avatar
                                src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></span>
                          </div>

                          <div class="content">
                            <div class="content_top">
                              <div class="content_user ">{{ replyItem.userName }}</div>
                              <div class="content_data ">{{ replyItem.replycreateTime | timeFormater }}</div>
                              <div class="content_user tag_conpanyName_color"> <span class=""><el-badge
                                    :value="replyItem.replypraiseNum" class="item " type="primary">
                                    <el-button size="small" @click="praise(item.contentID,replyItem.replyID,replyItemindex)">点赞</el-button>
                                  </el-badge></span></div>
                            </div>
                            <div class="Occupy"></div>
                            <div class="position_center">
                              <div class="content_top">
                                <div class="content_user " v-if="replyItem.replyReplyToPerson"> <el-link
                                    type="primary">@{{ replyItem.replyReplyToPerson }}</el-link></div>
                                <div class="content_data ">{{ replyItem.replycontent }}</div>
                                <!-- <div class="content_user tag_conpanyName_color">1</div> -->
                              </div>
                            </div>
                            <div class="Occupy"></div>
                            <div class="content_images">
                                  <!--   标记 -->
                                  <div v-if="replyItem.replyimages=='' || replyItem.replyimages==null" class="images">
                                    </div>
                                    <div v-else class="images">
                                      <div class="block images_item" v-for="  (imagesrc, i)  in dealImagesStr(replyItem.replyimages)" :key="i">
                                    
                                        <el-image :src="'./campusUploadImg/'+imagesrc" lazy :preview-src-list="['./campusUploadImg/'+imagesrc]">
                                          <div slot="placeholder" class="image-slot">
                                            加载中<span class="dot">...</span>
                                          </div>
                                        </el-image>
                                      </div>
                                    </div>
                                    
                                    
                                    </div>


                          </div>
                          <div class="reply_button"> <span> <el-popover placement="top-start" trigger="hover"
                                content="我也说一句">
                                <el-button slot="reference" @click="reply(item.contentID,replyItem.replyID,replyItem.userName)">回复</el-button>
                              </el-popover></span></div>

                        </div>

                      </div>
                      <div class="load"> <span> <el-popover placement="top-start" width="40" trigger="hover"
                            content="点击加载更多...">
                            <el-button slot="reference" @click="loadReplyContent(item.contentID,item.replyStatrtPage,item.replyCount)">查看更多</el-button>
                          </el-popover></span></div>
                    </div>
                    <div> <span> <el-popover placement="top-start" width="40" trigger="hover" content="我也说一句">
                          <el-button slot="reference" @click="reply(item.contentID,item.userName)">回复</el-button>
                        </el-popover></span></div>
                  </div>
                </div>
              </div>
            </div>
            <div class="rigth_width  ">
              <div class="more_jobtop">
                <h3>该公司跟多在招职位</h3>
              </div>
              <div class="more_jobdiv infinite-list" style="overflow: auto">

                <div class="more_job infinite-list-item" v-for="moreJobItem in morePosition " :key="moreJobItem.jobID">
                  <router-link active-class="active" :to="`/work/position/`+moreJobItem.jobID" target="_blank">  <div><i class="el-icon-suitcase"></i> <span>{{ moreJobItem.tag }}</span></div></router-link>
                  <div><i class="el-icon-wind-power"></i> <span>{{ moreJobItem.wage }}</span></div>
                  <div><i class="el-icon-notebook-1"></i> <span>感兴趣人数：{{ moreJobItem.interestedNum }}</span></div>
                  <div></div>
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
    <div class="publish_content" ref="publish_content" v-show="publish_content_show"><reply :model="1" :closef="closeReply" :uploadReply="uploadReply" /></div>
  </div>
</template>

<script>
import { uploadPostApi, postApi ,uploadGetApi} from "@/utils/model/api";
import dayjs from 'dayjs'
import {getUserInfoByt } from "@/utils/model/getInfo";
import userView from '@/components/workPrimary/userView.vue';
import reply from '@/components/content/publishContent.vue'
import itemVue from '@/components/work/item.vue';

export default {
  components:{userView,reply},
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
    
      requestJobInfo: {
        jobID: '',
        endpage: 4,
        startpage: 0,
        model: 1
      },
      ContentCounts: [],
      PositionContents: [],
      ReplyPositionContents: [],
      positions: [],
      primaryPositionInfo: {

      },
      morePosition: [],
      content: [],
      PositionContentCount: 0,
      positionDescriptions: [],
      positionRequire: [],
      init:0,
      replayGetData:[],
      isLogin:false,
      userInfo:{},
      publish_content_show:false,
      contentID:'',
      replayID:'',
      replayName:'',
      replyInit:0,

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

    praise(...arges)
    {
      console.log(this.content)
      if(arges.length==2)
      {
        const contentID=arges[0];
        uploadGetApi("praise",{"contentID":contentID,"replayID":null,'jobID': this.requestJobInfo.jobID,"model":arges[1]}).then(res=>{
           if(res.code==200&& arges[1]<4)
           {
            
            for (var i = 0; i < this.content.length; i++) {

              if(this.content[i].contentID==contentID)
              {
                this.content[i].praiseNum+=1;
                break;
              }
            }
           }
        })
      }
      else if (arges.length==3)
      {
        const contentID=arges[0];
        const replayID=arges[1];
        uploadGetApi("praise",{"contentID":null,"replayID":replayID,'jobID': this.requestJobInfo.jobID,"model":arges[2]}).then(res=>{
          if(res.code==200&& arges[2]<4)
           {
            
            for (var i = 0; i < this.content.length; i++) {

              if(this.content[i].contentID==contentID)
              {
                for (var j = 0; j< this.content[i].replyItem.length; j++) {

                           if(this.content[i].replyItem[j].replyID==replayID)
                           {
                            this.content[i].replyItem[j].replypraiseNum+=1;
                           }
                }
              }
            }
           }
          
        })
      }
    },
    dealImagesStr(imageStr){
      if (imageStr==null||imageStr=="")
      return null
    return  imageStr.split("，")

    },
    dealReply_replyContent(content){
      let data = {
        "protraitPhoto": this.userInfo.protraitPhoto,
        "replyID": content.replyID,
        "replyReplyToPerson": content.replyToPerson,
        "replycontent": content.content,
        "replycontentID": content.contentID,
        "replycreateTime": content.createTime,
        "replyimages": content.images,
        "replypraiseNum": content.praiseNum,
        "userID": this.userInfo.userID,
        "userName": this.userInfo.userName
      }

      for (var i = 0; i < this.content.length; i++) {
        // let item=this.content[i]
        if (this.content[i].contentID == content.contentID) {
          
        
          this.content[i].replyItem.unshift(data);
      

          break;
        }
      }


        
    },
    dealReply_Content(content){
      this.init=0;
      let data={"protraitPhoto":this.userInfo.protraitPhoto,
                "contentID":content.contentID,
                "replyCount":0,
                  "content":content.content,
                 "replyStatrtPage":0,
                  "createTime":content.createTime,
                  "images":content.images,
                  "praiseNum":content.praiseNum,
                  "userID":this.userInfo.userID,
                  "replyToPerson":"",
                  "userName":this.userInfo.userName,
        "replyItem":[]
                 }
    this.content.unshift(data);
    this.init=1;
    },
    replyContent(contentData,strimg){
      const param = new FormData();
      // console.log("strat")
      param.append('contentData', contentData);
      // console.log('contentID',this.contentID)
      // console.log('replayName',this.replayName)
      // console.log('replayID',this.replayID)
      // console.log('strimg',this.strimg)
      // console.log('jobID',this.requestJobInfo.jobID)
      // console.log('this.userInfo.userID',this.userInfo.userID)
      if (this.contentID.length > 0)
        param.append('contentID', this.contentID);
      if (this.replayName.length > 0)
        param.append('replayName', this.replayName);
      if (this.replayID.length > 0)
        param.append('replayID', this.replayID);
      if (strimg!=undefined ||strimg)
        param.append('strimgPath', strimg);
      if (this.requestJobInfo.jobID.length > 0)
        param.append('jobID', this.requestJobInfo.jobID);
      if (this.userInfo.userID.length == 0)
        return
      param.append('userID', this.userInfo.userID);
      param.append('protraitPhoto', this.userInfo.protraitPhoto);
      param.append('userName', this.userInfo.userName);
      // console.log("close")
      uploadPostApi('saveContent', param).then(res => {
        // console.log(res.data)
        if (res.code == 200) {
          this.publish_content_show=false;
          this.$bus.clearPublishContent();
          const data = res.data;
          if (data.model == 1) {
            this.dealReply_Content(data.content);

          }
          else if (data.model == 2) {
            this.dealReply_replyContent(data.content);
          }
        }
      })
      
    }
    ,
    uploadReply(contentData,imageData){
      if ( JSON.stringify(this.userInfo) == "{}" )
      {
        console.log('请登录')
       
        return
      }
      if(contentData.length==0)
      {
        console.log('请输入')
        return
      }
     
      if(!imageData.has('files'))
      {
        this.replyContent(contentData,'');
 
       

      }
    else
    {
      imageData.append("contentID", this.contentID);
        if (this.replayID)
          imageData.append("replayID", this.replayID);
        imageData.append("replayName", this.userInfo.userName);
        let hander = {
          'Content-Type': 'multipart/form-data'//必须要指定
        };


        uploadPostApi('uploadImg', imageData, hander).then(res => {
          if (res.code == 200) {
      
            var strimg='';
            res.data.forEach(item=>{
              strimg+=item+'，'
            })
            console.log(strimg)
            strimg= strimg.slice(0,strimg.length-1)
            this.replyContent(contentData,strimg);
          }
        })
     
    }

    },
    reply(...arges){
     
    
      if(arges.length==3)
      {
     
        this.contentID=arges[0];
        this.replayID=arges[1];
        this.replayName=arges[2];
        this.publish_content_show=true;
       
      }
      else if(arges.length==2)
      {
        this.contentID=arges[0];
        this.replayID='';
        this.replayName='';

        this.publish_content_show=true;
       
      }
      else
      {
        this.publish_content_show=true;
        this.contentID=''; 
        this.replayID='';
        this.replayName='';
      }
   

    },
    closeReply(){
        this.publish_content_show=false;
    },
    formaterTextContent() {
      var value = this.primaryPositionInfo.textContent.replace(/；/g, "。");
      // 【 任职资格
       value = this.primaryPositionInfo.textContent.replace(/ 【/g, "。");
       value =value.replace(/【/g, "。");
       value = value.replace(/】/g, "。");
      var values = value.split('职位要求')
     
      if(values.length <2){
        var values = value.split('任职资格')
      }
      if (values.length > 1) {
        this.positionDescriptions = values[0].split('。')
        let require = values[1].replace(/：/, "。");
        this.positionRequire = require.split('。')

      }
      else
      {
        this.positionDescriptions = values[0].split('。')
      }

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
    descriptionSplict(value) {
      if(value==null)
        return;

      let strs = value.split(/\|/)
      if (strs[strs.length - 1] === '') {
        strs.pop();
      }


      return strs;

    },
    loadContent(){
     
      if(this.init==0)
      {
        return
      }
 
      if(this.requestJobInfo.startpage<this.PositionContentCount)
      
      {     
        this.requestJobInfo.startpage=this.requestJobInfo.startpage+this.requestJobInfo.endpage;
      
        this.requestJobInfo.model=0;
       
        this.getJobDetal();
    }


    },
    loadReplyContent(contentID,startPage,count){
   
   
      if(this.replyInit<3)
      {
        this.replyInit+=1;
        return
      }
      if(isNaN(count) ||count==0)
      return
   
      // let startPage=0;
      // this.content.
  
      if(startPage<count)
      {
        postApi('getReplyContent', {'contentID':contentID,'startpage':startPage+4,'endpage':4}).then(res => {

           
            if(res.code==200)
            {
       
              this.replayGetData=res.data.getReplyPositionContents;
              this.dealReplyContent(this.replayGetData,contentID,startPage+4);
              this.replayGetData=[];
            }

            })
      }
    },
    dealReplyContent(replayContent,contentId,startPage){
   


      for(var i =0; i<this.content.length;i++)
        {
          // let item=this.content[i]
        if(this.content[i].contentID==contentId)
        {
          let item=this.content[i];
    
          let replyItem=  item.replyItem.concat(replayContent);
          item.replyItem=replyItem;
       
          
          this.$set( this.content,i,item)
          
      
          this.content[i].replyStatrtPage=startPage;
         
         break;
        }
        }

    },
    dealContent() {
    
      if (this.ContentCounts.length > 0 && this.PositionContents.length ) {
        this.PositionContentCount = this.ContentCounts[0].positioncontentCount;
        this.PositionContents.forEach(item => {

          let contentID = item.contentID;
          var replyItem = this.ReplyPositionContents.filter(replyItem => {
            return contentID == replyItem.replycontentID ? replyItem : null;
          })
          var replyCount = 0;
          this.ContentCounts.filter(countItem => {

            if (countItem.contentID == contentID) {
              replyCount = countItem.groupCount;
            }
          })
          item['replyItem'] = replyItem;
          item['replyCount'] = replyCount;
          item['replyStatrtPage'] = 0;
          this.content.push(item);
        });
 
   

     this.ReplyPositionContents=[];
     this.PositionContents=[];
     this.ContentCounts=[];

      }

    },
    dealJob() {
     
      
      this.positions.filter(item => {
        if (item.jobID == this.requestJobInfo.jobID) {
          this.primaryPositionInfo = item;
          this.formaterTextContent();
        }
        else {
          this.morePosition.push(item);
        }
      })
      this.positions=[];
    },

    getJobDetal() {
      postApi('getJobDetail', this.requestJobInfo).then(res => {

        if (res.code == 200) {
          if (this.requestJobInfo.model == 1) {
     
            this.positions = res.data.positions;
            this.dealJob();
          }
       

          this.ContentCounts = res.data.ContentCounts;
          this.PositionContents = res.data.PositionContents;
          this.ReplyPositionContents = res.data.ReplyPositionContents;
      
          this.dealContent();

          this.init=1;
        }
      })
    }

  },
  mounted() {
   

  },
  updated   (){
   
  },
  created(){
  
    this.requestJobInfo.jobID = this.$route.params.jobID;
  
    this.getJobDetal();
    this.getUser();
    
  }
}
</script>


<style src="@/assets/css/work/workDetail.scss" lang="scss" scoped >

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