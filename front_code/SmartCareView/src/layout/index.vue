<template>
  <el-container class="layout-container">
    <el-aside :width="isCollapse ? '64px' : '200px'" class="sidebar-container">
      <div class="logo">
        <h1 v-if="!isCollapse">{{ appTitle }}</h1>
        <h1 v-else>S</h1>
      </div>
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :collapse-transition="false"
        router
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
      >
        <template v-for="route in routes" :key="route.path">
          <el-menu-item v-if="!route.children || route.children.length === 1" :index="route.children ? route.children[0].path : route.path">
            <el-icon><component :is="route.meta?.icon || 'Menu'" /></el-icon>
            <template #title>{{ route.meta?.title || route.children?.[0]?.meta?.title }}</template>
          </el-menu-item>
          <el-sub-menu v-else :index="route.path">
            <template #title>
              <el-icon><component :is="route.meta?.icon || 'Menu'" /></el-icon>
              <span>{{ route.meta?.title }}</span>
            </template>
            <el-menu-item v-for="child in route.children" :key="child.path" :index="child.path">
              {{ child.meta?.title }}
            </el-menu-item>
          </el-sub-menu>
        </template>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="navbar">
        <div class="navbar-left">
          <el-icon class="collapse-icon" @click="toggleCollapse">
            <component :is="isCollapse ? 'Expand' : 'Fold'" />
          </el-icon>
        </div>
        <div class="navbar-right">
          <span>管理员</span>
        </div>
      </el-header>
      <el-main class="app-main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import {
  HomeFilled,
  User,
  House,
  UserFilled,
  Avatar,
  DataAnalysis,
  Warning,
  Menu,
  Expand,
  Fold
} from '@element-plus/icons-vue'

export default {
  name: 'AppLayout',
  components: {
    HomeFilled,
    User,
    House,
    UserFilled,
    Avatar,
    DataAnalysis,
    Warning,
    Menu,
    Expand,
    Fold
  },
  setup() {
    const route = useRoute()
    const isCollapse = ref(false)
    const appTitle = process.env.VUE_APP_TITLE || 'SmartCare管理系统'

    const routes = [
      {
        path: '/dashboard',
        meta: { title: '首页', icon: 'HomeFilled' }
      },
      {
        path: '/user',
        meta: { title: '用户管理', icon: 'User' },
        children: [{ path: '/user/list', meta: { title: '用户列表' } }]
      },
      {
        path: '/room',
        meta: { title: '房间管理', icon: 'House' },
        children: [{ path: '/room/list', meta: { title: '房间列表' } }]
      },
      {
        path: '/patient',
        meta: { title: '病人管理', icon: 'UserFilled' },
        children: [{ path: '/patient/list', meta: { title: '病人列表' } }]
      },
      {
        path: '/caregiver',
        meta: { title: '监护人管理', icon: 'Avatar' },
        children: [{ path: '/caregiver/list', meta: { title: '监护人列表' } }]
      },
      {
        path: '/param',
        meta: { title: '参数数据管理', icon: 'DataAnalysis' },
        children: [
          { path: '/param/list', meta: { title: '参数数据列表' } },
          { path: '/param/day', meta: { title: '日均统计' } },
          { path: '/param/week', meta: { title: '周均统计' } },
          { path: '/param/month', meta: { title: '月均统计' } },
          { path: '/param/year', meta: { title: '年均统计' } }
        ]
      },
      {
        path: '/event',
        meta: { title: '事件管理', icon: 'Warning' },
        children: [{ path: '/event/list', meta: { title: '事件列表' } }]
      }
    ]

    const activeMenu = computed(() => {
      const { path } = route
      if (path.startsWith('/param')) {
        return path
      }
      return path
    })

    const toggleCollapse = () => {
      isCollapse.value = !isCollapse.value
    }

    return {
      isCollapse,
      appTitle,
      routes,
      activeMenu,
      toggleCollapse
    }
  }
}
</script>

<style scoped>
.layout-container {
  height: 100vh;
}

.sidebar-container {
  background-color: #304156;
  transition: width 0.28s;
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  background-color: #2b2f3a;
  color: #fff;
}

.logo h1 {
  margin: 0;
  font-size: 18px;
  font-weight: 600;
}

.navbar {
  background-color: #fff;
  border-bottom: 1px solid #e6e6e6;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
}

.navbar-left {
  display: flex;
  align-items: center;
}

.collapse-icon {
  font-size: 20px;
  cursor: pointer;
  margin-right: 20px;
}

.navbar-right {
  display: flex;
  align-items: center;
}

.app-main {
  background-color: #f0f2f5;
  padding: 20px;
}
</style>

