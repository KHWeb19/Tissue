<template>
  <div>
    <v-container style="width: 2000px">
      <v-row>
        <v-col>
          <div class="mainTitle">R A N K I N G</div><br>
            <router-link :to="{ name: 'TodayRankingPage'}" >
              <div class="day" data-hover="Today's Ranking">
                    {{ $route.params.ticketingRegDate }}
              </div>
            </router-link>
                <v-data-table
                  :headers="headers"
                  :items="dateRankings"
                  hide-default-footer>
                  <template v-slot:[`item.ranking`]="{ item }">
                    <v-chip color="deep-purple" large outlined v-if="item.ranking== 1">
                      {{ item.ranking }}위
                    </v-chip>
                    <v-chip color="#F48FB1" large outlined v-else-if="item.ranking== 2">
                      {{ item.ranking }}위
                    </v-chip>
                    <v-chip color="#F48FB1 " large outlined v-else-if="item.ranking== 3">
                      {{ item.ranking }}위
                    </v-chip>
                    <td v-else>
                      {{ item.ranking }}위
                    </td>
                  </template>

                  <template v-slot:[`item.performDate`]="{ item }">
                    {{ item.performStart }} ~ {{ item.performEnd }}
                  </template>

                  <template v-slot:[`item.img`]="{ item }">
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

export default {
  name: 'DateRanking',
  props: {
    dateRankings: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      ticketingRegDate: '',
      headers: [
        { text: "순위", value: 'ranking', width: '10%'},
        { text: "", value: "img" },
        { text: "공연이름", value: "performName" },
        { text: "공연기간", value: "performDate" },
        { text: "공연장", value: "hallName" },
        { text: "평점", value: "reviewRating" },
        { text: "예매수", value: "count"}
      ]
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
.day:hover {
  cursor: pointer;
}
.day:hover:after{
  content: attr(data-hover)
}
.day{
  width: 500px;
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
