import {
    FETCH_NOTICE_LIST,
    FETCH_NOTICE,
    FETCH_MEMBER_INFO
} from './mutation-types'

import axios from 'axios'

export default {
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
    }
}