<template>
    <body>
      <v-container>
      <form @submit.prevent="onSubmit">
        <h2>공지사항 등록</h2>
          <v-select v-model="noticeCategory" label="카테고리" :items="categoryList"></v-select>
          <v-text-field v-model="noticeTitle" label="제목" type="text"></v-text-field>
          <v-textarea v-model="noticeContent" label="내용" type="text" height="300px" ></v-textarea>
          <v-select v-model="noticeNecessary" label="필독" :items="necessaryList"></v-select>
          <input type="file" ref="files" @change="handleFileUpload()"/>
          <div id ='btn'>
            <v-btn class="white--text" id="BtnRegister" type="submit">등록</v-btn>
          </div><br><br><br><br><br>
      </form>
  </v-container>
</body>
</template>

<script>
import axios from 'axios'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
export default {
  name: 'NoticeRegister',
  data () {
    return {
      noticeCategory: '',
      categoryList: ['공연안내', '서비스점검', '환불/취소', '기타'],
      noticeTitle: '',
      noticeContent: '',
      noticeNecessary: '',
      necessaryList: ['필독', '일반'],
      files: '',
      image: ''
    }
  },
  methods: {
    handleFileUpload () {
      console.log('file upload')
      var image = this.$refs['files'].files[0]
      const url = URL.createObjectURL(image)
      this.image = url

      this.files = this.$refs.files.files[0]
    },
    onSubmit () {
      const { noticeCategory, noticeTitle, noticeContent, noticeNecessary } = this
      const file = this.$refs.files.files[0]
      this.$emit('submit', { noticeCategory, noticeTitle, noticeContent, noticeNecessary, file })
      console.log(noticeCategory, noticeTitle, file)
    }
  }
}
</script>

<style scoped>
h2 { font-size: 30px;}
#btn { text-align: center; font-size: 20px; }
#BtnRegister { background-color: blue; width: 500px; height: 55px;}
</style>
