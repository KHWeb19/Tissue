<template>
    <div align="center">
        <notice-list :notices="notices"/>
    </div>
</template>

<script>
import NoticeList from '@/components/notice/NoticeList.vue'
import { mapActions, mapState } from 'vuex'

export default {
  name: 'NoticeListPage.vue',
  components: {
      NoticeList
  },
  computed: {
    ...mapState(['notices'])
  },
  mounted () {
    this.fetchNoticeList()
  },
  methods: {
    ...mapActions(['fetchNoticeList'])
  }
}
</script>

