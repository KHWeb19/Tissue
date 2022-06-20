<template>
    <div align="center">

        <br/>

        <performance-modify-form v-if="performance" :performance="performance" @submit="onSubmit"/>

        <p v-else>로딩중 .......</p>
    </div>
</template>

<script>

import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import PerformanceModifyForm from '@/components/performance/PerformanceModifyForm.vue'

export default {
    name: 'PerformanceModifyPage',
    components: {
        PerformanceModifyForm
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
    methods: {
        ...mapActions(['fetchPerformance']),
        onSubmit (payload) {
           const { formData } = payload
            axios.put(`http://localhost:7777/performance/${this.performNo}`, formData, { 
                headers : {
                    'Content-Type': 'multipart/form-data'
                }})
                .then(res => {
                    alert('게시물 수정 성공!')
                        this.$router.push({
                            name: 'PerformanceReadPage',
                            params: { performNo: res.data.performNo.toString() }
                        })
                })
                .catch(() => {
                    alert('게시물 수정 실패!')
                })
        }
    },
    created () {
        this.fetchPerformance(this.performNo)
                .catch(() => {
                    alert('게시물 DB 조회 실패!')
                    this.$router.back()
                })
    }
}

</script>