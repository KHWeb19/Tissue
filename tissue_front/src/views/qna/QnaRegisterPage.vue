<template>
    <div align="center">
        <qna-register @submit="onSubmit" v-if="memberInfo" :memberInfo="memberInfo"/>
    </div>
</template>

<script>
import axios from 'axios'
import QnaRegister from '@/components/qna/QnaRegister.vue'
import { mapActions, mapState } from 'vuex'
export default {
  name: 'QnaRegisterPage',
  components: {
    QnaRegister
  },
  data() {
    return {
      token: localStorage.getItem('token')
    }
  },
  computed: {
    ...mapState(['memberInfo'])
  },
  created () {
    this.fetchMemberInfo(this.token)
  },
  methods: {
    ...mapActions(['fetchMemberInfo']),
    onSubmit (payload) {
      const {qnaCategory, qnaTitle, qnaContent, qnaSecret, qnaPw, qnaCheck} = payload
      axios.post('qna/register', {qnaCategory, qnaTitle, qnaWriter : this.memberInfo.memberId, qnaContent, qnaSecret, qnaPw, qnaCheck })
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
