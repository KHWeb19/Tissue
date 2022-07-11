<template>
    <body>
      <v-container>
      <form @submit.prevent="onSubmit"><br>
        <h2>공지사항 수정</h2><br>
          <v-select v-model="noticeCategory" label="카테고리" :items="categoryList"></v-select>
          <v-text-field v-model="noticeTitle" label="제목" type="text"></v-text-field>
          <v-textarea v-model="noticeContent" label="내용" type="text" height="300px" ></v-textarea>
          <v-select v-model="noticeNecessary" label="필독" :items="necessaryList"></v-select>
          <v-text-field v-model="noticeYoutube" label="유튜브src" type="text"></v-text-field>
          <input type="file" ref="files" @change="handleFileUpload()" clearable/>
          <v-btn @click="fileDeleteBtn()">파일삭제</v-btn>
          <v-img :src="image" alt=""/><br><br><br><br>
          <div id ='btn'>
            <v-btn class="white--text" id="BtnModify" type="submit">수정</v-btn>
          </div><br><br>
      </form>
  </v-container>
</body>
</template>

<script>
export default {
  name: 'NoticeModify',
  props: {
    notice: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      noticeCategory: '',
      categoryList: ['공연안내', '서비스점검', '환불/취소', '기타'],
      noticeTitle: '',
      noticeContent: '',
      noticeNecessary: '',
      necessaryList: ['필독', '일반'],
      noticeYoutube: '',
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
      const { noticeCategory, noticeTitle, noticeContent, noticeNecessary, noticeYoutube, noticeImg } = this
      const file = this.$refs.files.files[0]
      this.$emit('submit', { noticeCategory, noticeTitle, noticeContent,noticeNecessary, noticeYoutube, file, noticeImg})
    },
    fileDeleteBtn () {
      this.files = '',
      this.image = ''
    }
  },
  created () {
    this.noticeCategory = this.notice.noticeCategory
    this.noticeTitle = this.notice.noticeTitle
    this.noticeContent = this.notice.noticeContent
    this.noticeNecessary = this.notice.noticeNecessary
    this.noticeYoutube = this.notice.noticeYoutube
    this.noticeImg = this.notice.noticeImg
  }
}
</script>
<style scoped>
#btn { text-align: center; font-size: 20px; }
#BtnModify { background-color: #F48FB1; width: 500px; height: 55px;}
</style>