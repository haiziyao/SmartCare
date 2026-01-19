<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6" v-for="item in stats" :key="item.title">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon" :style="{ backgroundColor: item.color }">
              <el-icon :size="30"><component :is="item.icon" /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ item.value }}</div>
              <div class="stat-title">{{ item.title }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref } from 'vue'
import { User, House, UserFilled, Warning } from '@element-plus/icons-vue'

export default {
  name: 'DashboardView',
  components: {
    User,
    House,
    UserFilled,
    Warning
  },
  setup() {
    const stats = ref([
      { title: '用户总数', value: 0, icon: 'User', color: '#409EFF' },
      { title: '房间总数', value: 0, icon: 'House', color: '#67C23A' },
      { title: '病人总数', value: 0, icon: 'UserFilled', color: '#E6A23C' },
      { title: '事件总数', value: 0, icon: 'Warning', color: '#F56C6C' }
    ])

    return {
      stats
    }
  }
}
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.stat-card {
  margin-bottom: 20px;
}

.stat-content {
  display: flex;
  align-items: center;
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  margin-right: 20px;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 28px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 8px;
}

.stat-title {
  font-size: 14px;
  color: #909399;
}
</style>

