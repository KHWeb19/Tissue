<template>
  <div>
    <v-container style="width: 2000px">
      <v-row>
        <v-col>
          <div class="rankingTop">
          <div class="mainTitle">R A N K I N G</div><br>
            <router-link :to="{ name: 'TodayRankingPage'}" >
              <div class="day" data-hover="Today's Ranking">
                    {{ $route.params.ticketingRegDate }}
              </div><br>
            </router-link>
          </div><br>
                <v-data-table
                  :headers="headers"
                  :items="dateRankings"
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
                    <td v-else>
                      &emsp;{{ item.ranking }}위
                    </td>
                  </template>
                  <template v-slot:[`item.performDate`]="{ item }">
                    {{ item.performStart }} ~ {{ item.performEnd }}
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
        { text: 'Rank' ,value: "ranking", width: '7%' },
        { value: "img", width: '10%' },
        { value: "performName", width: '35%'},
        { value: "performDate", width: '20%' },
        { value: "hallName", width: '13%' },
        { value: "reviewRating", width: '3%'},
        { text: "예매수", value: "count", width: '8%'}
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
