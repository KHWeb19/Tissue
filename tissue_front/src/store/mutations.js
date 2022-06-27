import {
    FETCH_HALL_LIST,
    FETCH_HALL,

    FETCH_COUPON_LIST,
    FETCH_COUPON,
  // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,

    FETCH_NOTICE_LIST,
    FETCH_NOTICE,

    FETCH_QNA_LIST,
    FETCH_QNA,
    FETCH_QNA_COMMENT_LIST,
    FETCH_QNA_BEST_LIST,
    FETCH_QNA_BEST

    FETCH_MEMBER_INFO,
    FETCH_MEMBER


} from './mutation-types'

export default{
    [FETCH_HALL_LIST](state, halls){
        state.halls = halls
    },
    [FETCH_HALL](state, hall) {
        state.hall = hall
    },
    [FETCH_COUPON_LIST](state, couponList){
        state.couponList = couponList
    },
    [FETCH_COUPON](state, coupon) {
        state.coupon = coupon
    },
    [FETCH_PERFORMANCE_LIST] (state, performances) {
        state.performances = performances
    },
    [FETCH_PERFORMANCE] (state, performance) {
        state.performance = performance
    },
    [FETCH_NOTICE_LIST] (state, notices) {
        state.notices = notices
    },
    [FETCH_NOTICE] (state, notice) {
        state.notice = notice
    },
    [FETCH_QNA_LIST] (state, qnaList) {
        state.qnaList = qnaList
    },
    [FETCH_QNA] (state, qna) {
        state.qna = qna
    },
    [FETCH_QNA_COMMENT_LIST] (state, qnaComments) {
        state.qnaComments = qnaComments
    },
    [FETCH_QNA_BEST_LIST] (state, qnaBestList) {
        state.qnaBestList = qnaBestList
    },
    [FETCH_QNA_BEST] (state, qnaBest) {
        state.qnaBest = qnaBest
    },
    [FETCH_MEMBER_INFO](state, memberInfo) {
        state.memberInfo = memberInfo
    },
    [FETCH_MEMBER](state, member) {
        state.member = member
    }
}