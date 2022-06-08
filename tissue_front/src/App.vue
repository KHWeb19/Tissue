<template>
  <v-app>
    <v-main>
      <nav-bar-black
        v-if="
          this.url != 'http://localhost:8080/' &&
          this.url != 'http://localhost:8080/join' &&
          this.url != 'http://localhost:8080/join/form'
        "
      />
      <v-main>
        <router-view />
      </v-main>
    </v-main>
    <footer-area
      v-if="
        this.url != 'http://localhost:8080/join' &&
        this.url != 'http://localhost:8080/join/form'
      "
    />
    <join-footer />
  </v-app>
</template>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
import FooterArea from "@/components/Layout/FooterArea.vue";
import NavBarBlack from "@/components/Layout/NavBarBlack.vue";
import joinFooter from "@/components/Layout/JoinFooter.vue";

export default {
  name: "App",

  components: {
    FooterArea,
    NavBarBlack,
    joinFooter,
  },

  data() {
    return {
      url: window.location.href,
    };
  },
};
</script>

<style scoped></style>
