<template>
    <div>
        <v-container><br>
          <v-expansion-panels
                v-model="panel"
                multiple
                accordion
                focusable
              >
                <v-expansion-panel
                  v-for="qnaBest in qnaBestList" v-bind:key="qnaBest.qnaBestNo"
                >
                  <v-expansion-panel-header disable-icon-rotate>
                    <v-col cols="2">
                    <v-chip
                            color="pink lighten-3" label
                            outlined>
                      {{qnaBest.qnaBestCategory}}
                    </v-chip>
                    </v-col>
                    <v-col>
                        {{ qnaBest.qnaBestTitle }}

                    </v-col>
                   <div v-if="memberInfo.roles === 'ADMIN'">
                      <v-row>
                        <router-link style="color: gray"
                          :to="{ name: 'QnaBestModifyPage', params: { qnaBestNo: qnaBest.qnaBestNo } }">
                          <v-icon right style="color: gray">
                            mdi-pencil
                          </v-icon>
                            </router-link>
                          <v-icon style="color: red" @click="editItem(qnaBest.qnaBestNo)">mdi-delete</v-icon>
                      </v-row>
                   </div>
                          <v-dialog
                                  v-model="dialog"
                                  max-width="500"
                                >
                                  <v-card align="center">
                                    <v-card-title class="justify-center">
                                      정말 삭제하시겠습니까?
                                    </v-card-title><br>
                                      <v-card-actions>
                                        <v-spacer></v-spacer>
                                          <v-btn class="white--text" color="#D3D3D3" @click="dialog = false">
                                            취소
                                          </v-btn>
                                          <v-btn class="white--text" color="#F48FB1" @click="onDelete(editedItem.qnaBestNo)">
                                            삭제
                                          </v-btn>
                                          <v-spacer></v-spacer>
                                        </v-card-actions>
                                  </v-card>
                                </v-dialog>

                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                          <v-textarea :value="qnaBest.qnaBestContent" readonly auto-grow flat solo>
                          </v-textarea>
                        </v-expansion-panel-content>
                  </v-expansion-panel>
              </v-expansion-panels>

                <br><br>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'QnaBestList',
  props: {
    qnaBestList: {
      type: Array,
      required: true
    },
    memberInfo: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      items: ['공연문의', '결제/예매 문의', '환불/취소 문의', '기타'] ,
      keyword: '',
      panel: [],
      dialog : false,
      editedIndex: -1,
      editedItem: {
        qnaBestNo: ''
      }
    }
  },
  methods: {
    editItem (qnaBestNo) {
      this.editedIndex = this.qnaBestList.indexOf(qnaBestNo)
      this.editedItem = Object.assign({}, qnaBestNo)
      this.dialog = true
    },
    all () {
        this.panel = [...Array(this.items).keys()].map((k, i) => i)
      },
      // Reset the panel
      none () {
        this.panel = []
      },
      onDelete (qnaBestNo) {
      axios.delete(`qnaBest/delete/${qnaBestNo}`, { qnaBestNo })
        .then(() => {
          alert('문의가 삭제되었습니다.')
          this.$router.go()
        })
        .catch(() => {
          alert('삭제 실패')
        })
    }
  }
}
</script>