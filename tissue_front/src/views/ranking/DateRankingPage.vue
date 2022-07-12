<template>
    <div align="center">
        <date-ranking v-if="dateRankings" :dateRankings="dateRankings"/>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import DateRanking from '@/components/ranking/DateRanking.vue'
export default {
    name: 'DateRankingPage',
    components: {
        DateRanking
    },
    props: {
      ticketingRegDate: {
          type: String,
          required: true
      }
    },
    computed: {
        ...mapState(['dateRankings'])
    },
    mounted () {
        this.fetchRankingDateList(this.ticketingRegDate)
    },
    methods: {
        ...mapActions(['fetchRankingDateList'])
    }
}
</script>
