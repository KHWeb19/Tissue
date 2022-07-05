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
    FETCH_QNA_COMMENT_LIST,
    FETCH_QNA_BEST_LIST,
    FETCH_QNA_BEST,

    FETCH_MEMBER_INFO,
    FETCH_MEMBER,
    FETCH_MEMBER_ROLE,

    FETCH_SEARCH_LIST,
    FETCH_SEARCH_LIST2,
    FETCH_NOTICE_SEARCH_LIST,
    // event
    FETCH_EVENT_LIST,
    FETCH_EVENT,


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
    [FETCH_CONCERT_LIST] (state, concertList) {
        state.concertList = concertList
    },
    [FETCH_MUSICAL_LIST] (state, musicalList) {
        state.musicalList = musicalList
    },
    [FETCH_THEATER_LIST] (state, theaterList) {
        state.theaterList = theaterList
    },
    [FETCH_EXHIBITION_LIST] (state, exhibitionList) {
        state.exhibitionList = exhibitionList
    },
    [FETCH_PERFORMANCE_EVENT] (state, performanceEvent) {
        state.performanceEvent = performanceEvent
    },
    [FETCH_REVIEW_LIST] (state, reviewList) {
        state.reviewList = reviewList
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
    [FETCH_MEMBER_ROLE] (state, memberInfo) {
        state.memberInfo = memberInfo
    },
    [FETCH_QNA_COMMENT_LIST] (state, qnaComments) {
        state.qnaComments = qnaComments
    },
    [FETCH_QNA_BEST_LIST] (state, qnaBestList, memberInfo) {
        state.qnaBestList = qnaBestList
        state.memberInfo = memberInfo
    },
    [FETCH_QNA_BEST] (state, qnaBest) {
        state.qnaBest = qnaBest
    },
    [FETCH_MEMBER_INFO](state, memberInfo) {
        state.memberInfo = memberInfo
    },
    [FETCH_MEMBER](state, member) {
        state.member = member
    },
    [FETCH_SEARCH_LIST](state, searchList) {
        state.searchList = searchList
    },
    [FETCH_SEARCH_LIST2](state, searchList) {
        state.searchList2 = searchList
    },
    [FETCH_NOTICE_SEARCH_LIST](state, noticeSearchList) {
        state.noticeSearchList = noticeSearchList
    },
    // event
    [FETCH_EVENT_LIST] (state, events) {
        state.events = events
    },
    [FETCH_EVENT] (state, event) {
        state.event = event
    }
}