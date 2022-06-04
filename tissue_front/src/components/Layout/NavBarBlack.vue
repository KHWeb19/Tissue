<template>
  <div class="menubar">
    <div class="header_box">
      <div>
        <router-link to="/">
          <div class="header_logo"><h1>Tissue</h1></div></router-link
        >
      </div>
      <v-spacer></v-spacer>
      <div class="header_mainItem">
        <v-tabs
          class="header_main_tabs"
          background-color="transparent"
          slider-color="orange"
          slider-size="3"
          color="black"
        >
          <v-tab style="display: none" to="/"></v-tab>
          <v-tab class="header_main_tab" to="/">콘서트</v-tab>
          <v-tab class="header_main_tab" to="/">뮤지컬</v-tab>
          <v-tab class="header_main_tab" to="/">연극</v-tab>
          <v-tab class="header_main_tab" to="/">전시회</v-tab>
        </v-tabs>
      </div>

      <div class="header_subItem">
        <v-tabs
          class="header_sub_tabs"
          background-color="transparent"
          slider-color="transparent"
          slider-size="3"
          color="black"
        >
          <v-tab class="header_sub_tab" to="/"
            ><span class="sub_tab_dot"></span>지역</v-tab
          >
          <v-tab class="header_sub_tab" to="/"
            ><span class="sub_tab_dot"></span>랭킹</v-tab
          >
          <v-tab class="header_sub_tab" to="/"
            ><span class="sub_tab_dot"></span>이벤트</v-tab
          >
          <v-text-field
            class="mt-1"
            hide-details
            append-icon="mdi-magnify"
            single-line
            light
            filled
            dense
            rounded
          />
          <v-btn icon class="mt-2 ml-2">
            <v-icon color="black" large>mdi-account-outline</v-icon>
          </v-btn>
        </v-tabs>
      </div>

      <v-spacer></v-spacer>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavBarBlack",
  data() {
    return {};
  },
};
</script>

<style scoped>
.menubar {
  position: absolute;
  height: 90px;
  width: 1920px;
  z-index: 1;
  /*
  background: white;
  background-color: white;
  border-bottom: 1px solid lightgrey;
  */
}
.header_box {
  display: flex;
  margin-top: 20px;
  margin-left: 50px;
}
.header_mainItem {
  padding-left: 200px;
  padding-right: 50px;
}
.header_logo {
  color: black;
  font-weight: bold;
}
.header_mainItem {
  margin-left: 300px;
  margin-right: 30px;
}
.header_main_tabs {
  color: black;
}
.header_main_tab {
  color: black;
  font-weight: bold;
  font-size: 18px;
}
.header_sub_tab {
  font-size: 14px;
  width: 100px;
}
.sub_tab_dot {
  margin-right: 10px;
  width: 3px;
  height: 3px;
  background: skyblue;
}

.theme--light.v-tabs > .v-tabs-bar .v-tab:not(.v-tab--active),
.theme--light.v-tabs > .v-tabs-bar .v-tab:not(.v-tab--active) > .v-icon,
.theme--light.v-tabs > .v-tabs-bar .v-tab:not(.v-tab--active) > .v-btn,
.theme--light.v-tabs > .v-tabs-bar .v-tab--disabled {
  color: black;
}

a {
  text-decoration: none;
}
</style>
