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
      reviewRegDate: {
          type: String,
          required: true
      }
    },
    computed: {
        ...mapState(['dateRankings'])
    },
    mounted () {
        this.fetchRankingDateList(this.reviewRegDate)
    },
    methods: {
        ...mapActions(['fetchRankingDateList'])
        /*onSubmit (payload) {
            const { reviewRegDate } = payload
            axios.get(`ranking/list/byDate/${this.reviewRegDate}`, { params: { reviewRegDate }})
            .then(res => {
                alert('success')
                this.$router.replace({
                    name: 'DateRankingPage',
                    params: { reviewRegDate: res.reviewRegDate }
                })
            })
            .catch(() => {
                alert('fail')
            })
        }*/
    }
}
</script>
