<template>
  <div>
    <v-container>
       <v-app-bar app elevation="3">
        <v-toolbar-title class="ml-3"> 📌 공연 관리 </v-toolbar-title>
      </v-app-bar>
      <v-container>
        <v-row>
            <v-col>
              <form>
                <div>
                  <label>공연명</label>
                  <v-text-field
                    :value="performance.performName"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>공연날짜</label>
                  <v-text-field
                  :value="performance.performShowDate"
                    outlined
                    type="Date"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>공연시작일</label>
                  <v-text-field
                  :value="performance.performStart"
                    outlined
                    type="Date"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>공연마감일</label>
                <v-text-field
                :value="performance.performEnd"
                    outlined
                    type="Date"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>공연시간</label>
                  <v-text-field
                  :value="performance.performTime"
                    outlined
                    type="Time"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>S석가격</label>
                  <v-text-field
                  :value="performance.performPriceS"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>R석가격</label>
                  <v-text-field
                  :value="performance.performPriceR"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>vip석가격</label>
                  <v-text-field
                  :value="performance.performPriceVip"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>지역</label>
                  <v-text-field
                  :value="performance.performArea"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>공연카테고리</label>
                  <v-text-field
                  :value="performance.performCategory"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>등급</label>
                  <v-text-field
                  :value="performance.performGrade"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>
                <div>
                  <label>출연자</label>
                  <v-text-field
                  :value="performance.performer"
                    outlined
                    type="text"
                    color="pink lighten-3"
                    readonly
                  ></v-text-field>
                </div>

                <div>
                  <label>썸네일</label>
                  <div align="center" style="background-color:#EEEEEE;">
                    <img
                      class="img"
                      :src="
                        require(`@/assets/thumbNail/${this.performance.performThumbnail}`)
                      "
                    />
                  </div>
                </div>

                <div>
                  <label>상세이미지</label>
                  <div class="mt-3" align="center" style="background-color:#EEEEEE;">
                    <v-row justify="center">
                      <v-col>
                        <img
                          class="detailimg"
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
                          class="detailimg"
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
                          class="detailimg"
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
                          class="detailimg"
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
                          class="detailimg"
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
                </div>

                <div class="mt-5">
                  <label>지도</label>
                  <div class="mt-3" align="center" style="background-color:#EEEEEE;">
                    <v-row>
                      <v-col v-if="showMap">
                        <v-col style="font-size:13px;color:#F48FB1"> {{ name }} </v-col>
                        <v-col style="font-size:13px;color:#F48FB1"> {{ address }} ({{ phone }}) </v-col>
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
                </div>

              </form>
            </v-col>
        </v-row>

        <v-row class="mt-8" justify="center">
          <v-btn color="blue lighten-3 mr-8" dark :to="{ name: 'PerformanceModifyPage', params: { performNo } }">
            수정
          </v-btn>
        <v-btn class="mr-8" color="blue lighten-3" dark @click="onDelete"> 삭제 </v-btn>
          <v-btn class="mr-5" color="blue lighten-3" dark :to="{ name: 'PerformanceListPage' }">
            리스트
          </v-btn>
        </v-row>
      </v-container>
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
label {
  font-size: 5px;
  text-align: center;
}

.img {
  position: relative;
   max-width: 450px;
}
.detailimg{
  max-width: 450px;
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
