import {

    FETCH_HALL_LIST,
    FETCH_HALL,

    FETCH_COUPON_LIST,
    FETCH_COUPON,

      // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,
    FETCH_CONCERT_LIST,
    FETCH_MUSICAL_LIST,
    FETCH_THEATER_LIST,
    FETCH_EXHIBITION_LIST,
    FETCH_PERFORMANCE_EVENT,
    FETCH_REVIEW_LIST,

    FETCH_NOTICE_LIST,
    FETCH_NOTICE,

    FETCH_QNA_LIST,
    FETCH_QNA,
    FETCH_MEMBER_ROLE,
    FETCH_QNA_COMMENT_LIST,
    FETCH_QNA_BEST_LIST,
    FETCH_QNA_BEST,

    FETCH_MEMBER_INFO,
    FETCH_MEMBER,
    FETCH_SEARCH_LIST,
    FETCH_NOTICE_SEARCH_LIST,

    // event
    FETCH_EVENT_LIST,
    FETCH_EVENT,



} from './mutation-types'

// import Vue from 'vue'
import axios from 'axios'

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
    fetchCouponList({commit}) {
        return axios.get("http://localhost:7777/coupon/list")
        .then((res)=>{
            commit(FETCH_COUPON_LIST,res.data)
        })
    },
    fetchCoupon({commit},couponNo) {
        return axios.get(`http://localhost:7777/coupon/${couponNo}`)
        .then((res)=>{
            commit(FETCH_COUPON,res.data)
        })
    },
    fetchConcertList({ commit }) {
        return axios.get('http://localhost:7777/performance/concertList')
                .then((res) => {
                    commit(FETCH_CONCERT_LIST, res.data)
                })
    },
    fetchMusicalList({ commit }) {
        return axios.get('http://localhost:7777/performance/musicalList')
                .then((res) => {
                    commit(FETCH_MUSICAL_LIST, res.data)
                })
    },
    fetchTheaterList({ commit }) {
        return axios.get('http://localhost:7777/performance/theaterList')
                .then((res) => {
                    commit(FETCH_THEATER_LIST, res.data)
                })
    },
    fetchExhibitionList({ commit }) {
        return axios.get('http://localhost:7777/performance/exhibitionList')
                .then((res) => {
                    commit(FETCH_EXHIBITION_LIST, res.data)
                })
    },
    fetchPerformanceEvent({ commit }, performNo) {
        return axios.get(`http://localhost:7777/performance/event/${performNo}`)
        .then((res) => {
            commit(FETCH_PERFORMANCE_EVENT, res.data)
        })
      },
    fetchPerformanceReviewList({commit},performNo) {
        return axios.get(`http://localhost:7777/review/list/${performNo}`)
        .then((res)=>{
            commit(FETCH_REVIEW_LIST,res.data)
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
    fetchQnaList ({ commit }) {
        return axios.get('qna/list')
          .then((res) => {
            commit(FETCH_QNA_LIST, res.data)
          })
    },
    fetchQna ({ commit }, qnaNo) {
        return axios.get(`qna/${qnaNo}`)
          .then((res) => {
            commit(FETCH_QNA, res.data)
          })
    },
    fetchMemberRole ({ commit }, token) {
      return axios.get('qnaBest/getRole', {params: {token: token}})
        .then((res) => {
          commit(FETCH_MEMBER_ROLE, res.data)
        })
    },
    fetchQnaCommentList ({ commit }, qnaCommentNo) {
    return axios.get(`qnaComment/${qnaCommentNo}`)
      .then((res) => {
        commit(FETCH_QNA_COMMENT_LIST, res.data)
      })
    },
    fetchQnaBestList ({ commit }) {
        return axios.get('qnaBest/list')
          .then((res) => {
            commit(FETCH_QNA_BEST_LIST, res.data)
          })
    },
    fetchQnaBest ({ commit }, qnaBestNo) {
        return axios.get(`qnaBest/${qnaBestNo}`)
          .then((res) => {
            commit(FETCH_QNA_BEST, res.data)
          })
    },
    fetchMemberInfo({ commit }, token) {
        return axios.get('Member/info', { params: { token: token } })
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
    fetchSearchList({ commit }, keyword) {
        return axios.post('performance/search', { keyword })
            .then((res) => {
            commit(FETCH_SEARCH_LIST, res.data)
        })
    },
    fetchNoticeSearchList({ commit }, keyword) {
        return axios.post('notice/search', { keyword })
            .then((res) => {
            commit(FETCH_NOTICE_SEARCH_LIST, res.data)
        })
    }

}


