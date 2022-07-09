<template>
  <div>
    <v-container>
      <v-app-bar app elevation="3">
        <v-toolbar-title class="ml-3"> 📌 환불 관리 </v-toolbar-title>
      </v-app-bar>

      <div>
        <v-data-table
          :headers="headers"
          :items="refundList"
          :items-per-page="itemsPerPage"
          :key="refundList.refundNo"
          :page.sync="page"
          hide-default-footer
          @page-count="pageCount = $event"
        >
          <template v-slot:[`item.refundStatus`]="{ item }">
            <v-chip
              color="pink lighten-3"
              outlined
              v-if="item.refundStatus === '취소 대기'"
            >
              환불 대기
            </v-chip>
            <v-chip
              v-else-if="item.refundStatus == '환불 완료'"
              color="blue lighten-3"
              outlined
            >
              환불 완료
            </v-chip>
            <v-chip v-else color="grey" outlined> 취소완료 </v-chip>
          </template>
          <template v-slot:[`item.accept`]="{ item }">
            <v-btn
              color="blue lighten-3"
              v-if="item.refundStatus == '취소 대기'"
              @click="acceptRefund(item.refundNo)"
              >환불 수락</v-btn
            >
            <v-btn text disabled v-if="item.refundStatus == '취소완료'"
              >환불 완료</v-btn
            >
          </template>
        </v-data-table>
        <div class="text-center">
          <v-pagination
            v-model="page"
            :length="pageCount"
            color="pink lighten-3"
            circle
          ></v-pagination>
        </div>
      </div>
    </v-container>
    <div>{{ refundList }}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "RefundList",
  props: {
    refundList: {
      type: Array,
      required: true,
    },
  },

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  data() {
    return {
      headers: [
        {
          text: "주문일",
          value: "ticketingDate",
          width: "100px",
          align: "center",
        },
        {
          text: "환불접수일",
          value: "refundDate",
          width: "100px",
          align: "center",
        },
        {
          text: "구매자",
          value: "memberId",
          width: "100px",
          align: "center",
        },
        {
          text: "상품",
          value: "performName",
          width: "100px",
          align: "center",
        },
        {
          text: "좌석",
          value: "seatNameArr",
          width: "100px",
          align: "center",
        },
        {
          text: "총 환불액",
          value: "finalPrice",
          width: "100px",
          align: "center",
        },
        {
          text: "사용한 마일리지",
          value: "usedMileage",
          width: "100px",
          align: "center",
        },
        {
          text: "사용한 쿠폰 금액",
          value: "usedCouponPrice",
          width: "100px",
          align: "center",
        },
        {
          text: "수수료",
          value: "cancelCommission",
          width: "100px",
          align: "center",
        },
        {
          text: "주문 상태",
          value: "refundStatus",
          width: "100px",
          align: "center",
        },
        {
          text: "환불처리",
          value: "accept",
          width: "100px",
          align: "center",
        },
      ],

      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
    };
  },

  methods: {
    acceptRefund(val) {
      this.refundNo = val;
      console.log(this.refundNo);

      axios
        .post(`http://localhost:7777/refund/accept/${this.refundNo}`)
        .then(() => {
          alert("환불 성공");
          this.$router.go();
        })
        .catch(() => {
          alert("환불 실패");
        });
    },
  },
};
</script>
