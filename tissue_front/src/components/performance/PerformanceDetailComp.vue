<template>
  <div>
    <div style="border-top: 2px solid black">
      <div class="performInfo mb-15">공연 정보</div>
      <div class="performDetailImg">
        <v-img
          v-if="this.performance.performDetailImg5 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg5}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg4 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg4}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg3 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg3}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg2 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg2}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg1 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg1}`)
          "
        ></v-img>
      </div>
      <div style="border-bottom: 2px solid black"></div>

      <div align="center" class="mb-10">
        <div v-if="showMap">
          <div style="padding-top: 30px; padding-bottom: 30px">
            <span style="font-size: 35px"> {{ name }}</span>
          </div>
          <div style="font-size: 16px; padding-bottom: 30px">
            {{ address }} <b class="pl-3">({{ phone }})</b>
          </div>
          <div align="center" style="z-index: -1">
            <div>
              <naver-maps
                :height="500"
                :width="1000"
                :mapOptions="mapOptions"
              ></naver-maps>
              <naver-marker
                :lat="mapOptions.lat"
                :lng="mapOptions.lng"
              ></naver-marker>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PerformanceDetailComp",
  props: {
    performance: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      mapOptions: {
        lat: null,
        lng: null,
        zoom: 17,
      },
      showMap: false,
      name: null,
      address: null,
      phone: null,
      url: null,
    };
  },
  watch: {
    performance() {
      this.fetchMap(this.performance.performNo);
    },
  },
  methods: {
    fetchMap(performNo) {
      axios
        .get(`map/read/${performNo}`)
        .then((res) => {
          console.log(performNo);
          const map = res.data;
          console.log(map);
          this.mapOptions.lat = map.y;
          this.mapOptions.lng = map.x;
          this.name = map.name;
          this.address = map.address;
          this.phone = map.phone;
          this.url = map.url;
          if (map.y != 0) {
            this.showMap = true;
          }
        })
        .catch(() => {
          //alert("오류");
        });
    },
  },
};
</script>

<style scoped>
.performInfo {
  padding-top: 70px;
  font-size: 30px;
  text-align: center;
}
.performDetailImg {
  width: 1000px;
  margin: auto;
  padding-bottom: 30px;
}
</style>
