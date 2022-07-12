<template>
    <div align="center">
        <notice-read v-if="notice && memberInfo" :memberInfo="memberInfo" :notice="notice"/>
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
  data () {
      return {
        token: localStorage.getItem('token')
      }
  },
  props: {
      noticeNo: {
          type: String,
          required: true
      }
  },
  computed: {
      ...mapState(['notice', 'memberInfo'])
  },
  created () {
      this.fetchMemberRole(this.token)
      this.fetchNotice(this.noticeNo)
        .catch(() => {
            alert('공지사항 요청 실패')
            this.$router.back()
        })
  },
  mounted() {
    this.fetchMemberRole(this.token)
  },
  methods: {
      ...mapActions(['fetchNotice','fetchMemberRole'])
  }
}
</script>

