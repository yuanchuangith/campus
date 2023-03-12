<template>
     <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="outTradeNo"
        label="账单ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="totalAmount"
        label="充值金额"
        width="180">
      </el-table-column>
      <el-table-column
      
        label="交易状态">
        <template slot-scope="scope">
       <span v-if="scope.row.tradeModel==1 ">
        <i class="el-icon-s-goods"></i>
        <span style="margin-left: 10px">已支付</span>
       </span>
       <span v-else>
        <i class="el-icon-goods"></i>
        <span style="margin-left: 10px">未支付</span>
       </span>
      </template>
      </el-table-column>
      <el-table-column
        prop="tradeName"
        label="交易名称">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="timeStampT"
        label="完成时间">
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
          param .append("model",this.model);
      userUpdatePostApi('getOrder', param).then(res => {
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
        this.model = this.$route.params.model;
        this.userID = this.$route.params.userID;
       this.getOrder();
      }
    }
</script>

<style>

</style>