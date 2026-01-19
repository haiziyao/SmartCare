<template>
  <div class="year-param">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>年均参数统计</span>
          <div>
            <el-input
              v-model="keyword"
              placeholder="请输入关键字"
              style="width: 200px; margin-right: 10px"
            />
            <el-date-picker
              v-model="date"
              type="date"
              placeholder="选择日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
              style="width: 200px; margin-right: 10px"
            />
            <el-button type="primary" @click="handleCalculate">手动计算</el-button>
          </div>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="patientId" label="病人ID" />
        <el-table-column prop="statYear" label="统计年份" />
        <el-table-column prop="hrAvg" label="心率平均值" />
        <el-table-column prop="hrDiffAvg" label="心率平均偏差(%)" />
        <el-table-column prop="rrAvg" label="呼吸率平均值" />
        <el-table-column prop="healthScoreAvg" label="健康评分平均值" />
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
  name: 'YearParam',
  setup() {
    const loading = ref(false)
    const tableData = ref([])
    const keyword = ref('')
    const date = ref('')

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const loadData = async () => {
      loading.value = true
      try {
        const res = await paramApi.getYearPage({
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

    const handleCalculate = async () => {
      if (!keyword.value) {
        ElMessage.warning('请输入关键字')
        return
      }
      try {
        await paramApi.calYear(keyword.value, date.value)
        ElMessage.success('计算成功')
        loadData()
      } catch (error) {
        ElMessage.error('计算失败')
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
      tableData,
      keyword,
      date,
      pagination,
      handleCalculate,
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


