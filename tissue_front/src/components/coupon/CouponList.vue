<template>
  <div>
    <div class="titleBox">
      <div>
        <div
          style="
            font-size: 50px;
            text-align: center;
            font-weight: bold;
            padding-top: 25px;
            padding-bottom: 10px;
          "
        >
          COUPON MANAGEMENT
        </div>
        <div
          style="
            font-size: 20px;
            text-align: center;
            font-weight: bold;
            color: grey;
          "
        >
          Tissue 사용자들을 위한 쿠폰을 관리해주세요.
        </div>
      </div>
    </div>
    <div
      style="height: 20px; background-color: #f8bbd0; margin-bottom: 50px"
    ></div>

    <v-container>
      <v-row class="mt-5 mb-5">
        <v-col
          v-for="coupon in paginatedData"
          :key="coupon.couponNo"
          lg="4"
          sm="6"
        >
          <v-card width="300" height="250">
            <v-img
              :src="require(`@/assets/coupon/${coupon.filename}`)"
              height="150px"
            >
              <div class="couponPrice">{{ coupon.couponPrice | comma }}</div>
            </v-img>

            <v-card-title class="pt-0 pb-0 couponTitle">
              [{{ coupon.couponCategory }}]{{ coupon.couponName }}
            </v-card-title>

            <v-card-actions class="pb-0 pt-0 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">발급 기간:</b> {{ coupon.couponStart }} ~
                {{ coupon.couponEnd }}</v-card-text
              >
            </v-card-actions>

            <v-card-actions class="pb-0 pt-0 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">사용 조건:</b>
                {{ coupon.couponCondition }}</v-card-text
              >
              <template>
                <div><v-btn text>수정</v-btn></div>
                <div>
                  <v-dialog v-model="dialogDeleteCoupon" width="450">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        color="red"
                        text
                        v-bind="attrs"
                        v-on="on"
                        @click="resetCheckBox"
                      >
                        삭제
                      </v-btn>
                    </template>

                    <v-card>
                      <v-card-title> 쿠폰 삭제 </v-card-title>
                      <v-card-text class="mt-5 pb-0">
                        정말로 쿠폰을 삭제하시겠습니까? <br />
                        삭제를 원하시면 동의버튼을 체크하시고 <br />
                        삭제버튼을 클릭해주세요.
                      </v-card-text>
                      <v-container fluid>
                        <v-checkbox
                          v-model="checkbox"
                          label="동의합니다."
                        ></v-checkbox>
                      </v-container>
                      <v-divider></v-divider>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          text
                          :disabled="!checkbox"
                          @click="remove(coupon.couponNo)"
                        >
                          삭제
                        </v-btn>
                        <v-btn text @click="dialogDeleteHall = false">
                          취소
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </div>
              </template>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <div class="btn-cover">
            <v-btn
              rounded
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
              rounded
              :disabled="pageNum >= pageCount - 1"
              @click="nextPage"
              class="page-btn"
              color="blue lighten-3"
            >
              다음
            </v-btn>
          </div>
          <v-btn
            x-large
            rounded
            color="blue lighten-3"
            class="mx-auto white--text"
            style="float: right"
            to="/couponRegister"
          >
            쿠폰 등록</v-btn
          >
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CouponList",
  props: {
    listArray: {
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
      couponNo: null,
      pageNum: 0,
      checkbox: false,
      dialogDeleteCoupon: false,
    };
  },

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },

  computed: {
    pageCount() {
      let listLeng = this.listArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.listArray.slice(start, end);
    },
  },

  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
    },
    resetCheckBox() {
      this.checkbox = false;
    },
    remove(val) {
      this.couponNo = val;
      const { couponNo } = this;
      axios.delete(`http://localhost:7777/coupon/${couponNo}`).then(() => {
        alert("쿠폰 삭제가 완료되었습니다.");
        this.$router.go();
      });
    },
  },
};
</script>

<style scoped>
.titleBox {
  width: 100%;
  height: 168.91px;
  padding-top: 0;
  padding-bottom: 0;
  background-color: #fce4ec;
  border-bottom: 1px solid lightgrey;
}
.couponPrice {
  font-size: 60px;
  padding-top: 30px;
  padding-left: 25px;
}
.btn-cover {
  margin-top: 1.5rem;
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

.couponTitle {
  font-size: 16px;
  line-height: 23px;
  color: #333;
  font-weight: 700;
  height: 46px;
  overflow: hidden;
}
.subTitle {
  float: left;
  width: 57px;
  font-size: 13px;
  line-height: 21px;
  color: #666;
  font-weight: 700;
  margin-right: 5px;
}
.subContent {
  clear: right;
  font-size: 13px;
  line-height: 21px;
  color: #999;
}
</style>
