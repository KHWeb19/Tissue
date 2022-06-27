<template>
    <div>
        <v-container><br><br><br><br>
          <div class="MyQnaUp">
            <h1>고객센터 > 1:1 문의내역</h1><br>
              <h3 style="color: skyblue"><strong>{{ qna.qnaCategory}}</strong></h3>
          </div><br>
          <hr aria-setsize="5px"><br>
              <div class="MyQna">
                <v-row justify="center">
                  <v-col xs="18" sm="8" md="8">
                    <v-card class="pa-3">
                      <v-subheader style="dark">나의 문의사항</v-subheader>
                        <v-divider :inset="false"></v-divider><br>
                          <v-input label="*제목:">&emsp;{{ qna.qnaTitle}}</v-input>
                          <v-input label="*아이디:">&emsp;{{ qna.qnaWriter}}</v-input>
                          <v-textarea label="*내용:" :value="qna.qnaContent" auto-grow flat solo readonly></v-textarea>
                          <v-input>{{ qna.qnaDate}}</v-input>
                            <v-spacer/>
                              <v-btn @click="onDelete(qna.qnaNo)">삭제</v-btn>
                                <router-link style="color: gray"
                                  :to="{ name: 'QnaModifyPage', params: { qnaNo: qna.qnaNo } }">
                                  <v-icon right style="color: gray; font-size:15px">
                                    mdi-pencil
                                  </v-icon>수정
                                </router-link>
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
  left: 20%;
}
</style>
