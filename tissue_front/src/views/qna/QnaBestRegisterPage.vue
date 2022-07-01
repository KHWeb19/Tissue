<template>
    <div align="center">
        <qna-best-register @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import QnaBestRegister from '@/components/qna/qnaBest/QnaBestRegister.vue'
export default {
  name: 'QnaBestRegisterPage',
  components: {
    QnaBestRegister
  },
  methods: {
    onSubmit (payload) {
      const { qnaBestCategory, qnaBestTitle, qnaBestContent } = payload
      axios.post('qnaBest/register', { qnaBestCategory, qnaBestTitle, qnaBestContent })
        .then(() => {
          alert('게시물 등록 성공')
          this.$router.replace({
            name: 'QnaListPage'
          })
        })
        .catch(() => {
          alert('모두 입력해주세요')
        })
    }
  }
}
</script>
