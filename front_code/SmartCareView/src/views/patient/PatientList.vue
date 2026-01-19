<template>
  <div class="patient-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>病人管理</span>
          <el-button type="primary" @click="handleAdd">新增病人</el-button>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="patientName" label="病人姓名" />
        <el-table-column prop="gender" label="性别">
          <template #default="{ row }">
            {{ row.gender === 1 ? '男' : row.gender === 2 ? '女' : '未知' }}
          </template>
        </el-table-column>
        <el-table-column prop="birthDate" label="出生日期" />
        <el-table-column prop="phone" label="联系电话" />
        <el-table-column prop="roomId" label="房间ID" />
        <el-table-column prop="patientStatus" label="健康状态" />
        <el-table-column prop="eventGrade" label="风险等级" />
        <el-table-column label="操作" width="250" fixed="right">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
            <el-button type="success" size="small" @click="handleViewCaregivers(row)">监护人</el-button>
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
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="病人姓名" prop="patientName">
              <el-input v-model="formData.patientName" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender">
              <el-select v-model="formData.gender" placeholder="请选择">
                <el-option label="男" :value="1" />
                <el-option label="女" :value="2" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
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
          <el-col :span="12">
            <el-form-item label="身份证号" prop="idCard">
              <el-input v-model="formData.idCard" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="formData.phone" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="紧急联系人" prop="emergencyContactPhone">
              <el-input v-model="formData.emergencyContactPhone" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="家庭住址" prop="homeAddress">
          <el-input v-model="formData.homeAddress" />
        </el-form-item>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="房间ID" prop="roomId">
              <el-input-number v-model="formData.roomId" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备ID" prop="equipmentId">
              <el-input-number v-model="formData.equipmentId" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="formData.remark" type="textarea" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </template>
    </el-dialog>

    <!-- 监护人列表对话框 -->
    <el-dialog v-model="caregiverDialogVisible" title="监护人列表" width="800px">
      <el-table :data="caregiverList" border>
        <el-table-column prop="caregiverName" label="姓名" />
        <el-table-column prop="phone" label="电话" />
        <el-table-column prop="relationship" label="关系">
          <template #default="{ row }">
            {{ getRelationshipText(row.relationship) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button
              type="danger"
              size="small"
              @click="handleRemoveCaregiver(row)"
            >
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { patientApi, relationApi } from '../../api'

export default {
  name: 'PatientList',
  setup() {
    const loading = ref(false)
    const dialogVisible = ref(false)
    const caregiverDialogVisible = ref(false)
    const formRef = ref(null)
    const tableData = ref([])
    const caregiverList = ref([])
    const currentPatientId = ref(null)
    const formData = reactive({
      id: null,
      patientName: '',
      gender: null,
      birthDate: '',
      idCard: '',
      phone: '',
      emergencyContactPhone: '',
      homeAddress: '',
      roomId: null,
      equipmentId: null,
      staffId: null,
      equipmentStatus: null,
      patientStatus: null,
      eventGrade: null,
      remark: ''
    })

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const rules = {
      patientName: [{ required: true, message: '请输入病人姓名', trigger: 'blur' }]
    }

    const dialogTitle = ref('新增病人')

    const getRelationshipText = (type) => {
      const map = { 1: '父母', 2: '子女', 3: '配偶', 4: '亲属', 5: '其他' }
      return map[type] || '未知'
    }

    const loadData = async () => {
      loading.value = true
      try {
        const res = await patientApi.getPage({
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
      dialogTitle.value = '新增病人'
      Object.keys(formData).forEach(key => {
        if (typeof formData[key] === 'string') {
          formData[key] = ''
        } else {
          formData[key] = null
        }
      })
      formData.id = null
      dialogVisible.value = true
    }

    const handleEdit = async (row) => {
      dialogTitle.value = '编辑病人'
      try {
        const res = await patientApi.getById(row.id)
        if (res.code === 200 || !res.code) {
          Object.assign(formData, res.data || row)
          dialogVisible.value = true
        }
      } catch (error) {
        ElMessage.error('获取病人信息失败')
      }
    }

    const handleViewCaregivers = async (row) => {
      currentPatientId.value = row.id
      try {
        const res = await relationApi.listCaregiverByPatientId(row.id)
        if (res.code === 200 || !res.code) {
          caregiverList.value = Array.isArray(res.data) ? res.data : []
          caregiverDialogVisible.value = true
        }
      } catch (error) {
        ElMessage.error('获取监护人列表失败')
      }
    }

    const handleRemoveCaregiver = (row) => {
      ElMessageBox.confirm('确定要移除该监护人吗？', '提示', {
        type: 'warning'
      })
        .then(async () => {
          try {
            await relationApi.remove(currentPatientId.value, row.id)
            ElMessage.success('移除成功')
            handleViewCaregivers({ id: currentPatientId.value })
          } catch (error) {
            ElMessage.error('移除失败')
          }
        })
        .catch(() => {})
    }

    const handleDelete = (row) => {
      ElMessageBox.confirm('确定要删除该病人吗？', '提示', {
        type: 'warning'
      })
        .then(async () => {
          try {
            await patientApi.removeById(row.id)
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
            await patientApi.saveOrUpdate(formData)
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
      caregiverDialogVisible,
      formRef,
      tableData,
      caregiverList,
      formData,
      pagination,
      rules,
      dialogTitle,
      getRelationshipText,
      handleAdd,
      handleEdit,
      handleViewCaregivers,
      handleRemoveCaregiver,
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


