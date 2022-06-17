<template>
  <v-app>
    <v-main>
      <div
        v-if="
          this.$route.name != 'GoogleOAuth' &&
          this.$route.name != 'KakaoOAuth' &&
          this.$route.name != 'MemberLoginPage' &&
          this.$route.name != 'MemberJoinPage' &&
          this.$route.name != 'MemberJoinPage2' &&
          this.$route.name != 'MemberFindIdPage' &&
          this.$route.name != 'MemberFindPwPage'
        "
      >
        <new-nav-bar-2 /> 
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
        this.$route.name != 'MemberFindPwPage' 
      "
    />
    <join-footer 
      v-if="
        this.$route.name != 'KakaoOAuth' && 
        this.$route.name != 'GoogleOAuth'"/>
  </v-app>
</template>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import FooterArea from "@/components/Layout/FooterArea.vue";
import NewNavBar2 from "@/components/Layout/NewNavBar2.vue";
import joinFooter from "@/components/Layout/JoinFooter.vue";
export default {
  name: "App",
  components: {
    FooterArea,
    joinFooter,
    NewNavBar2,
  },
  data() {
    return {
      url: null,
    };
  },
}
</script>
<style scoped>
.v-application {
    font-family: 'Noto Sans KR', sans-serif;
}
@font-face {
    font-family: 'Noto Sans KR' ;
    src: url('assets/font/NotoSansKR-Bold.otf');
    font-weight: 500;
}
</style>

<style>
a {
  text-decoration: none;
}
</style>