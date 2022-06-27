<template>
  <div>
    <v-container style="width: 1300px">
      <v-row>
        <v-col>
          <div class="mainTitle">T H E A T E R</div>
          <div class="categoryBox">
            <v-tabs color="blue lighten-3" centered height="70px">
              <v-tab
                class="black--text sortBtn"
                v-for="item in sort"
                :key="item.name"
                >{{ item.name }}</v-tab
              >
            </v-tabs>
          </div>
          <div class="countBox">
            현재 예매가능한 공연은
            <b style="color: skyblue">{{ theaterList.length }}개</b>
            입니다.
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col
          v-for="perform in theaterList"
          :key="perform.title"
          lg="3"
          sm="6"
        >
          <router-link
            :to="{
              name: 'PerformanceDetailPage',
              params: { performNo: perform.performNo },
            }"
          >
            <v-card class="mx-auto" max-width="216" height="410" flat>
              <v-img
                :src="
                  require(`../../assets/thumbNail/${perform.performThumbnail}`)
                "
                height="300px"
              ></v-img>

              <v-card-title class="performTitle mb-1">
                {{ perform.performName }}
              </v-card-title>

              <v-card-subtitle class="performSub pb-0">
                {{ perform.performStart }} ~
                {{ perform.performEnd }}</v-card-subtitle
              >
              <v-card-subtitle class="performSub pt-0">
                {{ perform.performArea }}</v-card-subtitle
              >
            </v-card>
          </router-link>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "TheaterForm",
  props: {
    theaterList: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      sort: [{ name: "평점순" }, { name: "최신순" }, { name: "종료임박 순" }],
    };
  },
};
</script>

<style scoped>
.mainTitle {
  font-size: 50px;
  text-align: center;
  margin-bottom: 20px;
  margin-top: 50px;
}
.categoryBox {
  padding-bottom: 20px;
  border-bottom: 2px solid black;
}
.sortBtn {
  font-size: 15px;
}
.countBox {
  margin: 50px;
  text-align: center;
  font-size: 20px;
}
.performTitle {
  font-size: 30px;
  justify-content: center;
  color: black;
}
.performSub {
  font-size: 15px;
  text-align: center;
}
</style>
