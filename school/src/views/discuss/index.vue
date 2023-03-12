<template>
  <div class="content">
    <loopImg></loopImg>
    <div>    <el-row :gutter="10">
    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3"><div class="grid-content divH"></div></el-col>
    <el-col  :xs="16" :sm="16" :md="16" :lg="16" :xl="16"><div class="grid-content  divH">
  
    <div class="center">
      <div class="center_content ">       <div  class="bname"> <h2><span class="text">热门板块</span></h2>
      
      
        <div > <ul  class="ulinfo"><li  v-for="(item) in topData" :key="item.classificationID">
          <router-link active-class="active" :to="`/obedient/`+item.classificationID" target="_blank">   <div class="img"><img v-if="item.images" :src="require('@/assets/img/userAvatar/'+item.images)" alt="">
          <img v-else :src="require('@/assets/img/userAvatar/8255979a-91a8-4d04-a420-3b88fd8616d8.jpg')" alt="">
        </div></router-link>
        <h3 style=" text-align: center;" >{{item.title}}</h3>
      
      </li>
     
    </ul> </div>
      </div>
      
      <div class="obedient">

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
                        <div class="content_data "><span>标题：{{ item.title |tagStr }}</span></div>
                       
                        <div class="content_user tag_count">
                          <span class=""><el-badge :value="item.praiseNum" class="item " type="primary">
                              <el-button size="small" >点赞</el-button>
                            </el-badge></span>
                          <span class=""><el-badge :value="item.contenrtNum" class="item " type="primary">
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
                 
                  
                    </div>
              
                  </div>
                </div>
              </div>
      </div>

      </div>
        
    
      </div>
  
  
    </div></el-col>
    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
      <div class="bg-purple divH">
        <div class="zhanweiIDnv"></div>
        <!-- <div class="divfour grid-content ">
          <div v-show="isLogin">
            <userView :userInfo="userInfo" />
          </div>
          <router-link active-class="active" to="/login"> <el-empty :image-size="200" v-show="!isLogin" > <el-button
                type="primary">去登录</el-button></el-empty></router-link>
    
        </div> -->
      </div>
    </el-col>
  
  </el-row></div>
  </div>
</template>

<script>
  import loopImg from "@/components/loopImg.vue"
  import {obedientPostApi} from "@/utils/model/api"
  import {message} from '@/utils/model/usullyUtils'
  import dayjs from 'dayjs'
export default {
    
    components:{loopImg},
    data(){
      return{
        srcList: ['https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'],
        postData:{
          strart:0,
          end:10,
          model:1
        },
        topData:[],
        obedient:[]

        
      }
    },
    filters:{
      tagStr(value)
    {

      return  value.length <= 23? value:value.substring(0,23)+'.....';
    },
      timeFormater(value) {
      return dayjs(value).format('MM月DD日 ')
    },
    },
    methods:{
  

      loadContent(){

      },
      loadData(){
        obedientPostApi("getObedient",this.postData).then(res=>{
          try {
          if(res.code==200)
          {
            message(res.code,res.message)
           this.topData=res.data.topPlant;
           this.obedient=res.data.hatObenident;

          }
       } catch (error) {
        message(null,"失败")
        
       }
        })
      },
      dealImagesStr(imageStr){
      if (imageStr==null||imageStr=="")
      return null
    return  imageStr.split("，")

    },
    },
    mounted(){
      this.loadData();

    }
    

}
</script>

<style lang="scss" scoped src="@/assets/css/index.scss">

</style>