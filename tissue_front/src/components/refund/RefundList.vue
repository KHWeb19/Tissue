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
        </v-data-table>
        <div class="text-center">
          <v-pagination
            v-model="page"
            :length="pageCount"
            color="pink lighten-3"
          ></v-pagination>
        </div>
        <div>{{ refundList }}</div>
      </div>
    </v-container>
  </div>
</template>

<script>
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
          class: "blue lighten-3",
        },
        {
          text: "환불접수일",
          value: "refundDate",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "구매자",
          value: "memberId",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "상품",
          value: "performName",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "좌석",
          value: "seatNameArr",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "총 환불액",
          value: "finalPrice",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "사용한 마일리지",
          value: "usedMileage",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "사용한 쿠폰 금액",
          value: "usedCouponPrice",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "수수료",
          value: "cancelCommission",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "주문 상태",
          value: "refundStatus",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
        {
          text: "환불처리",
          value: "",
          width: "100px",
          align: "center",
          class: "blue lighten-3",
        },
      ],

      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
    };
  },
};
</script>
