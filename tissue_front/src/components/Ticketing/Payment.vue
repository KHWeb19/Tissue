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
    <div style="display: flex; font-size: 15px">
      <v-checkbox
        v-model="checkbox1"
        label="취소수수료 및 취소기한을 확인하였으며, 동의합니다."
      ></v-checkbox>
      <v-checkbox
        v-model="checkbox2"
        label="제3자 정보제공 내용에 동의합니다."
      ></v-checkbox>
    </div>
    <section class="payBox">
      <div @click="PaymentBtn">결제</div>
    </section>
  </div>
</template>

<script>
const { IMP } = window;

export default {
  name: "PayBox",
  data() {
    return {
      price: 0,
      checkbox1: false,
      checkbox2: false,
    };
  },
  props: {
    finalPrice: {
      type: Number,
      required: true,
    },
    memberInfo: {
      type: Object,
      required: true,
    },
    performance: {
      type: Object,
      required: true,
    },
  },

  created() {
    document.cookie = "safeCookie1=foo; SameSite=Lax";
    document.cookie = "safeCookie2=foo";
    document.cookie = "crossCookie=bar; SameSite=None; Secure";
  },
  methods: {
    PaymentBtn: function () {
      this.price = this.finalPrice;

      IMP.init("imp38099687");

      IMP.request_pay(
        {
          // param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "123",
          name: this.performance.performName,
          amount: this.price,
          buyer_email: this.memberInfo.memberEmail,
          buyer_name: this.memberInfo.memberName,
          buyer_tel: this.memberInfo.memberPhone,
          buyer_addr: this.memberInfo.memberAddress,
          buyer_postcode: this.memberInfo.addZipCode,
        },
        (rsp) => {
          // callback
          console.log(rsp);
          if (rsp.success) {
            console.log("결제 성공");
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
