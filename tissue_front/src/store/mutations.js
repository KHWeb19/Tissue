import {

    FETCH_HALL_LIST,
    FETCH_HALL,
  FETCH_NOTICE_LIST,
    FETCH_NOTICE
} from './mutation-types'

export default{
    [FETCH_HALL_LIST](state, halls){
        state.halls = halls
    },
    [FETCH_HALL](state, hall){
        state.hall = hall
    },
    [FETCH_NOTICE_LIST] (state, notices) {
        state.notices = notices
    },
    [FETCH_NOTICE] (state, notice) {
        state.notice = notice
    }
}