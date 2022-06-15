import {
    FETCH_NOTICE_LIST,
    FETCH_NOTICE
} from './mutation-types'

export default {
    [FETCH_NOTICE_LIST] (state, notices) {
        state.notices = notices
    },
    [FETCH_NOTICE] (state, notice) {
        state.notice = notice
    }
}