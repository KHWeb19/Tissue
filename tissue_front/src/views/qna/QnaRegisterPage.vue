<template>
    <div align="center">
        <qna-register @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import QnaRegister from '@/components/qna/QnaRegister.vue'
export default {
  name: 'QnaRegisterPage',
  components: {
    QnaRegister
  },
  methods: {
    onSubmit (payload) {
      const {qnaCategory, qnaTitle, qnaWriter, qnaContent, qnaSecret, qnaPw, qnaCheck} = payload
      axios.post('qna/register', {qnaCategory, qnaTitle, qnaWriter, qnaContent, qnaSecret, qnaPw, qnaCheck })
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
