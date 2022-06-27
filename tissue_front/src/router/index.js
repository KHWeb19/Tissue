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
import MyPageView from '../views/member/myPage/MyPage.vue'
import AdminMember from '../components/Admin/AdminMember.vue'

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

import QnaRegisterPage from '../views/qna/QnaRegisterPage.vue'
import QnaListPage from '../views/qna/QnaListPage.vue'
import QnaReadPage from '../views/qna/QnaReadPage.vue'
import QnaModifyPage from '../views/qna/QnaModifyPage.vue'

import QnaBestRegisterPage from '../views/qna/QnaBestRegisterPage.vue'
import QnaBestModifyPage from '../views/qna/QnaBestModifyPage.vue'



Vue.use(VueRouter)

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
    name: 'AdminMainPage',
    component: AdminMainPage
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
        }
    },
    {
        path: '/myPage/modify',
        name: 'MyPageModify',
        components: {
            default: MyPageView
        },
        props: {
            default: true
        }
    },
    {
        path: '/myPage/signOut',
        name: 'MyPageOut',
        components: {
            default: MyPageView
        },
        props: {
            default: true
        }
    },
    {
        path: '/myPage/coupon',
        name: 'MyPageCoupon',
        component: MyPageView
    },
    {
        path: '/Admin/member',
        name: 'AdminMember',
        component: AdminMember 
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
  {
    path: '/qnaRegister',
    name: 'QnaRegisterPage',
    component: QnaRegisterPage
  },
  {
    path: '/qnaList',
    name: 'QnaListPage',
    component: QnaListPage
  },
  {
    path: '/qnaRead/:qnaNo',
    name: 'QnaReadPage',
    components: {
      default: QnaReadPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/qnaModify/:qnaNo',
    name: 'QnaModifyPage',
    components: {
      default: QnaModifyPage
    },
    props: {
      default: true
    }
  },
  {
    path: '/qnaBestRegister',
    name: 'QnaBestRegisterPage',
    component: QnaBestRegisterPage
  },
  {
    path: '/qnaBestModify/:qnaBestNo',
    name: 'QnaBestModifyPage',
    components: {
      default: QnaBestModifyPage
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
