import {
    // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,

} from './mutation-types'

// import Vue from 'vue'
import axios from 'axios'

export default {
    // peformance
    fetchPerformanceList({ commit }) {
        return axios.get('http://localhost:7777/performance/list')
                .then((res) => {
                    commit(FETCH_PERFORMANCE_LIST, res.data)
                })
    },
    fetchPerformance({ commit }, performNo) {
        return axios.get(`http://localhost:7777/performance/${performNo}`)
        .then((res) => {
            commit(FETCH_PERFORMANCE, res.data)
        })
    },
}
