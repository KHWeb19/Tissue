<template>
  <div>
    <refund-list :refundList="refundList" />
  </div>
</template>

<script>
import RefundList from "@/components/refund/RefundList.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "RefundPage",
  components: {
    RefundList,
  },

  computed: {
    ...mapState(["refundList"]),
  },
  methods: {
    ...mapActions(["fetchRefundList"]),
  },
  mounted() {
    this.fetchRefundList();
  },
};
</script>
