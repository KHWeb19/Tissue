<template>
  <div>
    <div class="commentInfo">
      <!-- 로그인 안하면 댓글 못 쓰도록 해야함 -->
      <v-toolbar flat v-if="token === null || token === 'null'" @click="btnLogin">
        <v-text-field flat solo hide-details disabled label="로그인이 필요한 서비스입니다."></v-text-field>
      </v-toolbar>

      <div v-else>
        <div align="center">
          <v-textarea v-model="expectContent" placeholder="기대평을 작성해주세요."
            counter clearable auto-grow outlined color="pink lighten-3" 
            style="padding-top: 20px; width: 1000px;"
            :rules="[v => (v || '' ).length >= 30|| '30자 이상 작성해주세요.']"/>
          <div align="center" style="margin: 25px">
            <v-btn  width="150" height="50" @click="addComment" 
              color="blue lighten-3" class="white--text mr-3" style="font-size: 15px">
              등록
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>  
</template>


<script>

import axios from 'axios'
import { mapActions } from 'vuex'

export default {
  data () {
    return {
      expectContent: null,
      token: localStorage.getItem('token'),
    }
  },
  props: {
    eventNo: {
      
    },
    memberInfo: {
      type:Object,
      required: true
    },
  },
  created() {
   console.log(this.token)
  },
  methods: {
    ...mapActions([ 'fetchExpectList']),
    btnLogin() {
      this.$router.push({ path: '/login'})
    },
    addComment () {
      
      const id = this.memberInfo.memberId
      const expectContent = this.expectContent
      const eventNo = this.eventNo

      console.log(id, expectContent, eventNo)
      axios.post('http://localhost:7777/expectation/register', { id, expectContent, eventNo })
        .then((res) => { 
          if(res.data == true) {  
            alert("기대평이 등록되었습니다.")
            this.fetchExpectList(eventNo)
            this.expectContent = null

            const memberMileage = this.memberInfo.memberMileage + 3000

            axios.post('Member/addMileage', { memberId: this.memberInfo.memberId, memberMileage} )
                .then(() => {
                  console.log('마일리지 적립', memberMileage)
                  alert('기대평 적립금 3,000원이 적립되었습니다!')
                })
          }
          else{
            alert("기대평은 1회만 작성 가능합니다.")
            this.$router.push({ path: '/event'})
          }
      }).catch(err => {
        alert(err)
      })
    }
  }
}
</script>

<style scoped>
.commentInfo {
  width: 100%;
}

.comment-input {
  width: 100%;
  height: 3.3em;
}
</style>