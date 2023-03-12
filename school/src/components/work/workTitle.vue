<template>
        <div class="item_title" v-if="title !== ''">
        <div class="zuo"></div>
        <span class="title-inner"> &nbsp;&nbsp;{{ title }}&nbsp;&nbsp; </span>
        <div class="you"></div>
        <slot></slot>
      </div>
</template>

<script>
export default {
    data() {
      return {};
    },
    props: {
      title: {
        type: String,
        default: () => "",
      },
    },
    created() {},
  
    mounted() {},
    methods: {},

}
</script>

<style  lang='scss' scoped>

$item-title-height: 38px;
  $item_title_content-height: calc(100% - 38px);
 .item_title {
      height: $item-title-height;
      line-height: $item-title-height;
      width: 100%;
      color: #31abe3;
      text-align: center;
      // background: linear-gradient(to right, transparent, #0f0756, transparent);
      position: relative;
      display: flex;
      align-items: center;
      justify-content: center;
  
      .zuo,
      .you {
        width: 58px;
        height: 14px;
        background-image: url("@/assets/img/you.png");
      }
  
      .you {
        transform: rotate(180deg);
      }
      .title-inner {
        font-weight: 900;
        letter-spacing: 2px;
        background: linear-gradient(
          92deg,
          #0072ff 0%,
          #00eaff 48.8525390625%,
          #01aaff 100%
        );
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }
    }
</style>