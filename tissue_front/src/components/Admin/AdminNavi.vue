<template>
  <div>
    <v-navigation-drawer
      permanent
      height="100%"
      width="350px"
      app
      color="blue lighten-5"
    >
      <v-list-item style="height: 25%">
        <v-list-item-content>
          <v-list-item-title class="mb-5 ml-3">
            <router-link to="/">
              <span class="logo" style="color: skyblue">T</span>
              <span class="logo" style="color: pink">issue</span>
            </router-link>
          </v-list-item-title>
          <v-list-item-subtitle
            class="black--text ml-5"
            style="font-size: 20px"
          >
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
          class="mt-5 mb-5 ml-5"
        >
          <v-list-item-icon>
            <v-icon color="pink lighten-3">{{ item.icon }}</v-icon>
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
        { title: "회원 관리", icon: "mdi-account", route: "/Admin" },
        {
          title: "공연 관리",
          icon: "mdi-play-box-outline",
          route: "/performanceListPage",
        },
        { title: "공연장 관리", icon: "mdi-town-hall", route: "/hallList" },
        {
          title: "이벤트 관리",
          icon: "mdi-calendar-question",
          route: "/eventListPage",
        },
        {
          title: "쿠폰 관리",
          icon: "mdi-ticket-percent",
          route: "/couponList",
        },
        { title: "환불 관리", icon: "mdi-cash-refund", route: "/refundList" },
        {
          title: "공지사항 관리",
          icon: "mdi-bulletin-board",
          route: "/noticeRegister",
        },
        {
          title: "자주 묻는 질문 관리",
          icon: "mdi-comment-question",
          route: "/qnaBestRegister",
        },
        {
          title: "QnA 답변 관리",
          icon: "mdi-comment-question-outline",
          route: "/qnaList",
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
