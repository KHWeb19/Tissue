<template>
    <v-container class="couponMain">
        <div class="mr-10 mb-5 mt-7">
            <span class="ml-3" style="font-size:20pt;"> 사용 가능 쿠폰 </span>
            <p/>
            <hr color="#90CAF9">
        </div>
      <v-row v-if="coupons.length == 0">
            <v-icon large color="red">mdi-exclamation-thick</v-icon>
            <span style="font-size:17pt" class="mr-10">사용 가능한 쿠폰이 없습니다.</span>
            <v-btn depressed color="blue lighten-3" dark @click="goPage"> 다운하러 가기 </v-btn>
      </v-row>
      <v-row v-else no-gutters>
        <v-col
          v-for="coupon in coupons"
          :key="coupon.couponNo"
          lg="4"
          sm="6"
          class="pt-5"
        >
          <v-card class="card">
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
            <div class="content">

            <v-card-title class="pt-3 pb-1 couponTitle">
              [{{ coupon.couponCategory }}]{{ coupon.couponName }}
            </v-card-title>

            <v-card-actions class="pb-2 pt-3 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">사용 기간 :</b><p/> {{ coupon.couponStart }} ~
                {{ coupon.couponEnd }}</v-card-text
              >
            </v-card-actions>

            <v-card-actions class="pb-3 pt-1 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">사용 조건 :</b>
                {{ coupon.couponCondition }}</v-card-text
              >
            </v-card-actions>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
</template>

<script>
export default {
    name:'MyPageCoupon',
    props: {
        coupons: {
            type: Array
        }
    },
    methods: {
        goPage() {
            this.$router.push("/event")
        }
    }
}
</script>

<style scoped>
.imgWrap {
  display: block;
  background: #f6f6f6;
  width: 100%;
  height: 208px;
  position: relative;
  z-index: 1;
}
.couponPrice {
  font-size: 40px;
  padding-top: 28px;
  padding-left: 32px;
}
.img {
  width: 272px;
  margin: auto;
  border-radius: 15px;
}
.content {
    zoom:0.9;
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
  width: 60px;
  font-size: 14px;
  line-height: 21px;
  color: #666;
  font-weight: 700;
  margin-right: 10px;
}
.subContent {
  clear: right;
  font-size: 15px;
  line-height: 21px;
  color: #999;
}
.couponMain {
    position: relative;

    bottom: 30px;
}
.card {
    width:223px;
    height: 360px; 
}
@media (min-width: 1800px) {
    .card {
        width:330px;
    }
    .couponPrice {
    font-size: 50px;
    padding-top: 30px;
    padding-left: 50px;
    }
}

</style>