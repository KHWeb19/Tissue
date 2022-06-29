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
                  <v-btn icon><v-icon>mdi-heart</v-icon></v-btn>
                  <span class="font15"
                    ><b style="color: skyblue" class="mr-3">{{ "123" }} </b
                    >Likes
                  </span>
                </div>
                <div class="wrapRating pt-1">
                  <v-rating
                    v-model="rating"
                    background-color="orange lighten-3"
                    color="orange"
                    small
                    dense
                    hover
                    readonly
                    class="mr-3 pt-0"
                  ></v-rating>
                  <b style="color: skyblue" class="mr-3">{{ "4.1" }}</b>
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
                      |
                    </template>

                    <v-card
                      v-if="this.availableCoupon.length != 0"
                      height="100%"
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
              <v-divider></v-divider>
              <div>
                <div>배송정보</div>
                <div>현장 수령만 가능</div>
              </div>
              <v-divider></v-divider>
            </div>
          </div>
          <div>
            <div>날짜/시간 선택</div>
            <v-divider></v-divider>
            <v-date-picker v-model="picker" locale="ko-KR"></v-date-picker>
          </div>
          <div>
            <v-btn color="blue lighten-3" class="white--text">예매하기</v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
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
  },
  data() {
    return {
      availableCoupon: [],
      memberGrade: [
        { grade: "일반", sale: 5 },
        { grade: "VIP", sale: 10 },
      ],
      couponDialog: false,
      memberDialog: false,
      rating: 4,
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
    };
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
  margin-bottom: 30px;
}
.downBtn {
  border: 1px solid pink;
  display: inline-block;
}
</style>
