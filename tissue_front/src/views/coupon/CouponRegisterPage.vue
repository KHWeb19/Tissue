<template>
  <div>
    <coupon-register @submit="onSubmit" />
  </div>
</template>

<script>
import CouponRegister from "@/components/coupon/CouponRegister.vue";
import axios from "axios";

export default {
  name: "CouponRegisterPage",
  components: {
    CouponRegister,
  },

  methods: {
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
        .post("http://localhost:7777/coupon/register", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(() => {
          alert("쿠폰 등록 성공");
          setTimeout(() => {
            this.$router.push({
              name: "CouponListPage",
            });
          }, 300);
        })
        .catch(() => {
          alert("문제 발생");
        });
    },
  },
};
</script>
