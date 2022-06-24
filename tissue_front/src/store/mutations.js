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
    [FETCH_MEMBER_INFO](state, memberInfo) {
        state.memberInfo = memberInfo
    },
    [FETCH_MEMBER](state, member) {
        state.member = member
    }
}