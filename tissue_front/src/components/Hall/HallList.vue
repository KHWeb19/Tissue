<template>
  <div>
    <v-container class="white" style="width: 1400px">
      <v-row>
        <v-col
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
                  color="orange"
                ></v-pagination>
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
          class: "orange lighten-5",
        },
        {
          text: "공연장명",
          value: "hallName",
          width: "500px",
          align: "left",
          class: "orange lighten-5",
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
