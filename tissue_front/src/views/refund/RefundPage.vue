<template>
  <div>
    <refund-list
      :refundList="refundList"
      :nonMemberRefundLust="nonMemberRefundLust"
    />
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
    ...mapState(["refundList", "nonMemberRefundLust"]),
  },
  methods: {
    ...mapActions(["fetchRefundList", "fetchNonMemberRefundList"]),
  },
  mounted() {
    this.fetchRefundList();
    this.fetchNonMemberRefundList();
  },
};
</script>
