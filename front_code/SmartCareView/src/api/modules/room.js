import request from '../../utils/request'

/**
 * 房间管理API
 */
export const roomApi = {
  /**
   * 新增或修改房间信息
   */
  saveOrUpdate(data) {
    return request.post('/room/saveOrUpdate', data)
  },

  /**
   * 根据房间id查询房间信息
   */
  getById(id) {
    return request.get(`/room/${id}`)
  },

  /**
   * 分页查询房间信息
   */
  getPage(params) {
    return request.get('/room/page', { params })
  },

  /**
   * 根据房间id删除房间信息
   */
  removeById(id) {
    return request.delete('/room/removeById', { params: { id } })
  }
}


