<template>
  <div>
    <v-container style="margin-top: 100px">
      <v-row>
        <v-col>
          <v-stepper v-model="e1">
            <v-stepper-header>
              <v-stepper-step
                :complete="e1 > 1"
                step="1"
                color="blue lighten-3"
              >
                좌석 선택
              </v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step
                :complete="e1 > 2"
                step="2"
                color="blue lighten-3"
              >
                할인 / 쿠폰
              </v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step
                :complete="e1 > 3"
                step="3"
                color="blue lighten-3"
              >
                정보 확인
              </v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="4" color="blue lighten-3">
                결제
              </v-stepper-step>
            </v-stepper-header>
            <div class="d-flex">
              <div class="d-flex mt-5 mb-5 mr-5">
                <div class="mr-5">
                  <img
                    class="img mt-7 ml-5"
                    :src="
                      require(`@/assets/thumbNail/${this.performance.performThumbnail}`)
                    "
                  />
                </div>
                <div class="summaryInfo">
                  <span class="infoTitle">공연명</span>
                  <div class="mb-3 titleInfo">
                    {{ this.performance.performName }}
                  </div>
                  <div class="mb-3">선택한 날짜</div>
                  <span class="infoTitle">공연 시간</span>
                  <div class="mr-5 titleInfo">
                    {{ this.performance.performTime }}
                  </div>
                </div>
              </div>

              <div v-if="e1 != 1" class="summaryInfo mt-5 mb-5 mr-5">
                <span class="infoTitle">선택한 좌석</span>
                <div style="height: 70px">
                  <span
                    class="titleInfo"
                    v-for="(index, temp) in tempSelectSeatInfo"
                    :key="temp"
                    >({{ index.grade }}석){{ index.info }}</span
                  >
                </div>
                <span class="infoTitle">좌석 매수</span>
                <div class="titleInfo">{{ tempSelectSeatInfo.length }} 매</div>
              </div>
              <div v-if="e1 != 1" class="summaryInfo mt-5 mb-5">
                <span class="infoTitle">총 금액</span>
                <div class="titleInfo">{{ tempSelectPriceInfo }} 원</div>
                <span>할인/쿠폰 등 할인 내역 보여줄 예정</span>
              </div>
            </div>
            <v-divider></v-divider>
            <v-stepper-items>
              <v-stepper-content step="1">
                <v-card class="mb-12">
                  <hall-form
                    v-if="hall"
                    :hall="hall"
                    :performance="performance"
                    v-on:showSelectInfo="showSelectInfo"
                    v-on:selectPrice="selectPrice"
                  />
                </v-card>

                <div style="float: right">
                  <v-btn color="pink lighten-3" text @click="goStep2">
                    좌석 선택 완료
                  </v-btn>

                  <v-btn color="pink lighten-3" text to="/"> 뒤로 </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="2">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>
                <div style="float: right">
                  <v-btn color="pink lighten-3" text @click="e1 = 3">
                    다음 단계
                  </v-btn>

                  <v-btn color="pink lighten-3" text @click="backStep1">
                    이전 단계
                  </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="3">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>
                <div style="float: right">
                  <v-btn color="pink lighten-3" text @click="e1 = 4">
                    다음 단계
                  </v-btn>

                  <v-btn color="pink lighten-3" text @click="e1 = 2">
                    이전 단계
                  </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="4">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>
                <div style="float: right">
                  <v-btn color="pink lighten-3" text @click="e1 = 1">
                    다음 단계
                  </v-btn>

                  <v-btn color="pink lighten-3" text @click="e1 = 3">
                    이전 단계
                  </v-btn>
                </div>
              </v-stepper-content>
            </v-stepper-items>
          </v-stepper>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import HallForm from "@/components/Hall/HallForm.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "TicketingForm",
  components: {
    HallForm,
  },
  props: {
    performance: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      e1: 1,
      hallNo: this.performance.hallName,
      selectSeatInfo: "",
      selectSeatPrice: "",
      tempSelectSeatInfo: "",
      tempSelectPriceInfo: 0,
    };
  },

  computed: {
    ...mapState(["hall"]),
  },
  created() {
    //this.$store.state.hall = null;

    this.fetchHall(this.hallNo)
      .then(() => {
        alert("공연장 요청 성공");
      })
      .catch(() => {
        alert("공연장 요청 실패");
        this.$router.push();
      });
  },
  methods: {
    ...mapActions(["fetchHall"]),

    showSelectInfo(val) {
      this.selectSeatInfo = val;
      console.log("에밋" + this.selectSeatInfo);
    },

    selectPrice(val) {
      this.selectSeatPrice = val;
      console.log("에밋" + this.selectSeatPrice);
    },

    goStep2() {
      if (this.selectSeatInfo.length == 0) {
        alert("1개 이상의 좌석을 선택해주세요");
      } else {
        this.e1 = 2;
        this.tempSelectSeatInfo = this.selectSeatInfo;
        this.tempSelectPriceInfo = this.selectSeatPrice;
      }
    },
    backStep1() {
      this.e1 = 1;
      this.tempSelectSeatInfo = "";
      this.tempSelectPriceInfo = 0;
    },
  },
};
</script>

<style scoped>
.img {
  position: relative;
  max-height: 100px;
  max-width: 100px;
  margin: 10px;
}
.summaryInfo {
  width: 390px;
  border: 1px solid skyblue;
  padding: 10px;
  border-radius: 15px;
}
.infoTitle {
  font-size: 20px;
}
.titleInfo {
  font-size: 13px;
  font-weight: lighter;
}
</style>
