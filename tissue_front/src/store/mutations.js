import {
    FETCH_HALL_LIST,
    FETCH_HALL
} from './mutation-types'

export default{
    [FETCH_HALL_LIST](state, halls){
        state.halls = halls
    },
    [FETCH_HALL](state, hall){
        state.hall = hall
    },
}