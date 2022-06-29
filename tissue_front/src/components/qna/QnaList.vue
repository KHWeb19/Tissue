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
                <v-card align="center">
                  <v-card-title class="justify-center">
                    비밀번호를 입력하세요
                  </v-card-title>
                    <v-card-text>
                      <v-text-field
                        v-model="qnaPw" color="pink lighten-3" type="password"/>
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                        <v-btn class="white--text" color="#D3D3D3" @click="close">
                          취소
                        </v-btn>
                        <v-btn class="white--text" color="#90CAF9" @click ="checkQnaPw(editedItem.qnaNo)">
                          확인
                        </v-btn>
                        <v-spacer></v-spacer>
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

                    <td v-if="item.qnaSecret === true && memberInfo.roles == 'USER'" text @click="editItem(item)">
                      <v-icon  style="color: black">
                        mdi-lock-outline
                      </v-icon>
                      {{item.qnaTitle}}
                    </td>

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
export default {
  name: 'QnaList',
  props: {
    qnaList: {
      type: Array
    },
    memberInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      dialog: false,
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
      headers: [
          { text: '답변', value: 'qnaCheck', width: '10%' },
          { text: '구분', value: 'qnaCategory', width: '15%' },
          { text: '제목', value: 'qnaTitle', width: '45%' },
          { text: '작성자', value: 'qnaWriter', width: '15%' },
          { text: '등록일', value: 'qnaDate', width: '20%' }
      ],
      keyword: '',
      qnaPw: '',
      qnaNo: '',
      editedIndex: -1,
      editedItem: {
        qnaNo: ''
      }
    }
  },
  watch: {
    dialog: function (val) {
      if (val) {
        this.qnaPw = ''
      }
    }
  },
  methods: {
    editItem (item) {
      this.editedIndex = this.qnaList.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    close () {
      this.dialog = false
    },
    async checkQnaPw (qnaNo) {
        console.log(this.qnaPw, this.editedItem.qnaNo)
      try {
          const { qnaPw } = this
          let result = await axios.get('qna/CheckQnaPw', { params: { qnaNo, qnaPw }})
          if (result.data == true) {
            this.$router.replace({
              name: 'QnaReadPage',
              params: { qnaNo: this.editedItem.qnaNo }
            })
          } else {
              alert('비밀번호가 틀렸습니다.')
              this.dialog = false
            }
    } catch(error) {
      alert(error)
    }
    }
  }
}
</script>
<style scoped>
#btn { text-align: center; font-size: 20px; }
td:hover { cursor: pointer; }
</style>