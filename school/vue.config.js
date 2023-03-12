const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false ,
  devServer:{
    // hot:true,//自动保存
    // open : true,//自动启动
    // port : 8080,//默认端口号
    historyApiFallback: true,
allowedHosts: "all",

    proxy:{
    
      '/check':{
        target:'http://localhost:9000',//http://localhost:9000/checkCode
        changeOrigin: true,
        pathRewrite:{'^/check':''}
      },
      '/api':{
        target:'http://localhost:8081',
        changeOrigin: true,
        pathRewrite:{'^/api':''}
      },
    }
  },
  chainWebpack: config => {
    config.plugins.delete("prefetch")//只加载当前页面需要的js
  },
})

