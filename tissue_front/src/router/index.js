import Vue from 'vue'
import VueRouter from 'vue-router'
// 메인페이지 (임지훈)
import Home from '../views/Home.vue'
import hallTest from '@/components/Hall/HallTest.vue'
import HallRegisterPage from '@/views/hall/HallRegisterPage.vue'
import HallListPage from '@/views/hall/HallListPage.vue'
import HallReadPage from '@/views/hall/HallReadPage.vue'


import MemberJoinPage2 from '../views/member/MemberJoinPage2.vue'
import MemberJoinPage from '../views/member/MemberJoinPage.vue'
import MemberLoginPage from '../views/member/MemberLoginPage.vue'
import MemberFindIdPage from '../views/member/MemberFindIdPage.vue'
import MemberFindPwPage from '../views/member/MemberFindPwPage.vue'


Vue.use(VueRouter)

const routes = [
  // 메인페이지 (임지훈)
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/hallTest',
    name: 'hallTest',
    component: hallTest
  },
  {
    path: '/hallRegister',
    name: 'HallRegisterPage',
    component: HallRegisterPage
  },
  {
    path: '/hallList',
    name: 'HallListPage',
    component: HallListPage
  },
  {
    path: '/hallRead/:hallNo',
    name: 'HallReadPage',
    components: {
      default: HallReadPage
    },
    props: {
      default: true
    }
  },

  // 메인페이지 (임지훈)

  // 유아림
  {
    path: '/join',
    name: 'MemberJoinPage',
    component: MemberJoinPage,
  },
  {
    path: '/join/form',
    name: 'MemberJoinPage2',
    component: MemberJoinPage2,
    },
    {
        path: '/login',
        name: 'MemberLoginPage',
        component:MemberLoginPage
    },
    {
        path: '/findId',
        name: 'MemberFindIdPage',
        component:MemberFindIdPage
    },
    {
        path: '/findPw',
        name: 'MemberFindPwPage',
        component:MemberFindPwPage
    }
  // 유아림
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
