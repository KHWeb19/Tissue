<template>
  <v-app>
    <v-main>
      <new-nav-bar-2 />
      <div v-if="!isHome" style="height: 80px"></div>

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
  created() {
    this.url = window.location.href;
    if (this.url == "http://localhost:8080/") {
      this.isHome = true;
    } else {
      this.isHome = false;
    }
  },
};
</script>

<style scoped></style>
