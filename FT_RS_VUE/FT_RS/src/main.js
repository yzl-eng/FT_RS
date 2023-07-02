import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from './plugins/axiosInstance.js'
import router from './router'
import { Axios } from 'axios'

import App from './App.vue'
const app = createApp(App)

app.config.globalProperties.$http = axios;
//Vue3.0中不支持Vue.prototype的方式来挂载axios了，改用上面的方法挂载

import pinia from './store/index'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'


pinia.use(piniaPluginPersistedstate)
app.use(pinia)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.use(router);
app.use(ElementPlus, { size: 'large', zIndex: 3000 })
app.mount('#app')
