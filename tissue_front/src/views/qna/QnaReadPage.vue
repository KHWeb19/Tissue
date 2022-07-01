<template>
    <div class="background">
        <qna-read v-if="qna && memberInfo" :qna="qna" :memberInfo="memberInfo"/>
        <qna-comment-list @submit="editQnA" v-if="qnaComments && memberInfo" :memberInfo="memberInfo" :qnaComments="qnaComments"/>
        <qna-comment-register @submit="onSubmit" v-if="memberInfo" :memberInfo="memberInfo"/><br>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import QnaRead from '@/components/qna/qna/QnaRead.vue'
import QnaCommentList from '@/components/qna/qnaComment/QnaCommentList.vue'
import QnaCommentRegister from '@/components/qna/qnaComment/QnaCommentRegister.vue'
export default {
  name: 'QnaReadPage',
  components: {
    QnaRead,
    QnaCommentList,
    QnaCommentRegister
  },
  data() {
    return {
      token: localStorage.getItem('token')
    }
  },
  props: {
    qnaNo: {
      type: Number,
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
    //this.fetchQnaCommentList(this.qnaCommentNo)
    this.fetchMemberRole(this.token)

  },
  methods: {
    ...mapActions(['fetchQna', 'fetchQnaCommentList', 'fetchMemberRole']),
    onSubmit (payloads) {
      const { qnaCommentContent } = payloads
      axios.post(`qnaComment/${this.qnaNo}`, { qnaCommentContent })
        .then(() => {
          alert('QnA 답변을 등록하였습니다.')
          this.$router.go()
        })
        .catch(() => {
          alert('문제 발생')
        })
    },
    editQnA (payload) {
      console.log(this.qna.qnaNo)
      const { qnaCommentContent, qnaCommentNo } = payload
      axios.put(`qnaComment/${qnaCommentNo}`, { qnaCommentContent, qnaCommentNo }, { params: { qnaNo: this.qna.qnaNo }})
        .then(() => {
          alert('1:1 답변이 수정되었습니다.')
          this.$router.go()
        })
        .catch(() => {
          alert('Comment Modify Fail')
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
