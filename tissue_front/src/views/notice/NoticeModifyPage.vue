<template>
    <div>
        <notice-modify v-if="notice" :notice="notice" @submit="onSubmit"/>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import NoticeModify from '@/components/notice/NoticeModify.vue'
export default {
  name: 'NoticeModifyPage',
  components: {
    NoticeModify
  },
  props: {
    noticeNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState(['notice'])
  },
  methods: {
    ...mapActions(['fetchNotice']),
    onSubmit (payload) {
      const { noticeCategory, noticeTitle, noticeContent, noticeNecessary } = payload
      axios.put(`http://localhost:7777/notice/${this.noticeNo}`,
        { noticeCategory, noticeTitle, noticeContent, noticeNecessary,
            noticeDate: this.notice.noticeDate, noticeView: this.notice.noticeView })
        .then(res => {
          alert('게시물 수정 성공!')
          this.$router.replace({
            name: 'NoticeReadPage',
            params: { noticeNo: res.data.noticeNo.toString() }
          })
        })
        .catch(() => {
          alert('게시물 수정 실패!')
        })
    }
  },
  created () {
    this.fetchNotice(this.noticeNo)
      .catch(() => {
        alert('게시물 DB 조회 실패!')
        this.$router.back()
      })
  }
}
</script>
