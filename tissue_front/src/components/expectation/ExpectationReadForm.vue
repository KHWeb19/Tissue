<template>
  <div>
    <v-card class="commentBox mb-10" flat>
      <!-- 댓글 등록 (로그인 여부 판단해야함)-->
      <expectation-register-form :eventNo="eventNo" :memberInfo="memberInfo"/>

      <!-- 댓글 리스트 --> 
      <v-card v-if="!expectList || (Array.isArray(expectList) && expectList.length ===0)" class="pa-5" flat>
        <div align="center" style="color:#F48FB1; font-size:15px">등록된 기대평이 없습니다.</div>
      </v-card>

      <v-card v-else v-for="expect in expectList" :key="expect.expectNo" class="mx-4"  flat>
        <v-list>
          <v-list-item>
            <v-list-item-avatar width="100px">
              {{ expect.id }}
            </v-list-item-avatar>
        
            <v-tooltip left>
              <template v-slot:activator="{on}">
                <v-list-item v-on="on">
                  {{ expect.expectContent }}
                </v-list-item>
              </template>
              <span>
                {{new Date(expect.regDate).toLocaleString("ko-KR", {timeZone: "Asia/Seoul"}).toString().substr(0, 11)}}
                {{new Date(expect.regDate).toLocaleString("ko-KR", {timeZone: "Asia/Seoul"}).toString().substr(12, 8)}}
              </span>
            </v-tooltip>
              
            <v-menu offset-y>
              <template v-slot:activator="{ on }">
                <v-btn v-if="memberInfo.memberId == expect.id" v-on="on" icon> <!-- v-if="memberInfo.id == expect.id" 멤버 아이디랑 일치하면 보이는 것 추가하기 0701-->
                  <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
              </template>
              <v-list>
                <expectation-modify-form :expectNo="expect.expectNo" :eventNo="eventNo" :memberInfo="memberInfo"/>
                <v-list-item @click="removeDialog(expect.expectNo)">
                  삭제
                </v-list-item>
              </v-list>
            </v-menu>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
      </v-card>

      <v-dialog v-model="dialog" max-width="350">
        <v-card>
          <v-card-title>
            <p>정말 삭제하시겠습니까?</p>
          </v-card-title>

          <v-card-text></v-card-text>

          <v-card-actions>
            <v-btn @click="btnCancle" text >
              Cancle
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn @click="btnRemove" text>
              Delete
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

    </v-card>
  </div>  
</template>


<script>
import ExpectationRegisterForm from '@/components/expectation/ExpectationRegisterForm.vue'
import ExpectationModifyForm from '@/components/expectation/ExpectationModifyForm.vue'

import axios from 'axios'
import { mapActions, mapState } from 'vuex'

export default {
  components: {
    ExpectationRegisterForm,
    ExpectationModifyForm
  },
  props: {
    eventNo: {
      
    },
    memberInfo: {
      type:Object,
      required: true
    },
  },
  data () {
    return {
      dialog: false,
      expectNo: null,
      token: localStorage.getItem('token'),
    }
  },
  computed: {
    ...mapState([ 'expectList' ])
  },
  mounted () {
    this.fetchExpectList(this.eventNo)
  },
  created() {

    },
  methods: {
    ...mapActions([ 'fetchExpectList', 'fetchExpect' ]),
    removeDialog (expectNo) {
      this.dialog = true
      this.expectNo = expectNo
    },
    btnCancle () {
      this.dialog = false
    },
    btnRemove () {
      axios.delete(`http://localhost:7777/expectation/remove/${this.expectNo}`).then(() => {
        alert('댓글이 삭제되었습니다!')
        this.fetchExpectList(this.eventNo)
        this.dialog = false
      })
    },
  }
}
</script>

<style scoped>

</style>