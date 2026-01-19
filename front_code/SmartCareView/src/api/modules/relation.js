import request from '../../utils/request'

/**
 * 病人与监护人关系管理API
 */
export const relationApi = {
  /**
   * 新增或修改关系
   */
  saveOrUpdate(data) {
    return request.post('/relation/saveOrUpdate', data)
  },

  /**
   * 根据病人id查询监护人信息
   */
  listCaregiverByPatientId(id) {
    return request.get(`/relation/patient/${id}`)
  },

  /**
   * 删除关系
   */
  remove(patientId, caregiverId) {
    return request.delete('/relation/remove', {
      params: { patientId, caregiverId }
    })
  }
}


