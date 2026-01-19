import axios from 'axios'
import { ElMessage } from 'element-plus'
import config from '../config'

// 创建axios实例
const service = axios.create({
  baseURL: config.baseURL,
  timeout: config.timeout,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 打印请求信息（开发环境）
    if (process.env.NODE_ENV === 'development') {
      console.log('请求:', {
        url: config.url,
        method: config.method,
        baseURL: config.baseURL,
        data: config.data,
        params: config.params
      })
    }
    
    // 可以在这里添加token等认证信息
    // const token = localStorage.getItem('token')
    // if (token) {
    //   config.headers.Authorization = `Bearer ${token}`
    // }
    return config
  },
  (error) => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    const res = response.data
    
    // 如果响应数据是字符串，尝试解析为JSON
    if (typeof res === 'string') {
      try {
        const parsed = JSON.parse(res)
        return parsed
      } catch (e) {
        // 如果解析失败，直接返回原始数据
        return res
      }
    }
    
    // 根据业务状态码处理（如果后端返回了 code 字段）
    if (res && typeof res === 'object' && res.code !== undefined && res.code !== 200 && res.code !== null) {
      ElMessage.error(res.message || '请求失败')
      return Promise.reject(new Error(res.message || '请求失败'))
    }
    
    // 直接返回响应数据
    return res
  },
  (error) => {
    console.error('响应错误:', error)
    console.error('错误详情:', {
      message: error.message,
      code: error.code,
      response: error.response,
      request: error.request,
      config: error.config
    })
    
    let message = '请求失败'
    
    if (error.response) {
      // 服务器返回了响应，但状态码不在 2xx 范围内
      switch (error.response.status) {
        case 401:
          message = '未授权，请重新登录'
          break
        case 403:
          message = '禁止访问'
          break
        case 404:
          message = '请求的资源不存在，请检查API地址'
          break
        case 500:
          message = '服务器错误'
          break
        default:
          message = error.response.data?.message || `请求失败: ${error.response.status}`
      }
    } else if (error.request) {
      // 请求已发出，但没有收到响应
      if (error.code === 'ECONNABORTED') {
        message = '请求超时，请检查网络连接或稍后重试'
      } else if (error.code === 'ERR_NETWORK') {
        message = '网络错误，无法连接到服务器。请确认后端服务已启动（http://localhost:8080）'
      } else {
        message = `网络错误: ${error.message || '请检查网络连接和后端服务状态'}`
      }
    } else {
      // 在设置请求时发生错误
      message = `请求配置错误: ${error.message}`
    }
    
    ElMessage.error(message)
    return Promise.reject(error)
  }
)

export default service

