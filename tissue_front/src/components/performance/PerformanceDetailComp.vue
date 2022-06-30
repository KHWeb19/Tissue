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

      <div v-if="performanceEvent">
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

        <div style="padding: 0">
          <div
            style="
              display: flex;
              border-bottom: 1px solid black;
              padding-bottom: 15px;
            "
          >
            <v-icon large color="blue lighten-3" class="mr-3"
              >mdi-comment-processing-outline</v-icon
            >
            <div>
              <div style="font-size: 14px; color: pink">
                매매, 욕설 등 예스24 게시판 운영 규정에 위반되는 글은 사전
                통보없이 삭제될 수 있습니다.
              </div>
              <div style="font-size: 14px">
                개인정보가 포함된 내용은 삼가 주시기 바라며, 게시물로 인해
                발생하는 문제는 작성자 본인에게 책임이 있습니다.
              </div>
            </div>
            <v-spacer></v-spacer>
            <div class="text-center">
              <v-dialog v-model="reviewDialog" width="750" hide-overlay>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn text color="transparent" v-bind="attrs" v-on="on">
                    <div
                      style="
                        border: 1px solid #90caf9;
                        width: 180px;
                        font-size: 17px;
                        color: #90caf9;
                        padding-top: 5px;
                        padding-bottom: 5px;
                      "
                    >
                      관람후기등록
                      <v-icon color="blue lighten-3">mdi-pencil-plus</v-icon>
                    </div>
                  </v-btn>
                </template>

                <v-card height="100%" style="border: 1px solid black">
                  <div
                    style="
                      display: flex;
                      justify-content: space-between;
                      border-bottom: 2px solid black;
                      margin-left: 30px;
                      margin-right: 30px;
                    "
                  >
                    <div style="font-size: 25px; padding: 25px">
                      {{ performance.performName }}
                    </div>
                    <div>
                      <v-btn icon @click="reviewDialog = false"> X </v-btn>
                    </div>
                  </div>
                  <div
                    style="
                      display: flex;
                      border-bottom: 1px solid black;
                      margin-left: 30px;
                      margin-right: 30px;
                    "
                  >
                    <div class="reviewSub">별점</div>
                    <div>
                      <v-rating
                        v-model="reviewRating"
                        background-color="orange lighten-3"
                        color="orange"
                        large
                        style="padding-top: 12px"
                      ></v-rating>
                    </div>
                  </div>
                  <div
                    style="
                      display: flex;
                      border-bottom: 1px solid lightgrey;
                      margin-left: 30px;
                      margin-right: 30px;
                    "
                  >
                    <div class="reviewSub">관람 후기</div>
                    <div>
                      <v-textarea
                        v-model="reviewContent"
                        label="리뷰"
                        placeholder="내용을 작성해주세요."
                        clearable
                        auto-grow
                        outlined
                        color="pink lighten-3"
                        style="padding-top: 12px; width: 550px"
                      />
                    </div>
                  </div>
                  <div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        color: #90caf9;
                        font-size: 16px;
                        margin-top: 10px;
                        margin-bottom: 10px;
                      "
                    >
                      <v-icon small color="blue lighten-3"
                        >mdi-exclamation</v-icon
                      >
                      관람후기 작성 시 유의사항
                    </div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                        margin-bottom: 10px;
                      "
                    >
                      Tissue 게시판 운영 규정에 위반된다고 판단되는 글은
                      사전고지 없이 삭제될 수 있습니다.
                    </div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                      "
                    >
                      <v-icon>mdi-check</v-icon>
                      티켓 매매 및 양도, 교환의 글
                    </div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                      "
                    >
                      <v-icon>mdi-check</v-icon>
                      전화번호, 이메일, 주소 등 회원 및 타인의 개인정보 유출이
                      우려되는 경우
                    </div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                      "
                    >
                      <v-icon>mdi-check</v-icon>
                      욕설, 비방, 도배성 글을 작성한 경우
                    </div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                      "
                    >
                      <v-icon>mdi-check</v-icon>
                      명예훼손, 저작권, 초상권의 권리 침해 및 음란한 사진을
                      게재한 경우
                    </div>
                    <div
                      style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                        margin-top: 10px;
                        margin-bottom: 10px;
                        padding-bottom: 10px;
                        border-bottom: 1px solid black;
                      "
                    >
                      작성된 게시물의 저작권은 작성자에게 있으며, 게시물로 인해
                      발생하는 문제는 작성자 본인에게 책임이 있습니다. 작성 시
                      유의해주시기 바랍니다.
                    </div>
                  </div>
                  <div align="center" style="margin: 25px">
                    <v-btn
                      width="150"
                      height="50"
                      @click="registerReview"
                      color="blue lighten-3"
                      class="white--text mr-3"
                      style="font-size: 15px"
                      >등록</v-btn
                    >
                    <v-btn
                      width="150"
                      height="50"
                      @click="reviewDialog = false"
                      class="blue--text text--lighten-3 ml-3"
                      style="border: 1px solid #90caf9; background-color: white"
                      >취소</v-btn
                    >
                  </div>
                </v-card>
              </v-dialog>
            </div>
          </div>

          <div>
            <!--
            <v-data-table
              :headers="headers"
              :items="reviewList"
              :items-per-page="itemsPerPage"
              :key="reviewList.reviewNo"
              :page.sync="page"
              hide-default-header
              hide-default-footer
              @page-count="pageCount = $event"
              class="vTable"
            >
            </v-data-table>
            -->
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

      page: 1,
      pageCount: 0,
      itemsPerPage: 10,

      reviewContent: "",
      reviewRating: 0,
      reviewDialog: false,
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

    registerReview() {
      let token = localStorage.getItem("token");

      const { reviewContent, reviewRating } = this;
      console.log(token);
      if (token != null) {
        axios
          .post(`http://localhost:7777/review/register/${this.performNo}`, {
            params: { token: token },
            reviewContent,
            reviewRating,
          })
          .then(() => {
            alert("후기 등록 성공");
            this.$router.go();
          })
          .catch(() => {
            alert("실패");
          });
      } else {
        alert("로그인이 필요합니다.");
      }
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
.reviewSub {
  width: 120px;
  font-size: 16px;
  padding: 25px;
}
</style>
