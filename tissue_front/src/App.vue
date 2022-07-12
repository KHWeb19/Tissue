<template>
  <v-app>
    <v-main style="padding: 0">
      <div
        v-if="
          this.$route.name == 'HallRegisterPage' ||
          this.$route.name == 'HallListPage' ||
          this.$route.name == 'HallReadPage' ||
          this.$route.name == 'PerformanceListPage' ||
          this.$route.name == 'PerformanceRegisterPage' ||
          this.$route.name == 'PerformanceReadPage' ||
          this.$route.name == 'PerformanceModifyPage' ||
          this.$route.name == 'CouponRegisterPage' ||
          this.$route.name == 'CouponListPage' ||
          this.$route.name == 'CouponModifyPage' ||
          this.$route.name == 'AdminMember' ||
          this.$route.name == 'EventListPage' ||
          this.$route.name == 'EventRegisterPage' ||
          this.$route.name == 'EventReadPage' ||
          this.$route.name == 'EventModifyPage' ||
          this.$route.name == 'RefundPage'
        "
      >
        <admin-navi />
      </div>
      <div
        v-if="
          this.$route.name != 'GoogleOAuth' &&
          this.$route.name != 'KakaoOAuth' &&
          this.$route.name != 'MemberLoginPage' &&
          this.$route.name != 'MemberJoinPage' &&
          this.$route.name != 'MemberJoinPage2' &&
          this.$route.name != 'MemberFindIdPage' &&
          this.$route.name != 'MemberFindPwPage' &&
          this.$route.name != 'PerformanceRegisterPage' &&
          this.$route.name != 'PerformanceReadPage' &&
          this.$route.name != 'PerformanceModifyPage' &&
          this.$route.name != 'AdminMainPage' &&
          this.$route.name != 'HallRegisterPage' &&
          this.$route.name != 'HallListPage' &&
          this.$route.name != 'HallReadPage' &&
          this.$route.name != 'PerformanceListPage' &&
          this.$route.name != 'PerformanceRegisterPage' &&
          this.$route.name != 'PerformanceReadPage' &&
          this.$route.name != 'PerformanceModifyPage' &&
          this.$route.name != 'CouponRegisterPage' &&
          this.$route.name != 'CouponListPage' &&
          this.$route.name != 'CouponModifyPage' &&
          this.$route.name != 'AdminMember' &&
          this.$route.name != 'EventListPage' &&
          this.$route.name != 'EventRegisterPage' &&
          this.$route.name != 'EventReadPage' &&
          this.$route.name != 'EventModifyPage' &&
          this.$route.name != 'RefundPage' &&
          this.$route.name != 'NonMemberFindSerialPage' &&
          this.$route.name != 'NonMemberFindRvPage'
        "
      >
        <new-nav-bar-2 />
        <div v-if="this.$route.name != 'home'" style="height: 80px"></div>
      </div>
      <v-main>
        <router-view />
      </v-main>
    </v-main>

    <footer-area
      v-if="
        this.$route.name != 'GoogleOAuth' &&
        this.$route.name != 'KakaoOAuth' &&
        this.$route.name != 'MemberLoginPage' &&
        this.$route.name != 'MemberJoinPage' &&
        this.$route.name != 'MemberJoinPage2' &&
        this.$route.name != 'MemberFindIdPage' &&
        this.$route.name != 'MemberFindPwPage' &&
        this.$route.name != 'AdminMainPage' &&
        this.$route.name != 'HallRegisterPage' &&
        this.$route.name != 'HallListPage' &&
        this.$route.name != 'HallReadPage' &&
        this.$route.name != 'PerformanceListPage' &&
        this.$route.name != 'PerformanceRegisterPage' &&
        this.$route.name != 'PerformanceReadPage' &&
        this.$route.name != 'PerformanceModifyPage' &&
        this.$route.name != 'CouponRegisterPage' &&
        this.$route.name != 'CouponListPage' &&
        this.$route.name != 'CouponModifyPage' &&
        this.$route.name != 'AdminMember' &&
        this.$route.name != 'EventListPage' &&
        this.$route.name != 'EventRegisterPage' &&
        this.$route.name != 'EventReadPage' &&
        this.$route.name != 'EventModifyPage' &&
        this.$route.name != 'RefundPage' &&
        this.$route.name != 'NonMemberFindSerialPage' &&
        this.$route.name != 'NonMemberFindRvPage'
      "
    />
    <join-footer
      v-if="
        this.$route.name != 'KakaoOAuth' &&
        this.$route.name != 'GoogleOAuth' &&
        this.$route.name != 'AdminMainPage' &&
        this.$route.name != 'HallRegisterPage' &&
        this.$route.name != 'HallListPage' &&
        this.$route.name != 'HallReadPage' &&
        this.$route.name != 'PerformanceListPage' &&
        this.$route.name != 'PerformanceRegisterPage' &&
        this.$route.name != 'PerformanceReadPage' &&
        this.$route.name != 'PerformanceModifyPage' &&
        this.$route.name != 'CouponRegisterPage' &&
        this.$route.name != 'CouponListPage' &&
        this.$route.name != 'CouponModifyPage' &&
        this.$route.name != 'AdminMember' &&
        this.$route.name != 'EventListPage' &&
        this.$route.name != 'EventRegisterPage' &&
        this.$route.name != 'EventReadPage' &&
        this.$route.name != 'EventModifyPage' &&
        this.$route.name != 'RefundPage'
      "
    />
  </v-app>
</template>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import FooterArea from "@/components/Layout/FooterArea.vue";
import NewNavBar2 from "@/components/Layout/NewNavBar2.vue";
import joinFooter from "@/components/Layout/JoinFooter.vue";
import AdminNavi from "@/components/Admin/AdminNavi.vue";
export default {
  name: "App",
  components: {
    FooterArea,
    joinFooter,
    NewNavBar2,
    AdminNavi,
  },
  data() {
    return {
      url: null,
    };
  },
};
</script>
<style scoped>
.v-application {
  font-family: "Noto Sans KR", sans-serif;
}
@font-face {
  font-family: "Noto Sans KR";
  src: url("assets/font/NotoSansKR-Bold.otf");
}
</style>

<style>
a {
  text-decoration: none;
}
</style>
