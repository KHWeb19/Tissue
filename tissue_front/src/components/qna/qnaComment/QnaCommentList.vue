<template>
    <v-container>
      <div class="Public Comment Zone" v-for="qnaComment in qnaComments"
           :key="qnaComment.qnaCommentNo" justify="center">
        <v-row>
          <v-col xs="18" sm="8" md="8">
            <v-card class="pa-3">
              <v-subheader style="dark">Tissue Ticket</v-subheader>
                <v-divider :inset="false"></v-divider>
                  <v-col>
                    <v-textarea v-model="qnaComment.qnaCommentContent" auto-grow flat solo
                                background-color="rgb(231, 242, 251)" readonly></v-textarea>
                    <v-spacer/>
                    <v-input text-align="right">{{ qnaComment.qnaCommentDate }}</v-input>
                  </v-col>
                  <div class="Private Comment Zone" v-if="memberInfo.roles != 'USER'" id ='btn'>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                            @click="editDialog = true">Edit
                      </v-btn>
                      <v-btn
                            @click="deleteDialog = true">Delete
                      </v-btn>
                    </v-card-actions>
                  </div>
            </v-card>
          </v-col>
        </v-row>
          <v-dialog
            class="Comment Edit"
            v-model="editDialog"
            max-width="1000"
            transition="dialog-bottom-transition"
          >
            <v-form @submit.prevent="editQnA" ref="form">
              <v-card align="center">
                <v-card-title>
                   Edit Answer...
                </v-card-title>
                  <v-card-text>
                    <v-textarea v-model="qnaCommentContent" auto-grow filled
                       color="#F48FB1" type="text" :rules="contentRule"/>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                      <v-btn class="white--text" color="#F48FB1" @click="editQnA(qnaComment.qnaCommentNo)">
                        수정
                      </v-btn>
                      <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
              </v-form>
            </v-dialog>

      <v-dialog
            class="Comment delete"
            v-model="deleteDialog"
            max-width="500"
            transition="dialog-bottom-transition"
          >
              <v-card align="center">
                <v-card-title>
                   정말로 삭제하시겠습니까?
                </v-card-title>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                      <v-btn class="white--text" color="#F48FB1" @click="onDelete(qnaComment.qnaCommentNo)">
                        삭제
                      </v-btn>
                      <v-spacer></v-spacer>
                  </v-card-actions>
                </v-card>
            </v-dialog>
      </div>
    </v-container>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CommentRegister',
  props: {
    qnaComments: {
      type: Array
    },
    qnaCommentNo : {
      type: Number
    },
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
      editDialog: false,
      deleteDialog: false
    }
  },
  methods: {
    editQnA (qnaCommentNo) {
      const { qnaCommentContent } = this
      this.$emit('submit', { qnaCommentContent, qnaCommentNo })
      console.log(qnaCommentNo)
    },
    onDelete (qnaCommentNo) {
      axios.delete(`qnaComment/${qnaCommentNo}`, { qnaCommentNo })
        .then(() => {
          alert('QnA 답변을 삭제하였습니다.')
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
