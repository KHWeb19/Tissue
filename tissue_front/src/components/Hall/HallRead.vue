<template>
  <div>
    <v-container>
      <div style="border: 1px solid black" class="mb-10">
        <v-row>
          <v-col>
            <v-text-field
              flat
              height="100px"
              solo
              readonly
              label="이름"
              :value="hall.hallName"
              single-line
              style="height: 100px"
            />
            <v-text-field
              flat
              height="100px"
              solo
              readonly
              label="행"
              :value="hall.rowCnt"
              single-line
              style="height: 100px"
            />
            <v-text-field
              flat
              height="100px"
              solo
              readonly
              label="열"
              :value="hall.colCnt"
              single-line
              style="height: 100px"
            />
          </v-col>
        </v-row>
      </div>
      <div>
        <v-row v-for="(index, row) in rowArr" :key="row">
          <div>{{ row + 1 }} 열</div>
          <v-col v-for="(index, col) in colArr" :key="col">
            <div
              style="width: 100px; height: 100px; border: 1px solid black"
              @click="changeColor"
              :row="row"
              :col="col"
              id="color"
            >
              {{ row }},{{ col }}
            </div>
          </v-col>
        </v-row>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "HallRead",
  props: {
    hall: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      rowArr: [],
      colArr: [],
      dataTable: Array,
      isCreate: true,
    };
  },

  created() {
    if (this.isCreate == true) {
      /*
      this.dataTable = new Array(this.hall.rowCnt);

      for (let i = 0; i < this.dataTable.length; i++) {
        this.dataTable[i] = new Array(this.hall.colCnt);
      }
*/

      for (let i = 0; i < this.hall.rowCnt; i++) {
        this.rowArr.push("0");
      }
      for (let i = 0; i < this.hall.colCnt; i++) {
        this.colArr.push("0");
      }

      this.isCreate = false;
    }
  },
  methods: {
    changeColor(e) {
      const row = e.target.getAttribute("row");
      const col = e.target.getAttribute("col");
      console.log(row, col);

      for (let i = 0; i < this.rowArr.length; i++) {
        for (let j = 0; j < this.colArr.length; j++) {
          if (row == i && col == j) {
            console.log();
          }
        }
      }
    },
  },
};
</script>

<style scoped></style>
