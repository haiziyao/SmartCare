<template>
  <div class="user-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>用户管理</span>
          <el-button type="primary" @click="handleAdd">新增用户</el-button>
        </div>
      </template>

      <el-form :inline="true" :model="queryForm" class="search-form">
        <el-form-item label="用户ID">
          <el-input v-model="queryForm.id" placeholder="请输入用户ID" clearable />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="queryForm.username" placeholder="请输入用户名" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="userType" label="用户类型">
          <template #default="{ row }">
            {{ getUserTypeText(row.userType) }}
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="status" label="状态">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ getStatusText(row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="lastLoginTime" label="最后登录时间" />
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

    <!-- 新增/编辑对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="600px"
      @close="handleDialogClose"
    >
      <el-form :model="formData" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="formData.username" />
        </el-form-item>
        <el-form-item label="密码" prop="passwordHash" v-if="!formData.id">
          <el-input v-model="formData.passwordHash" type="password" />
        </el-form-item>
        <el-form-item label="用户类型" prop="userType">
          <el-select v-model="formData.userType" placeholder="请选择">
            <el-option label="患者" :value="1" />
            <el-option label="家属" :value="2" />
            <el-option label="护工" :value="3" />
            <el-option label="医护人员" :value="4" />
            <el-option label="系统管理员" :value="9" />
          </el-select>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formData.phone" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="formData.status" placeholder="请选择">
            <el-option label="正常" :value="1" />
            <el-option label="禁用" :value="2" />
            <el-option label="冻结" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="关联病人ID" prop="patientId">
          <el-input-number v-model="formData.patientId" :min="0" />
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
import { userApi } from '../../api'

export default {
  name: 'UserList',
  setup() {
    const loading = ref(false)
    const dialogVisible = ref(false)
    const formRef = ref(null)
    const tableData = ref([])
    const queryForm = reactive({
      id: '',
      username: ''
    })
    const formData = reactive({
      id: null,
      username: '',
      passwordHash: '',
      userType: null,
      phone: '',
      email: '',
      status: 1,
      patientId: null
    })

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const rules = {
      username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      userType: [{ required: true, message: '请选择用户类型', trigger: 'change' }]
    }

    const getUserTypeText = (type) => {
      const map = { 1: '患者', 2: '家属', 3: '护工', 4: '医护人员', 9: '系统管理员' }
      return map[type] || '未知'
    }

    const getStatusText = (status) => {
      const map = { 1: '正常', 2: '禁用', 3: '冻结' }
      return map[status] || '未知'
    }

    const dialogTitle = ref('新增用户')

    const loadData = async () => {
      loading.value = true
      try {
        const params = {
          current: pagination.current,
          size: pagination.size,
          ...(queryForm.id && { id: queryForm.id }),
          ...(queryForm.username && { username: queryForm.username })
        }
        const res = await userApi.getPage(params)
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
      queryForm.id = ''
      queryForm.username = ''
      handleSearch()
    }

    const handleAdd = () => {
      dialogTitle.value = '新增用户'
      Object.assign(formData, {
        id: null,
        username: '',
        passwordHash: '',
        userType: null,
        phone: '',
        email: '',
        status: 1,
        patientId: null
      })
      dialogVisible.value = true
    }

    const handleEdit = async (row) => {
      dialogTitle.value = '编辑用户'
      try {
        const res = await userApi.getById(row.id)
        if (res.code === 200 || !res.code) {
          Object.assign(formData, res.data || row)
          dialogVisible.value = true
        }
      } catch (error) {
        ElMessage.error('获取用户信息失败')
      }
    }

    const handleDelete = (row) => {
      ElMessageBox.confirm('确定要删除该用户吗？', '提示', {
        type: 'warning'
      })
        .then(async () => {
          try {
            await userApi.removeById(row.id)
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
            await userApi.saveOrUpdate(formData)
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
      queryForm,
      formData,
      pagination,
      rules,
      getUserTypeText,
      getStatusText,
      handleSearch,
      handleReset,
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
.user-list {
  padding: 0;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-form {
  margin-bottom: 20px;
}
</style>


