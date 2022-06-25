<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          <div
            style="
              text-align: center;
              font-size: 30px;
              font-weight: lighter;
              margin: 30px;
            "
          >
            <b style="font-size: 45px; font-weight: bold">E</b> V E N T &nbsp;
            <b style="font-size: 45px; font-weight: bold">Z</b> O N E
          </div>
          <v-divider></v-divider>
        </v-col>
      </v-row>
      <v-row>
        <!-- <v-col v-for="event in paginatedData" :key="event" lg="4" sm="6"> -->
        <v-col lg="4" sm="6">
          <v-card width="370px" flat>
            <v-img :src="require(`@/assets/notice.png`)"> </v-img>

            <v-card-title class="eventCategory blue--text text--lighten-3">
              이벤트 카테고리
            </v-card-title>
            <v-card-subtitle class="eventTitle"> 이벤트 제목 </v-card-subtitle>

            <v-card-text class="eventDate"
              >이벤트 기간 : {{ "이벤트 시작일" }} ~ {{ "이벤트 종료일" }}
            </v-card-text>

            <v-progress-linear
              color="blue lighten-3"
              value="15"
            ></v-progress-linear>
            <v-card-text
              style="text-align: right; font-size: 13px; color: #999"
            >
              이벤트 남은 기간
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "EventEval",
  data() {
    return {};
  },
};
</script>

<style scoped>
.eventTitle {
  font-size: 16px;
  color: #333;
  height: 46px;
  margin-top: 20px;
}
.eventCategory {
  margin-left: 13px;
  display: inline-block;
  font-size: 15px;
  padding: 5px;
  border: 1px solid skyblue;
}
.eventDate {
  font-size: 13px;
  color: #999;
}
</style>
