<template>
  <div>
    <v-container style="width: 1300px">
      <v-row>
        <v-col>
          <div class="mainTitle">M U S I C A L</div>
          <div class="categoryBox">
            <v-tabs color="blue lighten-3" centered height="70px">
              <v-tab class="black--text sortBtn" @click="sortOrig"
                >평점순</v-tab
              >
              <v-tab class="black--text sortBtn" @click="sortRecent"
                >최신순</v-tab
              >
              <v-tab class="black--text sortBtn" @click="sortEnd"
                >종료임박순</v-tab
              >
            </v-tabs>
          </div>
          <div class="countBox">
            현재 예매가능한 공연은
            <b style="color: skyblue">{{ musicalList.length }}개</b>
            입니다.
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col v-for="perform in copyData" :key="perform.title" lg="3" sm="6">
          <router-link
            :to="{
              name: 'PerformanceDetailPage',
              params: { performNo: perform.performNo.toString() },
            }"
          >
            <v-card class="mx-auto" max-width="216" flat>
              <v-img
              class="mb-5"
                :src="
                  require(`../../assets/thumbNail/${perform.performThumbnail}`)
                "
                height="300px"
              ></v-img>

            <div class="performTitle">
                {{ perform.performName }}
            </div>

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
  name: "MusicalForm",
  props: {
    musicalList: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      originalData: [...this.musicalList],
      copyData: this.musicalList,
    };
  },
  methods: {
    sortOrig() {
      this.copyData = [...this.originalData];
    },
    sortRecent() {
      this.copyData.sort(function (a, b) {
        return new Date(b.performStart) - new Date(a.performStart);
      });
    },
    sortEnd() {
      this.copyData.sort(function (a, b) {
        return new Date(a.performEnd) - new Date(b.performEnd);
      });
    },
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
  font-size: 18px;
  text-align: center;
  justify-content: center;
  color: black;
}
.performSub {
  font-size: 13px;
  text-align: center;
    font-family: 'Nanum Gothic', sans-serif !important;
}
</style>
