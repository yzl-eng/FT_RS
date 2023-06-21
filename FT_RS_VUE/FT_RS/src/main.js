import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import axios from './plugins/axiosInstance.js'

import router from './router'

import App from './App.vue'

const app = createApp(App)
// app.config.globalProperties.$axios=axios

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.use(router);
app.use(ElementPlus, { size: 'large', zIndex: 3000 })
app.mount('#app')
