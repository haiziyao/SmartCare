import request from '../../utils/request'

/**
 * 监护人管理API
 */
export const caregiverApi = {
  /**
   * 新增或修改监护人信息
   */
  saveOrUpdate(data) {
    return request.post('/caregiver/saveOrUpdate', data)
  },

  /**
   * 根据监护人id查询监护人信息
   */
  getById(id) {
    return request.get(`/caregiver/${id}`)
  },

  /**
   * 分页查询监护人信息
   */
  getPage(params) {
    return request.get('/caregiver/page', { params })
  },

  /**
   * 根据监护人id删除监护人
   */
  removeById(id) {
    return request.delete('/caregiver/removeById', { params: { id } })
  }
}


