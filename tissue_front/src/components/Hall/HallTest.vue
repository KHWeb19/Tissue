<template>
  <div>
    <v-stepper v-model="e1">
      <v-stepper-header>
        <v-stepper-step :complete="e1 > 1" step="1"> 행열 지정 </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step :complete="e1 > 2" step="2">
          좌석 등급 지정
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step step="3"> 최종 저장</v-stepper-step>
      </v-stepper-header>

      <v-stepper-items>
        <v-stepper-content step="1">
          <v-card class="mb-12" color="grey lighten-1" height="200px">
            <div style="width: 500px">
              <v-text-field v-model="seatRow" label="행" clearable />
              <v-text-field v-model="seatCol" label="열" clearable />
            </div>
          </v-card>

          <v-btn color="primary" @click="[(e1 = 2), click()]"> Continue </v-btn>

          <v-btn text> Cancel </v-btn>
        </v-stepper-content>

        <v-stepper-content step="2">
          <v-card class="mb-12" color="grey lighten-1" height="500px">
            <div class="hall">
              <div>
                <v-radio-group v-model="radioGroup" row>
                  <v-radio
                    color="orange"
                    v-for="kinds in kindsGrade"
                    :key="kinds"
                    :label="`${kinds}`"
                    :value="kinds"
                  >
                  </v-radio>
                </v-radio-group>
              </div>
              <div class="hall_box">
                <v-row v-model="rowI" v-for="(index, row) in rowArr" :key="row">
                  <v-col
                    v-model="colI"
                    v-for="(index, col) in colArr"
                    :key="col"
                  >
                    <div class="hall_seat" @click="changeColor">
                      {{ rowI }}
                    </div>
                  </v-col>
                </v-row>
              </div>
            </div>
          </v-card>

          <v-btn color="primary" @click="e1 = 3"> Continue </v-btn>

          <v-btn text @click="e1 = 1"> Cancel </v-btn>
        </v-stepper-content>

        <v-stepper-content step="3">
          <v-card class="mb-12" color="grey lighten-1" height="500px"></v-card>

          <v-btn color="primary" @click="e1 = 1"> Continue </v-btn>

          <v-btn text @click="e1 = 2"> Cancel </v-btn>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </div>
</template>

<script>
//import axios from "axios";

export default {
  name: "HallTest",
  data() {
    return {
      rowI: 0,
      colI: 0,
      e1: 1,
      seatRow: "",
      seatCol: "",
      rowArr: [],
      colArr: [],
      radioGroup: "",
      kindsGrade: ["R", "S", "VIP"],
      arr: null,
    };
  },
  methods: {
    click() {
      for (let i = 0; i < this.seatRow; i++) {
        this.rowArr.push("R");
      }
      for (let j = 0; j < this.seatCol; j++) {
        this.colArr.push("R");
      }

      /*
      this.arr = [this.seatRow][this.seatCol];
      for (let i = 0; i < this.seatRow; i++) {
        for (let j = 0; j < this.seatCol; j++) {
          this.arr[i][j] = "R";
        }
      }
      console.log(this.arr);
      */
    },

    changeColor() {
      console.log(this.rowI + "," + this.colI);
    },
  },
};
</script>

<style scoped>
.hall {
  display: flex;
  justify-content: center;
}

.hall_seat {
  width: 50px;
  height: 50px;
  border: 1px solid black;
}
</style>
