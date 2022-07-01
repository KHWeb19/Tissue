<template>
  <v-container>
    <div class="Admin Zone" v-if="this.memberInfo.roles != 'USER'" id ='btn'>
        <v-btn id="BtnRegister" @click="dialog = true">QnA 답변</v-btn>
    </div>

          <v-dialog
            class="Comment Register"
            v-model="dialog"
            max-width="1000"
            transition="dialog-top-transition"
          >
            <v-form @submit.prevent="onSubmit" ref="form">
              <v-card align="center">
                <v-card-title>
                   Answer...
                </v-card-title>
                  <v-card-text>
                    <v-textarea v-model="qnaCommentContent" auto-grow filled
                       color="#F48FB1" type="text" :rules="contentRule"/>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                      <v-btn class="white--text" color="#F48FB1" @click="onSubmit">
                        등록
                      </v-btn>
                      <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-form>
            </v-dialog>
  </v-container>
</template>

<script>
export default {
  name: 'QnaCommentRegister',
  props: {
    memberInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      qnaCommentContent: '',
      contentRule: [
        v => !!v || '내용을 입력해주세요.'
      ],
      dialog: false,
      token: localStorage.getItem('token')
    }
  },
  methods: {
    onSubmit () {
      const validate = this.$refs.form.validate()
      const { qnaCommentContent } = this
      if (validate) {
        this.$emit('submit', { qnaCommentContent })
      } else {
        alert('항목을 모두 작성하세요!')
      }
    }
  }
}
</script>


<style scoped>
#btn { text-align: center; font-size: 20px; }
#BtnRegister { background-color: white; width: 500px; height: 45px;}
</style>