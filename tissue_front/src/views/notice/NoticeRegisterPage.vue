<template>
    <div align="center">
        <notice-register @submit="onSubmit"/>
    </div>
</template>

<script>

import axios from 'axios'
import NoticeRegister from '@/components/notice/NoticeRegister.vue'
// axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
export default {
  name: 'NoticeRegisterPage',
  components: {
    NoticeRegister
  },
  methods: {
    onSubmit (payload) {
      const { noticeCategory, noticeTitle, noticeContent, noticeNecessary, noticeYoutube, file} = payload
      let formData = new FormData()
      if (file != null)
      formData.append('noticeCategory', noticeCategory)
      formData.append('noticeTitle', noticeTitle)
      formData.append('noticeContent', noticeContent)
      formData.append('noticeNecessary', noticeNecessary)
      formData.append('noticeYoutube', noticeYoutube)
      formData.append('file', file)

      axios.post('notice/register', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(() => {
          alert('게시물 등록 성공')
          this.$router.replace({
            name: 'NoticeListPage'
          })
        })
        .catch(() => {
          alert('문제 발생')
        })
    }
  }
}
</script>
