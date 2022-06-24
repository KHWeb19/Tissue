import {
    // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,

    FETCH_NOTICE_LIST,
    FETCH_NOTICE,

    // event
    FETCH_EVENT_LIST,
    FETCH_EVENT,

} from './mutation-types'

// import Vue from 'vue'
import axios from 'axios'

export default {
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

    // event
    fetchEventList({ commit }) {
      return axios.get('http://localhost:7777/event/list')
              .then((res) => {
                  commit(FETCH_EVENT_LIST, res.data)
              })
    },
    fetchEvent({ commit }, eventNo) {
      return axios.get(`http://localhost:7777/event/${eventNo}`)
      .then((res) => {
          commit(FETCH_EVENT, res.data)
      })
    },
}

