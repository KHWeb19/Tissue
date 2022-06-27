import Vue from 'vue'
import VueRouter from 'vue-router'
// 메인페이지 (임지훈)
import Home from '../views/Home.vue'
import HallRegisterPage from '@/views/hall/HallRegisterPage.vue'
import HallListPage from '@/views/hall/HallListPage.vue'
import HallReadPage from '@/views/hall/HallReadPage.vue'

import TicketingPage from '@/views/Ticketing/TicketingPage.vue' 
import PerformanceTest from '@/views/performance/PerformanceTest.vue'

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
import MyPageView from '../views/member/myPage/MyPage.vue'
import AdminMember from '../components/Admin/AdminMember.vue'

import SearchPage from '../views/search/SearchPage.vue'

// admin


// performance
import PerformanceRegisterPage from '@/views/performance/PerformanceRegisterPage.vue'
import PerformanceReadPage from '@/views/performance/PerformanceReadPage.vue'
import PerformanceListPage from '@/views/performance/PerformanceListPage.vue'
import PerformanceModifyPage from '@/views/performance/PerformanceModifyPage.vue'

import NoticeRegisterPage from '../views/notice/NoticeRegisterPage.vue'
import NoticeListPage from '../views/notice/NoticeListPage.vue'
import NoticeReadPage from '../views/notice/NoticeReadPage.vue'
import NoticeModifyPage from '../views/notice/NoticeModifyPage.vue'


Vue.use(VueRouter)

const requireLogin = () => (to, from, next) => {
    let token = localStorage.getItem('token')
    if (token !== null) {
        return next()
    } else {
        alert('로그인이 필요한 서비스입니다.')
        router.push("/")
    }
} 

const routes = [
  // 메인페이지 (임지훈)
  {
    path: '/',
    name: 'home',
    component: Home
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
  {
    path: '/ticketing/:performNo',
    name: 'TicketingPage',
    components: {
      default: TicketingPage
  },
  props:{
      default: true
  }
  },
  {
    path: '/test',
    name: 'PerformanceTest',
    component: PerformanceTest
  },
  {
    path: '/Admin',
    name: 'AdminMember',
    component: AdminMember
  },
  {
    path: '/couponRegister',
    name: 'CouponRegisterPage',
    component: CouponRegisterPage
  },
  {
    path: '/couponList',
    name: 'CouponListPage',
    component: CouponListPage
  },
  {
    path: '/couponModify/:couponNo',
    name: 'CouponModifyPage',
    components: {
      default: CouponModifyPage
  },
  props:{
      default: true
  }
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
    {
        path: '/myPage',
        name: 'MyPageView',
        components: {
            default: MyPageView
        },
        props: {
            default: true
        },
        beforeEnter: requireLogin()
    },
    {
        path: '/myPage/modify',
        name: 'MyPageModify',
        components: {
            default: MyPageView
        },
        props: {
            default: true
        },
        beforeEnter: requireLogin()
    },
    {
        path: '/myPage/signOut',
        name: 'MyPageOut',
        components: {
            default: MyPageView
        },
        props: {
            default: true
        },
        beforeEnter: requireLogin()
    },
    {
        path: '/myPage/coupon',
        name: 'MyPageCoupon',
        component: MyPageView,
        beforeEnter: requireLogin()
    },
    {
        path: '/myPage/qna',
        name: 'MyPageQnA',
        component: MyPageView,
        beforeEnter: requireLogin()
    },
    {
        path: '/search/:keyword',
        name: 'SearchPage',
        components: {
            default: SearchPage
        },
        props: {
            default: true
        }
    },
  // 유아림

  // 공연장 (노서현)
 
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
  // 공연장 (노서현)
  
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
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
