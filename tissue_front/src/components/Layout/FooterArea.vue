<template>
  <div>
    <v-footer dark padless style="display: block">
      <v-card flat tile class="white lighten-1 black--text text-center">
        <div class="footer_box">
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
};
</script>

<style scoped>
.footer_box {
  display: flex;
  justify-content: center;
  border-top: 1px solid lightgrey;
}
.footer_center {
  margin: 30px;
  padding-left: 100px;
  padding-right: 100px;
  display: flex;
  border-left: 1px solid lightgrey;
  border-right: 1px solid lightgrey;
}
.footer_center_item {
  margin-left: 50px;
  margin-right: 50px;
  color: black;
}
.footer_center_item_text {
  font-size: 16px;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 0;
}
a {
  text-decoration: none;
}
</style>
