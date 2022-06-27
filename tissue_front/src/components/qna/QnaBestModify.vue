<template>
    <div class="background"><br><br><br>
                  <form @submit.prevent="onSubmit">
      <v-row justify="center">
        <v-col xs="20" sm="10" md="10">
          <v-card><br>
            <v-card-title class="text-center justify-center">
              <h1 class=" text-h2">
                 고객센터> 자주묻는질문
              </h1>
            </v-card-title><br><br>
            <div class="qnaBestRegisterForm">

                  <v-select
                      v-model="qnaBestCategory" label="카테고리" color="pink lighten-3" :items="categoryList"/>
                  <v-text-field
                      v-model="qnaBestTitle" label="제목" color="pink lighten-3"/>
                  <v-textarea
                      v-model="qnaBestContent" label="내용" counter outlined clearable
                      row-height="60" clear-icon="mdi-close-circle" color="pink lighten-3" auto-grow/>
                  <div id ='btn'>
                      <v-btn class="white--text" id="BtnRegister" type="submit">등록</v-btn>
                  </div><br><br>

            </div>
            </v-card>
          </v-col>
        </v-row>
                      </form>
    </div>
</template>

<script>
export default {
  name: 'QnaBestModify',
  props: {
    qnaBest: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      qnaBestCategory: '',
      categoryList : [ '공연문의', '결제/예매 문의', '환불/취소 문의', '기타'],
      qnaBestTitle: '',
      qnaBestContent: ''
    }
  },
  methods: {
    onSubmit () {
        const { qnaBestCategory, qnaBestTitle, qnaBestContent } = this
        this.$emit('submit', { qnaBestCategory, qnaBestTitle, qnaBestContent })
    }
  },
  created () {
    this.qnaBestCategory = this.qnaBest.qnaBestCategory
    this.qnaBestTitle = this.qnaBest.qnaBestTitle
    this.qnaBestContent = this.qnaBest.qnaBestContent
  }
}
</script>

<style scoped>
.background{
  background-color: rgb(241, 241, 241);
}
.qnaBestRegisterForm {
  width: 70%;
  margin: auto;
}
#btn { text-align: center; font-size: 20px; }
#BtnRegister { background-color: #90CAF9; width: 500px; height: 55px;}
</style>
