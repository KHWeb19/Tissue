<template>
  <div>
    <coupon-list :list-array="pageArray" />
  </div>
</template>

<script>
import CouponList from "@/components/coupon/CouponList.vue";
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  name: "CouponListPage",
  components: {
    CouponList,
  },

  data() {
    return {
      pageArray: [],
    };
  },

  computed: {
    ...mapState(["couponList"]),
  },
  mounted() {
    this.fetchCouponList();
  },
  methods: {
    ...mapActions(["fetchCouponList"]),
  },

  created() {
    axios
      .get("http://localhost:7777/coupon/list")
      .then((res) => {
        this.pageArray = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
