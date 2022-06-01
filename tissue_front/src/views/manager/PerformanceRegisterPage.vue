<template>
    <div align="center">
      <performance-register @submit="onSubmit"/>
    </div>
</template>

<script>

import BoardRegister from '@/components/board/BoardRegister.vue'
import axios from 'axios'

export default {
  name: 'PerformanceRegisterPage',
  components: {
    BoardRegister
  },
  methods: {
    onSubmit (payload) {
      const { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                performPriceVip, performArea, performCategory, performGrade, performer, file } = payload
      axios.post('http://localhost:7777/performance/register', { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                performPriceVip, performArea, performCategory, performGrade, performer, file })
        .then(() => {
          alert('게시물 등록 성공')
        })
        .catch(() => {
          alert('문제 발생')
        })
    },
    handleFileUpload () {
            this.file = this.$refs.file.files
            console.log("this.file")
        },
    submitFiles () {
            let formData = new FormData()
            for (let idx = 0; idx < this.files.length; idx++) {
                formData.append('fileList', this.files[idx])
            }
            axios.post('http://localhost:7777/performance/register', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then (res => {
                alert('처리 결과: ' + res.data)
            })
            .catch (res => {
                alert('처리 결과: ' + res.message)
            })
        }
  }
}
</script>
