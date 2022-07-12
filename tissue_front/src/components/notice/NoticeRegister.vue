<template>
    <body>
      <v-container>
      <form @submit.prevent="onSubmit"><br>
        <h2>공지사항 등록</h2><br><br>
          <v-select v-model="noticeCategory" label="카테고리" :items="categoryList"></v-select>
          <v-text-field v-model="noticeTitle" label="제목" type="text"></v-text-field>
          <v-textarea v-model="noticeContent" label="내용" type="text" height="300px" ></v-textarea>
          <v-select v-model="noticeNecessary" label="필독" :items="necessaryList"></v-select>
          <v-text-field v-model="noticeYoutube" label="유튜브src" type="text"></v-text-field>
          <input type="file" ref="files" @change="handleFileUpload()"/>
          <v-img :src="image" alt=""/><br><br><br><br>
          <div id ='btn'>
            <v-btn class="white--text" id="BtnRegister" type="submit">등록</v-btn>
          </div><br><br><br>
      </form>
  </v-container>
</body>
</template>

<script>
export default {
  name: 'NoticeRegister',
  data () {
    return {
      noticeCategory: '',
      categoryList: ['공연안내', '서비스점검', '환불/취소', '기타'],
      noticeTitle: '',
      noticeContent: '',
      noticeNecessary: '',
      necessaryList: ['필독', '일반' ],
      files: '',
      image: '',
      noticeYoutube: ''
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
      const { noticeCategory, noticeTitle, noticeContent, noticeNecessary, noticeYoutube } = this
      const file = this.$refs.files.files[0]
      this.$emit('submit', { noticeCategory, noticeTitle, noticeContent, noticeNecessary, noticeYoutube, file })
      console.log(noticeCategory, noticeYoutube, file)
    }
  }
}
</script>

<style scoped>
h2 { font-size: 30px;}
#btn { text-align: center; font-size: 20px; }
#BtnRegister { background-color: skyblue; width: 500px; height: 55px;}
</style>
