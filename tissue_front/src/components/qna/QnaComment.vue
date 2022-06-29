<template>
  <form @submit.prevent="onSubmit">
    <v-container>
    <v-row justify="left">
      <v-col xs="18" sm="8" md="8">
        <v-card class="pa-3">
          <v-subheader style="dark">Tissue Ticket</v-subheader>
            <v-divider :inset="false"></v-divider>
            <div v-for="qnaComment in qnaComments"
              :key="qnaComment.qnaCommentNo" justify="center" class="comment">
              <v-col>
                <v-textarea v-model="qnaComment.qnaCommentContent" auto-grow flat solo
                            background-color="rgb(231, 242, 251)" readonly></v-textarea>
                <v-input text-align="right">{{ qnaComment.qnaCommentDate }}</v-input>
               <!--<a v-if="comment.commentWriter == commentWriter"
                     @click="onDelete(comment.commentNo)">삭제
               </a>-->
              </v-col>
            </div>
            <v-divider :inset="false"></v-divider><br>
            <div>
              <textarea class="textareaComment" v-model="qnaCommentContent" type="text" placeholder="댓글을 입력하세요."></textarea>
              <div id ='btn'>
                <v-btn id="BtnRegister" type="submit">댓글 등록</v-btn>
              </div>
            </div>
        </v-card>
      </v-col>
    </v-row>
    </v-container>
  </form>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CommentRegister',
  props: {
    qnaComments: {
      type: Array
    },
    memberInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      qnaCommentContent: '',
      token: localStorage.getItem('token')
    }
  },
  methods: {
    onSubmit () {
      const { qnaCommentContent } = this
      this.$emit('submit', { qnaCommentContent })
    },
    onDelete (qnaCommentNo) {
      axios.delete(`qnaComment/${qnaCommentNo}`, { qnaCommentNo })
        .then(() => {
          alert('댓글 삭제 성공')
          this.$router.go()
        })
        .catch(() => {
          alert('실패..문제 해결하세요')
          console.log(qnaCommentNo)
        })
    }
  }
}
</script>

<style scoped>
.textareaComment {
    width: 100%;
    height: 100px;
    border: 1px solid #bbb;
    margin: 10px 0;
}
#btn { text-align: center; font-size: 20px; }
#BtnRegister { background-color: white; width: 500px; height: 45px;}
.comment.regDate { font-size: 5px; }
.deleteComment { font-style: red; }
.pa-3 {
  background-color: rgb(231, 242, 251);
}
</style>
