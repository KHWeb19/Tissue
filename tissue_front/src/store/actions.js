import {
    // performance
    FETCH_PERFORMANCE_LIST,
    FETCH_PERFORMANCE,
    // map
    FETCH_MAP
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
    // map
    fetchMap({ commit }, performNo) {
        return axios.get(`http://localhost:7777/map/read/${performNo}`)
        .then((res) => {
            const map = res.data
            console.log(map)
            this.mapOptions.lat = map.y
            this.mapOptions.lng = map.x
            this.name = map.name
            this.address = map.address
            commit(FETCH_MAP, res.data)
        })
    },
}
