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
                  <v-form @submit.prevent="onSubmit">
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
                  </v-form>
              </div>
          </v-menu><br>

                <v-data-table
                  :headers="headers"
                  :items="rankings"
                  hide-default-footer>
                  <template v-slot:[`item.ranking`]="{ item }">
                    {{ item.ranking }}위
                  </template>

                  <template v-slot:[`item.performDate`]="{ item }">
                    {{ item.performStart }} ~ {{ item.performEnd }}
                  </template>

                  <template v-slot:[`item.img`]="{ item }">
                    <!--router-link
                      :to="{
                        name: 'PerformanceDetailPage',
                        param: { performNo: rankings.performNo } }">
                          <img :src="require(`../../assets/thumbNail/${item.performThumbnail}`)" alt=""/>
                    </router-link-->
                    <img :src="require(`../../assets/thumbNail/${item.performThumbnail}`)" alt=""/>
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
  name: 'RankingList',
  props: {
    rankings: {
      type: Array,
      required: true
    }
  },
  data () {
    return {
      nowDate: '',
      nowTime: '',
      reviewRegDate: '',
      headers: [
        { text: "순위", value: "ranking" },
        { text: "", value: "img" },
        { text: "공연이름", value: "performName" },
        { text: "공연기간", value: "performDate" },
        { text: "공연장", value: "hallName" },
        { text: "좋아요", value: "likes" },
        { text: "예매수", value: "count"}
      ]
    }
  },
  mounted() {
    this.timer = setInterval(() => {
      this.setNowTimes()
    },1000)
  },
  methods: {
    setNowTimes() {
      let myDate = new Date()
      let yy = String(myDate.getFullYear())
      let mm = myDate.getMonth() + 1
      let dd = String(myDate.getDate() < 10 ? '0' + myDate.getDate() : myDate.getDate())
      this.nowDate = yy + '-' + mm + '-' + dd
    },
    onSubmit () {
      const { reviewRegDate } = this
      console.log(reviewRegDate)
      this.$emit('submit', {reviewRegDate})
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
  width: 200px;
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
