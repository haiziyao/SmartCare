<template>
  <div class="room-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>房间管理</span>
          <el-button type="primary" @click="handleAdd">新增房间</el-button>
        </div>
      </template>

      <el-table :data="tableData" v-loading="loading" border>
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="roomCode" label="房间编号" />
        <el-table-column prop="roomName" label="房间名称" />
        <el-table-column prop="roomType" label="房间类型" />
        <el-table-column prop="maxSize" label="最大容纳数" />
        <el-table-column prop="usedSize" label="当前占用数" />
        <el-table-column prop="roomStatus" label="房间状态" />
        <el-table-column prop="remark" label="备注" />
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
      width="600px"
      @close="handleDialogClose"
    >
      <el-form :model="formData" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="房间编号" prop="roomCode">
          <el-input v-model="formData.roomCode" />
        </el-form-item>
        <el-form-item label="房间名称" prop="roomName">
          <el-input v-model="formData.roomName" />
        </el-form-item>
        <el-form-item label="房间类型" prop="roomType">
          <el-input-number v-model="formData.roomType" :min="0" />
        </el-form-item>
        <el-form-item label="最大容纳数" prop="maxSize">
          <el-input-number v-model="formData.maxSize" :min="0" />
        </el-form-item>
        <el-form-item label="当前占用数" prop="usedSize">
          <el-input-number v-model="formData.usedSize" :min="0" />
        </el-form-item>
        <el-form-item label="房间状态" prop="roomStatus">
          <el-input-number v-model="formData.roomStatus" :min="0" />
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
import { roomApi } from '../../api'

export default {
  name: 'RoomList',
  setup() {
    const loading = ref(false)
    const dialogVisible = ref(false)
    const formRef = ref(null)
    const tableData = ref([])
    const formData = reactive({
      id: null,
      roomCode: '',
      roomName: '',
      roomType: null,
      maxSize: null,
      usedSize: null,
      roomStatus: null,
      remark: ''
    })

    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    const rules = {
      roomCode: [{ required: true, message: '请输入房间编号', trigger: 'blur' }],
      roomName: [{ required: true, message: '请输入房间名称', trigger: 'blur' }]
    }

    const dialogTitle = ref('新增房间')

    const loadData = async () => {
      loading.value = true
      try {
        const res = await roomApi.getPage({
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
      dialogTitle.value = '新增房间'
      Object.assign(formData, {
        id: null,
        roomCode: '',
        roomName: '',
        roomType: null,
        maxSize: null,
        usedSize: null,
        roomStatus: null,
        remark: ''
      })
      dialogVisible.value = true
    }

    const handleEdit = async (row) => {
      dialogTitle.value = '编辑房间'
      try {
        const res = await roomApi.getById(row.id)
        if (res.code === 200 || !res.code) {
          Object.assign(formData, res.data || row)
          dialogVisible.value = true
        }
      } catch (error) {
        ElMessage.error('获取房间信息失败')
      }
    }

    const handleDelete = (row) => {
      ElMessageBox.confirm('确定要删除该房间吗？', '提示', {
        type: 'warning'
      })
        .then(async () => {
          try {
            await roomApi.removeById(row.id)
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
            await roomApi.saveOrUpdate(formData)
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


