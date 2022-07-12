<template>
  <div>
    <coupon-modify v-if="coupon" :coupon="coupon" @submit="onSubmit" />
  </div>
</template>

<script>
import CouponModify from "@/components/coupon/CouponModify.vue";
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  name: "CouponModifyPage",
  components: {
    CouponModify,
  },
  props: {
    couponNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["coupon"]),
  },
  created() {
    this.$store.state.coupon = null;

    this.fetchCoupon(this.couponNo).catch(() => {
      //alert("조회 실패");
      this.$router.back();
    });
  },
  methods: {
    ...mapActions(["fetchCoupon"]),

    onSubmit(payload) {
      const {
        name,
        selectCategory,
        price,
        selectCondition,
        startDate,
        endDate,
        file,
      } = payload;

      let formData = new FormData();

      if (file != null) {
        formData.append("file", file);
      }
      formData.append("couponName", name);
      formData.append("couponCategory", selectCategory);
      formData.append("couponPrice", price);
      formData.append("couponCondition", selectCondition);
      formData.append("couponStart", startDate);
      formData.append("couponEnd", endDate);

      axios
        .put(`http://localhost:7777/coupon/${this.couponNo}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          //alert("쿠폰 수정 성공");
          setTimeout(() => {
            this.$router.push({
              name: "CouponListPage",
            });
          }, 300);
        })
        .catch(() => {
          //alert("문제 발생");
        });
    },
  },
};
</script>
