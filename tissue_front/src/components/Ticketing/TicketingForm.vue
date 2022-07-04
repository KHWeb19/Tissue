<template>
  <div>
    <v-container>
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
                <span class="infoTitle">할인 금액</span>
                <div class="d-flex">
                  <div class="titleInfo">할인 쿠폰</div>

                  <div class="salePrice">
                    - {{ this.couponSalePrice | comma }} 원
                  </div>
                </div>
                <div class="d-flex">
                  <div class="titleInfo">마일리지</div>
                  <div class="salePrice">
                    - {{ this.mileageSalePrice | comma }} 원
                  </div>
                </div>
                <span class="infoTitle">총 할인액</span>

                <div class="salePrice">
                  {{ finalSalePrice | comma }}
                  원
                </div>
                <span class="infoTitle">총 금액</span>
                <div class="salePrice">
                  {{ (this.tempSelectPriceInfoCopy - finalSalePrice) | comma }}
                  원
                </div>
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
                  <v-btn color="blue lighten-3" text @click="goStep2">
                    좌석 선택 완료
                  </v-btn>

                  <v-btn color="blue lighten-3" text to="/"> 뒤로 </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="2">
                <v-card class="mb-12" flat>
                  <div style="display: flex; justify-content: center">
                    <div class="mr-10">
                      <div
                        style="display: flex; justify-content: space-between"
                      >
                        <div style="font-size: 20px" class="mt-5 mb-5">
                          쿠폰 할인
                        </div>
                        <v-btn
                          @click="resetCoupon"
                          color="blue lighten-3"
                          class="mt-4"
                          >쿠폰 미선택</v-btn
                        >
                      </div>
                      <div class="d-flex">
                        <table class="couponTable">
                          <tr class="tableTr">
                            <th width="300">쿠폰명</th>
                            <th width="100">쿠폰 금액</th>
                            <th width="100">조건</th>
                            <th width="70">사용</th>
                          </tr>
                          <tr
                            v-for="coupon in filterCouponList"
                            :key="coupon.couponName"
                            style="height: 20px"
                          >
                            <td class="tableTd">
                              [{{ coupon.couponCategory }}]
                              {{ coupon.couponName }}
                            </td>
                            <td class="tableTd">
                              {{ coupon.couponPrice | comma }}원
                            </td>
                            <td class="tableTd">
                              {{ coupon.couponCondition }}
                            </td>
                            <td class="tableTd">
                              <v-radio-group v-model="radioGroup">
                                <v-radio
                                  :value="coupon"
                                  @click="selectCoupon"
                                  class="ml-5"
                                  color="pink lighten-3"
                                ></v-radio>
                              </v-radio-group>
                            </td>
                          </tr>
                        </table>
                      </div>
                    </div>

                    <div class="ml-10 mileageBox">
                      <div style="font-size: 20px" class="mt-5 mb-5">
                        마일리지 할인
                      </div>
                      <div>보유</div>
                      <div>{{ memberInfo.memberMileage | comma }} 원</div>
                      <div class="d-flex">
                        <v-text-field
                          type="number"
                          v-model="inputMileage"
                          color="pink lighten-3"
                          suffix="원"
                        />
                        <v-btn
                          color="pink lighten-3"
                          @click="resetMileage"
                          class="mt-4"
                          icon
                          ><v-icon>mdi-close-circle-outline</v-icon></v-btn
                        >
                      </div>
                      <v-btn color="blue lighten-3" @click="useSelectMileage"
                        >적용용</v-btn
                      >
                      <v-btn color="blue lighten-3" @click="useAllMileage"
                        >전액 사용</v-btn
                      >
                    </div>
                  </div>
                </v-card>
                <div style="float: right">
                  <v-btn color="blue lighten-3" text @click="goStep3">
                    다음 단계
                  </v-btn>

                  <v-btn color="blue lighten-3" text @click="backStep1">
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
                  <v-btn color="blue lighten-3" text @click="e1 = 4">
                    다음 단계
                  </v-btn>

                  <v-btn color="blue lighten-3" text @click="e1 = 2">
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
                  <v-btn color="blue lighten-3" text @click="e1 = 1">
                    다음 단계
                  </v-btn>

                  <v-btn color="blue lighten-3" text @click="e1 = 3">
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
    coupons: {
      type: Array,
    },
    memberInfo: {
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
      tempSelectPriceInfoCopy: 0,
      radioGroup: 0,
      inputMileage: 0,
      filterCouponList: [],
      couponSalePrice: 0,
      mileageSalePrice: 0,
    };
  },

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },

  computed: {
    ...mapState(["hall"]),

    finalSalePrice() {
      return Number(this.couponSalePrice) + Number(this.mileageSalePrice);
    },
    finalPrice() {
      return this.tempSelectPriceInfoCopy - this.finalSalePrice;
    },
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

  mounted() {
    for (let i = 0; i < this.coupons.length; i++) {
      if (this.coupons[i].couponCategory == this.performance.performCategory) {
        this.filterCouponList.push(this.coupons[i]);
      }
    }
  },
  methods: {
    ...mapActions(["fetchHall"]),

    showSelectInfo(val) {
      this.selectSeatInfo = val;
    },

    selectPrice(val) {
      this.selectSeatPrice = val;
    },

    goStep2() {
      if (this.selectSeatInfo.length == 0) {
        alert("1개 이상의 좌석을 선택해주세요");
      } else {
        this.e1 = 2;
        this.tempSelectSeatInfo = this.selectSeatInfo;
        this.tempSelectPriceInfo = this.selectSeatPrice;
        this.tempSelectPriceInfoCopy = this.selectSeatPrice;
      }
    },
    goStep3() {
      console.log(this.finalPrice);

      this.e1 = 3;
    },
    backStep1() {
      this.e1 = 1;
      this.tempSelectSeatInfo = "";
      this.tempSelectPriceInfo = 0;
      this.tempSelectPriceInfoCopy = 0;
    },

    selectCoupon() {
      this.tempSelectPriceInfoCopy = this.tempSelectPriceInfo;
      this.couponSalePrice = 0;

      if (this.radioGroup.couponCondition == "10만원 이상") {
        if (this.tempSelectPriceInfo < 100000) {
          this.radioGroup = false;
          alert("조건 X");
          return;
        }
      }
      if (this.radioGroup.couponCondition == "5만원 이상") {
        if (this.tempSelectPriceInfo < 50000) {
          this.radioGroup = false;
          alert("조건 X");
          return;
        }
      }
      if (this.radioGroup.couponCondition == "3만원 이상") {
        if (this.tempSelectPriceInfo < 30000) {
          this.radioGroup = false;
          alert("조건 X");
          return;
        }
      }
      if (this.radioGroup.couponCondition == "1만원 이상") {
        if (this.tempSelectPriceInfo < 10000) {
          this.radioGroup = false;
          alert("조건 X");
          return;
        }
      }

      this.couponSalePrice = this.radioGroup.couponPrice;
    },

    resetCoupon() {
      this.couponSalePrice = 0;
      this.radioGroup = 0;
    },
    useSelectMileage() {
      if (this.inputMileage > this.memberInfo.memberMileage) {
        alert("보유한 마일리지만 사용가능합니다.");
        this.inputMileage = 0;
        return;
      }
      this.mileageSalePrice = this.inputMileage;
    },
    useAllMileage() {
      this.mileageSalePrice = this.memberInfo.memberMileage;
      this.inputMileage = this.mileageSalePrice;
    },
    resetMileage() {
      this.mileageSalePrice = 0;
      this.inputMileage = 0;
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
  border: 1px solid #f48fb1;
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
.couponTable {
  border-collapse: collapse;
  text-align: center;
  border-left: 1px solid lightgrey;
  border-right: 1px solid lightgrey;
}
.tableTr {
  background-color: #90caf9;
}
.tableTd {
  border-bottom: 1px solid lightgrey;
  font-size: 12px;
}
.salePrice {
  font-size: 12px;
}
.mileageBox {
  width: 300px;
}
</style>
