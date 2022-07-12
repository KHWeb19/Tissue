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
                <v-container class="cantBox">
                  <div class="cant">현재 [비회원] 예매입니다.</div>
                  <div class="cant2">📌쿠폰 및 마일리지 사용 불가능</div>
                </v-container>
                <div style="font-size: 14pt" class="mb-7">
                  ✋ 예매 번호 찾기에 필요한 휴대폰 번호를 입력해주세요
                </div>
                <v-row no-gutters>
                  <v-col cols="8">
                    <v-text-field
                      dense
                      v-model="phone"
                      color="pink lighten-3"
                      placeholder="휴대폰 번호 ('-') 없이 입력"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="2">
                    <v-btn
                      fab
                      @click="ckPhone(phone.toString())"
                      dark
                      color="blue lighten-3"
                      depressed
                      >인증</v-btn
                    >
                  </v-col>
                </v-row>
                <v-row no-gutters>
                  <v-col cols="4">
                    <v-text-field
                      dense
                      color="pink lighten-3"
                      placeholder="인증번호"
                      v-model="checkNum"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="2" class="mb-2">
                    <v-btn
                      dark
                      @click="ckNum()"
                      depressed
                      fab
                      :color="
                        this.checkPhoneCondition == true
                          ? 'pink lighten-4'
                          : 'blue lighten-3'
                      "
                    >
                      확인</v-btn
                    >
                  </v-col>
                </v-row>
                <div style="float: right">
                  <v-btn color="blue lighten-3" text @click="backStep1">
                    이전 단계
                  </v-btn>
                  <v-btn
                    color="blue lighten-3"
                    text
                    @click="goStep3"
                    :disabled="pass == false"
                  >
                    다음 단계
                  </v-btn>
                </div>
              </v-stepper-content>

              <v-stepper-content step="3">
                <v-card flat>
                  <payment-2
                    :finalPrice="finalPrice"
                    :phone="phone"
                    :performance="performance"
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
import { mapActions, mapState } from "vuex";
import Payment2 from "./Payment2.vue";
import axios from "axios";

export default {
  name: "TicketingForm",
  components: {
    HallForm,
    Payment2,
  },
  props: {
    performance: {
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
      phone: "",
      authNum: "",
      checkNum: "",
      sendAuth: false,
      pass: false,
      checkPhoneCondition: false,
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
    console.log("2홀폼");
    console.log(this.$store.state.ticketingList);
    this.$store.state.hall = null;

    /*
    for (let i = 0; i < this.coupons.length; i++) {
      if (this.coupons[i].couponCategory == this.performance.performCategory) {
        this.filterCouponList.push(this.coupons[i]);
      }
    }*/
  },

  mounted() {
    this.fetchHall(this.hallNo)
      .then(() => {
        //this.$store.state.hall = res.data;
        //alert("공연장 요청 성공");
      })
      .catch(() => {
        //alert("공연장 요청 실패");
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
    ckPhone(phone) {
      axios
        .get(`phone/check/${phone}`)
        .then((res) => {
          alert("인증번호가 전송되었습니다.");
          this.authNum = res.data;
          this.sendAuth = true;
        })
        .catch(() => {
          console.log("전송실패");
        });
    },
    ckNum() {
      if (this.checkNum == this.authNum) {
        this.pass = true;
        this.checkPhoneCondition = true;
        alert("인증되셨습니다.");
      } else {
        alert("인증번호가 올바르지 않습니다.");
      }
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
.cant {
  text-align: center;
  font-size: 20pt;
  margin: auto;
}
.cant2 {
  text-align: center;
  font-size: 15pt;
  margin: auto;
}
.cantBox {
  position: relative;
  width: 100%;
  line-height: 100px;
  height: 500px;
}
</style>
