import axios from 'axios'
import {
    FETCH_HALL_LIST,
    FETCH_HALL,
      // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,

    FETCH_NOTICE_LIST,
    FETCH_NOTICE,
    FETCH_MEMBER_INFO,
    FETCH_MEMBER

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
   // peformance
    fetchPerformanceList({ commit }) {
        return axios.get('http://localhost:7777/performance/list')
                .then((res) => {
                    commit(FETCH_PERFORMANCE_LIST, res.data)
                })
    },
    fetchPerformance({ commit }, performNo) {
        return axios.get(`http://localhost:7777/performance/${performNo}`)
        .then((res) => {
            commit(FETCH_PERFORMANCE, res.data)
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
    fetchMemberInfo({ commit }, token) {
        return axios.get('Member/info', { params: { token: token }})
            .then((res) => {
                commit(FETCH_MEMBER_INFO, res.data)
        })
    },
    fetchMember({ commit }) {
        return axios.get('Admin/memberInfo')
            .then((res) => {
                commit(FETCH_MEMBER, res.data)
        })
    },
    
}

