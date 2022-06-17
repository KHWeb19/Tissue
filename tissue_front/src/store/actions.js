import axios from 'axios'
import {
    FETCH_HALL_LIST,
    FETCH_HALL,
  
    
} from './mutation-types'

//import router from '@/router'

export default {
    fetchHallList({commit}) {
        return axios.get('http://localhost:7777/hall/list')
        .then((res)=>{
            commit(FETCH_HALL_LIST,res.data)
        })
    },
    
    fetchHall({commit},hallNo) {
        return axios.get(`http://localhost:7777/hall/${hallNo}`)
        .then((res)=>{
            commit(FETCH_HALL,res.data)
        })
    },
    
    
 }

