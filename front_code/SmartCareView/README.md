# SmartCare 管理系统

基于 Vue 3 + Element Plus 的后台管理系统

## 功能模块

- ✅ 用户管理
- ✅ 房间管理
- ✅ 病人管理
- ✅ 监护人管理
- ✅ 病人与监护人关系管理
- ✅ 参数数据管理（原始数据、日均、周均、月均、年均统计）
- ✅ 事件管理

## 技术栈

- Vue 3.2.13
- Vue Router 4.2.5
- Element Plus 2.4.4
- Axios 1.6.0

## 环境配置

### 创建环境变量文件

在项目根目录创建以下文件：

#### `.env.development` (开发环境)
```
VUE_APP_BASE_API=http://localhost:8080
VUE_APP_TIMEOUT=10000
VUE_APP_TITLE=SmartCare管理系统
```

#### `.env.production` (生产环境)
```
VUE_APP_BASE_API=http://your-production-api.com
VUE_APP_TIMEOUT=10000
VUE_APP_TITLE=SmartCare管理系统
```

## 安装依赖

```bash
npm install
```

## 开发运行

```bash
npm run serve
```

## 构建生产版本

```bash
npm run build
```

## 项目结构

```
src/
├── api/                    # API接口封装
│   ├── modules/           # 各模块API
│   │   ├── user.js       # 用户管理API
│   │   ├── room.js       # 房间管理API
│   │   ├── patient.js    # 病人管理API
│   │   ├── caregiver.js # 监护人管理API
│   │   ├── relation.js   # 关系管理API
│   │   ├── param.js      # 参数数据管理API
│   │   └── event.js      # 事件管理API
│   └── index.js          # API统一导出
├── assets/                # 静态资源
├── components/           # 公共组件
├── config/               # 配置文件
│   └── index.js         # 应用配置
├── layout/               # 布局组件
│   └── index.vue        # 主布局
├── router/               # 路由配置
│   └── index.js         # 路由定义
├── utils/               # 工具函数
│   └── request.js       # Axios请求封装
├── views/               # 页面组件
│   ├── Dashboard.vue   # 首页
│   ├── user/          # 用户管理页面
│   ├── room/          # 房间管理页面
│   ├── patient/       # 病人管理页面
│   ├── caregiver/     # 监护人管理页面
│   ├── param/         # 参数数据管理页面
│   └── event/         # 事件管理页面
├── App.vue            # 根组件
└── main.js            # 入口文件
```

## API接口说明

所有API接口严格按照 `api.json` 中的OpenAPI规范实现：

### 用户管理
- `POST /user/saveOrUpdate` - 新增或修改用户
- `GET /user/{id}` - 根据ID查询用户
- `GET /user/page` - 分页查询用户
- `DELETE /user/removeById` - 删除用户

### 房间管理
- `POST /room/saveOrUpdate` - 新增或修改房间
- `GET /room/{id}` - 根据ID查询房间
- `GET /room/page` - 分页查询房间
- `DELETE /room/removeById` - 删除房间

### 病人管理
- `POST /patient/saveOrUpdate` - 新增或修改病人
- `GET /patient/{id}` - 根据ID查询病人
- `GET /patient/page` - 分页查询病人
- `DELETE /patient/removeById` - 删除病人

### 监护人管理
- `POST /caregiver/saveOrUpdate` - 新增或修改监护人
- `GET /caregiver/{id}` - 根据ID查询监护人
- `GET /caregiver/page` - 分页查询监护人
- `DELETE /caregiver/removeById` - 删除监护人

### 关系管理
- `POST /relation/saveOrUpdate` - 新增或修改关系
- `GET /relation/patient/{id}` - 根据病人ID查询监护人
- `DELETE /relation/remove` - 删除关系

### 参数数据管理
- `POST /param` - 新增参数数据
- `GET /param/page` - 分页查询参数数据
- `GET /param/day/page` - 分页查询日均统计
- `GET /param/week/page` - 分页查询周均统计
- `GET /param/month/page` - 分页查询月均统计
- `GET /param/year/page` - 分页查询年均统计
- `GET /param/data/day` - 手动计算日均数据
- `GET /param/data/week` - 手动计算周均数据
- `GET /param/data/month` - 手动计算月均数据
- `GET /param/data/year` - 手动计算年均数据

### 事件管理
- `POST /event/save` - 新增事件
- `GET /event/{id}` - 根据病人ID查询事件
- `GET /event/page` - 分页查询事件

## 注意事项

1. 确保后端API服务已启动，默认地址为 `http://localhost:8080`
2. 如需修改API地址，请更新 `.env.development` 或 `.env.production` 文件
3. 所有API请求都通过 `src/utils/request.js` 统一处理，包含请求/响应拦截器
4. 错误处理已集成，会自动显示错误提示

## 开发规范

- 使用 Composition API (setup语法)
- 组件命名采用 PascalCase
- 文件命名采用 kebab-case
- API调用统一使用封装的模块方法
- 表单验证使用 Element Plus 的 rules
