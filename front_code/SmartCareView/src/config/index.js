// 应用配置
export default {
  // API基础地址
  // 统一使用 /api 前缀，开发环境通过 devServer 代理，生产环境通过 Nginx 代理
  baseURL: process.env.VUE_APP_BASE_API || '/api',
  
  // 请求超时时间
  timeout: parseInt(process.env.VUE_APP_TIMEOUT) || 10000,
  
  // 应用标题
  title: process.env.VUE_APP_TITLE || 'SmartCare管理系统'
}


