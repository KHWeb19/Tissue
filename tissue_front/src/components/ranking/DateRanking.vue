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
                    {{$route.params.reviewRegDate}}
                  <v-icon size="40px">mdi-calendar-check</v-icon>
              </v-input>
            </template>

              <div class="datePicker">
                <v-date-picker
                  no-title
                  scrollable
                  v-model="reviewRegDate">
                  <v-spacer></v-spacer>
                    <v-btn
                      text
                      color="#F48FB1"
                      type="submit"
                    >
                      OK
                  </v-btn>
                </v-date-picker>
              </div>
          </v-menu><br>

                <v-data-table
                  :headers="headers"
                  :items="dateRankings"
                  hide-default-footer>
                  <template v-slot:[`item.ranking`]="{ item }">
                    {{ item.ranking }}위
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
      reviewRegDate: '',
      headers: [
        { text: "순위", value: 'ranking' },
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
