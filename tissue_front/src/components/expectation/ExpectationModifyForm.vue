<template>
  <v-dialog v-model="dialog" max-width="350">
    <template v-slot:activator="{ on }">
      <v-list-item v-on="on" @click="readComment">
        수정
      </v-list-item>
    </template>

    <v-card color="basil">
      
      <v-text-field v-model="expectContent" class="px-3"></v-text-field>

      <v-card-text></v-card-text>

      <v-card-actions>
        <v-btn @click="btnCancle"  text >
          Cancle
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn @click="modifyComment" text>
          Modify
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
        alert('수정이 완료되었습니다')
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