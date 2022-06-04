<template>
  <v-app>
    <v-main>
      <nav-bar-black v-if="this.url != 'http://localhost:8080/'" />
      <v-main>
        <router-view />
      </v-main>
    </v-main>
    <FooterArea />
  </v-app>
</template>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import FooterArea from "@/components/Layout/FooterArea.vue";
import NavBarBlack from "./components/Layout/NavBarBlack.vue";

export default {
  name: "App",

  components: {
    FooterArea,
    NavBarBlack,
  },

  data() {
    return {
      url: window.location.href,
    };
  },
};
</script>

<style scoped></style>
