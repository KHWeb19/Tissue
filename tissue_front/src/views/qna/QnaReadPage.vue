<template>
    <div class="background">
        <qna-read v-if="qna && memberInfo" :qna="qna" :memberInfo="memberInfo"/>
        <qna-comment @submit="onSubmit" v-if="qnaComments && memberInfo" :memberInfo="memberInfo" :qnaComments="qnaComments"/><br>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import QnaRead from '@/components/qna/QnaRead.vue'
import QnaComment from '@/components/qna/QnaComment.vue'
export default {
  name: 'QnaReadPage',
  components: {
    QnaRead,
    QnaComment
  },
  data() {
    return {
      token: localStorage.getItem('token')
    }
  },
  props: {
    qnaNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState(['qna', 'qnaComments', 'memberInfo'])
  },
  mounted () {
    this.fetchQnaCommentList(this.qnaNo)
    this.fetchMemberRole(this.token)
  },
  created () {
    this.fetchQna(this.qnaNo)
      .catch(() => {
        alert('요청 실패..')
        this.$router.back()
    })
    this.fetchMemberRole(this.token)

  },
  methods: {
    ...mapActions(['fetchQna', 'fetchQnaCommentList', 'fetchMemberRole']),
    onSubmit (payload) {
      const { qnaCommentContent } = payload
      axios.post(`qnaComment/${this.qnaNo}`, { qnaCommentContent })
        .then(() => {
          alert('QnA 답변을 등록하였습니다.')
          this.$router.go()
        })
        .catch(() => {
          alert('문제 발생')
        })
    }
  }
}
</script>

<style scoped>
.background {
    background-color: rgb(241, 241, 241);
}
</style>
