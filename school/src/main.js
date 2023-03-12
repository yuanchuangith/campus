
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import {url,axiosRequest} from './utils/api'
import {borderBox13,borderBox8} from '@jiaminghi/data-view'
import Echart from './components/echart/index.vue'
import VueTypedJs from 'vue-typed-js'


Vue.use(VueTypedJs)
Vue.use(ElementUI)
Vue.component("Echart",Echart)
Vue.config.productionTip = false
Vue.use(borderBox13)
Vue.use(borderBox8)

new Vue({
  router,
  store,
  render: h => h(App),
  beforeCreate() {
		Vue.prototype.$bus = this,
    
    Vue.prototype.$requestUrl=url,
    Vue.prototype.$axiosRequest=axiosRequest

	}
}).$mount('#app')
