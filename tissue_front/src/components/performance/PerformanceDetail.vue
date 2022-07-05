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
              {{ performance.performArea }}
              <v-icon color="blue lighten-3">mdi-map-marker</v-icon>
            </div>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <div style="display: flex">
            <div>
              <v-img
                :src="
                  require(`@/assets/thumbNail/${this.performance.performThumbnail}`)
                "
              ></v-img>
              <div class="additionFunc mb-5 mt-5">
                <div>
                  <v-btn icon v-if="this.likeMember === true" @click="dislike" color="red"><v-icon>mdi-heart</v-icon></v-btn>
                  <v-btn icon v-else @click="like" color="grey"><v-icon>mdi-heart</v-icon></v-btn>
                  <span class="font15"
                    ><b style="color: skyblue" class="mr-3">{{ likeList.length }} </b
                    >Likes
                  </span>
                </div>
                <div class="wrapRating pt-1">
                  <v-btn
                    color="transparent"
                    text
                    @click="scrollReview"
                    id="scrollBtn"
                  >
                    <v-rating
                      :value="reviewSumAvg"
                      background-color="orange lighten-3"
                      color="orange"
                      small
                      dense
                      hover
                      readonly
                      class="mr-3 pb-4"
                    ></v-rating>
                  </v-btn>
                  <b style="color: skyblue" class="mr-3">{{ reviewSumAvg }}</b>
                  Reviews
                </div>
              </div>
            </div>
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
              <div class="wrapSubTitle">
                <div class="performSubTitle pt-2">혜택</div>
                <div class="text-center">
                  <v-dialog v-model="couponDialog" width="450" hide-overlay>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="transparent"
                        text
                        v-bind="attrs"
                        v-on="on"
                        style="padding: 0"
                      >
                        <div class="font15">
                          사용가능쿠폰 (<b style="color: skyblue">{{
                            availableCoupon.length
                          }}</b
                          >)
                        </div>
                      </v-btn>
                    </template>

                    <v-card
                      v-if="this.availableCoupon.length != 0"
                      height="100%"
                      style="border: 1px solid black"
                    >
                      <div
                        style="display: flex; justify-content: space-between"
                      >
                        <div style="padding: 25px">
                          본 공연에 사용 가능한 쿠폰입니다.
                        </div>
                        <div>
                          <v-btn
                            color="black"
                            icon
                            style="margin-left: 120px"
                            @click="couponDialog = false"
                          >
                            X
                          </v-btn>
                        </div>
                      </div>
                      <div>
                        <v-simple-table
                          style="
                            width: 400px;
                            margin: auto;
                            padding-bottom: 20px;
                          "
                        >
                          <template v-slot:default>
                            <thead>
                              <tr style="background-color: #90caf9">
                                <th class="text-center">쿠폰명</th>
                                <th class="text-center">할인</th>
                                <th class="text-center">링크</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="coupon in availableCoupon"
                                :key="coupon.couponNo"
                              >
                                <td class="text-center">
                                  {{ coupon.couponName }}
                                </td>
                                <td class="text-center">
                                  {{ coupon.couponPrice | comma }}
                                </td>
                                <td class="text-center">
                                  <v-btn
                                    text
                                    color="pink lighten-3"
                                    class="downBtn"
                                    @click="down(coupon.couponNo)"
                                    >다운하기</v-btn
                                  >
                                </td>
                              </tr>
                            </tbody>
                          </template>
                        </v-simple-table>
                      </div>
                    </v-card>
                  </v-dialog>
                </div>
                <v-divider vertical class="ml-3 mr-3"></v-divider>
                <div class="text-center">
                  <v-dialog v-model="memberDialog" width="450" hide-overlay>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="transparent"
                        text
                        v-bind="attrs"
                        v-on="on"
                        style="padding: 0"
                      >
                        <div class="font15">회원등급할인가</div>
                      </v-btn>
                    </template>

                    <v-card
                      v-if="this.availableCoupon.length != 0"
                      height="100%"
                      style="border: 1px solid black"
                    >
                      <div
                        style="display: flex; justify-content: space-between"
                      >
                        <div style="padding: 25px">
                          회원 등급 할인 혜택입니다.
                        </div>
                        <div>
                          <v-btn
                            color="black"
                            icon
                            style="margin-left: 120px"
                            @click="memberDialog = false"
                          >
                            X
                          </v-btn>
                        </div>
                      </div>
                      <div>
                        <v-simple-table
                          style="
                            width: 400px;
                            margin: auto;
                            padding-bottom: 20px;
                          "
                        >
                          <template v-slot:default>
                            <thead>
                              <tr style="background-color: #90caf9">
                                <th class="text-center">회원등급</th>
                                <th class="text-center">할인</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="grade in memberGrade"
                                :key="grade.grade"
                              >
                                <td class="text-center">{{ grade.grade }}</td>
                                <td class="text-center">{{ grade.sale }}%</td>
                              </tr>
                            </tbody>
                          </template>
                        </v-simple-table>
                      </div>
                    </v-card>
                  </v-dialog>
                </div>
              </div>

              <div
                class="pt-3 pb-3"
                style="
                  border-top: 2px solid black;
                  border-bottom: 2px solid black;
                "
              >
                <div class="font15">배송정보</div>
                <div style="font-size: 14px">현장 수령만 가능</div>
              </div>
            </div>
          </div>
          <div style="display: flex">
            <div class="wrapReserveBox">
              <div style="margin: 25px">
                <div
                  style="
                    border-bottom: 2px solid black;
                    font-size: 18px;
                    padding-top: 5px;
                    padding-bottom: 20px;
                  "
                >
                  날짜/시간 선택
                </div>
                <div style="display: flex">
                  <div style="width: 50%">
                    <v-date-picker
                      v-model="picker"
                      locale="ko-KR"
                      no-title
                      :min="performance.performStart"
                      :max="performance.performEnd"
                      color="blue lighten-3"
                      :allowed-dates="allowedDates"
                      :show-current="false"
                    ></v-date-picker>
                  </div>
                  <div style="margin: 25px; width: 50%">
                    <v-btn
                      text
                      class="performTime"
                      @click="changeBackgroundColor = !changeBackgroundColor"
                      :style="{
                        backgroundColor: changeBackgroundColor
                          ? 'skyblue !important'
                          : '',
                        color: changeBackgroundColor
                          ? 'white !important'
                          : 'black',
                      }"
                      >1회 오후 2시 00분</v-btn
                    >
                  </div>
                </div>
              </div>
            </div>
            <div class="wrapSeatCount">
              <div style="margin: 25px">
                <div
                  style="
                    border-bottom: 2px solid black;
                    font-size: 18px;
                    padding-top: 5px;
                    padding-bottom: 20px;
                  "
                >
                  예매 가능 좌석
                </div>
                <div class="remainSeatBox">
                  <div class="wrapPriceGrade">
                    <div class="gradeText">S석</div>
                    <b style="color: #f48fb1">{{
                      performance.performPriceS | comma
                    }}</b
                    >원 <b>(잔여: {{ 1 }}석)</b>
                  </div>
                  <div class="wrapPriceGrade">
                    <div class="gradeText">R석</div>
                    <b style="color: #f48fb1">{{
                      performance.performPriceR | comma
                    }}</b
                    >원 <b>(잔여: {{ 1 }}석)</b>
                  </div>
                  <div class="wrapPriceGrade">
                    <div class="gradeText">VIP석</div>
                    <b style="color: #f48fb1">{{
                      performance.performPriceVip | comma
                    }}</b
                    >원 <b>(잔여: {{ 1 }}석)</b>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div style="display: flex; justify-content: center">
            <router-link
              :to="{
                name: 'TicketingPage',
                params: { performNo: performance.performNo.toString() },
              }"
            >
              <v-btn
                color="blue lighten-3"
                class="reserveBtn white--text"
                width="230"
                height="50"
                :disabled="clickDate"
                >예매하기</v-btn
              >
            </router-link>
          </div>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-tabs
            color="blue lighten-3"
            height="55px"
            hide-slider
            active-class="tab_active"
          >
            <v-tab
              v-for="item in items"
              :key="item.name"
              style="font-size: 18px; width: 180px"
              >{{ item.name }}</v-tab
            >
            <v-tab-item v-for="n in 2" :key="n">
              <performance-detail-comp
                v-if="n == 1"
                :performance="performance"
              />
              <performance-review
                v-if="n == 1"
                :reviewList="reviewList"
                :performanceEvent="performanceEvent"
                :performance="performance"
                class="reviewBox"
              />
              <perform-caution v-if="n ==2"/>
            </v-tab-item>
          </v-tabs>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import PerformanceDetailComp from "@/components/performance/PerformanceDetailComp.vue";
