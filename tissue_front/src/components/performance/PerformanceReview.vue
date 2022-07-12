<template>
  <div>
    <v-row
      class="mb-10"
      style="border-bottom: 4px solid #333; font-size: 30px; width: 115px"
    >
      관람후기
    </v-row>
    <v-row class="reviewWrap" justify="center" v-if="performanceEvent != null">
      <v-col cols="8">
        <div class="reviewBox">
          <div class="reviewTitle">
            {{ performanceEvent.eventTitle }}
          </div>
          <div class="reviewDes">
            {{ performanceEvent.eventContent }}
          </div>
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
            <div>{{ performanceEvent.eventWinnerCnt }} 명</div>
          </div>
          <div style="border-bottom: 1px solid black; margin-right: 20px"></div>
          <div style="font-size: 13px; width: 650px; margin-top: 20px">
            이벤트 응모 시 당첨자 추첨 및 안내, 경품 발송 등을 위한
            개인정보제공에 동의하신 것으로 간주되며, 이벤트 외 목적으로 사용되지
            않습니다. 본 이벤트는 기획사 및 당사 사정에 의해 사전고지 없이
            변경/취소될 수 있습니다.
          </div>
        </div>
      </v-col>
      <v-col cols="3">
        <v-img
          class="eventImg"
          :src="
            require(`@/assets/thumbNail/${this.performanceEvent.performance.performThumbnail}`)
          "
        />
      </v-col>
    </v-row>
    <v-row justify="start" class="ml-2">
      <div style="font-size: 18px; color: pink">
        <v-icon large color="blue lighten-3" class="mr-3"
          >mdi-comment-processing-outline</v-icon
        >
        매매, 욕설 등 예스24 게시판 운영 규정에 위반되는 글은 사전 통보없이
        삭제될 수 있습니다.
      </div>
      <div class="ml-13" style="font-size: 15px">
        개인정보가 포함된 내용은 삼가 주시기 바라며, 게시물로 인해 발생하는
        문제는 작성자 본인에게 책임이 있습니다.
      </div>
    </v-row>
    <v-row class="mt-12" justify="center">
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
                <v-icon small color="blue lighten-3">mdi-exclamation</v-icon>
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
                Tissue 게시판 운영 규정에 위반된다고 판단되는 글은 사전고지 없이
                삭제될 수 있습니다.
              </div>
              <div
                style="margin-left: 30px; margin-right: 30px; font-size: 13px"
              >
                <v-icon>mdi-check</v-icon>
                티켓 매매 및 양도, 교환의 글
              </div>
              <div
                style="margin-left: 30px; margin-right: 30px; font-size: 13px"
              >
                <v-icon>mdi-check</v-icon>
                전화번호, 이메일, 주소 등 회원 및 타인의 개인정보 유출이
                우려되는 경우
              </div>
              <div
                style="margin-left: 30px; margin-right: 30px; font-size: 13px"
              >
                <v-icon>mdi-check</v-icon>
                욕설, 비방, 도배성 글을 작성한 경우
              </div>
              <div
                style="margin-left: 30px; margin-right: 30px; font-size: 13px"
              >
                <v-icon>mdi-check</v-icon>
                명예훼손, 저작권, 초상권의 권리 침해 및 음란한 사진을 게재한
                경우
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
                @click="[registerReview(performance.performNo)]"
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
      <v-col cols="12">
        <hr class="mt-5" />
      </v-col>
    </v-row>
    <div v-for="review in reviewList" :key="review.reviewNo" class="reviewZone">
      <v-row class="pl-10 mt-3">
        <v-col cols="1">
          {{ review.reviewWriter }}
        </v-col>
        <v-divider class="ml-5 mr-2 mt-3" style="height: 30px" vertical />
        <v-col cols="2">
          {{ review.reviewRegDate }}
        </v-col>
        <v-col>
          <v-rating
            :value="review.reviewRating"
            background-color="orange lighten-3"
            color="orange"
            small
            dense
            hover
            readonly
          ></v-rating>
        </v-col>
      </v-row>
      <v-row no-gutters class="pl-10 mt-5 mb-5">
        <v-col style="word-break: break-all">
          {{ review.reviewContent }}
        </v-col>
      </v-row>
      <v-divider />
    </div>
    <v-row justify="center">
      <div class="btn-cover">
        <v-btn
          :disabled="pageNum === 0"
          @click="prevPage"
          class="page-btn"
          color="blue lighten-3"
        >
          이전
        </v-btn>
        <span class="page-count"
          >{{ pageNum + 1 }} / {{ pageCount }} 페이지</span
        >
        <v-btn
          :disabled="pageNum >= pageCount - 1"
          @click="nextPage"
          class="page-btn"
          color="blue lighten-3"
        >
          다음
        </v-btn>
      </div>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "PerformanceReview",

  props: {
    performance: {
      type: Object,
      required: true,
    },
    performanceEvent: {
      type: Object,
      required: false,
    },
    reviewList: {
      type: Array,
      required: true,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 12,
    },
  },
  data() {
    return {
      pageNum: 0,
      performNo: "",
      reviewContent: "",
      reviewRating: 0,
      reviewDialog: false,
      headers: [
        {
          text: "작성자",
          value: "reviewWriter",
          width: "50px",
          align: "left",
        },
        {
          text: "평점",
          value: "reviewRating",
          width: "50px",
          align: "left",
        },
        {
          text: "작성일",
          value: "reviewRegDate",
          width: "50px",
          align: "center",
        },
        {
          text: "내용",
          value: "reviewContent",
          width: "300px",
          align: "center",
        },
      ],
    };
  },

  created() {
    let token = localStorage.getItem("token");

    this.performNo = this.performance.performNo;
    this.fetchMemberInfo(token);
  },

  computed: {
    ...mapState(["memberInfo"]),
    pageCount() {
      let listLeng = this.reviewList.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.reviewList.slice(start, end);
    },
  },

  methods: {
    ...mapActions(["fetchMemberInfo"]),
    registerReview(performNo) {
      let token = localStorage.getItem("token");

      const { reviewContent, reviewRating } = this;
      console.log(token);
      if (token != null) {
        axios
          .post(`http://localhost:7777/review/register/${performNo}`, {
            reviewWriter: token,
            reviewContent,
            reviewRating,
          })
          .then((res) => {
            //alert("후기 등록 성공");
            if (res.data == true) {
              alert("후기가 등록되었습니다.");

              const memberMileage = this.memberInfo.memberMileage + 3000;

              axios.post("Member/addMileage", {
                memberId: this.memberInfo.memberId,
                memberMileage,
              });

              console.log("마일리지 적립", memberMileage);
              alert("후기 적립금 3,000원이 적립되었습니다!");
            } else {
              alert("이미 후기를 작성하셨습니다.");
            }
            this.$router.go();
          })
          .catch(() => {
            //alert("실패");
          });
      } else {
        alert("로그인이 필요합니다.");
      }
    },
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
  },
};
</script>

<style scoped>
.reviewWrap {
  height: 450px;
  background-color: #e3f2fd;
  margin-top: 30px;
  margin-bottom: 30px;
}
.reviewBox {
  padding: 50px;
}
.reviewZone {
  font-family: "Nanum Gothic", sans-serif !important;
  font-size: 11pt;
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
  margin: 75px 5px 35px;
}
.reviewSub {
  width: 120px;
  font-size: 16px;
  padding: 25px;
}
.btn-cover {
  margin-top: 3rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>
