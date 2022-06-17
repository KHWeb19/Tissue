import {

    // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,
  
    FETCH_NOTICE_LIST,
    FETCH_NOTICE

} from './mutation-types'

export default {
    // performance
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
    }

}