import PerformanceReview from "@/components/performance/PerformanceReview.vue";
import axios from 'axios';
import { mapActions } from 'vuex';
import PerformCaution from './PerformCaution.vue';

export default {
  name: "PerformanceDetail",
  props: {
    performance: {
      type: Object,
      required: true,
    },
    couponList: {
      type: Array,
      required: true,
    },
    likeList: {
        type: Array,
        required: true,
    },
    likeMember: {
        type:Boolean,
        required: true
    },
    performanceEvent: {
      type: Object,
      required: false,
    },
    reviewList: {
      type: Array,
      required: true,
    },
  },
  components: {
    PerformanceDetailComp,
    PerformanceReview,
    PerformCaution
  },
  data() {
    return {
      availableCoupon: [],
      memberGrade: [
        { grade: "일반", sale: 5 },
        { grade: "VIP", sale: 10 },
      ],
      items: [{ name: "상세정보" }, { name: "유의사항" }],
      changeBackgroundColor: false,
      couponDialog: false,
      memberDialog: false,
      rating: 0,
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),

      reviewAvg: "",
      clickDate: true,
    };
  },

  computed: {
    reviewSumAvg() {
      let sum = 0;
      let avg = 0;
      for (let i = 0; i < this.reviewList.length; i++) {
        sum = sum + this.reviewList[i].reviewRating;
      }
      avg = sum / this.reviewList.length;

    //   console.log("평균" + avg);
      return avg;
    },
  },

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  mounted() {
    for (let i = 0; i < this.couponList.length; i++) {
      if (
        this.couponList[i].couponCategory == this.performance.performCategory
      ) {
        this.availableCoupon.push(this.couponList[i]);
      }
    }

  },
  methods: {
      ...mapActions(['fetchPerformanceLike']),
      checkMember () {
          let token = localStorage.getItem('token')
          if(token != null){
            axios.get('likes/member' , { params:{token:token} })
            .then((res) => {
                for (let i = 0; i <this.likeList.length; i++){
                    if(this.likeList[i].member.memberNo === res.data) {
                        return this.$emit('update:likeMember', true)
                    }
                }
                return this.$emit('update:likeMember', false)
            })
          }
      },
        like() {
          let performNo = this.performance.performNo
          let token = localStorage.getItem('token')
          if(token != null) {
            axios.post('likes/register', {performNo, token})
            .then ((res)=> {
                this.$emit('update:likeList', res.data)
                alert("해당 공연이 찜되셨습니다.")
                this.checkMember()
            })
            .catch((res) => {
                console.log(res.message)
            })
          } else {
              alert("로그인이 필요합니다.")
          }
      },
      dislike() {
          let performNo = this.performance.performNo
          let token = localStorage.getItem('token')
          axios.delete('likes/delete', { params:{performNo: performNo, token: token}})
          .then((res) => {
              this.$emit('update:likeList', res.data)
              alert("찜이 취소되었습니다.")
              this.checkMember()
          })
          .catch((res) => {
              console.log(res.message)
          })
      },
      scrollReview() {
      const btn = document.getElementById("scrollBtn");

      btn.addEventListener("click", function (e) {
        e.preventDefault();
        document.querySelector(".reviewBox").scrollIntoView(true);
      });
    },
    down(couponNo) {
      let token = localStorage.getItem("token");

      if (token != null) {
        axios
          .get(`coupon/download/${couponNo}`, { params: { token: token } })
          .then((res) => {
            if (res.data == true) {
              alert("쿠폰이 발행되었습니다.");
            } else {
              alert("이미 발행된 쿠폰입니다.");
            }

            this.couponDialog = false;
          })
          .catch(() => {
            console.log("에러");
            console.log(couponNo, token);
          });
      } else {
        alert("로그인이 필요합니다.");
      }
    },
    allowedDates(val) {
      let show = this.performance.performShowDate;
    //   console.log(parseInt(val.split("-")[2], 10));
      if (parseInt(val.split("-")[2], 10) == parseInt(show.split("-")[2], 10)) {
        return true;
      }
      return false;
    },
  }
};
</script>

<style scoped>
.font15 {
  font-size: 15px;
  color: #333;
}
.additionFunc {
  display: flex;
  justify-content: space-between;
}
.wrapRating {
  display: flex;
  font-size: 15px;
  color: #333;
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
  width: 600px;
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
.downBtn {
  border: 1px solid pink;
  display: inline-block;
}
.wrapReserveBox {
  width: 55%;
  height: 380px;
  border: 1px solid #90caf9;
}
.wrapSeatCount {
  width: 45%;
  height: 380px;
  border: 1px solid #90caf9;
  border-left: none;
}
.performTime {
  display: flex;
  justify-content: center;
  height: 30px;
  text-align: center;
  border: 1px solid lightgrey;
  margin: auto;
}
.remainSeatBox {
  margin-top: 25px;
  width: 100%;
  height: 200px;
  border: 1px solid lightgrey;
  padding: 25px;
}
.reserveBtn {
  margin-top: 50px;
  margin-bottom: 50px;
  color: white;
  font-size: 20px;
}
.tab_active {
  border-top: 3px solid skyblue;
}
</style>
