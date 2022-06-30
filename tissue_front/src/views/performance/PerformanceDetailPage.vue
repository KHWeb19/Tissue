<template>
  <div>
    <performance-detail
      v-if="performance && performanceEvent"
      :performance="performance"
      :couponList="couponList"
      :performanceEvent="performanceEvent"
    />
  </div>
</template>

<script>
import PerformanceDetail from "@/components/performance/PerformanceDetail.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "PerformanceDetailPage",
  components: {
    PerformanceDetail,
  },
  props: {
    performNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["performance", "couponList", "performanceEvent"]),
  },
  mounted() {
    this.fetchPerformance(this.performNo);
    this.fetchCouponList();
    this.fetchPerformanceEvent(this.performNo);
  },
  methods: {
    ...mapActions([
      "fetchPerformance",
      "fetchCouponList",
      "fetchPerformanceEvent",
    ]),
  },
};
</script>
