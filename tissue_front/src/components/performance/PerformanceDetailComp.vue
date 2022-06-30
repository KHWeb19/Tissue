<template>
  <div>
    <div style="border-top: 2px solid black">
      <div class="performInfo">공연 정보</div>
      <div class="performDetailImg">
        <v-img
          v-if="this.performance.performDetailImg1 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg1}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg2 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg2}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg3 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg3}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg4 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg4}`)
          "
        ></v-img>
        <v-img
          v-if="this.performance.performDetailImg5 != null"
          :src="
            require(`@/assets/detailImg/${this.performance.performDetailImg5}`)
          "
        ></v-img>
      </div>
      <div style="border-bottom: 2px solid black"></div>

      <div align="center" class="mb-10">
        <div style="padding-top: 30px; padding-bottom: 30px">
          <span style="font-size: 35px"> {{ name }}</span>
        </div>
        <div style="font-size: 16px; padding-bottom: 30px">
          {{ address }} <b class="pl-3">({{ phone }})</b>
        </div>
        <div align="center">
          <div>
            <div v-if="showMap">
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

      <div>
        <div
          style="border-bottom: 4px solid #333; font-size: 30px; width: 115px"
        >
          관람후기
        </div>
        <div class="reviewWrap">
          <div class="reviewBox">
            <div style="width: 70%">
              <div class="reviewTitle">
                {{ performanceEvent.eventTitle }}
              </div>
              <div class="reviewDes">{{ performanceEvent.eventContent }}</div>
              <v-divider style="margin-right: 20px"></v-divider>
              <div class="eventSubBox">
                <div class="eventSubTitle">-이벤트 기간</div>
                <div>
                  {{ performanceEvent.eventStart }} ~
                  {{ performanceEvent.eventEnd }}
                </div>
              </div>
              <div class="eventSubBox">
                <div class="eventSubTitle">-당첨자 발표</div>
                <div>
                  {{ performanceEvent.eventWinnerDate }}
                </div>
              </div>
              <div class="eventSubBox">
                <div class="eventSubTitle">-경품</div>
                <div>
                  {{ performanceEvent.eventGoods }}
                </div>
              </div>
              <div class="eventSubBox">
                <div class="eventSubTitle">-당첨 인원</div>
                <div>
                  {{ performanceEvent.eventWinnerCnt }}
                </div>
              </div>
              <div
                style="border-bottom: 1px solid black; margin-right: 20px"
              ></div>
              <div style="font-size: 13px; width: 700px; margin-top: 20px">
                이벤트 응모 시 당첨자 추첨 및 안내, 경품 발송 등을 위한
                개인정보제공에 동의하신 것으로 간주되며, 이벤트 외 목적으로
                사용되지 않습니다. 본 이벤트는 기획사 및 당사 사정에 의해
                사전고지 없이 변경/취소될 수 있습니다.
              </div>
            </div>
            <div>
              <v-img
                class="eventImg"
                :src="
                  require(`@/assets/thumbNail/${this.performanceEvent.performance.performThumbnail}`)
                "
              />
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
    performanceEvent: {
      type: Object,
      required: true,
    },
  },

  data() {
    return {
      performNo: "",
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
  created() {
    this.performNo = this.performance.performNo;
  },
  mounted() {
    this.fetchMap(this.performNo);
    console.log(this.performanceEvent);
  },
  methods: {
    fetchMap(performNo) {
      axios.get(`map/read/${performNo}`).then((res) => {
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
.reviewWrap {
  height: 450px;
  background-color: #e3f2fd;
  margin-top: 30px;
  margin-bottom: 30px;
}
.reviewBox {
  padding: 50px;
  display: flex;
}
.reviewTitle {
  border-bottom: 2px solid black;
  font-size: 28px;
  padding-bottom: 7px;
  margin-right: 20px;
}
.reviewDes {
  margin-top: 30px;
  margin-bottom: 30px;
  font-size: 15px;
}
.eventSubBox {
  display: flex;
  margin-top: 7px;
  margin-bottom: 7px;
}
.eventSubTitle {
  font-size: 15px;
  color: #90caf9;
  width: 150px;
}
.eventImg {
  width: 200px;
  height: 280px;
  margin: 35px 35px 35px 50px;
}
</style>
