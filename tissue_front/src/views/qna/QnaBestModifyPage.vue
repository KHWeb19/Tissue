<template>
    <div>
        <qna-best-modify v-if="qnaBest" :qnaBest="qnaBest" @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import QnaBestModify from '@/components/qna/qnaBest/QnaBestModify.vue'
export default {
  name: 'QnaBestModifyPage',
  components: {
    QnaBestModify
  },
  props: {
    qnaBestNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState(['qnaBest'])
  },
  methods: {
    ...mapActions(['fetchQnaBest']),
    onSubmit (payload) {
      const { qnaBestCategory, qnaBestTitle, qnaBestContent } = payload
      axios.put(`qnaBest/${this.qnaBestNo}`, { qnaBestCategory, qnaBestTitle, qnaBestContent })
        .then(() => {
          alert('자주묻는 질문이 수정되었습니다.')
          this.$router.replace({
            name: 'QnaListPage'
          })
        })
        .catch(() => {
          alert('수정 실패...에러 해결하세여...')
        })
    }
  },
  created () {
    this.fetchQnaBest(this.qnaBestNo)
      .catch(() => {
        alert('게시물 DB 조회 실패')
        this.$router.back()
      })
  }
}
</script>
