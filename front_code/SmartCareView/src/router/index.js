import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/index.vue'

const routes = [
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/Dashboard.vue'),
        meta: { title: '首页', icon: 'HomeFilled' }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    meta: { title: '用户管理', icon: 'User' },
    children: [
      {
        path: 'list',
        name: 'UserList',
        component: () => import('../views/user/UserList.vue'),
        meta: { title: '用户列表' }
      }
    ]
  },
  {
    path: '/room',
    component: Layout,
    meta: { title: '房间管理', icon: 'House' },
    children: [
      {
        path: 'list',
        name: 'RoomList',
        component: () => import('../views/room/RoomList.vue'),
        meta: { title: '房间列表' }
      }
    ]
  },
  {
    path: '/patient',
    component: Layout,
    meta: { title: '病人管理', icon: 'UserFilled' },
    children: [
      {
        path: 'list',
        name: 'PatientList',
        component: () => import('../views/patient/PatientList.vue'),
        meta: { title: '病人列表' }
      }
    ]
  },
  {
    path: '/caregiver',
    component: Layout,
    meta: { title: '监护人管理', icon: 'Avatar' },
    children: [
      {
        path: 'list',
        name: 'CaregiverList',
        component: () => import('../views/caregiver/CaregiverList.vue'),
        meta: { title: '监护人列表' }
      }
    ]
  },
  {
    path: '/param',
    component: Layout,
    meta: { title: '参数数据管理', icon: 'DataAnalysis' },
    children: [
      {
        path: 'list',
        name: 'ParamList',
        component: () => import('../views/param/ParamList.vue'),
        meta: { title: '参数数据列表' }
      },
      {
        path: 'day',
        name: 'DayParam',
        component: () => import('../views/param/DayParam.vue'),
        meta: { title: '日均统计' }
      },
      {
        path: 'week',
        name: 'WeekParam',
        component: () => import('../views/param/WeekParam.vue'),
        meta: { title: '周均统计' }
      },
      {
        path: 'month',
        name: 'MonthParam',
        component: () => import('../views/param/MonthParam.vue'),
        meta: { title: '月均统计' }
      },
      {
        path: 'year',
        name: 'YearParam',
        component: () => import('../views/param/YearParam.vue'),
        meta: { title: '年均统计' }
      }
    ]
  },
  {
    path: '/event',
    component: Layout,
    meta: { title: '事件管理', icon: 'Warning' },
    children: [
      {
        path: 'list',
        name: 'EventList',
        component: () => import('../views/event/EventList.vue'),
        meta: { title: '事件列表' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router


