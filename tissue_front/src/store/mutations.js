import {
    FETCH_HALL_LIST,
    FETCH_HALL,
    FETCH_TICKETING_LIST,
    FETCH_REFUND_LIST,

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
    FETCH_PERFORMANCE_LIKE,
    FETCH_MY_LIKE,
    FETCH_MY_QNA,
    FETCH_MY_REVIEW,
    FETCH_MY_TICKET,
    FETCH_MY_NEW_QNA,
    FETCH_MY_NEW_LIKE,
    FETCH_MY_NEW_TICKET,

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


export default{
    [FETCH_HALL_LIST](state, halls){
        state.halls = halls
    },
    [FETCH_HALL](state, hall) {
        state.hall = hall
    },
    [FETCH_TICKETING_LIST](state, ticketingList) {
        state.ticketingList = ticketingList
    },
    [FETCH_REFUND_LIST](state, refundList) {
        state.refundList = refundList
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
    [FETCH_PERFORMANCE_LIKE](state, likeList) {
        state.likeList = likeList
    },
    [FETCH_MY_LIKE](state, myLike) {
        state.myLike = myLike
    },
    [FETCH_MY_QNA](state, myQna) {
        state.myQna = myQna
    },
    [FETCH_MY_TICKET](state, myTicket) {
        state.myTicket = myTicket
    },
    [FETCH_NEW_CONCERT](state, newConcert) {
        state.newConcert = newConcert
    },
    [FETCH_NEW_MUSICAL](state, newMusical) {
        state.newMusical = newMusical
    },
    [FETCH_NEW_THEATER](state, newTheater) {
        state.newTheater = newTheater
    },
    [FETCH_NEW_EXHIBITION](state, newExhibition) {
        state.newExhibition = newExhibition
    },
    [FETCH_MY_NEW_QNA](state, newQna) {
        state.newQna = newQna
    },
    [FETCH_MY_NEW_LIKE](state, newLike) {
        state.newLike = newLike
    },
    [FETCH_MY_NEW_TICKET](state, newTicket) {
        state.newTicket = newTicket
    },
    // event
    [FETCH_EVENT_LIST] (state, events) {
        state.events = events
    },
    [FETCH_EVENT] (state, event) {
        state.event = event
    },
    [FETCH_MY_REVIEW](state, myReview) {
        state.myReview = myReview
    },
    [FETCH_EXPECT_LIST] (state, expectList) {
        state.expectList = expectList
    },
    [FETCH_EXPECT] (state, expect) {
        state.expect = expect
    },
    [FETCH_RANKING_LIST] (state, rankings) {
        state.rankings = rankings
    },
    [FETCH_RANKING_DATE_LIST] (state, rankings) {
        state.rankings = rankings
    }
}
