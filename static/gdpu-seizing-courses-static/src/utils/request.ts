import axios from 'axios'

const baseURL = 'http://localhost:80/api'

export const request = axios.create({
  baseURL,
  timeout: 10000,
})

export interface Result<T = null> {
  code: number // 0、1表示失败、成功
  msg: string | null
  data: T | null
}

request.interceptors.response.use(
  //成功回调：HTTP 2xx
  (res) => {
    const data = res.data as Result

    if (data.code === 0) {
      ElMessage.error(data.msg || 'Service Error')
      return Promise.reject(data)
    }
    return res
  },
  //错误回调：404/500/网络错误/超时/服务未启动
  (err) => {
    if (err.response?.status === 404) {
      console.error(`Connection failed (404)：${err.message || 'Network Error'}`)
      ElMessage.error('Connection failed (404)：Network Error')
    } else {
      console.error(`Service Error: ${err.message || 'Unknown'}`)
      ElMessage.error('Service Error: Unknown')
    }
    return Promise.reject(err)
  },
)

export default request
