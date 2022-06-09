import Vue from 'vue'
import VueRouter from 'vue-router'

// 메인페이지 (임지훈)
import HomeView from '../views/Home.vue'
import MemberJoinPage2 from '../views/member/MemberJoinPage2.vue'
import MemberJoinPage from '../views/member/MemberJoinPage.vue'

// admin
import AdminPage from '@/views/AdminPage.vue'

// performance
import PerformanceRegisterPage from '@/views/performance/PerformanceRegisterPage.vue'
import PerformanceReadPage from '@/views/performance/PerformanceReadPage.vue'
import PerformanceListPage from '@/views/performance/PerformanceListPage.vue'
import PerformanceModifyPage from '@/views/performance/PerformanceModifyPage.vue'

// map
import MapPage from '@/views/map/MapPage.vue'

Vue.use(VueRouter)

const routes = [
  // 메인페이지 (임지훈)
  {
    path: '/',
    name: 'home',
    component: HomeView
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
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
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
    component: MapPage,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
