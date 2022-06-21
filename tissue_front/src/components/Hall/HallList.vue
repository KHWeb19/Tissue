<template>
  <div>
    <v-container class="white" style="width: 1400px; margin-top: 100px">
      <v-row justify="center">
        <v-col cols="auto"
          ><v-card flat>
            <div>
              <v-data-table
                :headers="headers"
                :items="halls"
                :items-per-page="itemsPerPage"
                :key="halls.hallNo"
                :search="search"
                :page.sync="page"
                hide-default-footer
                @page-count="pageCount = $event"
                class="vTable"
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
              </v-data-table>
              <div class="text-center pt-10">
                <v-pagination
                  v-model="page"
                  :length="pageCount"
                  color="pink lighten-3"
                ></v-pagination>
              </div>
              <div style="float: right">
                <v-btn
                  rounded
                  color="pink lighten-3"
                  style="color: white"
                  to="/HallRegister"
                  >공연장 등록</v-btn
                >
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
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
      headers: [
        {
          text: "No",
          value: "hallNo",
          width: "100px",
          align: "center",
        },
        {
          text: "공연장명",
          value: "hallName",
          width: "500px",
          align: "left",
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

<style scoped>
.vTable {
  line-height: 85px;
  border-top: 3px solid skyblue;
  color: black;
  width: 1000px;
}
</style>

<style lang="scss" scoped>
.v-data-table::v-deep th {
  font-size: 18px !important;
  border-bottom: 1px solid grey;
}
.v-data-table::v-deep td {
  font-size: 18px !important;
  border-bottom: 2px solid lightgrey;
  border-top: 1px solid lightgrey;
}
</style>
