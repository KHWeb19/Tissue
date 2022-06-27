<template>
    <div @submit.prevent="onSubmit">
        <v-container>
                <v-data-table
                    :headers="headers"
                    :items="qnaList"
                    hide-default-footer
                    :page.sync="page"
                    :items-per-page="itemsPerPage"
                    @page-count="pageCount = $event"
                >
                  <template v-slot:top>
                    <v-dialog
                      v-model="dialog"
                      max-width="500"
                    >
                    <v-card>
                      <v-card-title>
                        비밀번호를 입력하세요
                      </v-card-title>
                      <v-card-text>
                        <v-text-field
                          v-model="qnaPw"/>
                        <v-text-field
                          :value="editedItem.qnaNo"/>
                      </v-card-text>
                      <v-card-actions>
                        <v-btn @click="close">
                          cancel
                        </v-btn>
                        <v-btn @click ="checkQnaPw(editedItem.qnaNo)">
                          save
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                    </v-dialog>
                  </template>

                  <template v-slot:[`item.qnaCheck`]="{ item }">
                    <v-chip color="blue lighten-3"
                            outlined
                            v-if="item.qnaCheck === false">
                          답변대기중
                    </v-chip>
                    <v-chip v-else
                            color="pink lighten-3"
                            outlined>
                      답변완료
                    </v-chip>
                  </template>


                  <template v-slot:[`item.qnaTitle`]="{ item }">
                    <div v-if="item.qnaSecret === true">
                      <v-btn text @click="editItem(item)">
                        <v-icon style="color: black">
                          mdi-lock-outline
                        </v-icon>
                        {{item.qnaTitle}}
                      </v-btn>

                      </div>

                    <router-link v-else
                      style="color: black"
                      :to="{ name: 'QnaReadPage', params: { qnaNo: item.qnaNo } }">
                      {{ item.qnaTitle }}
                    </router-link>
                  </template>


                </v-data-table>
                  <v-pagination
                    v-model="page"
                    :length="pageCount"
                    total-visible="5"
                    color="pink lighten-3"
                    circle>
                </v-pagination>
                <br><br>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
export default {
  name: 'QnaList',
  props: {
    qnaList: {
      type: Array
    }
  },
  data () {
    return {
      dialog: false,
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
          { text: '숫자', value: 'qnaNo', width: '10%' },
          { text: '답변', value: 'qnaCheck', width: '10%' },
          { text: '구분', value: 'qnaCategory', width: '15%' },
          { text: '제목', value: 'qnaTitle', width: '50%' },
          { text: '작성자', value: 'qnaWriter', width: '10%' },
          { text: '등록일', value: 'qnaDate', width: '20%' }
      ],
      keyword: '',
      qnaPw: '',
      qnaNo: '',
      editedIndex: -1,
      editedItem: {
        qnaNo: ''
      },
    }
  },
  watch: {
    dialog: function (val) {
      if (val) {
        this.qnaPw = ''
      }
    }
  },
  computed: {
    ...mapState(['qnaList'])
  },
  mounted () {
    this.fetchQnaList()
  },
  methods: {
    ...mapActions(['fetchQnaList']),
    editItem (item) {
      this.editedIndex = this.qnaList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    close () {
      this.dialog = false
    },
    checkQnaPw (qnaNo) {
      console.log(this.qnaPw, this.editedItem.qnaNo)
      const { qnaPw } = this
      return axios.get(`qna/${qnaNo}`, {params: {qnaNo, qnaPw}} )
        .then(() => {
          alert('비밀번호을 입력하였습니다.')
          /*this.$router.replace({
            name: 'QnaReadPage',
            params: { qnaNo: this.qnaNo.toString() }
          })*/
        })
        .catch (() => {
          alert('문제 발생')
        })
    }
  }
}
</script>

<style scoped>
</style>