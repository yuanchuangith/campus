<template>
    <el-table
     :data="tableData"
     style="width: 100%">
     <el-table-column
       prop="contentID"
       label="评论ID"
       width="180">
     </el-table-column>
     <el-table-column
       prop="content"
       label="内容"
       width="180">
     </el-table-column>
     <el-table-column
     
       label="回复数">
       <template slot-scope="scope">
        <el-button type="warning">{{scope.row.num}}</el-button>
     
     </template>
     </el-table-column>
     <el-table-column
       prop="praiseNum"
       label="点赞">
     </el-table-column>
     <el-table-column
       prop="createTime"
       label="创建时间">
     </el-table-column>

   </el-table>

</template>

<script>
import {userUpdatePostApi} from '@/utils/model/api'
import {message} from '@/utils/model/usullyUtils'
export default {
     data() {
       return {
         tableData: [],
         model:'',
         userID:''
       }
     }
     ,
     methods:{
       getOrder(){
        const param  = new FormData();
          param .append("userID", this.userID);
     userUpdatePostApi('getContent', param).then(res => {
       try {
         if (res.code == 200) {
           message(res.code, res.message)
           this.tableData=res.data;
         }
         else
          message(res.code, res.message)
       } catch (error) {
         message(null, "失败")

       }

     })
   }
       
     },
     created(){

       this.userID = this.$route.params.userID;
      this.getOrder();
     }
   }
</script>

<style>

</style>