<template>
  <div>
    <v-container style="width: 2000px">
      <v-row>
        <v-col>
          <div class="mainTitle">R A N K I N G</div><br>
            <v-menu
              :close-on-content-click="false"
              offset-y
              min-width="290px"
            >
            <template v-slot:activator="{ on, attrs }">
              <v-input v-bind="attrs"
                        v-on="on" class="today">
                {{nowDate}}
                  <v-icon size="40px">mdi-calendar-check</v-icon>
              </v-input>
            </template>

              <div class="datePicker">
                <v-date-picker
                  no-title
                  scrollable
                  v-model="ticketingRegDate"
                  :allowed-dates="(date) => date <= new Date().toISOString().substr(0, 10)">
                  <v-spacer></v-spacer>
                  <router-link
                    style="color: black"
                    :to="{
                      name: 'DateRankingPage', params: { ticketingRegDate: this.ticketingRegDate }
                    }"
                  >
                    <v-btn
                      text
                      color="#F48FB1"
                    >
                      OK
                  </v-btn>
                  </router-link>
                </v-date-picker>
              </div>
          </v-menu><br>

                <v-data-table
                  :headers="headers"
                  :items="rankings"
                  hide-default-footer>
                  <template v-slot:[`item.ranking`]="{ item }">
                    <v-chip color="#EE82EE" outlined v-if="item.ranking== 1">
                      {{ item.ranking }}위
                    </v-chip>
                    <v-chip color="#90CAF9" outlined v-else-if="item.ranking== 2">
                      {{ item.ranking }}위
                    </v-chip>
                    <v-chip color="#F48FB1 " outlined v-else-if="item.ranking== 3">
                      {{ item.ranking }}위
                    </v-chip>
                    <td  v-else>
                      {{ item.ranking }}위
                    </td>
                  </template>

                  <template v-slot:[`item.performDate`]="{ item }">
                    {{ item.performStart }} ~ {{ item.performEnd }}
                  </template>

                  <template v-slot:[`item.img`]="{ item }">
                    <!--router-link
                      :to="{
                        name: 'PerformanceDetailPage',
                        param: { performNo: item.performNo } }">
                          <img :src="require(`../../assets/thumbNail/${item.performThumbnail}`)" alt=""/>
                    </router-link>-->
                    <img :src="require(`../../assets/thumbNail/${item.performThumbnail}`)" alt=""/>
                  </template>

                  <template v-slot:[`item.reviewRating`]="{ item }">
                      <v-rating
                        :value="item.reviewRating"
                        background-color="orange lighten-3"
                        color="orange"
                        small
                        dense
                        hover
                        readonly
                        class="mr-3 pb-4"
                      ></v-rating>
                  </template>
                </v-data-table>
            </v-col>
          </v-row>
        <v-row>
      </v-row>
    </v-container>
  </div>
</template>


<script>
//import { mapActions, mapState } from "vuex"
export default {
  name: 'TodayRanking',
  props: {
    rankings: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      ticketingRegDate: '',
      nowDate: '',
      nowTime: '',
      headers: [
        { text: "순위", value: "ranking" },
        { text: "", value: "img" },
        { text: "공연이름", value: "performName" },
        { text: "공연기간", value: "performDate" },
        { text: "공연장", value: "hallName" },
        { text: "평점", value: "reviewRating" },
        { text: "예매수", value: "count"}
      ]
    }
  },
  mounted() {
    //this.fetchPerformanceReviewList();
    this.timer = setInterval(() => {
      this.setNowTimes()
    },1000)
  },
  /*computed: {
    ...mapState(['reviewList']),
    reviewSumAvg() {
      let sum = 0;
      let avg = 0;
      if (this.reviewList.length != 0) {
        for (let i = 0; i < this.reviewList.length; i++) {
          sum = sum + this.reviewList[i].reviewRating;
        }
        avg = sum / this.reviewList.length;

        console.log("평균" + avg);
        return Number(avg);
      }
      return Number(avg);
    }
  },*/
  methods: {
    //...mapActions(["fetchPerformanceReviewList"]),
    setNowTimes() {
      let date = new Date()
      let yy = String(date.getFullYear())
      let mm = ("0" + (1 + date.getMonth())).slice(-2);
      let dd = String(date.getDate() < 10 ? '0' + date.getDate() : date.getDate())
      this.nowDate = yy + '-' + mm + '-' + dd
    }
  }
}
</script>

<style scoped>
.mainTitle {
  font-size: 50px;
  text-align: center;
  margin-bottom: 20px;
  margin-top: 50px;
}
.today:hover {
  cursor: pointer;
}
.today{
  width: 230px;
  font-size: 30px;
  text-align: center;
  color:#90CAF9;
}
.v-data-table >>> td {
    font-size: 60px;
}
img {
    width: 100px;
    height: 100px;
    border-radius: 70%;
    overflow: hidden;
    object-fit: cover;
}
</style>
