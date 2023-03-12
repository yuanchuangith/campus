<template>
  <div>
    <div class="top">
      <div class="title">测试接口：</div>
      
    </div>
    
    <div class="content">
     
    <div class="input_text">
      <el-form-item label="输入token">
    <el-input placeholder="请获取内token" v-model="token" disabled clearable></el-input>
    <el-button type="primary" @click="this.getToken" style="margin-top: 10px;">获取Token</el-button>
 
  </el-form-item>

     </div>
      <div class="submit_content"> <el-button type="primary" @click="postCodeImg">识别</el-button></div>
    </div>
    <el-form-item label="上传图片">
    <div  class="buttom_upload">
        <el-upload action="#" list-type="picture-card" :auto-upload="false" :on-change="replyUpload" :limit =1 :fileList="fileList"  >
        <i slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{ file  }">
            <img :src="file.url" class="avatar">
          <span class="el-upload-list__item-actions">

            <span v-if="!disabled" class="el-upload-list__item-delete" :on-remove="handleDownload">
              <i class="el-icon-download"></i>
            </span>
            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
              <i class="el-icon-delete"></i>
            </span>
          </span>
        </div>
      </el-upload>
    </div>
  </el-form-item>

        

  </div>
</template>

<script>
import pubsub from 'pubsub-js'
export default {
    props: ["postData",'getToken'],
    data() {
    return {
        imageUrl:'',
        dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      fileList: [],
      token:"",
    };
  },
    methods: {

        replyUpload(file,fileList){
        //  if(file.type==)
        // this.imageUrl=file.url;
        this.fileList = fileList;
        
       
        return false;
    },
    handleRemove(file) {
        this.fileList= this.fileList.filter(item=>{
        if(item.uid!=file.uid)
        {
            return item
        }
       })
    },
    postCodeImg(){
      

      const param  = new FormData();
      if (this.fileList.length>0)
      {
        param .append("file", this.fileList[0].raw);
        this.postData(param,this.token);
      }
     


      
    },
     gettoken(){
    this.getToken();
    


    },
    cleardata(){
     this. textarea2='';
     this. fileList= [];
    }
    ,
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
      console.log(file)
    },
    handleDownload(file) {
      console.log(file);
    },

},
created(){
  pubsub.subscribe("getToken",(name,data)=>{
    this.token=data

   

  });
}
,


}
</script>

<style src="@/assets/css/punlishContent.scss" lang="scss" scoped />