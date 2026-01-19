<template>
  <div class="caregiver-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>监护人管理</span>
          <el-button type="primary" @click="handleAdd">新增监护人</el-button>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="caregiverName" label="姓名" />
        <el-table-column prop="gender" label="性别">
          <template #default="{ row }">
            {{ row.gender === 1 ? '男' : row.gender === 2 ? '女' : '未知' }}
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="联系电话" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="relationship" label="关系类型">
          <template #default="{ row }">
            {{ getRelationshipText(row.relationship) }}
          </template>
        </el-table-column>
        <el-table-column prop="notifyLevel" label="通知等级">
          <template #default="{ row }">
            {{ getNotifyLevelText(row.notifyLevel) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
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

    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="700px"
      @close="handleDialogClose"
    >
      <el-form :model="formData" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="监护人姓名" prop="caregiverName">
          <el-input v-model="formData.caregiverName" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select v-model="formData.gender" placeholder="请选择">
                <el-option label="男" :value="1" />
                <el-option label="女" :value="2" />
                <el-option label="未知" :value="0" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期" prop="birthDate">
              <el-date-picker
                v-model="formData.birthDate"
                type="datetime"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DDTHH:mm:ss"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="formData.idCard" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="formData.phone" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="formData.email" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="关系" prop="relationship">
              <el-select v-model="formData.relationship" placeholder="请选择">
                <el-option label="父母" :value="1" />
                <el-option label="子女" :value="2" />
                <el-option label="配偶" :value="3" />
                <el-option label="亲属" :value="4" />
                <el-option label="其他" :value="5" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="通知等级" prop="notifyLevel">
              <el-select v-model="formData.notifyLevel" placeholder="请选择">
                <el-option label="全部通知" :value="1" />
                <el-option label="仅中高风险" :value="2" />
                <el-option label="仅高风险" :value="3" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="家庭住址" prop="homeAddress">
          <el-input v-model="formData.homeAddress" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="formData.remark" type="textarea" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { caregiverApi } from '../../api'

export default {
  name: 'CaregiverList',
  setup() {
    const loading = ref(false)
    const dialogVisible = ref(false)
    const formRef = ref(null)
    const tableData = ref([])
    const formData = reactive({
      id: null,
      userId: null,
      caregiverName: '',
      gender: null,
      birthDate: '',
      idCard: '',
      relationship: null,
      phone: '',
      email: '',
      homeAddress: '',
      notifyLevel: 1,
      remark: ''
    })

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const rules = {
      caregiverName: [{ required: true, message: '请输入监护人姓名', trigger: 'blur' }]
    }

    const dialogTitle = ref('新增监护人')

    const getRelationshipText = (type) => {
      const map = { 1: '父母', 2: '子女', 3: '配偶', 4: '亲属', 5: '其他' }
      return map[type] || '未知'
    }

    const getNotifyLevelText = (level) => {
      const map = { 1: '全部通知', 2: '仅中高风险', 3: '仅高风险' }
      return map[level] || '未知'
    }

    const loadData = async () => {
      loading.value = true
      try {
        const res = await caregiverApi.getPage({
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
      dialogTitle.value = '新增监护人'
      Object.keys(formData).forEach(key => {
        if (typeof formData[key] === 'string') {
          formData[key] = ''
        } else {
          formData[key] = null
        }
      })
      formData.id = null
      formData.notifyLevel = 1
      dialogVisible.value = true
    }

    const handleEdit = async (row) => {
      dialogTitle.value = '编辑监护人'
      try {
        const res = await caregiverApi.getById(row.id)
        if (res.code === 200 || !res.code) {
          Object.assign(formData, res.data || row)
          dialogVisible.value = true
        }
      } catch (error) {
        ElMessage.error('获取监护人信息失败')
      }
    }

    const handleDelete = (row) => {
      ElMessageBox.confirm('确定要删除该监护人吗？', '提示', {
        type: 'warning'
      })
        .then(async () => {
          try {
            await caregiverApi.removeById(row.id)
            ElMessage.success('删除成功')
            loadData()
          } catch (error) {
            ElMessage.error('删除失败')
          }
        })
        .catch(() => {})
    }

    const handleSubmit = async () => {
      if (!formRef.value) return
      await formRef.value.validate(async (valid) => {
        if (valid) {
          try {
            await caregiverApi.saveOrUpdate(formData)
            ElMessage.success('操作成功')
            dialogVisible.value = false
            loadData()
          } catch (error) {
            ElMessage.error('操作失败')
          }
        }
      })
    }

    const handleDialogClose = () => {
      formRef.value?.resetFields()
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
      dialogVisible,
      dialogTitle,
      formRef,
      tableData,
      formData,
      pagination,
      rules,
      getRelationshipText,
      getNotifyLevelText,
      handleAdd,
      handleEdit,
      handleDelete,
      handleSubmit,
      handleDialogClose,
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


