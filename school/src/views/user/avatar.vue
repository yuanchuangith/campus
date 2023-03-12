<template>
  <div >
    <uploadimg title="修改头像:" :upload="upload"></uploadimg>
  </div>
</template>

<script>
import uploadimg from "@/components/user/uploadimg.vue"
import {userUpdatePostApi} from '@/utils/model/api'
import {message} from '@/utils/model/usullyUtils'
 export default {
  components:{uploadimg},
    data() {
       
      return {
      userID:'',
  
      }
    },
    methods: {
      upload(param) {
        console.log(this.userID)
        if(param&&this.userID)
        {
          param .append("userID", this.userID);
          userUpdatePostApi('avatar',param).then(res=>{
            if (res.code==200)
            {
              message(res.code,res.message)
            }
            else
            message(res.code,res.message)
          })
        }

      }
    },
    created(){
      this.userID = this.$route.params.userID;
    }
  }
</script>

<style lang="scss" scoped>

</style>