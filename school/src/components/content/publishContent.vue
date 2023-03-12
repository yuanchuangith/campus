<template>
  <div>
    <div class="top">
      <div class="title">评论</div>
      <div class="close"><i class="el-icon-close" @click="close"></i></div>
    </div>
    <div class="content">
      <div class="input_text"> <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 6 }" :maxlength="100" placeholder="请输入内容"
          v-model="textarea2">
        </el-input></div>
      <div class="submit_content"> <el-button type="primary" @click="postReply">发布</el-button></div>
    </div>
    <div v-if="model==1" class="buttom_upload">
      <el-upload action="#" list-type="picture-card" :auto-upload="false" :on-change="replyUpload" :fileList="fileList">
        <i slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{ file  }">
          <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
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
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="" />
      </el-dialog>
    </div>
    <div v-else class="rate"> 
      <el-rate v-model="rate"  show-score text-color="#ff9900"
                    score-template="企业评分"></el-rate>
    </div>
  </div>
</template>

<script>
import { uploadPostApi,uploadGetApi } from "@/utils/model/api";
export default {
  props: ["model", "closef",'uploadReply'],
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      textarea2:'',
      fileList: [],
      rate:0,
    };
  },
  methods: {

    replyUpload(file,fileList){
        //  if(file.type==)
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
    postReply(){
      
    if(this.model==1){
      const param  = new FormData();
    
    this.fileList.filter(item=>{
      param .append("files", item.raw);
    })
    this.$bus.clearPublishContent=this.cleardata;
    this.uploadReply(this.textarea2,param)
    }
    else if(this.model==2)
    {

      this.uploadReply(this.textarea2,this.rate)
    }


      
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
    close() {
      this.closef();
    },
  },
};
</script>

<style src="@/assets/css/punlishContent.scss" lang="scss" scoped />
<style>
</style>