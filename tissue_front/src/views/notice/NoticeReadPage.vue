<template>
    <div align="center">
        <notice-read v-if="notice" :notice="notice"/>
    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import NoticeRead from '@/components/notice/NoticeRead.vue'


export default {
  name: 'NoticeReadPage',
  components: {
      NoticeRead
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
  created () {
      this.fetchNotice(this.noticeNo)
        .catch(() => {
            alert('공지사항 요청 실패')
            this.$router.back()
        })
  },
  methods: {
      ...mapActions(['fetchNotice'])
  }
}
</script>

