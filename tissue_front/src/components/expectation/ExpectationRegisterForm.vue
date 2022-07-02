<template>
  <div>
    <div class="commentInfo">
      <!-- 로그인 안하면 댓글 못 쓰도록 해야함 -->
      <v-toolbar flat v-if="token === null || token === 'null'" @click="btnLogin">
        <v-text-field flat label="댓글을 입력하려면 로그인해주세요." solo hide-details disabled></v-text-field>
      </v-toolbar>

      <div v-else>
          <v-textarea v-model="expectContent" label="기대평" placeholder="내용작성해주세요." clearable auto-grow outlined
            color="pink lighten-3" style="padding-top: 12px; width: 100%"/>
          <div align="center" style="margin: 25px">
            <v-btn width="150" height="50" @click="addComment" 
              color="blue lighten-3" class="white--text mr-3" style="font-size: 15px">
              등록
            </v-btn>
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
      console.log(this.memberInfo.memberId)
      
      const id = this.memberInfo.memberId
      const expectContent = this.expectContent
      const eventNo = this.eventNo
      axios.post('http://localhost:7777/expectation/register', { id, expectContent, eventNo })
        .then(() => {
        alert('댓글이 등록되었습니다!')

        this.fetchExpectList(eventNo)
        this.expectContent = null
      }).catch(res => {
        alert(res)
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