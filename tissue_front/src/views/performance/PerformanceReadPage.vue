<template>
    <!-- <div align = "center"> -->
        <v-container>
            <v-row>
                <v-col>
                    <h1>{{ performance.performCategory }}</h1>
                </v-col>
                <v-col>
                    <h1>[{{ performance.performName}}]</h1>
                </v-col>
            </v-row> 
            <v-row>
                <v-col>{{ performance.performStart }} ~ {{ performance.performEnd }}</v-col>
                <v-col> {{ name }} {{ address }}</v-col>
            </v-row>
            <hr class="mt-5 mb-5" color="black">
            <v-row>
                <v-col>
                    <img class="img" :src="require(`@/assets/thumbNail/${this.performance.performThumbnail}`)"/>
                </v-col>
                <v-col>
                    <h4>{{ performance.performGrade }}</h4>
                    <h4>{{ performance.performStart }}</h4>
                    <h4>{{ performance.performer }}</h4>
                    <h4>{{ performance.performPriceS }}원</h4>
                    <h4>{{ performance.performPriceR }}원</h4>
                    <h4>{{ performance.performPriceVip }}원</h4>
                </v-col>
            </v-row>
            <hr class="mt-5 mb-5">
            <div align="center" class="detailImgBox">
            <v-row justify="center">
                    <v-col class="label">상세이미지</v-col>
            </v-row>
            <v-row justify="center">
                <v-col>
                <img class="img" v-if="this.performance.performDetailImg1 !== null && this.performance.performDetailImg1 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg1}`)"/>
                </v-col>
            </v-row>
             <v-row justify="center">
                <v-col>
                    <img class="img" v-if="this.performance.performDetailImg2 !== null && this.performance.performDetailImg2 !== 'null'"
                        :src="require(`@/assets/detailImg/${this.performance.performDetailImg2}`)"/>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col>
                    <img class="img" v-if="this.performance.performDetailImg3 !== null && this.performance.performDetailImg3 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg3}`)"/>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col>
                    <img class="img" v-if="this.performance.performDetailImg4 !== null && this.performance.performDetailImg4 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg4}`)"/>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col>
                    <img class="img" v-if="this.performance.performDetailImg5 !== null && this.performance.performDetailImg5 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg5}`)"/>
                </v-col>
            </v-row>
            </div>

            <hr class="mt-5 mb-5">

            <div align="center">
             <v-row>
                <v-col>
                    <v-col> {{ name }} </v-col>
                    <v-col> {{ address }} ({{ phone }}) </v-col>
                    <naver-maps :height="500" :width="1000" :mapOptions="mapOptions"></naver-maps>
                    <naver-marker :lat="mapOptions.lat" :lng="mapOptions.lng"></naver-marker>
                </v-col>
            </v-row>
            </div>

            <!-- <table>
                <v-row justify="center">
                    <v-col class="label">공연번호</v-col>
                    <v-col><input type="text" :value="performance.performNo" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">공연명</v-col>
                    <v-col><input type="text" :value="performance.performName" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">공연시작일</v-col>
                    <v-col><input type="Date" :value="performance.performStart" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">공연종료일</v-col>
                    <v-col><input type="Date" :value="performance.performEnd" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">공연시간</v-col>
                    <v-col><input type="Time" :value="performance.performTime" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">R석가격</v-col>
                    <v-col><input type="text" :value="performance.performPriceR" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">S석가격</v-col>
                    <v-col><input type="text" :value="performance.performPriceS" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">vip석가격</v-col>
                    <v-col><input type="text" :value="performance.performPriceVip" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">지역</v-col>
                    <v-col><input type="text" :value="performance.performArea" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">공연카테고리</v-col>
                    <v-col><input type="text" :value="performance.performCategory" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">등급</v-col>
                    <v-col><input type="text" :value="performance.performGrade" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">출연자</v-col>
                    <v-col><input type="text" :value="performance.performer" readonly/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">썸네일</v-col>
                    <v-col><img class="img" :src="require(`@/assets/thumbNail/${this.performance.performThumbnail}`)"/></v-col>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">상세이미지</v-col>
                    <img class="img" v-if="this.performance.performDetailImg1 !== null && this.performance.performDetailImg1 !== 'null'"
                        :src="require(`@/assets/detailImg/${this.performance.performDetailImg1}`)"/>
                </v-row>
                <v-row>
                    <img class="img" v-if="this.performance.performDetailImg2 !== null && this.performance.performDetailImg2 !== 'null'"
                        :src="require(`@/assets/detailImg/${this.performance.performDetailImg2}`)"/>
                </v-row>
                <v-row justify="center">
                    <img class="img" v-if="this.performance.performDetailImg3 !== null && this.performance.performDetailImg3 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg3}`)"/>
                </v-row>
                <v-row justify="center">
                    <img class="img" v-if="this.performance.performDetailImg4 !== null && this.performance.performDetailImg4 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg4}`)"/>
                </v-row>
                <v-row justify="center">
                    <img class="img" v-if="this.performance.performDetailImg5 !== null && this.performance.performDetailImg5 !== 'null'"
                    :src="require(`@/assets/detailImg/${this.performance.performDetailImg5}`)"/>
                </v-row>
                <v-row justify="center">
                    <v-col class="label">지도</v-col>
                    <v-col>
                        {{ name }} ( {{ address }})
                        <naver-maps :height="500" :width="800" :mapOptions="mapOptions"></naver-maps>
                        <naver-marker :lat="mapOptions.lat" :lng="mapOptions.lng"></naver-marker>
                    </v-col>
                </v-row>
            </table> -->
            <div align="center">
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
        </v-container>
    <!-- </div> -->
</template>

<script>

import axios from 'axios'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'PerformanceReadPage',
    data(){
        return {
           performNo: '',
           mapOptions: {
                lat: null,
                lng: null,
                zoom: 17,
            },
            // showMap: false,
            name: null,
            address: null,
            phone: null,
            url: null
            // showPlace: false
        }
    },
    computed: {
        ...mapState(['performance'])
    },
    created () {
       this.performNo = this.$route.params.performNo
    },
     mounted() {
        this.fetchPerformance(this.performNo)
                .catch(() => {
                    alert('게시물 요청 실패!')
                    this.$router.push()
                })

        this.fetchMap(this.performNo)
    },
    methods: {
        ...mapActions(['fetchPerformance']),

        onDelete () {
            const { performNo, performThumbnail, performDetailImg1, performDetailImg2, performDetailImg3, performDetailImg4, performDetailImg5 } = this.performance
            axios.delete(`http://localhost:7777/performance/${performNo}`, { performThumbnail, performDetailImg1, performDetailImg2, performDetailImg3, performDetailImg4, performDetailImg5 })
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
                    this.phone = map.phone
                    this.url = map.url

                    if (map.y != 0) {
                    // this.showMap = true
                    }
            })
        }
    }
}

</script>

<style scoped>

table {
  position: relative;
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
  margin-left: auto;
  margin-right: auto;
}

.label {
    text-align: center;
}

.img {
    position: relative;
    max-height: 500px;
    max-width: 500px;
    /* margin-top: 5%; */
}

.detailImgBox {
    width: 100%;
    height: 100%;
}


</style>