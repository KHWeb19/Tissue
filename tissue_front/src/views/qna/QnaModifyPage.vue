<template>
    <div>
        <qna-modify v-if="qna" :qna="qna" @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import QnaModify from '@/components/qna/QnaModify.vue'

export default {
  name: 'QnaModifyPage',
  components: {
    QnaModify
  },
  props: {
    qnaNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState(['qna'])
  },
  methods: {
    ...mapActions(['fetchQna']),
    onSubmit (payload) {
      const { qnaCategory, qnaTitle, qnaContent } = payload
      axios.put(`qna/${this.qnaNo}`,
        { qnaCheck:this.qna.qnaCheck, qnaCategory, qnaTitle, qnaWriter:this.qna.qnaWriter, qnaContent, qnaSecret:this.qna.qnaSecret, qnaPw:this.qna.qnaPw, qnaDate: this.qna.qnaDate})
        .then(res => {
          alert('게시물 수정 성공!')
          this.$router.replace({
            name: 'QnaReadPage',
            params: { qnaNo: res.qna.qnaNo.toString() }
          })
        })
        .catch(() => {
          alert('게시물 수정 실패!')
        })
    }
  },
  created () {
    this.fetchQna(this.qnaNo)
      .catch(() => {
        alert('게시물 DB 조회 실패!')
        this.$router.back()
      })
  }
}
</script>
