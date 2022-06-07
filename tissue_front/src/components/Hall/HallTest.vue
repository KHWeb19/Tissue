<template>
  <div>
    <div class="hall">
      <div class="hall_box">
        <v-row v-for="(row, index) in rows" :key="index">
          <v-col v-for="(col, index) in cols" :key="index">
            <div class="hall_seat">{{ index }}</div>
          </v-col>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HallTest",
  data() {
    return {
      cols: [
        { num: 1, isClick: 0 },
        { num: 2, isClick: 0 },
        { num: 3, isClick: 0, grade: "r" },
        { num: 4, isClick: 0 },
        { num: 5, isClick: 0 },
      ],
      rows: [1, 2, 3, 4, 5],
    };
  },
  mounted() {
    let i;
    for (i = 0; i < this.cols.length; i++) {
      if (this.cols[i].grade == "r") {
        document.getElementsByClassName("hall_seat")[i].style.background =
          "orange";
      }
    }
  },
  methods: {
    clickSeat() {
      this.rows[0].isClick = 1;
    },
  },
};
</script>

<style scoped>
.hall {
  display: flex;
  justify-content: center;
  width: 500px;
  height: 500px;
}

.hall_seat {
  width: 50px;
  height: 50px;
  border: 1px solid black;
}
</style>
