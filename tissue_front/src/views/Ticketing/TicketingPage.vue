<template>
  <div>
    <ticketing-form
      v-if="performance"
      :performance="performance"
      :coupons="memberInfo.coupons"
      :memberInfo="memberInfo"
      :ticketingList="ticketingList"
    />
  </div>
</template>

<script>
import TicketingForm from "@/components/Ticketing/TicketingForm.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "TicketingPage",
  components: {
    TicketingForm,
  },
  data() {
    return {
      token: localStorage.getItem("token"),
    };
  },
  props: {
    performNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["performance", "memberInfo", "ticketingList"]),
  },
  created() {
    this.fetchMemberInfo(this.token);
  },
  methods: {
    ...mapActions([
      "fetchPerformance",
      "fetchMemberInfo",
      "fetchTicketingList",
    ]),
  },
  mounted() {
    this.fetchPerformance(this.performNo)
      .then(() => {
        alert("공연 요청 성공");
      })
      .catch(() => {
        alert("게시물 요청 실패!");
        this.$router.push();
      });

    this.fetchTicketingList(this.performance.performNo)
      .then(() => {
        alert("예매좌석 요청 성공");
      })
      .catch(() => {
        alert("예매좌석 요청 실패");
        this.$router.push();
      });
  },
};
</script>
