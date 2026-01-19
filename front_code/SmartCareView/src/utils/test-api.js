// API 连接测试工具
// 在浏览器控制台运行: import('./utils/test-api.js').then(m => m.testAPI())

export async function testAPI() {
  console.log('开始测试 API 连接...')
  
  const baseURL = process.env.NODE_ENV === 'development' 
    ? '' 
    : (process.env.VUE_APP_BASE_API || 'http://localhost:8080')
  
  console.log('API Base URL:', baseURL || '使用代理')
  
  // 测试后端连接
  const testUrl = baseURL || 'http://localhost:8080'
  
  try {
    console.log('测试 1: 直接访问后端...')
    const directResponse = await fetch(`${testUrl}/user/page?current=1&size=10`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    console.log('直接访问结果:', {
      status: directResponse.status,
      statusText: directResponse.statusText,
      ok: directResponse.ok
    })
    
    if (directResponse.ok) {
      const data = await directResponse.json()
      console.log('直接访问数据:', data)
    }
  } catch (error) {
    console.error('直接访问失败:', error)
  }
  
  // 测试通过代理访问
  if (!baseURL) {
    try {
      console.log('测试 2: 通过代理访问...')
      const proxyResponse = await fetch('/user/page?current=1&size=10', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      })
      console.log('代理访问结果:', {
        status: proxyResponse.status,
        statusText: proxyResponse.statusText,
        ok: proxyResponse.ok
      })
      
      if (proxyResponse.ok) {
        const data = await proxyResponse.json()
        console.log('代理访问数据:', data)
      }
    } catch (error) {
      console.error('代理访问失败:', error)
    }
  }
  
  console.log('测试完成！请查看上面的结果。')
}

// 自动运行测试（仅在开发环境）
if (process.env.NODE_ENV === 'development' && typeof window !== 'undefined') {
  // 延迟执行，确保页面加载完成
  setTimeout(() => {
    console.log('提示: 运行 testAPI() 函数来测试 API 连接')
    window.testAPI = testAPI
  }, 1000)
}

