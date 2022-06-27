<template>
    <div class="background"><br><br><br>
      <v-row justify="center">
        <v-col xs="20" sm="10" md="10">
          <v-card><br>
            <v-card-title class="text-center justify-center">
              <h1 class=" text-h2">
                 고객센터> 1:1 문의사항 수정
              </h1>
            </v-card-title><br><br>
            <div class="qnaRegisterForm">
              <form @submit.prevent="onSubmit">
                  <v-select
                      v-model="qnaCategory" label="카테고리" color="pink lighten-3" :items="categoryList"/>
                  <v-text-field
                      v-model="qnaTitle" label="제목" color="pink lighten-3"/>
                  <v-text-field
                      :value="qnaWriter" label="작성자" color="pink lighten-3" readonly/>
                  <v-textarea
                      v-model="qnaContent" label="내용" counter outlined clearable
                      row-height="60" clear-icon="mdi-close-circle" color="pink lighten-3" auto-grow/>
                  <div id ='btn'>
                      <v-btn class="white--text" id="BtnRegister" type="submit">수정</v-btn>
                  </div><br><br>
              </form>
            </div>
            </v-card>
          </v-col>
        </v-row>
    </div>
</template>

<script>
export default {
  name: 'QnaModify',
  props: {
    qna: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      qnaCategory: '',
      categoryList : [ '공연문의', '결제/예매 문의', '환불/취소 문의', '기타'],
      qnaTitle: '',
      qnaContent: ''
    }
  },
  methods: {
    onSubmit () {
      const { qnaCategory, qnaTitle, qnaContent } = this
      this.$emit('submit', { qnaCategory, qnaTitle, qnaContent })
    }
  },
  created () {
    this.qnaCategory = this.qna.qnaCategory
    this.qnaTitle = this.qna.qnaTitle
    this.qnaWriter = this.qna.qnaWriter
    this.qnaContent = this.qna.qnaContent
  }
}
</script>

<style scoped>
#btn { text-align: center; font-size: 20px; }
.background{
  background-color: rgb(241, 241, 241);
}
.qnaRegisterForm {
  width: 70%;
  margin: auto;
}
#BtnRegister { background-color: #90CAF9; width: 500px; height: 55px;}
</style>
