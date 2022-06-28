<template>
  <div>
    <performance-detail
      v-if="performance"
      :performance="performance"
      :couponList="couponList"
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
    ...mapState(["performance", "couponList"]),
  },
  mounted() {
    this.fetchPerformance(this.performNo);
    this.fetchCouponList();
  },
  methods: {
    ...mapActions(["fetchPerformance", "fetchCouponList"]),
  },
};
</script>
