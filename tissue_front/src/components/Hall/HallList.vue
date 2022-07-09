<template>
  <v-container>
    <v-app-bar app elevation="3">
      <v-toolbar-title class="ml-3"> 📌 공연장 관리 </v-toolbar-title>
    </v-app-bar>
    <v-container class="white">
      <v-row justify="center">
        <v-col>
          <div>
            <v-row class="countMember ml-3 mt-10" style="font-size: 18pt">
              전체 공연장 수&nbsp;<span style="color: skyblue">{{
                halls.length
              }}</span>
              개
            </v-row>
            <v-row justify="end">
              <v-col cols="5">
                <v-text-field
                  v-model="keyword"
                  append-icon="search"
                  label="공연장 검색"
                  single-line
                  color="pink lighten-3"
                ></v-text-field>
              </v-col>
            </v-row>
            <v-data-table
              :headers="headers"
              :items="halls"
              :items-per-page="itemsPerPage"
              :key="halls.hallNo"
              :search="keyword"
              :page.sync="page"
              hide-default-footer
              @page-count="pageCount = $event"
            >
              <template v-slot:[`item.hallName`]="{ item }">
                <router-link
                  style="color: black; text-decoration: none"
                  :to="{
                    name: 'HallReadPage',
                    params: { hallNo: item.hallNo.toString() },
                  }"
                  >{{ item.hallName }}</router-link
                >
              </template>
              <template v-slot:[`item.seatCnt`]="{ item }">
                {{ item.rowCnt * item.colCnt }}
              </template>
            </v-data-table>
            <div class="text-center">
              <v-pagination
                v-model="page"
                :length="pageCount"
                color="pink lighten-3"
                circle
              ></v-pagination>
            </div>
            <div style="float: right">
              <v-btn
                rounded
                color="blue lighten-3"
                style="color: white"
                to="/HallRegister"
                >공연장 등록</v-btn
              >
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
export default {
  name: "HallList",
  props: {
    halls: {
      type: Array,
    },
  },

  data() {
    return {
      keyword: "",
      headers: [
        {
          text: "공연장 번호",
          value: "hallNo",
          width: "150",
          align: "center",
        },
        {
          text: "공연장명",
          value: "hallName",
          width: "150",
          align: "center",
        },
        {
          text: "좌석수",
          value: "seatCnt",
          width: "150",
          align: "center",
        },
      ],
      search: "",
      page: 1,
      pageCount: 0,
      itemsPerPage: 10,
    };
  },
};
</script>
