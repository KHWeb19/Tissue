<template>
  <v-dialog v-model="dialog" width="350px"  >
    <template v-slot:activator="{ on }">
      <v-list-item v-on="on" @click="readComment" style="font-size:15px; color:#F48FB1">
        수정
      </v-list-item>
    </template>

    <v-card> 
      <div align="center">
      <v-textarea v-model="expectContent" placeholder="기대평을 작성해주세요."
            
            counter clearable auto-grow color="pink lighten-3"
            style="width: 300px; height: 150px"
            :rules="[v => (v || '' ).length >= 30|| '30자 이상 작성해주세요.']"/>
      </div>
      <v-card-actions>
        <v-btn @click="btnCancle" text color="pink lighten-3">
         취소
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn @click="modifyComment" text color="pink lighten-3">
          완료
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>


<script>
import axios from 'axios'

import { mapActions, mapState } from 'vuex'

export default {
  name: 'ExpectationModifyForm',
  props: {
    expectNo: {
    },
    eventNo: {
    },
    memberInfo: {
      type:Object,
      required: true
    }
  },
  data () {
    return {
      dialog: false,
      expectContent: null
    }
  },
  computed: {
    ...mapState([ 'expectList', 'expect' ])
  },
  mounted () {
    this.fetchExpect(this.expectNo)
  },
  methods: {
    ...mapActions([ 'fetchExpect', 'fetchExpectList' ]),
    btnCancle () {
      this.dialog = false
    },
    modifyComment () {
      const expectContent  = this.expectContent
      axios.patch(`http://localhost:7777/expectation/modify/${this.expectNo}`, { expectContent })
        .then(() => {
        this.fetchExpectList(this.eventNo)
        this.dialog = false
      })
    },
    readComment () {
      this.fetchExpect(this.expectNo)
      this.expectContent = this.expect.expectContent
    }
  }
}
</script>

<style scoped>

</style>