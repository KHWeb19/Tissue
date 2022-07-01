<template>
    <div class="background">
      <v-row justify="center">
        <v-col xs="20" sm="10" md="10">
          <v-card><br>
            <v-card-title class="text-center justify-center">
              <h1 class=" text-h2">
                 고객센터> 1:1 문의하기
              </h1>
            </v-card-title><br><br>
            <div class="qnaRegisterForm">
              <v-form ref="form" @submit.prevent="onSubmit">
                  <v-select
                      v-model="qnaCategory" label="카테고리" color="pink lighten-3" :items="categoryList"
                      :rules="categotyRule"/>
                  <v-text-field
                      v-model="qnaTitle" label="제목" color="pink lighten-3"
                      :rules="titleRule"/>
                  <v-text-field
                      :value="memberInfo.sub" label="작성자" color="pink lighten-3" readonly/>
                  <v-textarea
                      v-model="qnaContent" label="내용" counter outlined clearable
                      row-height="60" clear-icon="mdi-close-circle" color="pink lighten-3" auto-grow
                      :rules="contentRule"/>
                   <v-radio-group v-model="radios" row>
                        <v-radio
                            label="비밀글로 작성하기" :value="true" checked="checked"/>
                    </v-radio-group>
                   <v-text-field
                            v-model="qnaPw" label="비밀번호" type="password" color="pink lighten-3"/><br>
                  <div id ='btn'>
                      <v-btn class="white--text" id="BtnRegister" type="submit">등록</v-btn>
                  </div><br><br>
              </v-form>
            </div>
            </v-card>
          </v-col>
        </v-row>
    </div>
</template>


<script>
export default {
  name: 'QnaRegister',
  props: {
    memberInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      qnaCategory: '',
      categoryList : [ '공연문의', '결제/예매 문의', '환불/취소 문의', '기타'],
      qnaTitle: '',
      qnaContent: '',
      radios: '',
      //qnaSecret: '',
      qnaPw: '',
      qnaCheck: false,
      categotyRule: [
                v => !!v || '카테고리를 선택해주세요.'
            ],
      titleRule: [
                v => !!v || '제목을 입력해주세요.',
                v => !(v.length >= 70) || '더이상 입력할 수 없습니다.'
            ],
      contentRule: [
                v => !!v || '내용을 입력해주세요.'
            ],
      token: localStorage.getItem('token')
    }
  },
  methods: {
    onSubmit () {
      const validate = this.$refs.form.validate()
      const { qnaCategory, qnaTitle, qnaContent, radios, qnaPw, qnaCheck } = this
      const qnaSecret = ( radios == true ? true : false)

      if (validate) {
          this.$emit('submit', { qnaCategory, qnaTitle, qnaContent, qnaSecret, qnaPw, qnaCheck })
        } else {
          alert('항목을 모두 작성하세요!')
        }
    }
  }
}
</script>

<style scoped>
.background{
  background-color: rgb(241, 241, 241);
}
.qnaRegisterForm {
  width: 70%;
  margin: auto;
}
#BtnRegister { background-color: #90CAF9; width: 500px; height: 55px;}
</style>
