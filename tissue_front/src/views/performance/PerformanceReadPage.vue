<template>
    <div align="center">
        <v-container fluid>
            <v-row>
                <v-col sm="3"><label>공연번호</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performNo" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연명</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performName" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연시작일</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performStart" readonly/>
                </v-col>
            </v-row>
        
            <v-row >
                <v-col sm="3"><label>공연종료일</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performEnd" readonly/>
                </v-col>
            </v-row>
            
            <v-row >
                <v-col sm="3"><label>공연시간</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performTime" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>R석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceR" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>S석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceS" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>vip석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceVip" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>지역</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performArea" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연카테고리</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performCategory" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>등급</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performGrade" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>출연자</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performer" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>썸네일</label></v-col>
                <v-col sm="6">
                    <img :src="require(`@/assets/thumbNail/${this.performance.performThumbnail}`)"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>상세이미지</label></v-col>
                <v-row sm="6">
                    <img v-if="this.performance.performDetailImg1 !== null && this.performance.performDetailImg1 !== 'null'"
                     :src="require(`@/assets/detailImg/${this.performance.performDetailImg1}`)"/>
                </v-row>
                <v-row sm="6">
                    <img v-if="this.performance.performDetailImg2 !== null && this.performance.performDetailImg2 !== 'null'"
                     :src="require(`@/assets/detailImg/${this.performance.performDetailImg2}`)"/>
                </v-row>
                <v-row sm="6">
                    <img v-if="this.performance.performDetailImg3 !== null && this.performance.performDetailImg3 !== 'null'"
                     :src="require(`@/assets/detailImg/${this.performance.performDetailImg3}`)"/>
                </v-row>
                <v-row sm="6">
                    <img v-if="this.performance.performDetailImg4 !== null && this.performance.performDetailImg4 !== 'null'"
                     :src="require(`@/assets/detailImg/${this.performance.performDetailImg4}`)"/>
                </v-row>
                <v-row sm="6">
                    <img v-if="this.performance.performDetailImg5 !== null && this.performance.performDetailImg5 !== 'null'"
                     :src="require(`@/assets/detailImg/${this.performance.performDetailImg5}`)"/>
                </v-row>
            </v-row>

            <v-row>
                <v-col sm="3"><label>지도</label></v-col>
                <naver-maps :height="500" :width="1000" :mapOptions="mapOptions"></naver-maps>
                <naver-marker :lat="mapOptions.lat" :lng="mapOptions.lng"></naver-marker>
            </v-row>
    
        </v-container>
       
        <div>
            <v-btn plain router-link :to="{ name: 'PerformanceModifyPage', params: { performNo } }">
                수정
            </v-btn>

            <v-btn plain @click="onDelete">
                삭제
            </v-btn>

            <v-btn plain router-link :to="{ name: 'PerformanceListPage' }">
                돌아가기
            </v-btn>
        </div>
    </div>
</template>

<script>

import axios from 'axios'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'PerformanceReadPage',
    data(){
        return {
           mapOptions: {
                lat: null,
                lng: null,
                zoom: 17,
            },
            // showMap: false,
            name: null,
            address: null,
            // showPlace: false
        }
    },
    props: {
        performNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['performance'])
    },
    created () {
        this.fetchPerformance(this.performNo)
                .catch(() => {
                    alert('게시물 요청 실패!')
                    this.$router.push()
                })
        this.fetchMap(this.performNo)
    },
     mounted() {

    },
    methods: {
        ...mapActions(['fetchPerformance']),
        onDelete () {
            const { performNo, performDetailImg1, performDetailImg2, performDetailImg3, performDetailImg4, performDetailImg5 } = this.performance
            axios.delete(`http://localhost:7777/performance/${performNo}`, { performDetailImg1, performDetailImg2, performDetailImg3, performDetailImg4, performDetailImg5 })
                    .then(() => {
                        alert('삭제 성공!')
                        this.$router.push({ name: 'PerformanceListPage' })
                    })
                    .catch(() => {
                        alert('삭제 실패! 문제 발생!')
                    })
        },
        fetchMap (performNo) {
            axios.get(`http://localhost:7777/map/read/${performNo}`)
                .then(res => {
                    const map = res.data
                    console.log(map)

                    this.mapOptions.lat = map.y
                    this.mapOptions.lng = map.x
                    this.name = map.name
                    this.address = map.address

                    if (map.y != 0) {
                    // this.showMap = true
                    }
            })
        }
    }
}

</script>