import Vue from 'vue'
import VueRouter from 'vue-router'

import MemberJoinPage2 from '../views/member/MemberJoinPage2.vue'
import MemberJoinPage from '../views/member/MemberJoinPage.vue'


Vue.use(VueRouter)

const routes = [
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
