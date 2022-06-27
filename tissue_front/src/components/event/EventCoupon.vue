<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          <div
            style="
              text-align: center;
              font-size: 30px;
              font-weight: lighter;
              margin: 30px;
            "
          >
            <b style="font-size: 45px; font-weight: bold">C</b> O U P O N &nbsp;
            <b style="font-size: 45px; font-weight: bold">Z</b> O N E
          </div>
          <v-divider></v-divider>
        </v-col>
      </v-row>
      <v-row class="mt-5 mb-5">
        <v-col
          v-for="coupon in paginatedData"
          :key="coupon.couponNo"
          lg="4"
          sm="6"
        >
          <v-card width="400" height="390">
            <div class="imgWrap">
              <div class="pt-10">
                <v-img
                  :src="require(`@/assets/coupon/${coupon.filename}`)"
                  class="img"
                >
                  <div class="couponPrice">
                    {{ coupon.couponPrice | comma }}
                  </div>
                </v-img>
              </div>
            </div>

            <v-card-title class="pt-3 pb-1 couponTitle">
              [{{ coupon.couponCategory }}]{{ coupon.couponName }}
            </v-card-title>

            <v-card-actions class="pb-3 pt-2 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">발급 기간 :</b> {{ coupon.couponStart }} ~
                {{ coupon.couponEnd }}</v-card-text
              >
            </v-card-actions>

            <v-card-actions class="pb-2 pt-1 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">사용 기간 :</b> {{ coupon.couponStart }} ~
                {{ coupon.couponEnd }}</v-card-text
              >
            </v-card-actions>

            <v-card-actions class="pb-3 pt-1 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">사용 조건 :</b>
                {{ coupon.couponCondition }}</v-card-text
              >
                <v-row justify="end" class="mt-2 mr-3">
                    <v-btn  color="blue lighten-3"  depressed  dark @click="down(coupon.couponNo)">다운로드</v-btn>
                </v-row>
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
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "EventCoupon",
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
      pageNum: 0,
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
    down(couponNo) {
         let token = localStorage.getItem('token')

         if(token !=null) {
             axios.get(`coupon/download/${couponNo}`,{ params : {token: token} })
             .then((res) => {
                 if(res.data == true){
                 alert("쿠폰이 발행되었습니다.")
                 }else {
                     alert('이미 발행된 쿠폰입니다.')
                 }
             })
             .catch(() => {
                 console.log("에러")
                 console.log(couponNo, token)
             }) 
         }else {
             alert("로그인이 필요합니다.")
         }
    }
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
  font-size: 50px;
  padding-top: 25px;
  padding-left: 30px;
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
  font-size: 20px;
  line-height: 23px;
  color: #333;
  font-weight: 700;
  height: 46px;
  overflow: hidden;
}
.subTitle {
  float: left;
  width: 60px;
  font-size: 13px;
  line-height: 21px;
  color: #666;
  font-weight: 700;
  margin-right: 10px;
}
.subContent {
  clear: right;
  font-size: 13px;
  line-height: 21px;
  color: #999;
}
.imgWrap {
  display: block;
  background: #f6f6f6;
  width: 100%;
  height: 208px;
  position: relative;
  z-index: 1;
}
.img {
  width: 272px;
  margin: auto;
  border-radius: 15px;
}
</style>
