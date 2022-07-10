<template>
  <div>
    <v-container style="width: 1200px">
      <v-row>
        <v-col>
          <div style="border-bottom: 2px solid black" class="pb-5 mt-10">
            <div class="font15">
              {{ performance.performCategory }}
            </div>
            <div
              style="font-size: 35px; color: #333; font-weight: bold"
              class="mb-5"
            >
              {{ performance.performName }}
            </div>
            <div class="font15">
              {{ performance.performStart }} ~ {{ performance.performEnd }} |
              {{ name }} {{ address }}
              <v-icon color="blue lighten-3">mdi-map-marker</v-icon>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <img
            class="img"
            :src="
              require(`@/assets/thumbNail/${this.performance.performThumbnail}`)
            "
          />
        </v-col>
        <v-col>
            <div style="margin: 0 0 50px 50px">
              <div class="wrapSubTitle">
                <div class="performSubTitle">등급</div>
                <div class="font15">{{ performance.performGrade }}</div>
              </div>
              <div class="wrapSubTitle">
                <div class="performSubTitle">관람시간</div>
                <div class="font15">{{ "필드추가 필요" }}</div>
              </div>
              <div class="wrapSubTitle">
                <div class="performSubTitle">출연</div>
                <div class="font15">{{ performance.performer }}</div>
              </div>
              <div class="wrapSubTitle">
                <div class="performSubTitle">가격</div>
                <div class="priceTable">
                  <div class="wrapPriceGrade">
                    <div class="gradeText">VIP석</div>
                    <b style="color: #f48fb1" class="ml-2">{{
                      performance.performPriceVip | comma
                    }}</b
                    >원
                  </div>
                  <div class="wrapPriceGrade">
                    <div class="gradeText">R석</div>
                    <b style="color: #f48fb1" class="ml-2">{{
                      performance.performPriceR | comma
                    }}</b
                    >원
                  </div>
                  <div class="wrapPriceGrade">
                    <div class="gradeText">S석</div>
                    <b style="color: #f48fb1" class="ml-2">{{
                      performance.performPriceS | comma
                    }}</b
                    >원
                  </div>
                </div>
              </div>
            </div>
        </v-col>
      </v-row>
      <hr class="mt-5 mb-5" />
      <div align="center" class="detailImgBox">
        <v-row justify="center">
          <v-col class="label">상세이미지</v-col>
        </v-row>
        <v-row justify="center">
          <v-col>
            <img
              class="img"
              v-if="
                this.performance.performDetailImg1 !== null &&
                this.performance.performDetailImg1 !== 'null'
              "
              :src="
                require(`@/assets/detailImg/${this.performance.performDetailImg1}`)
              "
            />
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-col>
            <img
              class="img"
              v-if="
                this.performance.performDetailImg2 !== null &&
                this.performance.performDetailImg2 !== 'null'
              "
              :src="
                require(`@/assets/detailImg/${this.performance.performDetailImg2}`)
              "
            />
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-col>
            <img
              class="img"
              v-if="
                this.performance.performDetailImg3 !== null &&
                this.performance.performDetailImg3 !== 'null'
              "
              :src="
                require(`@/assets/detailImg/${this.performance.performDetailImg3}`)
              "
            />
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-col>
            <img
              class="img"
              v-if="
                this.performance.performDetailImg4 !== null &&
                this.performance.performDetailImg4 !== 'null'
              "
              :src="
                require(`@/assets/detailImg/${this.performance.performDetailImg4}`)
              "
            />
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-col>
            <img
              class="img"
              v-if="
                this.performance.performDetailImg5 !== null &&
                this.performance.performDetailImg5 !== 'null'
              "
              :src="
                require(`@/assets/detailImg/${this.performance.performDetailImg5}`)
              "
            />
          </v-col>
        </v-row>
      </div>

      <hr class="mt-5 mb-5" />
      <div align="center">
        <v-row>
          <v-col v-if="showMap">
            <v-col> {{ name }} </v-col>
            <v-col> {{ address }} ({{ phone }}) </v-col>
            <naver-maps
              :height="500"
              :width="1000"
              :mapOptions="mapOptions"
            ></naver-maps>
            <naver-marker
              :lat="mapOptions.lat"
              :lng="mapOptions.lng"
            ></naver-marker>
          </v-col>
        </v-row>
      </div>

      <div align="center" class="mt-5">
        <v-btn class="mr-5" color="blue lighten-3" dark :to="{ name: 'PerformanceModifyPage', params: { performNo } }">
          수정
        </v-btn>
        <v-btn class="mr-5" color="blue lighten-3" dark @click="onDelete"> 삭제 </v-btn>
        <v-btn class="mr-5" color="blue lighten-3" dark :to="{ name: 'PerformanceListPage' }">
          리스트
        </v-btn>
      </div>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "PerformanceReadPage",
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
      // showPlace: false
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  computed: {
    ...mapState(["performance"]),
  },
  created() {
    this.performNo = this.$route.params.performNo;
  },
  mounted() {
    this.fetchPerformance(this.performNo).catch(() => {
      alert("게시물 요청 실패!");
      this.$router.push();
    });
    this.fetchMap(this.performNo);
  },
  methods: {
    ...mapActions(["fetchPerformance"]),
    onDelete() {
      const {
        performNo,
        performThumbnail,
        performDetailImg1,
        performDetailImg2,
        performDetailImg3,
        performDetailImg4,
        performDetailImg5,
      } = this.performance;
      axios
        .delete(`performance/${performNo}`, {
          performThumbnail,
          performDetailImg1,
          performDetailImg2,
          performDetailImg3,
          performDetailImg4,
          performDetailImg5,
        })
        .then(() => {
          alert("삭제 성공!");
          this.$router.push({ name: "PerformanceListPage" });
        })
        .catch(() => {
          alert("삭제 실패! 문제 발생!");
        });
    },
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
.label {
  text-align: center;
}

.img {
  position: relative;
  max-height: 100%;
  max-width: 100;
  /* margin-top: 5%; */
}

.detailImgBox {
  width: 100%;
  height: 100%;
}

.wrapSubTitle {
  display: flex;
  margin-bottom: 20px;
}
.performSubTitle {
  font-size: 15px;
  color: #333;
  font-weight: bold;
  margin-right: 10px;
  width: 80px;
}
.priceTable {
  width: 400px;
  height: 100%;
  background-color: #f6f6f6;
  padding: 10px;
}
.wrapPriceGrade {
  display: flex;
}
.gradeText {
  text-align: center;
  width: 50px;
  margin-bottom: 25px;
}
</style>
