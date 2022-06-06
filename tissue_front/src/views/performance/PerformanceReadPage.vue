<template>
    <div align="center">
        <performance-read-form v-if="performance" :performance="performance"/>
        <p v-else>로딩중 ....... </p>

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

import PerformanceReadForm from '@/components/performance/PerformanceReadForm.vue'

export default {
    name: 'PerformanceReadPage',
    props: {
        performNo: {
            type: String,
            required: true
        }
    },
    components: {
        PerformanceReadForm
    },
    computed: {
        ...mapState(['performance'])
    },
    created () {
        this.fetchPerformance(this.performNo)
                .catch(() => {
                    alert('게시물 요청 실패!')
                    console.log(this.performNo)
                    // this.$router.push()
                })
    },
    methods: {
        ...mapActions(['fetchPerformance']),
        onDelete () {
            const { performNo } = this.performance
            //alert('지우는 게시물 번호: ' + boardNo)
            axios.delete(`http://localhost:7777/performance/${performNo}`)
                    .then(() => {
                        alert('삭제 성공!')
                        this.$router.push({ name: 'PerformanceListPage' })
                    })
                    .catch(() => {
                        alert('삭제 실패! 문제 발생!')
                    })
        }
    }
}

</script>