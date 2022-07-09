<template>
    <div align="center">
        <today-ranking @submit="onSubmit" v-if="rankings" :rankings="rankings"/>
    </div>
</template>

<script>
import TodayRanking from '@/components/ranking/TodayRanking.vue'
import { mapActions, mapState } from 'vuex'
import axios from 'axios'

export default {
    name: 'RankingPage.vue',
    components: {
        TodayRanking
    },
    computed: {
        ...mapState(['rankings'])
    },
    mounted () {
        this.fetchRankingList()
    },
    methods: {
        ...mapActions(['fetchRankingList']),
        onSubmit (payload) {
            const { reviewRegDate } = payload
            axios.get('ranking/list/byDate', { params: { reviewRegDate }})
            .then(() => {
                alert('success')
            })
            .catch(() => {
                alert('fail')
            })
        }
    }
}
</script>
