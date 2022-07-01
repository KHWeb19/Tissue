<template>
    <div class="background">
    <v-row justify="center">
      <v-col xs="20" sm="10" md="10">
      <v-card><br>
            <v-card-title class="text-center justify-center">
              <h1 class=" text-h2">
                 고객센터
              </h1>
            </v-card-title><br><br>
            <div class="cardInform">
              <v-row justify="center">
                <div>
                  <v-col><br>
                  <v-img
                    :src="require('@/assets/cs.png')"
                    max-width="80px"
                    max-height="80px"
                  />
                  </v-col>
                </div>
                <div>
                <v-col>
                  <v-card-subtitle style="font-size:20px">Tissue Service Center</v-card-subtitle>
                  <v-card-text>전화문의 : 1234 - 5678<br>
                              이용시간 : 평일 09:00~18:00 / 토요일 09:00~17:00<br>
                              (점심시간: 12:00~13:00 / 일요일, 공휴일 휴무)</v-card-text>
                </v-col>
                </div>
                <v-spacer/>
                <div>
                  <v-col><br>
                  <v-img
                    :src="require('@/assets/resume.png')"
                    max-width="80px"
                    max-height="80px"
                  />
                  </v-col>
                </div>

                <div>
                <v-col>
                  <v-card-subtitle style="font-size:20px">Tissue 1:1 문의 &emsp;
                    <v-chip
                      color="blue lighten-3" label to="/qnaRegister"
                       outlined>
                      1:1문의하러가기
                    </v-chip>
                  </v-card-subtitle>

                    <v-card-text>평일 오후 5시 이후 문의는 당일 답변이 어려울 수 있습니다.<br>
                                토,일요일/공휴일 문의는 운영 시간 내 순차적으로 답변 드리겠습니다.<br>
                      <v-icon color="#90CAF9">mdi-information-outline</v-icon>
                      잠깐! 1:1 문의 전 "자주묻는질문" 을 먼저 확인해보세요.<br>


                    </v-card-text>
                </v-col>
                </div>
              </v-row>
            </div><br><br>
            <v-tabs
              v-model="tab"
              color="blue lighten-3"
              grow
            >
              <v-tab v-for="item in items" :key="item.tab">
                  {{ item.tab }}
              </v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
              <v-tab-item>
                <qna-best-list v-if="qnaBestList && memberInfo" :qnaBestList="qnaBestList" :memberInfo="memberInfo"/>
              </v-tab-item>
              <v-tab-item>
                <qna-list v-if="qnaList && memberInfo" :qnaList="qnaList" :memberInfo="memberInfo"/>
              </v-tab-item>
            </v-tabs-items>
  </v-card>
      </v-col>
    </v-row>

    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import QnaList from '@/components/qna/qna/QnaList.vue'
import QnaBestList from '@/components/qna/qnaBest/QnaBestList.vue'
export default {
  name: 'QnaListPage.vue',
  components: {
    QnaList,
    QnaBestList
  },
  data () {
    return {
      tab : null,
      items: [
        {tab: '자주묻는질문', content: QnaBestList },
        {tab: '1대1 문의하기', content: QnaList }
      ],
      token: localStorage.getItem('token')
    }
  },
  computed: {
    ...mapState(['qnaBestList', 'qnaList', 'memberInfo'])
  },
  created () {
    this.fetchMemberRole(this.token)
    console.log("this token: " + this.token)
  },
  mounted () {
    this.fetchQnaBestList()
    this.fetchQnaList()
    this.fetchMemberRole(this.token)
  },
  methods : {
    ...mapActions(['fetchQnaBestList', 'fetchQnaList', 'fetchMemberRole'])
  }
}
</script>

<style scoped>
.background{
  background-color: rgb(241, 241, 241);
}
.cardInform {
  background-color: rgb(241, 241, 241);
  width: 85%;
  margin: auto;
}
</style>
