<template>
  <div class="event-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>事件管理</span>
          <el-button type="primary" @click="handleAdd">新增事件</el-button>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="patientId" label="病人ID" />
        <el-table-column prop="equipmentId" label="设备ID" />
        <el-table-column prop="occurTime" label="发生时间" />
        <el-table-column prop="eventType" label="事件类型">
          <template #default="{ row }">
            {{ getEventTypeText(row.eventType) }}
          </template>
        </el-table-column>
        <el-table-column prop="eventLevel" label="严重等级">
          <template #default="{ row }">
            <el-tag :type="getEventLevelType(row.eventLevel)">
              {{ getEventLevelText(row.eventLevel) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="heartRate" label="心率" />
        <el-table-column prop="respRate" label="呼吸率" />
        <el-table-column prop="handledStatus" label="处理状态">
          <template #default="{ row }">
            {{ getHandledStatusText(row.handledStatus) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleView(row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        v-model:current-page="pagination.current"
        v-model:page-size="pagination.size"
        :total="pagination.total"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        style="margin-top: 20px; justify-content: flex-end"
      />
    </el-card>

    <!-- 查看详情对话框 -->
    <el-dialog v-model="detailDialogVisible" title="事件详情" width="800px">
      <el-descriptions :column="2" border>
        <el-descriptions-item label="事件ID">{{ detailData.id }}</el-descriptions-item>
        <el-descriptions-item label="病人ID">{{ detailData.patientId }}</el-descriptions-item>
        <el-descriptions-item label="设备ID">{{ detailData.equipmentId }}</el-descriptions-item>
        <el-descriptions-item label="事件类型">{{ getEventTypeText(detailData.eventType) }}</el-descriptions-item>
        <el-descriptions-item label="严重等级">{{ getEventLevelText(detailData.eventLevel) }}</el-descriptions-item>
        <el-descriptions-item label="发生时间">{{ detailData.occurTime }}</el-descriptions-item>
        <el-descriptions-item label="上报时间">{{ detailData.reportTime }}</el-descriptions-item>
        <el-descriptions-item label="心率">{{ detailData.heartRate }}</el-descriptions-item>
        <el-descriptions-item label="呼吸率">{{ detailData.respRate }}</el-descriptions-item>
        <el-descriptions-item label="健康评分">{{ detailData.healthScore }}</el-descriptions-item>
        <el-descriptions-item label="处理状态">{{ getHandledStatusText(detailData.handledStatus) }}</el-descriptions-item>
        <el-descriptions-item label="事件描述" :span="2">{{ detailData.eventDetail }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { eventApi } from '../../api'

export default {
  name: 'EventList',
  setup() {
    const loading = ref(false)
    const detailDialogVisible = ref(false)
    const tableData = ref([])
    const detailData = ref({})

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const getEventTypeText = (type) => {
      const map = { 1: '心率异常', 2: '呼吸异常', 3: '离床' }
      return map[type] || '未知'
    }

    const getEventLevelText = (level) => {
      const map = { 1: '轻度', 2: '中度', 3: '重度', 4: '危急' }
      return map[level] || '未知'
    }

    const getEventLevelType = (level) => {
      const map = { 1: 'info', 2: 'warning', 3: 'danger', 4: 'danger' }
      return map[level] || 'info'
    }

    const getHandledStatusText = (status) => {
      const map = { 0: '未处理', 1: '已确认', 2: '已处理', 3: '已忽略' }
      return map[status] || '未知'
    }

    const loadData = async () => {
      loading.value = true
      try {
        const res = await eventApi.getPage({
          current: pagination.current,
          size: pagination.size
        })
        if (res.code === 200 || !res.code) {
          tableData.value = res.data?.records || []
          pagination.total = res.data?.total || 0
        }
      } catch (error) {
        ElMessage.error('加载数据失败')
      } finally {
        loading.value = false
      }
    }

    const handleAdd = () => {
      ElMessage.info('新增功能待实现')
    }

    const handleView = async (row) => {
      try {
        const res = await eventApi.listByPatientId(row.patientId)
        if (res.code === 200 || !res.code) {
          detailData.value = res.data || row
          detailDialogVisible.value = true
        }
      } catch (error) {
        detailData.value = row
        detailDialogVisible.value = true
      }
    }

    const handleSizeChange = () => {
      loadData()
    }

    const handleCurrentChange = () => {
      loadData()
    }

    onMounted(() => {
      loadData()
    })

    return {
      loading,
      detailDialogVisible,
      tableData,
      detailData,
      pagination,
      getEventTypeText,
      getEventLevelText,
      getEventLevelType,
      getHandledStatusText,
      handleAdd,
      handleView,
      handleSizeChange,
      handleCurrentChange
    }
  }
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>


