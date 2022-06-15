import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

import PerformanceRegisterPage from '../views/manager/performance/PerformanceRegisterPage.vue'
import PerformanceListPage from '../views/manager/performance/PerformanceListPage.vue'

import NoticeRegisterPage from '../views/notice/NoticeRegisterPage.vue'
import NoticeListPage from '../views/notice/NoticeListPage.vue'
import NoticeReadPage from '../views/notice/NoticeReadPage.vue'
import NoticeModifyPage from '../views/notice/NoticeModifyPage.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/performanceRegister',
    name: 'PerformanceRegisterPage',
    component: PerformanceRegisterPage
  },
  {
    path: '/performanceList',
    name: 'PerformanceListPage',
    component: PerformanceListPage
  },
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
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
