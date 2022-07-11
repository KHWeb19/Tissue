<template>
    <div align="center">
        <today-ranking v-if="rankings" :rankings="rankings"/>
    </div>
</template>

<script>
import TodayRanking from '@/components/ranking/TodayRanking.vue'
import { mapActions, mapState } from 'vuex'

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
        ...mapActions(['fetchRankingList'])
    }
}
</script>
