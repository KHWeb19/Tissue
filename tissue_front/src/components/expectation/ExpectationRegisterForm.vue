<template>
  <div>
    <v-card flat>
      <!-- <v-toolbar flat v-if="!isLogin">
        <v-text-field flat label="댓글을 입력하려면 로그인해주세요." solo hide-details disabled></v-text-field>
      </v-toolbar> -->

      <v-toolbar flat>
        <v-text-field flat v-model="expectContent" class="basil--text" label="댓글을 입력해 주세요."
          solo hide-details></v-text-field>
        <v-btn plain class="basil--text" @click="addComment" >
          등록
        </v-btn>
      </v-toolbar>
    </v-card>  
  </div>
</template>


<script>

import axios from 'axios'
import { mapActions } from 'vuex'

export default {
  data () {
    return {
      expectContent: null
    }
  },
  props: {
    eventNo: {
      
    },
    memberInfo: {
      type:Object,
      required: true
    }
  },
  computed: {
   
  },
  methods: {
    ...mapActions([ 'fetchExpectList' ]),
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

</style>