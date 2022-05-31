<template>
  <v-app>
    <v-main>
      <NavBar />
      <v-main>
        <router-view />
      </v-main>
      <FooterArea />
    </v-main>
  </v-app>
</template>

<script>
import NavBar from "@/components/Layout/NavBar.vue";
import FooterArea from "@/components/Layout/FooterArea.vue";
export default {
  name: "App",

  components: {
    NavBar,
    FooterArea,
  },

  data: () => ({
    //
  }),
};
</script>
