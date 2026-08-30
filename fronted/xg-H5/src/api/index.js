import request from '../utils/request'

// 报名接口
export const apply = (data) => {
  return request.post('/student/apply', data)
}

// 获取专业列表
export const getSpecialtyList = () => {
  return request.get('/specialty/list')
}
// 获取问题列表
export function questionList() {
  return request.get('/question/list')
}

// 获取所有荣誉
export function honorList() {
  return request.get('/honor/list')
}
// 获取所有技术方向
export function techDirectionList() {
  return request.get('/tech-direction/list')
}
// 获取所有配置
export function configList() {
  return request.get('/config/list')
}
