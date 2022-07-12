<template>
  <div>
    <div class="menubar2">
      <v-toolbar height="83px" elevation="0">
        <v-toolbar-title>
          <router-link to="/">
            <div>
              <div class="header_logo" @click="goHome">
                  <span style="color:skyblue">T</span><span style="color:pink">issue</span></div>
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
            @click:append="search"
            v-model="keyword"
            single-line
            filled
            dense
            rounded
            color="black"
            class="mt-6 mr-10"
          />
          <v-btn icon class="mr-3 mt-1 sub_tab_icon" @click="goToMyPage">
            <v-icon color="black" large>mdi-account-outline</v-icon>
          </v-btn>
          <div class="mt-5" v-if="token">
            <v-btn icon class="sub_tab_icon mr-8" @click="logout">
              <v-icon color="black" large>mdi-logout</v-icon>
            </v-btn>
          </div>
          <div class="mt-5" v-else>
            <v-btn
              icon
              class="sub_tab_icon mr-8"
              :to="{ name: 'MemberLoginPage' }"
            >
              <v-icon color="black" large>mdi-login</v-icon>
            </v-btn>
          </div>
        </v-toolbar-items>
      </v-toolbar>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';
export default {
  name: "NewNavBar2",
  data() {
    return {
      isScroll: false,
      mainLinks: [
        { text: "콘서트", route: "/concert" },
        { text: "뮤지컬", route: "/musical" },
        { text: "연극", route: "/theater" },
        { text: "전시회", route: "/exhibition" },
      ],
      subLinks: [
        { text: "지역", route: "/area" },
        { text: "랭킹", route: "/ranking" },
        { text: "이벤트/쿠폰", route: "/event" },
      ],
      token: "",
      keyword:''
    };
  },
  mounted() {
    if (
      this.$route.name != "GoogleOAuth" &&
      this.$route.name != "KakaoOAuth" &&
      this.$route.name != "MemberLoginPage" &&
      this.$route.name != "MemberJoinPage" &&
      this.$route.name != "MemberJoinPage2" &&
      this.$route.name != "MemberFindIdPage" &&
      this.$route.name != "MemberFindPwPage" &&
      this.$route.name != "PerformanceDetailPage"
    ) {
      window.addEventListener("scroll", function () {
        let a = document.getElementsByClassName("menubar2");
        for (let i = 0; i < a.length; i++) {
          if (window.scrollY <= 600) {
            a[i].style.position = "absolute";
          } else if (window.scrollY > 600) {
            a[i].style.position = "fixed";
          }
        }
      });
    }
  },
  created() {
    this.token = localStorage.getItem("token");
  },
  methods: {
    ...mapActions(['fetchSearchList', 'fetchNoticeSearchList']),
    goHome() {
      this.$router.push("/");
    },
    logout() {
      let result = confirm("로그아웃하시겠습니까?");
      if (result) {
        localStorage.removeItem("token");
        localStorage.removeItem("refreshToken")
        this.$router.push("/");
        this.token = null;
      }
    },
    goToMyPage() {
        let token = localStorage.getItem('token')
        let refreshToken = localStorage.getItem('refreshToken')

        if (token != null) {
            axios.get('Admin/role', { params: { token:token } })
                .then((res) => {
                    if (res.data == true) {
                        return this.$router.push("/Admin")
                    } else {
                        return this.$router.push("/myPage")
                    }
                })
                .catch(() => {
                axios.get('security/check', {params: {token: token, refreshToken: refreshToken}})
                .then((res) => {
                    console.log(res.data)
                    console.log("리프레쉬 중 ! ")
                    localStorage.removeItem('token')
                    localStorage.setItem('token', res.data)
                    return this.goToMyPage()
                })
            })
        } else {
            alert("로그인이 필요합니다.");
            return this.$router.push("/login")
        }
    },
    search() {
        console.log(this.keyword)
        this.fetchSearchList(this.keyword),
        this.fetchNoticeSearchList(this.keyword)
        this.$router.push({name: 'SearchPage', params: { keyword: this.keyword }})
        this.keyword=''
    }
  },
};
</script>

<style scoped>
.menubar2 {
  position: absolute;
  z-index: 1;
  width: 100%;
  border-bottom: 1px solid lightgrey;
}

.header_logo {
  margin-left: 50px;
  font-family: 'Pacifico', cursive;
  font-size: 40pt;
}

.header_main_item {
  color: black;
  font-weight: bold;
  font-size: 18px;
  margin-top:3px;
}
.header_main_item::before {
  display: none;
}
.header_sub_item {
  color: black;
  font-size: 14px;
  margin-top:3px;
}
.header_sub_item::before {
  display: none;
}
.sub_tab_dot {
  width: 3px;
  height: 3px;
  background: skyblue;
  margin-right: 10px;
  margin-top:3px;
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
