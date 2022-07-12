<template>
  <div>
    <v-container>
      <v-app-bar app elevation="3">
        <v-toolbar-title class="ml-3"> 📌 환불 관리 </v-toolbar-title>
      </v-app-bar>
      <v-container>
        <v-row class="ml-3 mt-10" style="font-size: 18pt">
          전체 환불 요청 수&nbsp;<span style="color: skyblue">{{
            refundList.length + nonMemberRefundLust.length
          }}</span>
          개
        </v-row>
        <v-row justify="end">
          <v-col class="pt-8 pl-7" style="font-size: 15pt; color: #f48fb1"
            >회원</v-col
          >
          <v-col cols="5">
            <v-text-field
              v-model="keyword"
              append-icon="search"
              label="검색"
              single-line
              color="pink lighten-3"
            ></v-text-field>
          </v-col>
        </v-row>
        <div class="mb-10">
          <v-data-table
            :headers="headers"
            :items="refundList"
            :items-per-page="itemsPerPage"
            :key="refundList.refundNo"
            :page.sync="page"
            :search="keyword"
            hide-default-footer
            @page-count="pageCount = $event"
          >
            <template v-slot:[`item.finalPrice`]="{ item }">
              {{ item.finalPrice | comma }}
            </template>

            <template v-slot:[`item.usedMileage`]="{ item }">
              {{ item.usedMileage | comma }}
            </template>

            <template v-slot:[`item.usedCouponPrice`]="{ item }">
              <span v-if="item.usedCouponPrice != null">{{
                item.usedCouponPrice | comma
              }}</span>
              <span v-if="item.usedCouponPrice == null">0</span>
            </template>

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

        <v-divider></v-divider>

        <v-row justify="end" class="mt-10">
          <v-col class="pt-8 pl-7" style="font-size: 15pt; color: #f48fb1"
            >비회원</v-col
          >
          <v-col cols="5">
            <v-text-field
              v-model="keyword2"
              append-icon="search"
              label="검색"
              single-line
              color="pink lighten-3"
            ></v-text-field>
          </v-col>
        </v-row>
        <div>
          <v-data-table
            :headers="headers2"
            :items="nonMemberRefundLust"
            :items-per-page="itemsPerPage2"
            :key="refundList.refundNo"
            :page.sync="page2"
            :search="keyword2"
            hide-default-footer
            @page-count="pageCount2 = $event"
          >
            <template v-slot:[`item.finalPrice`]="{ item }">
              {{ item.finalPrice | comma }}
            </template>
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
    </v-container>
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
    nonMemberRefundLust: {
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
          width: "90px",
          align: "center",
        },
        {
          text: "요청일",
          value: "refundDate",
          width: "90px",
          align: "center",
        },
        {
          text: "구매자",
          value: "memberId",
          width: "100px",
          align: "center",
        },
        {
          text: "예매번호",
          value: "serial",
          width: "100px",
          align: "center",
        },
        {
          text: "좌석 수",
          value: "seatNameArr.length",
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

      headers2: [
        {
          text: "주문일",
          value: "ticketingDate",
          width: "80px",
          align: "center",
        },
        {
          text: "요청일",
          value: "refundDate",
          width: "80px",
          align: "center",
        },
        {
          text: "휴대번호",
          value: "phone",
          width: "100px",
          align: "center",
        },
        {
          text: "예매번호",
          value: "serial",
          width: "100px",
          align: "center",
        },
        {
          text: "좌석 수",
          value: "seatNameArr.length",
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
      keyword: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 5,
      keyword2: "",
      page2: 1,
      pageCount2: 0,
      itemsPerPage2: 5,
    };
  },

  methods: {
    acceptRefund(val) {
      this.refundNo = val;
      console.log(this.refundNo);

      axios
        .post(`http://localhost:7777/refund/accept/${this.refundNo}`)
        .then(() => {
          //alert("환불 성공");
          this.$router.go();
        })
        .catch(() => {
          //alert("환불 실패");
        });
    },
  },
};
</script>
