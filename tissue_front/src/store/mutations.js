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
    },

    // event
    [FETCH_EVENT_LIST] (state, events) {
        state.events = events
    },
    [FETCH_EVENT] (state, event) {
        state.event = event
    }
}