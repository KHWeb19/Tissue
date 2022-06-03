<template>
  <div>
    <v-footer dark padless style="display: block">
      <v-card flat tile class="white lighten-1 black--text text-center">
        <div class="footer_box">
          <div class="footer_left">
            <h1 class="footer_left_h1">1234 - 5678</h1>
            <div style="display: flex">
              <div>
                <p class="footer_left_left">평일</p>
                <p class="footer_left_left">토요일</p>
              </div>
              <div>
                <p class="footer_left_right">09:00 ~ 18:00</p>
                <p class="footer_left_right">
                  09:00 ~ 17:00
                  <b class="footer_left_left">( 일요일 및 공휴일 휴무 )</b>
                </p>
              </div>
            </div>
          </div>
          <div class="footer_center">
            <router-link to="/">
              <div class="footer_center_item">
                <v-img
                  :src="require('@/assets/notice.png')"
                  max-width="80px"
                  max-height="80px"
                />
                <p class="footer_center_item_text">공지사항</p>
              </div>
            </router-link>
            <router-link to="/">
              <div class="footer_center_item">
                <v-img
                  :src="require('@/assets/cs.png')"
                  max-width="80px"
                  max-height="80px"
                />
                <p class="footer_center_item_text">고객센터</p>
              </div>
            </router-link>
            <div class="footer_center_item">
              <v-img
                :src="require('@/assets/resume.png')"
                max-width="80px"
                max-height="80px"
                class="ml-3"
              />
              <p class="footer_center_item_text">공연 신청서</p>
            </div>
          </div>
          <div class="footer_right">
            <div class="footer_right_social">
              <v-btn class="footer_right_icon" icon
                ><v-icon x-large color="blue">mdi-facebook</v-icon></v-btn
              >
              <v-btn class="footer_right_icon" icon
                ><v-icon x-large color="pink">mdi-instagram</v-icon></v-btn
              >
              <v-btn class="footer_right_icon" icon
                ><v-icon x-large color="red">mdi-youtube</v-icon></v-btn
              >
            </div>

            <div class="footer_right_store">
              <v-img
                :src="require('@/assets/googleplay.png')"
                max-width="120px"
                max-height="80px"
                class="mr-5"
              />
              <v-img
                :src="require('@/assets/appstore.png')"
                max-width="110px"
                max-height="80px"
              />
            </div>
          </div>
        </div>

        <v-card-text
          class="black--text"
          style="border-top: 1px solid lightgrey"
        >
          {{ new Date().getFullYear() }} — <strong>Vuetify</strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </div>
</template>

<script>
export default {
  name: "FooterArea",
  data() {
    return {};
  },
};
</script>

<style scoped>
.footer_box {
  display: flex;
  justify-content: center;
  border-top: 1px solid lightgrey;
}
.footer_center {
  justify-content: center;
  width: 600px;
  margin: 30px;
  display: flex;
  border-left: 1px solid lightgrey;
  border-right: 1px solid lightgrey;
}
.footer_center_item {
  margin-left: 35px;
  margin-right: 35px;
  color: black;
  margin-top: 15px;
}
.footer_center_item_text {
  font-size: 16px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 0;
}
.footer_left {
  width: 350px;
  margin: 35px;
  text-align: left;
}
.footer_left_h1 {
  font-size: 50px;
  color: lightcoral;
}
.footer_left_left {
  font-size: 13px;
  margin-right: 10px;
  margin-bottom: 0px;
  font-weight: lighter;
}
.footer_left_right {
  font-size: 13px;
  font-weight: 500;
  margin-bottom: 0px;
}
.footer_right {
  margin: 30px;
  width: 250px;
}
.footer_right_icon {
  padding: 30px;
}
.footer_right_social {
  display: flex;
  margin: 10px 5px 20px 25px;
}
.footer_right_store {
  display: flex;
}
a {
  text-decoration: none;
}
</style>
