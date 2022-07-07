<template>
    <div>
        <ranking-list @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import RankingList from '@/components/ranking/RankingList.vue'

export default {
    name: 'RankingPage',
    components: {
        RankingList
    },
    onSubmit (payload) {
        const { bookDate } = payload
        axios.get('ranking/list', { params: { bookDate }})
          .then(() => {
              alert('success')
          })
          .catch(() => {
              alert('fail')
          })
    }
}
</script>
