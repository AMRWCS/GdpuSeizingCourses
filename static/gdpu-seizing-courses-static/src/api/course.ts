import request from '@/utils/request'

export interface JxbIdsRequest {
  njdmId: string
  xkxnm: string
  xkxqm: string
  kklxdm: string
  xkkzId: string
}

export interface Cookie {
  route: string
  JSESSIONID: string
}

export interface SeizeDTO {
  kchId: string
  jxbIds?: string
  qz: string
  jxbIdsRequest: JxbIdsRequest
  cookie: Cookie
}

export const selectCourse = (dataDTO: SeizeDTO) => request.post('/course/select', dataDTO)

export const removeCourse = (dataDTO: SeizeDTO) => request.post('/course/remove', dataDTO)
