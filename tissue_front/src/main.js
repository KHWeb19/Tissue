import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import 'material-design-icons-iconfont/dist/material-design-icons.css' // [노서현] prepend-icon위해 추가 0613
import VueNaverMpas from 'vue-naver-maps' //[노서현] naver 추가 0613

Vue.config.productionTip = false

// [노서현] naver 추가 0613
Vue.use(VueNaverMpas, {
  clientID: 'szbziogkhu'
})

new Vue({
  router,
  store,
  vuetify,
  // [노서현] prepend-icon위해 추가 0613
  iconfont: 'fa',
  icons: { iconfont: 'md', },
  render: h => h(App)
}).$mount('#app')
