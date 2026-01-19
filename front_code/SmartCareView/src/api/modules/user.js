import request from '../../utils/request'

/**
 * 用户管理API
 */
export const userApi = {
  /**
   * 新增或修改用户信息
   */
  saveOrUpdate(data) {
    return request.post('/user/saveOrUpdate', data)
  },

  /**
   * 根据用户id查询用户信息
   */
  getById(id) {
    return request.get(`/user/${id}`)
  },

  /**
   * 分页匹配查询用户信息
   */
  getPage(params) {
    return request.get('/user/page', { params })
  },

  /**
   * 根据用户id删除用户
   */
  removeById(id) {
    return request.delete('/user/removeById', { params: { id } })
  }
}


