<template>
  <div>
    <v-card class="commentBox mb-10" flat>
      <!-- 댓글 등록 (로그인 여부 판단해야함)-->
      <expectation-register-form :eventNo="eventNo" :memberInfo="memberInfo" :expect="expect"/>

      <!-- 댓글 리스트 --> 
      <v-card v-if="!expectList || (Array.isArray(expectList) && expectList.length ===0)" class="pa-5" flat>
        <div align="center" style="color:#F48FB1; font-size:15px">등록된 기대평이 없습니다.</div>
      </v-card>

      <v-card v-else v-for="expect in expectList" :key="expect.expectNo" class="expectList mx-4"  flat>
        <v-list>
          <v-list-item>
            <v-list-item-avatar width="100px">
              {{ expect.id }}
            </v-list-item-avatar>
            <v-divider class="ml-5 mr-2 mt-3" style="height:30px" vertical/>
        
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
                <v-btn v-if="memberInfo.memberId == expect.id" v-on="on" icon> 
                  <v-icon small>mdi-pencil</v-icon>
                </v-btn>
              </template>
              <v-list>
                <expectation-modify-form :expectNo="expect.expectNo" :eventNo="eventNo" :memberInfo="memberInfo"/>
                <v-list-item @click="removeDialog(expect.expectNo)" style="font-size:15px; color:#F48FB1">
                  삭제
                </v-list-item>
              </v-list>
            </v-menu>
            
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
      </v-card>

      <v-dialog v-model="dialog" width="300">
        <v-card>
          <v-card-title style="font-size:15px">
           삭제하시겠습니까?
          </v-card-title>
          <v-card-actions>
            <v-btn @click="btnCancle" text style="color:#F48FB1">
              취소
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn @click="btnRemove" text style="color:#F48FB1">
              삭제
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <!-- <v-row justify="center" class="mt-5 mb-5">
        <div class="btn-cover">
        <v-btn :disabled="pageNum === 0" @click="prevPage" class="page-btn" color="blue lighten-3">
            이전
        </v-btn>
        <span class="page-count"
            >{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
        <v-btn :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn" color="blue lighten-3">
            다음
        </v-btn>
        </div>
      </v-row> -->

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
    pageSize: {
      type: Number,
      required: false,
      default: 5,
    },
  },
  data () {
    return {
      dialog: false,
      expectNo: null,
      token: localStorage.getItem('token'),
      pageNum: 0,
    }
  },
  computed: {
    ...mapState([ 'expectList' , 'expect']),
     pageCount() {
      let listLeng = this.expectList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.expectList.slice(start, end);
    },
  },
  mounted () {
    this.fetchExpectList(this.eventNo)
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
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  }
}
</script>

<style scoped>
.expectList{
  font-family: 'Nanum Gothic', sans-serif !important;
  font-size:11pt;
}
</style>