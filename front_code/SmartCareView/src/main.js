import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 预加载 API 模块，确保它们被打包到主 bundle
import './api'

// 开发环境下打印配置信息
if (process.env.NODE_ENV === 'development') {
  console.log('=== 开发环境配置 ===')
  console.log('API Base URL:', process.env.VUE_APP_BASE_API || '使用代理')
  console.log('当前环境:', process.env.NODE_ENV)
}

const app = createApp(App)

// 注册所有图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(router)
app.use(ElementPlus)
app.mount('#app')
