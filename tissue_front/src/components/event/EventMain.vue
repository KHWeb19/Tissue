<template>
  <div>
    <div>슬라이더 구역</div>
    <v-container>
      <div class="titleCategory">
        <v-tabs color="blue lighten-3" centered height="70px">
          <v-tab v-for="item in items" :key="item.name" class="tab">{{
            item.name
          }}</v-tab>
          <v-tab-item v-for="n in 2" :key="n">
            <event-coupon v-if="n == 2" :list-array="pageArray" />
          </v-tab-item>
        </v-tabs>
      </div>
    </v-container>
  </div>
</template>

<script>
import EventCoupon from "@/components/event/EventCoupon.vue";
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  name: "EventMain",
  components: {
    EventCoupon,
  },
  data() {
    return {
      pageArray: [],
      items: [{ name: "이벤트" }, { name: "쿠폰" }],
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

<style scoped>
.tab {
  width: 250px;
  font-size: 20px;
}
</style>
