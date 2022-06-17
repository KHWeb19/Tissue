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
      const { noticeCategory, noticeTitle, noticeContent, noticeNecessary, noticeYoutube, file, noticeImg } = payload

      let formData = new FormData()
      if (file != null) { formData.append('file', file)}
      formData.append('noticeCategory', noticeCategory)
      formData.append('noticeTitle', noticeTitle)
      formData.append('noticeContent', noticeContent)
      formData.append('noticeNecessary', noticeNecessary)
      formData.append('noticeYoutube', noticeYoutube)
      formData.append('noticeImg', noticeImg)
      formData.append('noticeDate', this.notice.noticeDate)
      formData.append('noticeView', this.notice.noticeView)

      axios.put(`notice/${this.noticeNo}`, formData, {
       headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
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
