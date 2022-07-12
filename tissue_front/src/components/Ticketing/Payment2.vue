<template>
  <div>
    <div style="display: flex; justify-content: center">
      <div>
        <table class="mt-5 mb-8" style="width: 100%">
          <tr class="trTitle">
            <td>취소일</td>
            <td>취소 수수료</td>
          </tr>
          <tr>
            <td>에매 후 7일 이내</td>
            <td>없음</td>
          </tr>
          <tr>
            <td>예매 후 8일 ~ 관람일 10일 전까지</td>
            <td>장당 2,000원</td>
          </tr>
          <tr>
            <td>관람일 9일 전 ~ 관람일 5일 전까지</td>
            <td>티켓 금액의 10%</td>
          </tr>
          <tr>
            <td>~ 취소 마감일시까지</td>
            <td>티켓 금액의 30%</td>
          </tr>
        </table>
        예매 후 7일 이내라도 취소시점이 관람일로부터 10일 이내라면 그에 해당하는
        취소수수료가 부과됩니다. <br />
        관람일 당일 취소 가능한 상품의 경우 관람일 당일 취소 시 티켓금액의 90%가
        부과됩니다. <br />
        <span style="color: red"
          >상품의 특성에 따라 취소수수료 정책이 달라질 수 있습니다. (각 상품
          예매 시 취소수수료 확인)
        </span>
        <br />
      </div>
    </div>
    <div style="display: flex; justify-content: center">
      <v-checkbox v-model="checkbox1" class="mr-1">
        <template v-slot:label>
          <span style="font-size: 12px"
            >취소수수료 및 취소기한을 확인하였으며, 동의합니다.</span
          >
        </template>
      </v-checkbox>
      <v-checkbox v-model="checkbox2" class="ml-1">
        <template v-slot:label>
          <span style="font-size: 12px">제3자 정보제공 내용에 동의합니다.</span>
        </template>
      </v-checkbox>
    </div>

    <section class="payBox">
      <v-btn
        width="200px"
        color="blue lighten-3"
        large
        @click="PaymentBtn"
        :disabled="checkbox1 == false || checkbox2 == false"
        >결제</v-btn
      >
    </section>

    <v-dialog v-model="dialog" width="500" content-class="elevation-2">
      <v-card>
        <v-card-title class="grey lighten-2"> 🎈 예매 완료 </v-card-title>
        <v-divider />
        <v-card-text
          class="mt-10 mb-4"
          style="text-align: center; font-size: 13pt"
        >
          회원님의 예매 번호는
          <span style="color: pink">{{ this.serial }} </span> 입니다.
        </v-card-text>
        <v-card-text>
          * 비회원은 예매번호를 통해 예매조회가 가능합니다.
        </v-card-text>
        <v-card-actions>
          <v-spacer />
          <v-btn @click="goHome" color="blue lighten-3" depressed dark>
            확인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";
const { IMP } = window;

export default {
  name: "PayBox",
  data() {
    return {
      price: 0,
      checkbox1: false,
      checkbox2: false,
      serial: "",
      dialog: false,
    };
  },
  props: {
    finalPrice: {
      type: Number,
      required: true,
    },
    phone: {
      type: String,
    },
    performance: {
      type: Object,
      required: true,
    },
    tempSelectSeatInfo: {
      required: true,
    },
  },

  created() {
    document.cookie = "safeCookie1=foo; SameSite=Lax";
    document.cookie = "safeCookie2=foo";
    document.cookie = "crossCookie=bar; SameSite=None; Secure";
  },
  methods: {
    goHome() {
      this.$router.push("/");
    },
    PaymentBtn: function () {
      this.price = this.finalPrice;
      let seatNameArr = [];
      for (let i = 0; i < this.tempSelectSeatInfo.length; i++) {
        seatNameArr.push(this.tempSelectSeatInfo[i].seatName);
      }

      let today = new Date();

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1; // 월
      let date = today.getDate(); // 날짜
      let hours = today.getHours(); // 시
      let minutes = today.getMinutes(); // 분
      let seconds = today.getSeconds();

      let createUid =
        year +
        "-" +
        month +
        "-" +
        date +
        "/" +
        hours +
        "-" +
        minutes +
        "-" +
        seconds +
        "//" +
        console.log(createUid);

      IMP.init("imp38099687");

      IMP.request_pay(
        {
          // param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: createUid,
          name: this.performance.performName,
          amount: 100,
          buyer_tel: this.phone,
        },
        (rsp) => {
          // callback
          console.log(rsp);
          if (rsp.success) {
            console.log("결제 성공");

            axios
              .post("http://localhost:7777/ticketing/register2", {
                performNo: this.performance.performNo,
                seatNameArr: seatNameArr,
                phone: this.phone,
                finalPrice: this.price,
              })
              .then((res) => {
                //alert("DB 저장 완료");
                this.serial = res.data;
                this.dialog = true;
              });
          } else {
            console.log("결제 실패");
          }
        }
      );
    },
  },
};
</script>

<style scoped>
.payBox {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
}
.trTitle {
  background-color: rgb(250, 215, 221);
  font-weight: bold;
}
table {
  width: 100%;
  text-align: center;
  border-collapse: collapse;
  border-spacing: 0;
}
td {
  border-bottom: 1px solid rgb(196, 196, 196);
  padding: 12px;
}
</style>
