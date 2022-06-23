<template>
  <div>
    <v-navigation-drawer permanent>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6"> Application </v-list-item-title>
          <v-list-item-subtitle> subtext </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item v-for="item in items" :key="item.title" link>
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "AdminMain",
  data() {
    return {
      items: [
        { title: "회원 관리", icon: "mdi-view-dashboard" },
        { title: "공연 관리", icon: "mdi-image" },
        { title: "공연장 관리", icon: "mdi-help-box" },
        { title: "이벤트 관리", icon: "mdi-help-box" },
        { title: "쿠폰 관리", icon: "mdi-help-box" },
      ],
      right: null,
    };
  },
};
</script>
