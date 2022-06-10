<template>
  <div class="background">
    <v-container class="mt-8 mb-10">
      <v-row justify="center">
        <div class="logo" style="color: skyblue">T</div>
        <div class="logo" style="color: pink">issue</div>
        <div class="mt-3">
          &nbsp; &nbsp; copyright Tissue corp. @2022 All rights reserved
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "JoinFooter",
};
</script>

<style scoped>
.background {
  background-color: rgb(241, 241, 241);
}
.logo {
  font-size: 20pt;
  font-family: "Pacifico", cursive;
}
</style>
