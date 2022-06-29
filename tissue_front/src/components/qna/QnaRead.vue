<template>
    <div>
        <v-container>
          <div class="MyQnaUp">
            <h1>고객센터 > 1:1 문의내역</h1><br>
              <h3 style="color: skyblue"><strong>{{ qna.qnaCategory}}</strong></h3>
          </div><br>
          <hr aria-setsize="5px"><br>
              <div class="MyQna">
                <v-row>
                  <v-col xs="18" sm="8" md="8">
                    <v-card class="pa-3">
                      <v-subheader style="dark">나의 문의사항</v-subheader>
                        <v-divider :inset="false"></v-divider><br>
                          <v-input label="*제목:">&emsp;{{ qna.qnaTitle}}</v-input>
                          <v-input label="*아이디:">&emsp;{{ qna.qnaWriter}}</v-input>
                          <v-textarea label="*내용:" :value="qna.qnaContent" auto-grow flat solo readonly></v-textarea>
                          <v-input color="gray">{{ qna.qnaDate}}</v-input>
                            <div id = "menu" v-if="this.memberInfo.memberId === qna.qnaWriter">
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <router-link style="color: gray"
                                  :to="{ name: 'QnaModifyPage', params: { qnaNo: qna.qnaNo } }">
                                <v-btn class="white--text" color="#D3D3D3">
                                  수정
                                </v-btn>
                              </router-link>&emsp;
                                <v-btn class="white--text" color="#F48FB1" @click="dialog = true">
                                  삭제
                                </v-btn>

                                <v-dialog
                                  v-model="dialog"
                                  max-width="500"
                                >
                                  <v-card align="center">
                                    <v-card-title class="justify-center">
                                      정말 삭제하시겠습니까?
                                    </v-card-title><br>
                                      <v-card-actions>
                                        <v-spacer></v-spacer>
                                          <v-btn class="white--text" color="#D3D3D3" @click="dialog = false">
                                            취소
                                          </v-btn>
                                          <v-btn class="white--text" color="#F48FB1" @click="onDelete(qna.qnaNo)">
                                            삭제
                                          </v-btn>
                                          <v-spacer></v-spacer>
                                        </v-card-actions>
                                  </v-card>
                                </v-dialog>
                              </v-card-actions>
                            </div>
                    </v-card>
                  </v-col>
                </v-row>
              </div>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'QnaRead',
  props: {
    qna : {
      type: Object,
      required: true
    },
    memberInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      dialog: false,
      token: localStorage.getItem('token')
    }
  },
  methods: {
    onDelete (qnaNo) {
      axios.delete(`qna/delete/${qnaNo}`, { qnaNo })
        .then(() => {
          alert('문의가 삭제되었습니다.')
          this.$router.replace({
            name: 'QnaListPage'
          })
        })
        .catch(() => {
          alert('삭제 실패')
        })
    }
  }
}
</script>
<style scoped>
.pa-3 {
  left: 50%;
}
</style>
