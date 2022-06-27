<template>
    <div class="background">
        <qna-read v-if="qna" :qna="qna"/>
        <qna-comment @submit="onSubmit" :qnaComments="qnaComments"/><br>
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
  props: {
    qnaNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState(['qna', 'qnaComments'])
  },
  mounted () {
    this.fetchQnaCommentList(this.qnaNo)
  },
  created () {
    this.fetchQna(this.qnaNo)
      .catch(() => {
        alert('요청 실패..')
        this.$router.back()
    })
  },
  methods: {
    ...mapActions(['fetchQna', 'fetchQnaCommentList']),
    onSubmit (payload) {
      const { qnaCommentContent } = payload
      axios.post(`qnaComment/${this.qnaNo}`, { qnaCommentContent })
        .then(() => {
          alert('댓글 작성 성공!')
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
