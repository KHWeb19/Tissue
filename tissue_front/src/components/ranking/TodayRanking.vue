<template>
  <div>
    <v-container style="width: 2000px">
      <v-row>
        <v-col>
          <div class="rankingTop">
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
          </v-menu>
          </div><br>
              <div class="tableZone">
                <v-data-table
                  :headers="headers"
                  :items="rankings"
                  hide-default-footer>
                  <template v-slot:[`item.ranking`]="{ item }">
                    <v-chip color="deep-purple" outlined v-if="item.ranking== 1">
                      {{ item.ranking }}위
                    </v-chip>
                    <v-chip color="#90CAF9" outlined v-else-if="item.ranking== 2">
                      {{ item.ranking }}위
                    </v-chip>
                    <v-chip color="#F48FB1" outlined v-else-if="item.ranking== 3">
                      {{ item.ranking }}위
                    </v-chip>
                    <td id="rank" v-else>
                      &emsp;{{ item.ranking }}위
                    </td>
                  </template>

                  <template v-slot:[`item.img`]="{ item }">
                    <router-link
                      :to="{
                        name: 'PerformanceDetailPage',
                        params: { performNo: item.performNo } }">
                          <img :src="require(`../../assets/thumbNail/${item.performThumbnail}`)" alt=""/>
                    </router-link>
                  </template>

                  <template v-slot:[`item.performName`]="{ item }">
                    <router-link
                      style="color: black"
                      :to="{
                        name: 'PerformanceDetailPage',
                        params: { performNo: item.performNo } }">
                        {{ item.performName }}
                    </router-link>
                  </template>

                  <template v-slot:[`item.performDate`]="{ item }">
                    {{ item.performStart }} ~ {{ item.performEnd }}
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
              </div>
            </v-col>
          </v-row>
        <v-row>
      </v-row>
    </v-container>
  </div>
</template>

<script>

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
        { text: 'Rank' ,value: "ranking", width: '7%' },
        { text: "", value: "img", width: '10%' },
        { text: "", value: "performName", width: '35%'},
        { text: "", value: "performDate", width: '20%' },
        { text: "", value: "hallName", width: '13%' },
        { text: "", value: "reviewRating", width: '3%'},
        { text: "예매수", value: "count", width: '8%'}
      ]
    }
  },
  mounted() {
    //this.fetchPerformanceReviewList();
    this.timer = setInterval(() => {
      this.setNowTimes()
    },1000)
  },
  methods: {
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
.rankingTop {
  border-bottom: 2px solid black;
}
.v-data-table::v-deep td {
  font-size: 15px !important;
}
img {
    width: 100px;
    height: 160px;
    overflow: hidden;
    object-fit: cover;
}
</style>
