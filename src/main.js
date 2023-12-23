import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI)

import {Message} from "element-ui"
Vue.prototype.$message = Message

import router from '@/router/index'
import axios from "@/axios/index"

import moment from "moment";

Vue.prototype.$moment = function (timestamp, format = "YYYY-MM-DD HH:mm:ss"){
  return moment(timestamp).format(format)
}
Vue.prototype.$axios = axios.service
Vue.prototype.$baseURL = axios.baseURL

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
