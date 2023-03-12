<template>
    <dv-border-box-13 class="lr_titles">

        <slot ></slot>

    </dv-border-box-13>
  </template>
  
  <script>
  export default {
    data() {
      return {};
    },
    
    created() {},
  
    mounted() {},
    methods: {},
  };
  </script>
  <style lang='scss' scoped>

  $item-title-height: 38px;
  $item_title_content-height: calc(100% - 38px);
  
  .lr_titles {
    box-sizing: border-box;
  
  :deep(.border-box-content)  {
      box-sizing: border-box;
      padding: 6px 16px 0px;
    }
  
   
  
    .item_title_content {
      height: $item_title_content-height;
    }
  
    .item_title_content_def {
      width: 100%;
      height: 100%;
    }
  }
  </style>