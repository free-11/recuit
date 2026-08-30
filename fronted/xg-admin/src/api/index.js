import request from '../utils/request'

// 登录
export function login(data) {
  return request.post('/admin/login', data)
}

export function logout() {
  return request.get('/admin/logout')
}

// 获取报名列表
export function studentPage(params) {
  return request.get('/student/page', { params })
}
// 删除报名
export function deleteStudent(studentNum) {
  return request.delete(`/student/${studentNum}`)
}
// 全部导出数据
export function exportAll() {
  return request.get('/student/exportall', {
    responseType: 'blob'  // 告诉axios返回二进制文件
  })
}
// 选择学号导出数据
export function exportByNum(data) {
  return request.post('/student/export', data, {
    responseType: 'blob'  // 告诉axios返回二进制文件
  })
}
// 获取学院信息列表
export function collegeList() {
  return request.get('/college/list')
}
// 删除学院
export function deleteCollege(id) {
  return request.delete(`/college/${id}`)
}
// 新增学院
export function addCollege(data) {
  return request.post('/college', data)
}
// 修改学院
export function updateCollege(data) {
  return request.put('/college', data)
}
// 获取专业列表
export function majorList() {
  return request.get('/specialty/list')
}
// 新增专业
export function addMajor(data) {
  return request.post('/specialty', data)
}
// 编辑专业
export function updateMajor(data) {
  return request.put('/specialty', data)
}
// 删除专业
export function deleteMajor(id) {
  return request.delete(`/specialty/${id}`)
}
// 获取问题列表
export function questionList() {
  return request.get('/question/list')
}
// 新增问题
export function addQuestion(data) {
  return request.post('/question', data)
}
//修改问题
export function updateQuestion(data) {
  return request.put('/question', data)
}
// 删除问题
export function deleteQuestion(id) {
  return request.delete(`/question/${id}`)
}
// 获取荣誉列表
export function honorList() {
  return request.get('/honor/list')
}
// 新增荣誉
export function addHonor(data) {
  return request.post('/honor', data)
}
// 修改荣誉
export function updateHonor(data) {
  return request.put('/honor', data)
}
// 删除荣誉
export function deleteHonor(id) {
  return request.delete(`/honor/${id}`)
}

// 获取所有技术方向
export function techDirectionList() {
  return request.get('/tech-direction/list')
}
// 新增技术方向
export function addTechDirection(data) {
  return request.post('/tech-direction', data)
}
// 修改技术方向
export function updateTechDirection(data) {
  return request.put('/tech-direction', data)
}
// 删除技术方向
export function deleteTechDirection(id) {
  return request.delete(`/tech-direction/${id}`)
}
// 获取所有配置
export function configList() {
  return request.get('/config/list')
}
// 编辑配置
export function updateConfig(data) {
  return request.put('/config', data)
}

// 上传QQ群二维码
export function qqQrcodeUpload(data) {
  return request.post('/qrcode/upload', data)
}
// 更新排序
export function updateSort(data) {
  return request.post('/honor/updateSort', data)
}
