import request from '../../utils/request'

/**
 * 病人管理API
 */
export const patientApi = {
  /**
   * 新增或修改病人信息
   */
  saveOrUpdate(data) {
    return request.post('/patient/saveOrUpdate', data)
  },

  /**
   * 根据病人id查询病人信息
   */
  getById(id) {
    return request.get(`/patient/${id}`)
  },

  /**
   * 分页查询病人信息
   */
  getPage(params) {
    return request.get('/patient/page', { params })
  },

  /**
   * 根据病人id删除病人信息
   */
  removeById(id) {
    return request.delete('/patient/removeById', { params: { id } })
  }
}


