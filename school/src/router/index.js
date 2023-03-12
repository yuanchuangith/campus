import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login/Login.vue'
import Register from '../views/login/register.vue'
import work from '../views/work/work.vue'
import scrollWork from '../components/work/scroll/scrollWork.vue'
import indexWork from '../views/work/workIndex.vue'
import workDetile from '../views/work/workDetail.vue'
import conpanyDetail from '../views/work/conpanyDetail.vue'
import conpany from '../views/work/conpany.vue'
import checkCode from '../views/captureCode/captureCode.vue'
import chatGpt from '../views/captureCode/chatGpt.vue'
import reCharge from  '../views/captureCode/reCharge.vue'
import userSettingIndex from '../views/user/userSetting.vue'
import updatepw from '../views/user/updatepw.vue'
import avatar from '../views/user/avatar.vue'

import userDetailInfo from '../views/user/userDetailInfo.vue'
import updateUserInfo from '../views/user/updateUserInfo.vue'
import order from '../views/user/order.vue'
import content from '../views/user/content.vue'
import index from '../views/discuss/index.vue'
import obedient from '../views/discuss/obedient.vue'
import obedientDetail from '../views/discuss/obedientDetail.vue'

Vue.use(VueRouter)

const routes = [

  { path: '/',
  name: 'index',
  component: work},
  {
    path: '/login',
    name: 'Login',
    component: Login
 },
 {
  path: '/register',
  name: 'Register',
  component: Register
},
{
  path: '/work',
  name: 'work',
  component: work,
  childern:[
    // {
    //   path:'position',
    //   name:'position',
    //   component:workDetile
    // },
  ]
},
{
  path: '/indexWork',
  name: 'indexWork',
  component: indexWork
},
{
  path:'/work/position/:jobID',
  name:'position',
  component:workDetile
},
{
  path:'/work/conpanyDetail/:conpanyid/:rate',
  name:'conpanyDetail',
  component:conpanyDetail
},
{
  path:'/work/conpany',
  name:'conpany',
  component:conpany
},
{
  path:'/checkCode',
  name:'checkCode',
  component:checkCode
},
{
  path:'/chatGpt',
  name:'chatGpt',
  component:chatGpt
},
{
  path:'/reCharge',
  name:'reCharge',
  component:reCharge
},
{
  path:'/userSettingIndex',
  name:'userSettingIndex',
  component:userSettingIndex,
  children:[
    {
      path:'updatepw/:userID',
      name:'updatepw',
      component:updatepw
    },
    {
      path:'avatar/:userID',
      name:'avatar',
      component:avatar
    },
   {
      path:'userDetailInfo/:userID',
      name:'userDetailInfo',
      component:userDetailInfo
    },
    {
      path:'updateUserInfo/:userID',
      name:'updateUserInfo',
      component:updateUserInfo
    },
    {
      path:'order/:model/:userID',
      name:'order',
      component:order
    },
    {
      path:'content/:userID',
      name:'content',
      component:content
    },
  ]
}
,
{
  path:'/index',
  name:'index',
  component:index
},{
  path:'/obedient/:classificationID',
  name:'obedient',
  component:obedient
},
{
  path:'/obedientDetail/:obedientID',
  name:'obedientDetail',
  component:obedientDetail
},
// {updateUserInfo
//   path: '/updatepw',
//   name: 'updatepw',
//   component: updatepw
// },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  routes
})

export default router
