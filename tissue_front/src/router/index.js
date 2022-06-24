import Vue from 'vue'
import VueRouter from 'vue-router'
// 메인페이지 (임지훈)
import Home from '../views/Home.vue'

import HallRegisterPage from '@/views/hall/HallRegisterPage.vue'
import HallListPage from '@/views/hall/HallListPage.vue'
import HallReadPage from '@/views/hall/HallReadPage.vue'

import TicketingPage from '@/views/Ticketing/TicketingPage.vue' 
import PerformanceTest from '@/views/performance/PerformanceTest.vue'

import AdminMainPage from '@/views/Admin/AdminMainPage.vue'
import CouponRegisterPage from '@/views/coupon/CouponRegisterPage.vue'
import CouponListPage from '@/views/coupon/CouponListPage.vue'
import CouponModifyPage from '@/views/coupon/CouponModifyPage.vue'

import EventMainPage from '@/views/event/EventMainPage.vue'

import MemberJoinPage2 from '../views/member/MemberJoinPage2.vue'
import MemberJoinPage from '../views/member/MemberJoinPage.vue'
import MemberLoginPage from '../views/member/MemberLoginPage.vue'
import MemberFindIdPage from '../views/member/MemberFindIdPage.vue'
import MemberFindPwPage from '../views/member/MemberFindPwPage.vue'
import KakaoOAuth from '../components/OAuth/KakaoOAuth.vue'
import GoogleOAuth from '../components/OAuth/GoogleOAuth.vue'


// admin
import AdminPage from '@/views/AdminPage.vue'

// performance
import PerformanceRegisterPage from '@/views/performance/PerformanceRegisterPage.vue'
import PerformanceReadPage from '@/views/performance/PerformanceReadPage.vue'
import PerformanceListPage from '@/views/performance/PerformanceListPage.vue'
import PerformanceModifyPage from '@/views/performance/PerformanceModifyPage.vue'
import MapPage from '@/views/map/MapPage.vue'

// event
import EventRegisterPage from '@/views/event/EventRegisterPage.vue'
import EventListPage from '@/views/event/EventListPage.vue'
import EventReadPage from '@/views/event/EventReadPage.vue'
import EventModifyPage from '@/views/event/EventModifyPage.vue'

import NoticeRegisterPage from '../views/notice/NoticeRegisterPage.vue'
import NoticeListPage from '../views/notice/NoticeListPage.vue'
import NoticeReadPage from '../views/notice/NoticeReadPage.vue'
import NoticeModifyPage from '../views/notice/NoticeModifyPage.vue'


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
    name: 'HallRegister',
    component: HallRegister
  },
  {
    path: '/event',
    name: 'EventMainPage',
    component: EventMainPage
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
},
{
    path: '/kakaoLogin',
    name:'KakaoOAuth',
    component:KakaoOAuth
},
{
    path: '/googleLogin',
    name: 'GoogleOAuth',
    component: GoogleOAuth
},
  // 유아림

  // 노서현
  {
    path: '/adminPage',
    name: 'AdminPage',
    component: AdminPage
  }, 
  {
    path: '/performanceRegisterPage',
    name: 'PerformanceRegisterPage',
    component: PerformanceRegisterPage
  },
  {
    path: '/performanceListPage',
    name: 'PerformanceListPage',
    component: PerformanceListPage
  },
  {
    path: '/performanceReadPage/:performNo',
    name: 'PerformanceReadPage',
    components: {
      default: PerformanceReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/performanceModifyPage/:performNo',
    name: 'PerformanceModifyPage',
    components: {
      default: PerformanceModifyPage
    },
     props: {
      default: true
     }
  },
  {
    path: '/mapPage',
    name: 'MapPage',
    component: MapPage
  },
  {
    path: '/eventRegisterPage',
    name: 'EventRegisterPage',
    component: EventRegisterPage
  },
  {
    path: '/eventListPage',
    name: 'EventListPage',
    component: EventListPage
  },
  {
    path: '/eventReadPage/:eventNo',
    name: 'EventReadPage',
    components: {
      default: EventReadPage
    },
     props: {
      default: true
     }
  },
  {
    path: '/eventModifyPage/:eventNo',
    name: 'EventModifyPage',
    components: {
      default: EventModifyPage
    },
     props: {
      default: true
     }
  },
  // 노서현
  
  {
    path: '/noticeRegister',
    name: 'NoticeRegisterPage',
    component: NoticeRegisterPage
  },
  {
    path: '/noticeList',
    name: 'NoticeListPage',
    component: NoticeListPage
  },
  {
    path: '/noticeRead/:noticeNo',
    name: 'NoticeReadPage',
    components: {
      default: NoticeReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/noticeModify/:noticeNo',
    name: 'NoticeModifyPage',
    components: {
      default: NoticeModifyPage
    },
    props: {
      default: true
    }
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
