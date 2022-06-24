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
            <b style="font-size: 45px; font-weight: bold">E</b> V E N T &nbsp;
            <b style="font-size: 45px; font-weight: bold">Z</b> O N E
          </div>
          <v-divider></v-divider>
        </v-col>
      </v-row>
      <v-row class="mt-5 mb-5">
        <v-col v-for="event in paginatedData" :key="event" lg="4" sm="6">
          <v-card width="400">
            <div class="imgWrap">
              <div class="pt-10">
                <v-img
                  :src="require(`@/assets/coupon/${이벤트썸네일}`)"
                  class="img"
                >
                </v-img>
              </div>
            </div>

            <v-card-title class="pt-1 pb-1 couponTitle">
              [{{ event.couponCategory }}]{{ coupon.couponName }}
            </v-card-title>

            <v-card-actions class="pb-1 pt-1 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">발급 기간 :</b> {{ coupon.couponStart }} ~
                {{ coupon.couponEnd }}</v-card-text
              >
            </v-card-actions>

            <v-card-actions class="pb-1 pt-1 pl-0">
              <v-card-text class="pb-0 pt-0 subContent"
                ><b class="subTitle">사용 기간 :</b> {{ coupon.couponStart }} ~
                {{ coupon.couponEnd }}</v-card-text
              >
              <v-btn icon @click="show = !show">
                <v-icon>{{
                  show ? "mdi-chevron-up" : "mdi-chevron-down"
                }}</v-icon>
              </v-btn>
            </v-card-actions>

            <v-divider></v-divider>

            <v-expand-transition>
              <div v-show="show">
                <v-card-text class="pb-1 pt-1 subContent"
                  ><b class="subTitle">사용 조건 :</b>
                  {{ coupon.couponCondition }}</v-card-text
                >
              </div>
            </v-expand-transition>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
