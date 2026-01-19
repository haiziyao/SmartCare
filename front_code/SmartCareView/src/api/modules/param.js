import request from '../../utils/request'

/**
 * 参数数据管理API
 */
export const paramApi = {
  /**
   * 新增一条数据记录
   */
  save(data) {
    return request.post('/param', data)
  },

  /**
   * 分页查询param记录
   */
  getPage(params) {
    return request.get('/param/page', { params })
  },

  /**
   * 分页查询YearAvgParam
   */
  getYearPage(params) {
    return request.get('/param/year/page', { params })
  },

  /**
   * 分页查询MonthAvgParam
   */
  getMonthPage(params) {
    return request.get('/param/month/page', { params })
  },

  /**
   * 分页查询WeekAvgParam
   */
  getWeekPage(params) {
    return request.get('/param/week/page', { params })
  },

  /**
   * 分页查询DayAvgParam
   */
  getDayPage(params) {
    return request.get('/param/day/page', { params })
  },

  /**
   * 手动进行执行year数据总结
   */
  calYear(keyword, date) {
    return request.get('/param/data/year', {
      params: { keyword, date }
    })
  },

  /**
   * 手动进行执行month数据总结
   */
  calMonth(keyword, date) {
    return request.get('/param/data/month', {
      params: { keyword, date }
    })
  },

  /**
   * 手动进行执行week数据总结
   */
  calWeek(keyword, date) {
    return request.get('/param/data/week', {
      params: { keyword, date }
    })
  },

  /**
   * 手动进行执行day数据总结
   */
  calDay(keyword, date) {
    return request.get('/param/data/day', {
      params: { keyword, date }
    })
  }
}


