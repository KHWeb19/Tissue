import {
    // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,
} from './mutation-types'

export default {
    // performance
    [FETCH_PERFORMANCE_LIST] (state, performances) {
        state.performances = performances
    },
    [FETCH_PERFORMANCE] (state, performance) {
        state.performance = performance
    },
}