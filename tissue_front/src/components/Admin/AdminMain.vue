<template>
  <div>
    <v-navigation-drawer
      permanent
      height="100%"
      width="400px"
      app
      color="blue lighten-5"
    >
      <v-list-item style="height: 20%">
        <v-list-item-content>
          <v-list-item-title class="mb-5">
            <router-link to="/">
              <span class="logo" style="color: skyblue">T</span>
              <span class="logo" style="color: pink">issue</span>
            </router-link>
          </v-list-item-title>
          <v-list-item-subtitle class="black--text" style="font-size: 20px">
            관리자님 환영합니다!
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          :to="item.route"
          link
          class="mt-5 mb-5"
        >
          <v-list-item-icon>
            <v-icon class="pink--text">{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content style="height: 100%">
            <v-list-item-title class="grey--text">{{
              item.title
            }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "AdminMain",
  components: {},
  data() {
    return {
      items: [
        { title: "회원 관리", icon: "mdi-account" },
        {
          title: "공연 관리",
          icon: "mdi-play-box-outline",
          route: "/performanceListPage",
        },
        { title: "공연장 관리", icon: "mdi-town-hall", route: "/hallList" },
        { title: "이벤트 관리", icon: "mdi-calendar-question" },
        {
          title: "쿠폰 관리",
          icon: "mdi-ticket-percent",
          route: "/couponList",
        },
        {
          title: "쿠폰 관리",
          icon: "mdi-ticket-percent",
          route: "/couponList",
        },
        {
          title: "쿠폰 관리",
          icon: "mdi-ticket-percent",
          route: "/couponList",
        },
      ],
    };
  },
};
</script>

<style scoped>
.logo {
  font-size: 50pt;
  font-family: "Pacifico", cursive;
}
</style>
