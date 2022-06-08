import Vue from 'vue'
import VueRouter from 'vue-router'
// 메인페이지 (임지훈)
import HomeView from '../views/Home.vue'
import hallTest from '@/components/Hall/HallTest.vue'

import MemberJoinPage2 from '../views/member/MemberJoinPage2.vue'
import MemberJoinPage from '../views/member/MemberJoinPage.vue'
Vue.use(VueRouter)

const routes = [
  // 메인페이지 (임지훈)
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/hallTest',
    name: 'hallTest',
    component: hallTest
  },
  // 메인페이지 (임지훈)
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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
