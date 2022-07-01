<template>
    <div>
        <qna-modify v-if="qna && memberInfo" :qna="qna" :memberInfo="memberInfo" @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import QnaModify from '@/components/qna/qna/QnaModify.vue'

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
  data() {
    return {
      token: localStorage.getItem('token')
    }
  },
  computed: {
    ...mapState(['qna', 'memberInfo'])
  },
  methods: {
    ...mapActions(['fetchQna', 'fetchMemberRole']),
    onSubmit (payload) {
      const { qnaCategory, qnaTitle, qnaContent } = payload
      axios.put(`qna/${this.qnaNo}`, { qnaCheck:this.qna.qnaCheck, qnaCategory, qnaTitle,
                                        qnaWriter:this.qna.qnaWriter, qnaContent,
                                        qnaSecret:this.qna.qnaSecret, qnaPw:this.qna.qnaPw,
                                        qnaDate: this.qna.qnaDate },
                                      { params: { memberNo: this.memberInfo.memberNo }})
        .then(res => {
          alert('1:1 문의사항이 수정되었습니다.')
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
    }),
    this.fetchMemberRole(this.token)
  }
}
</script>
