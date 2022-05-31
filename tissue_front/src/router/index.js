import Vue from 'vue'
import VueRouter from 'vue-router'
// 메인페이지 (임지훈)
import HomeView from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  // 메인페이지 (임지훈)
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  // 메인페이지 (임지훈)
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
