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
                    <v-spacer></v-spacer>
                    <router-link style="color: gray"
                      :to="{ name: 'QnaBestModifyPage', params: { qnaBestNo: qnaBest.qnaBestNo } }">
                      <v-icon right style="color: gray">
                        mdi-pencil
                      </v-icon>
                    </router-link>
                      <v-icon style="color: red" @click="onDelete(qnaBest.qnaBestNo)">mdi-delete</v-icon>
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
import { mapActions, mapState } from 'vuex'
export default {
  name: 'QnaBestList',
  props: {
    qnaBestList: {
      type: Array
    }
  },
  data () {
    return {
      items: ['공연문의', '결제/예매 문의', '환불/취소 문의', '기타'] ,
      keyword: '',
      panel: []
    }
  },
  computed: {
    ...mapState(['qnaBestList'])
  },
  mounted () {
    this.fetchQnaBestList()
  },
  methods: {
    ...mapActions(['fetchQnaBestList']),
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

<style scoped>
</style>