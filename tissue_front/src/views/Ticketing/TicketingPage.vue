<template>
  <div>
    <ticketing-form v-if="performance" :performance="performance" />
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
  props: {
    performNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["performance"]),
  },
  methods: {
    ...mapActions(["fetchPerformance"]),
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
  },
};
</script>
