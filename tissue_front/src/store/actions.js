
import axios from 'axios'
import {
    FETCH_HALL_LIST,
    FETCH_HALL,
  FETCH_NOTICE_LIST,
    FETCH_NOTICE
    
} from './mutation-types'

//import router from '@/router'

export default {
    fetchHallList({commit}) {
        return axios.get('http://localhost:7777/hall/list')
        .then((res)=>{
            commit(FETCH_HALL_LIST,res.data)
        })
    },
    
    fetchHall({commit},hallNo) {
        return axios.get(`http://localhost:7777/hall/${hallNo}`)
        .then((res)=>{
            commit(FETCH_HALL,res.data)
        })
    },
  fetchNoticeList ({ commit }) {
        return axios.get('http://localhost:7777/notice/list')
          .then((res) => {
            commit(FETCH_NOTICE_LIST, res.data)
          })
      },
    fetchNotice ({ commit }, noticeNo) {
        return axios.get(`http://localhost:7777/notice/${noticeNo}`)
          .then((res) => {
            commit(FETCH_NOTICE, res.data)
          })
      },
 }

