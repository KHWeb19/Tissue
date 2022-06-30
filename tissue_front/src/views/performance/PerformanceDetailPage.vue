<template>
  <div>
    <performance-detail
      v-if="performance"
      :performance="performance"
      :couponList="couponList"
      :likeList="likeList"
      @update:likeList="likeList = $event"
    />
  </div>
</template>

<script>
import PerformanceDetail from "@/components/performance/PerformanceDetail.vue";
import { mapActions, mapMutations, mapState } from "vuex";

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
      likeList: {
          ...mapState({get:'likeList'}),
          ...mapMutations({set:'FETCH_PERFORMANCE_LIKE'})
      },
    ...mapState(["performance", "couponList"]),
  },
  mounted() {
    this.fetchPerformance(this.performNo);
    this.fetchCouponList();
    this.fetchPerformanceLike(this.performNo);
  },
  methods: {
    ...mapActions(["fetchPerformance", "fetchCouponList", "fetchPerformanceLike"]),
  },
};
</script>
