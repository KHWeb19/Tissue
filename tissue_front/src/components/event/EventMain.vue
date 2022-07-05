<template>
  <div>
    <div><event-banner/></div>
    <v-container>
      <div class="titleCategory">
        <v-tabs color="blue lighten-3" centered height="70px">
          <v-tab v-for="item in items" :key="item.name" class="tab">{{
            item.name
          }}</v-tab>
          <v-tab-item v-for="n in 2" :key="n">
            <event-tab v-if="n == 1" :eventPageArray="eventPageArray" />
            <event-coupon v-else :list-array="pageArray" />
          </v-tab-item>
        </v-tabs>
      </div>
    </v-container>
  </div>
</template>

<script>
import EventCoupon from "@/components/event/EventCoupon.vue";
import EventTab from "@/components/event/EventTab.vue";
import { mapActions, mapState } from "vuex";
import axios from "axios";
import EventBanner from './EventBanner.vue';

export default {
  name: "EventMain",
  components: {
    EventCoupon,
    EventTab,
    EventBanner
  },
  data() {
    return {
      pageArray: [],
      eventPageArray: [],
      items: [{ name: "이벤트" }, { name: "쿠폰" }],
    };
  },

  computed: {
    ...mapState(["couponList","events"]),
  },
  mounted() {
    this.fetchCouponList();
    this.fetchEventList()
  },
  methods: {
    ...mapActions(["fetchCouponList", "fetchEventList"]),
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

    axios.get("/event/list")
    .then((res) => {
      console.log("event res: ", res)
      this.eventPageArray = res.data
    })
    .catch((err) => {
      console.log(err)
    })
  },
};
</script>

<style scoped>
.tab {
  width: 250px;
  font-size: 20px;
}
</style>
