import {
    // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,

    // map
    FETCH_MAP
} from './mutation-types'

export default {
    // performance
    [FETCH_PERFORMANCE_LIST] (state, performances) {
        state.performances = performances
    },
    [FETCH_PERFORMANCE] (state, performance) {
        state.performance = performance
    },
    // map
    [FETCH_MAP] (state, map) {
        state.map = map
    },
}