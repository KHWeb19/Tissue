<template>
  <div>
    <no-member-ticketing-form
      v-if="performance"
      :performance="performance"
      :ticketingList="ticketingList"
    />
  </div>
</template>
<script>
import NoMemberTicketingForm from "@/components/Ticketing/NoMemberTicketingForm.vue";

import { mapActions, mapState } from "vuex";
export default {
  name: "NoMemberTicketingPage",
  components: {
    NoMemberTicketingForm,
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
    ...mapState(["performance"]),
    ...mapState(["performance", "ticketingList"]),
  },
  created() {
    console.log("1홀페이지");
    console.log(this.$store.state.ticketingList);

    this.$store.state.ticketingList = [];
    this.fetchTicketingList(this.performance.performNo)
      .then(() => {
        //alert("예매좌석 요청 성공");
      })
      .catch(() => {
        //alert("예매좌석 요청 실패");
        //this.$router.push();
      });
  },
  methods: {
    ...mapActions(["fetchPerformance", "fetchTicketingList"]),
  },
  mounted() {
    this.fetchPerformance(this.performNo)
      .then(() => {
        //alert("공연 요청 성공");
      })
      .catch(() => {
        //alert("게시물 요청 실패!");
        this.$router.push();
      });
  },
};
</script>
