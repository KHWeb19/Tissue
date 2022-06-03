<template>
  <v-app>
    <v-main>
      <nav-bar-white v-if="this.url == 'http://localhost:8080/'" />
      <nav-bar-black v-else id="navbar" />
      <v-main>
        <router-view />
      </v-main>
    </v-main>
    <FooterArea />
  </v-app>
</template>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import NavBarWhite from "@/components/Layout/NavBarWhite.vue";
import FooterArea from "@/components/Layout/FooterArea.vue";
import NavBarBlack from "./components/Layout/NavBarBlack.vue";

export default {
  name: "App",

  components: {
    NavBarWhite,
    FooterArea,
    NavBarBlack,
  },

  data() {
    return {
      url: window.location.href,
      scrollY: 0,
    };
  },

  mounted() {
    window.addEventListener("scroll", function () {
      console.log(window.scrollY);
    });
  },
};
</script>

<style scoped></style>
