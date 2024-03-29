import {

    FETCH_HALL_LIST,
    FETCH_HALL,
    FETCH_TICKETING_LIST,

    FETCH_COUPON_LIST,
    FETCH_COUPON,

    FETCH_REFUND_LIST,
    FETCH_NON_MEMBER_REFUND_LIST,

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
    FETCH_PERFORMANCE_LIKE,
    FETCH_MY_LIKE,
    FETCH_MY_QNA,
    FETCH_MY_REVIEW,
    FETCH_MY_TICKET,
    FETCH_MY_NEW_QNA,
    FETCH_MY_NEW_LIKE,
    FETCH_MY_NEW_TICKET,
    FETCH_MY_NEW_RANKING,

    FETCH_NEW_CONCERT,
    FETCH_NEW_MUSICAL,
    FETCH_NEW_THEATER,
    FETCH_NEW_EXHIBITION,

    // event
    FETCH_EVENT_LIST,
    FETCH_EVENT,
    FETCH_EXPECT_LIST,
    FETCH_EXPECT,

    FETCH_RANKING_LIST,
    FETCH_RANKING_DATE_LIST



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
    fetchTicketingList({commit},performNo) {
        return axios.get(`http://localhost:7777/ticketing/${performNo}`)
        .then((res)=>{
            commit(FETCH_TICKETING_LIST,res.data)
        })
    },
    fetchRefundList({commit}) {
        return axios.get("http://localhost:7777/refund/list")
        .then((res)=>{
            commit(FETCH_REFUND_LIST,res.data)
        })
    },
    fetchNonMemberRefundList({commit}) {
        return axios.get("http://localhost:7777/refund/nonMemberList")
        .then((res)=>{
            commit(FETCH_NON_MEMBER_REFUND_LIST,res.data)
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
                if (res.data != '') {
                    commit(FETCH_PERFORMANCE_EVENT, res.data)
                } else {
                    commit(FETCH_PERFORMANCE_EVENT, null)
                }
        })
      },
    fetchPerformanceReviewList({commit},performNo) {
        return axios.get(`http://localhost:7777/review/list/${performNo}`)
        .then((res)=>{
            commit(FETCH_REVIEW_LIST, res.data)
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
        if(token != null){
        return axios.get('Member/info', { params: { token: token } })
            .then((res) => {
                commit(FETCH_MEMBER_INFO, res.data)
            })
        }
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
    },
    fetchPerformanceLike({ commit }, performNo) {
        return axios.get(`likes/${performNo}`)
            .then((res) => {
                commit(FETCH_PERFORMANCE_LIKE, res.data)
        })
    },
    fetchMyLike({ commit }, memberNo) {
        return axios.get(`likes/my/${memberNo}`)
            .then((res) => {
                console.log(res.data)
            commit(FETCH_MY_LIKE, res.data)
        })
    },
    fetchMyQna({ commit }, memberNo) {
        return axios.get(`Member/myQna/${memberNo}`)
            .then((res => {
                commit(FETCH_MY_QNA, res.data)
        }))
    },
    fetchMyReview({ commit }, memberNo) {
        return axios.get(`Member/myReview/${memberNo}`)
            .then((res => {
                console.log(res.data)
                commit(FETCH_MY_REVIEW, res.data)
            }))
    },
    fetchMyTicket({ commit }, memberNo) {
        return axios.get(`Member/myTicket/${memberNo}`)
            .then((res) => {
                console.log(res.data)
                commit(FETCH_MY_TICKET, res.data)
        })
    },
    fetchMyNewQna({ commit }, memberNo) {
        return axios.get(`Member/newQna/${memberNo}`)
            .then((res => {
                console.log(res.data)
                commit(FETCH_MY_NEW_QNA, res.data)
        }))
    },
    fetchMyNewLike({ commit }, memberNo) {
        return axios.get(`Member/newLikes/${memberNo}`)
            .then((res => {
                console.log(res.data)
                commit(FETCH_MY_NEW_LIKE, res.data)
            }))
    },
    fetchMyNewTicket({ commit }, memberNo) {
        return axios.get(`Member/newTicket/${memberNo}`)
            .then((res => {
                console.log(res.data)
                commit(FETCH_MY_NEW_TICKET, res.data)
            }))
    },
    fetchNewConcert({ commit }, performCategory) {
        return axios.get(`performance/new/${performCategory}`)
            .then((res => {
                commit(FETCH_NEW_CONCERT, res.data)
        }))
    },
    fetchNewMusical({ commit }, performCategory) {
        return axios.get(`performance/new/${performCategory}`)
            .then((res => {
                commit(FETCH_NEW_MUSICAL, res.data)
            }))
    },
    fetchNewTheater({ commit }, performCategory) {
        return axios.get(`performance/new/${performCategory}`)
            .then((res => {
                commit(FETCH_NEW_THEATER, res.data)
            }))
    },
    fetchNewExhibition({ commit }, performCategory) {
        return axios.get(`performance/new/${performCategory}`)
            .then((res => {
                commit(FETCH_NEW_EXHIBITION, res.data)
            }))
    },
    fetchNewRanking({ commit }) {
        return axios.get('ranking/main/list') 
            .then((res) => {
                commit(FETCH_MY_NEW_RANKING, res.data)
            })
    },
    fetchExpectList({ commit }, eventNo) {
        return axios.get(`http://localhost:7777/expectation/read/${eventNo}`)
                .then(res => {
                    commit(FETCH_EXPECT_LIST, res.data)
                })
    },
    fetchExpect ({commit}, expectNo) {
        return axios.get(`http://localhost:7777/expectation/read/only/${expectNo}`)
                .then((res) => {
                    commit(FETCH_EXPECT, res.data)
                })
    },
    fetchRankingList({ commit } ) {
        return axios.get('ranking/list')
            .then((res) => {
                commit(FETCH_RANKING_LIST, res.data)
            })
    },
    fetchRankingDateList({ commit }, ticketingRegDate ) {
        return axios.get(`ranking/list/byDate/${ticketingRegDate}`, {ticketingRegDate})
            .then((res) => {
                commit(FETCH_RANKING_DATE_LIST, res.data)
            })
    }
}


