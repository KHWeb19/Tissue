<template>
    <div align="center">
        <performance-register/>
    </div>
</template>

<script>

import axios from 'axios'
import PerformanceRegister from '@/components/manager/performance/PerformanceRegister.vue'

export default {
  name: 'PerformanceRegisterPage',
  components: {
      PerformanceRegister
  },
  methods: {
    onSubmit (payload) {
      const { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                performPriceVip, performArea, performCategory, performGrade, performer, file } = payload
      axios.post('http://localhost:7777/performance/register', { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                performPriceVip, performArea, performCategory, performGrade, performer, file })
        .then(() => {
          alert('게시물 등록 성공')
        })
        .catch(() => {
          alert('문제 발생')
        })
    }
  }
}
</script>
