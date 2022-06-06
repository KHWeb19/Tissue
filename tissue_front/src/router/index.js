import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

// performance
import PerformanceRegisterPage from '@/views/performance/PerformanceRegisterPage.vue'
import PerformanceReadPage from '@/views/performance/PerformanceReadPage.vue'
import PerformanceListPage from '@/views/performance/PerformanceListPage.vue'
import PerformanceModifyPage from '@/views/performance/PerformanceModifyPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
