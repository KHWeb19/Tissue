<template>
  <div>
    <v-container>
      <v-row>
        <v-col style="display: flex; justify-content: center">
          <v-stepper v-model="e1" width="1300px">
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
                결제
              </v-stepper-step>
            </v-stepper-header>
            <div class="leftInfo">
              <div class="d-flex mt-5 mb-5 mr-5">
                <div class="summaryInfo" style="display: flex">
                  <div>
                    <img
                      class="img mr-3"
                      :src="
                        require(`@/assets/thumbNail/${this.performance.performThumbnail}`)
                      "
                    />
                  </div>
                  <div>
                    <span class="infoTitle">공연명</span>
                    <div class="mb-3 titleInfo">
                      {{ this.performance.performName }}
                    </div>

                    <div class="mb-3 infoTitle">선택한 날짜</div>
                    <span class="infoTitle">공연 시간</span>
                    <div class="titleInfo">
                      {{ this.performance.performTime }}
                    </div>
                  </div>
                </div>
              </div>

              <div v-if="e1 != 1" class="summaryInfo mt-5 mb-5">
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
                <div
                  style="display: flex; justify-content: space-between"
                  class="mb-1 mt-1"
                >
                  <span class="infoTitle">총 금액(+)</span>
                  <div class="salePrice">
                    {{ this.tempSelectPriceInfoCopy | comma }}
                    원
                  </div>
                </div>
                <v-divider></v-divider>

                <span class="infoTitle mb-1">할인 금액</span>
                <div
                  style="display: flex; justify-content: space-between"
                  class="mb-1"
                >
                  <div class="titleInfo">할인 쿠폰</div>

                  <div class="salePrice">
                    - {{ this.couponSalePrice | comma }} 원
                  </div>
                </div>

                <div
                  style="display: flex; justify-content: space-between"
                  class="mb-1"
                >
                  <div class="titleInfo">마일리지</div>
                  <div class="salePrice">
                    - {{ this.mileageSalePrice | comma }} 원
                  </div>
                </div>

                <v-divider></v-divider>
                <div
                  style="display: flex; justify-content: space-between"
                  class="mt-1 mb-1"
                >
                  <span class="infoTitle">총 할인금액(-)</span>

                  <div class="salePrice">
                    - {{ finalSalePrice | comma }}
                    원
                  </div>
                </div>
                <v-divider></v-divider>

                <div
                  style="display: flex; justify-content: space-between"
                  class="mt-1"
                >
                  <span class="infoTitle">최종 금액</span>
                  <div class="salePrice">
                    {{
                      (this.tempSelectPriceInfoCopy - finalSalePrice) | comma
                    }}
                    원
                  </div>
                </div>
              </div>
            </div>

            <v-stepper-items style="border-left: 1px solid lightgrey">
              <v-stepper-content step="1">
                <v-card class="mb-12" flat>
                  <hall-form
                    v-if="hall"
                    :hall="hall"
                    :performance="performance"
                    :ticketingList="ticketingList"
                    v-on:showSelectInfo="showSelectInfo"
                    v-on:selectPrice="selectPrice"
                  />
                </v-card>

                <div style="float: right">
                  <v-btn color="blue lighten-3" text to="/"> 뒤로 </v-btn>
                  <v-btn color="blue lighten-3" text @click="goStep2">
                    좌석 선택 완료
                  </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="2">
                <v-card
                  class="mb-12"
                  flat
                  style="display: flex; justify-content: center"
                >
                  <div>
                    <div style="height: 250px">
                      <v-divider class="mt-5"></v-divider>
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
                      <div class="d-flex" style="overflow: auto">
                        <table class="couponTable">
                          <tr class="tableTr">
                            <th width="300">쿠폰명</th>
                            <th width="100">쿠폰 금액</th>
                            <th width="100">조건</th>
                            <th width="70">사용</th>
                          </tr>
                          <tr v-if="filterCouponList.length == 0">
                            <td colspan="4" class="tableTd">
                              보유한 쿠폰이 없습니다.
                            </td>
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
                              <v-radio-group v-model="selectCouponInfo">
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
                    <v-divider class="mt-10"></v-divider>
                    <div class="mileageBox">
                      <div style="font-size: 20px" class="mt-5 mb-5">
                        마일리지 할인
                      </div>
                      <div>보유</div>
                      <div>{{ memberInfo.memberMileage | comma }} 원</div>
                      <div class="d-flex">
                        <v-text-field
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
                        >적용</v-btn
                      >
                      <v-btn color="blue lighten-3" @click="useAllMileage"
                        >전액 사용</v-btn
                      >
                    </div>
                    <v-divider class="mt-5"></v-divider>
                  </div>
                </v-card>
                <div style="float: right">
                  <v-btn color="blue lighten-3" text @click="backStep1">
                    이전 단계
                  </v-btn>
                  <v-btn color="blue lighten-3" text @click="goStep3">
                    다음 단계
                  </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="3">
                <v-card flat>
                  <payment
                    :finalPrice="finalPrice"
                    :memberInfo="memberInfo"
                    :performance="performance"
                    :mileageSalePrice="mileageSalePrice"
                    :selectCouponInfo="selectCouponInfo"
                    :tempSelectSeatInfo="tempSelectSeatInfo"
                  />
                </v-card>
                <div style="float: right">
                  <v-btn color="blue lighten-3" text @click="e1 = 2">
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
import Payment from "@/components/Ticketing/Payment.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "TicketingForm",
  components: {
    HallForm,
    Payment,
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
    ticketingList: {
      type: Array,
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
      selectCouponInfo: 0,
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
    console.log(this.$store.state.ticketingList);
    this.$store.state.hall = null;

    for (let i = 0; i < this.coupons.length; i++) {
      if (this.coupons[i].couponCategory == this.performance.performCategory) {
        this.filterCouponList.push(this.coupons[i]);
      }
    }
  },

  mounted() {
    this.fetchHall(this.hallNo)
      .then(() => {
        //this.$store.state.hall = res.data;
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
      console.log(this.tempSelectSeatInfo);

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

      if (this.selectCouponInfo.couponCondition == "10만원 이상") {
        if (this.tempSelectPriceInfo < 100000) {
          this.selectCouponInfo = false;
          alert("조건 X");
          return;
        }
      }
      if (this.selectCouponInfo.couponCondition == "5만원 이상") {
        if (this.tempSelectPriceInfo < 50000) {
          this.selectCouponInfo = false;
          alert("조건 X");
          return;
        }
      }
      if (this.selectCouponInfo.couponCondition == "3만원 이상") {
        if (this.tempSelectPriceInfo < 30000) {
          this.selectCouponInfo = false;
          alert("조건 X");
          return;
        }
      }
      if (this.selectCouponInfo.couponCondition == "1만원 이상") {
        if (this.tempSelectPriceInfo < 10000) {
          this.selectCouponInfo = false;
          alert("조건 X");
          return;
        }
      }

      this.couponSalePrice = this.selectCouponInfo.couponPrice;
    },

    resetCoupon() {
      this.couponSalePrice = 0;
      this.selectCouponInfo = 0;
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
  max-height: 150px;
  max-width: 150px;
}
.summaryInfo {
  width: 390px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
}
.infoTitle {
  font-size: 15px;
}
.titleInfo {
  font-size: 12px;
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
  height: 250px;
}
.leftInfo {
  float: left;
  margin-left: 20px;
}
</style>
