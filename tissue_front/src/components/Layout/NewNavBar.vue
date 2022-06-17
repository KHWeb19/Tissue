<template>
  <div>
    <v-toolbar height="80px" color="transparent" elevation="0" class="menubar">
      <v-toolbar-title>
        <router-link to="/">
          <div>
            <div class="header_logo"><h1>Tissue</h1></div>
          </div>
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          active-class="main_active"
          class="header_main_item"
          text
          v-for="link in mainLinks"
          :key="link.icon"
          :to="link.route"
        >
          {{ link.text }}
        </v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          class="header_sub_item"
          text
          v-for="link in subLinks"
          :key="link.icon"
          :to="link.route"
        >
          <span class="sub_tab_dot"></span> {{ link.text }}
        </v-btn>
      </v-toolbar-items>

      <v-toolbar-items>
        <v-text-field
          hide-details
          append-icon="mdi-magnify"
          single-line
          filled
          dense
          rounded
          dark
          class="mt-5 mr-10"
        />
        <v-btn icon class="sub_tab_icon mr-3">
          <v-icon color="white" large>mdi-account-outline</v-icon>
        </v-btn>
        <div class="mt-4" v-if="token">
            <v-btn icon class="sub_tab_icon mr-8" @click="logout">
                <v-icon color="white" large>mdi-logout</v-icon>
            </v-btn>
        </div>
        <div class="mt-4" v-else>
            <v-btn icon class="sub_tab_icon mr-8" :to="{name: 'MemberLoginPage'}">
                <v-icon color="white" large>mdi-login</v-icon>
            </v-btn>
        </div>
      </v-toolbar-items>
    </v-toolbar>
  </div>
</template>

<script>
export default {
  name: "NewNavBar",
  data() {
    return {
      isScroll: false,
      mainLinks: [
        { text: "콘서트", route: "a" },
        { text: "뮤지컬", route: "b" },
        { text: "연극", route: "c" },
        { text: "전시회", route: "d" },
      ],
      subLinks: [
        { text: "지역", route: "ㄱ" },
        { text: "랭킹", route: "ㄴ" },
        { text: "이벤트", route: "ㄷ" },
      ],
      token: localStorage.getItem('token')
    };
  },
  methods: {
      logout() {
          let result = confirm('로그아웃하시겠습니까?')

          if (result) {
              localStorage.removeItem("token")
              history.go(0)
          }
      }
  }
};
</script>

<style scoped>
.menubar {
  position: absolute;
  z-index: 3;
  width: 100%;
}

.header_logo {
  color: white;
  font-weight: bold;
  margin-left: 50px;
}

.header_main_item {
  color: white;
  font-weight: bold;
  font-size: 18px;
}
.header_main_item::before {
  display: none;
}
.header_sub_item {
  color: white;
  font-size: 14px;
}
.header_sub_item::before {
  display: none;
}
.sub_tab_dot {
  width: 3px;
  height: 3px;
  background: skyblue;
  margin-right: 10px;
}
.sub_tab_icon::before {
  display: none;
}
.main_active {
  border-bottom: 5px solid skyblue;
}
a {
  text-decoration: none;
}
</style>
