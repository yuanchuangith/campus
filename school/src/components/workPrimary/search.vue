<template>
<div> 
    <el-col :xs="5" :sm="5" :md="5" :lg="3" :xl="5"><div class="grid-content ">
        <div class="el-radio-button__inner change_div " :class="dibIsChange==true? 'active':'is_active'" @click="changeOne(divStateOne)"><span  > {{ this.titleOne }}</span></div>
        <div class="el-radio-button__inner change_div " :class="dibIsChange==true? 'is_active':'active'" @click="changeOne(divStatetTow)"><span  >{{ this.titleTow }}</span></div>
    </div></el-col>
  <el-col :xs="14" :sm="14" :md="14" :lg="21" :xl="14"><div class="grid-content input_div" >
    <el-autocomplete
  popper-class="my-autocomplete "
  class="input_search"
  v-model="keyWord"
  ref="input"
  @focus="searchFocus"

  :fetch-suggestions="querySearch"
  placeholder="请输入内容"
  @select="handleSelect"  @keyup.native.enter="handleIconClick">
  
  
  <template slot-scope="{ item }"  >
    <div class="name">{{ item.tag }}</div>
    <span class="addr">{{ item.companyName }}</span>
    <span class="addr">{{ item.wage }}</span>
  </template>
  
  <!-- <template slot-scope="{ item }" v-if="!dibIsChange">
    <div class="name">{{ item.companyName }}</div>
    <span class="addr">{{ item.tag }}</span>
    <span class="addr">{{ item.wage }}</span>
  </template> -->
</el-autocomplete>
<!-- <i
    class="el-icon-search el-input__icon"
    slot="suffix"
    style="margin-left:5px;"
    @click="handleIconClick">
  </i> -->
  <el-button icon="el-icon-search" circle  style="margin-left:10px;" 
    @click="handleIconClick"></el-button>

  </div></el-col>
    </div>
</template>

<script>
import { getApi, postApi } from "@/utils/model/api";
  export default {
    props:['getSearchKey','titleOne','titleTow','model'],
    data() {
      return {

        keyWord: '',
        divStateOne:true,
        divStatetTow:false,
        dibIsChange:true,
        oldContent:'',
        oldSearch:'',
        oldData:[]

      };
    },
    methods: {
        changeOne(value){
            if(this.dibIsChange!==value)
            {
               
                this.dibIsChange=!this.dibIsChange;
          
                console.log('dibIsChange',this.dibIsChange)
            }

        }
        ,
        searchFocus(){
          this.$refs.input.activated =true

        },
        changeValue(){
          console.log('keyWord',this.keyWord)

        },
      querySearch(queryString, cb) {
       
        
     if(queryString&& queryString!==this.oldSearch)
     {
     
      if(this.model==1)
      {
        postApi("getSearchTips", {'key':queryString,'changeKeyKind':this.dibIsChange}).then((res) => {
        this.oldSearch=queryString;
         
          // var restaurants = res.data;
          this.oldContent=res.data
          cb(res.data);
      
        

        })
      }

     }
     else {
       if (this.oldContent ) {
         cb(this.oldContent);
       }
     }
      }, 
      conpanyNameFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      tagilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
     
      handleSelect(item) {
        console.log("handleSelect",this.dibIsChange);
        if(this.dibIsChange)
        {
          
          this.keyWord=item.tag
        }
        else
        {
          
          this.keyWord=item.tag
        }
      },
      handleIconClick(ev) {
     
      this.$refs.input.activated =false;
      this.$refs.input.$children[0].blur();
      console.log(this.$refs.input.$children[0])
      
        if(this.keyWord)
        {

          this.getSearchKey({'keyWord':this.keyWord,'dibIsChange':this.dibIsChange})
        }
        
      },
      serachKey(){

      }
    },
    mounted() {
      console.log('dibIsChange',this.dibIsChange)
    }
  }
</script>
<style lang='scss' scoped>


.grid-content::v-deep{
    div{
        border: none;
    }
}
// .grid-content>div{
   
// }
.active
{
 box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.is_active{
   
    color: #999;
    border-color: #ebebeb;
    -webkit-box-shadow:inset  0 -4px 8px rgb(255 96 0 / 20%);
    box-shadow:inset  0 -4px 8px rgb(255 96 0 / 20%);
    background: #EBEBEB;
  }
  .input_div{
    margin: auto;
  }
  .change_div{
    width: 100%;
    min-height: 50%;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 80px;
  }
  .input_search{
    width: 90%;
   
    margin: auto;
  }
 
  .input_search::v-deep{
    input{
        border: none;
        height:60px ;
    }
    div{
        margin: 1%;
    }
  }
 
.my-autocomplete {
  li {
    line-height: normal;
    padding: 7px;

    .name {
      text-overflow: ellipsis;
      overflow: hidden;
    }
    .addr {
      font-size: 12px;
      color: #b4b4b4;
    }

    .highlighted .addr {
      color: #ddd;
    }
  }
}
</style>

