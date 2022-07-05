<template>
  <section class="payBox">
    <div @click="PaymentBtn">결제</div>
  </section>
</template>

<script>
const { IMP } = window;

export default {
  name: "PayBox",
  data() {
    return {
      price: 0,
    };
  },
  props: {
    finalPrice: {
      type: Number,
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
      this.buyName = this.memberInfo.memberName;

      console.log(this.price + "" + this.buyName);

      IMP.init("imp38099687");

      IMP.request_pay(
        {
          // param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "123",
          name: "테스트",
          amount: this.price,
          buyer_email: "lgh9758@naver.com",
          buyer_name: "테스터",
          buyer_tel: "010-8685-9758",
          buyer_addr: "서울특별시 강남구",
          buyer_postcode: "01234",
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
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input {
  width: 300px;
  border: 0;
  border-bottom: 1px #a8a8a8 solid;
  margin: 0 10px 0;
  padding: 0;
  height: 40px;
  line-height: 40px;
  outline: none;
}
.payBox {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
}
div {
  width: 200px;
  height: 40px;
  background-color: #ffffff;
  border: 1px #a8a8a8 solid;
  color: black;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
</style>
