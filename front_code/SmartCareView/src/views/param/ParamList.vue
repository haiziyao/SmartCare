<template>
  <div class="param-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>参数数据列表</span>
          <el-button type="primary" @click="handleAdd">新增数据</el-button>
        </div>
      </template>

      <el-form :inline="true" :model="queryForm" class="search-form">
        <el-form-item label="病人ID">
          <el-input-number v-model="queryForm.patientId" :min="0" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="patientId" label="病人ID" />
        <el-table-column prop="equipmentId" label="设备ID" />
        <el-table-column prop="windowStartTime" label="开始时间" />
        <el-table-column prop="windowEndTime" label="结束时间" />
        <el-table-column prop="hrAvg" label="心率平均值" />
        <el-table-column prop="rrAvg" label="呼吸率平均值" />
        <el-table-column prop="healthScore" label="健康评分" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
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
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { paramApi } from '../../api'

export default {
  name: 'ParamList',
  setup() {
    const loading = ref(false)
    const tableData = ref([])
    const queryForm = reactive({
      patientId: null
    })

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const loadData = async () => {
      loading.value = true
      try {
        const params = {
          current: pagination.current,
          size: pagination.size,
          ...(queryForm.patientId && { patientId: queryForm.patientId })
        }
        const res = await paramApi.getPage(params)
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

    const handleSearch = () => {
      pagination.current = 1
      loadData()
    }

    const handleReset = () => {
      queryForm.patientId = null
      handleSearch()
    }

    const handleAdd = () => {
      ElMessage.info('新增功能待实现')
    }

    const handleDelete = () => {
      ElMessage.info('删除功能待实现')
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
      tableData,
      queryForm,
      pagination,
      handleSearch,
      handleReset,
      handleAdd,
      handleDelete,
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

.search-form {
  margin-bottom: 20px;
}
</style>


