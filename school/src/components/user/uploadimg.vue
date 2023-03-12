<template>
  <div>
    <div>{{ this.title }}</div>
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
    <div class="submit_content"> <el-button type="primary" @click="postImg">上传图片</el-button></div>
  </div>
</template>

<script>
export default {
    props: ["title",'upload'],
    data() {
    return {

        dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      fileList: [],

    };
  },
    methods: {

        replyUpload(file,fileList){

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
    postImg(){
      

      const param  = new FormData();
      if (this.fileList.length>0)
      {
        param .append("file", this.fileList[0].raw);
        this.upload(param);
    
      }
     


      
    },
  

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
 
}
,


}
</script>
<style lang="scss">
.buttom_upload{
    margin-top: 15px;
}
.submit_content{
    margin-top: 15px;
}
</style>
