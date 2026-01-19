# 故障排查指南

## 网络错误和加载失败问题

### 1. 检查后端服务是否启动

确保后端服务正在运行：
- 后端地址：`http://localhost:8080`
- 在浏览器中访问 `http://localhost:8080` 检查服务是否可访问

### 2. 跨域问题（CORS）

如果遇到跨域错误，有两种解决方案：

#### 方案一：使用 Vue CLI 代理（推荐）

已经在 `vue.config.js` 中配置了代理。如果需要使用代理，请：

1. 修改 `src/config/index.js`，将 `baseURL` 改为相对路径或使用代理路径
2. 或者修改 `src/utils/request.js` 中的 `baseURL`

#### 方案二：后端配置 CORS

在后端添加 CORS 支持，允许前端域名访问。

### 3. API 响应格式问题

如果后端返回的数据格式不是标准的 `{code, message, data}` 格式，响应拦截器会自动处理。

### 4. 常见错误信息

- **网络错误，无法连接到服务器**
  - 检查后端服务是否启动
  - 检查 `http://localhost:8080` 是否可访问
  - 检查防火墙设置

- **请求超时**
  - 检查网络连接
  - 增加超时时间（修改 `.env.development` 中的 `VUE_APP_TIMEOUT`）

- **404 错误**
  - 检查 API 路径是否正确
  - 检查后端路由配置

- **500 错误**
  - 检查后端服务日志
  - 检查后端代码是否有错误

### 5. 调试步骤

1. 打开浏览器开发者工具（F12）
2. 查看 Console 标签页的错误信息
3. 查看 Network 标签页，检查请求是否发送成功
4. 检查请求的 URL、方法、请求头和响应内容

### 6. 测试 API 连接

可以在浏览器控制台运行以下代码测试 API：

```javascript
// 测试 API 连接
fetch('http://localhost:8080/user/page?current=1&size=10')
  .then(response => response.json())
  .then(data => console.log('API 响应:', data))
  .catch(error => console.error('API 错误:', error))
```

### 7. 环境变量检查

确保 `.env.development` 文件存在且配置正确：
```
VUE_APP_BASE_API=http://localhost:8080
VUE_APP_TIMEOUT=10000
VUE_APP_TITLE=SmartCare管理系统
```

### 8. 清除缓存并重启

```bash
# 清除缓存
rm -rf node_modules/.cache
# Windows PowerShell
Remove-Item -Recurse -Force node_modules\.cache

# 重启开发服务器
npm run serve
```

