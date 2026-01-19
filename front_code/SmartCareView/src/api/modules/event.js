import request from '../../utils/request'

/**
 * 事件管理API
 */
export const eventApi = {
  /**
   * 新增报警信息
   */
  save(data) {
    return request.post('/event/save', data)
  },

  /**
   * 根据病人id查询事件
   */
  listByPatientId(id) {
    return request.get(`/event/${id}`)
  },

  /**
   * 分页查询事件信息
   */
  getPage(params) {
    return request.get('/event/page', { params })
  }
}


