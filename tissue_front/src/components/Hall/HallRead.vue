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
      <!--
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

      <div style="width: 700px">
        <v-row v-for="(line, index) in dataTable" :key="index">
          <div class="mt-3">{{ index + 1 }} 열</div>
          <v-col v-for="(item, indexes) in line" :key="indexes">
            <div
              style="width: 100px; height: 100px; border: 1px solid black"
              @click="changeColor"
              class="seat"
              :row="index"
              :col="indexes"
            >
              {{ index }}, {{ indexes }}
            </div>
          </v-col>
        </v-row>
      </div>
      -->
      <v-radio-group v-model="radioGroup" row>
        <v-radio
          v-for="kind in kindsGrade"
          :key="kind"
          :label="`${kind}`"
          :value="kind"
        >
        </v-radio>
      </v-radio-group>
      <div class="d-flex">
        <div class="mr-10" v-for="item in gradeColor" :key="item.grade">
          <div
            :class="{
              yellow: item.color == 'yellow',
              green: item.color == 'green',
              purple: item.color == 'purple',
            }"
          ></div>
          <div>{{ item.grade }}</div>
        </div>
      </div>
      <table>
        <tr v-for="(line, index) in dataTable" :key="index" :row-index="index">
          <td
            v-for="(item, indexes) in line"
            :key="indexes"
            :cell-index="indexes"
          >
            <div
              style="width: 100px; height: 100px; border: 1px solid black"
              @click="changeColor"
              class="seat"
              :row-index="index"
              :cell-index="indexes"
            >
              {{ index }},{{ indexes }},{{ item.grade }}
            </div>
          </td>
        </tr>
      </table>
      <v-btn @click="modify">수정</v-btn>
      <v-btn @click="reset">초기화</v-btn>
      <v-btn to="/hallList">리스트로</v-btn>
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
      radioGroup: "R",
      kindsGrade: ["R", "S", "VIP"],
      gradeColor: [
        { color: "yellow", grade: "R" },
        { color: "green", grade: "S" },
        { color: "purple", grade: "VIP" },
      ],
    };
  },

  created() {
    if (this.isCreate == true) {
      this.dataTable = new Array(this.hall.rowCnt);

      for (let i = 0; i < this.dataTable.length; i++) {
        this.dataTable[i] = new Array(this.hall.colCnt);
      }

      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          this.dataTable[i][j] = { grade: "R", click: false };
        }
      }
      console.log(this.dataTable);

      this.isCreate = false;
    }
  },
  mounted() {
    this.paintSeatWithGrade();
  },
  methods: {
    changeColor(e) {
      let row = e.target.getAttribute("row-index");
      let col = e.target.getAttribute("cell-index");
      console.log(this.radioGroup, row, col);

      this.rowArr.push(row);
      this.colArr.push(col);
      console.log(this.rowArr, this.colArr);

      if (this.dataTable[row][col].click == false) {
        this.dataTable[row][col].click = true;
        this.rowArr.push(row);
        this.colArr.push(col);
        console.log(this.rowArr, this.colArr);
      } else if (this.dataTable[row][col].click == true) {
        this.dataTable[row][col].click = false;
      }

      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (this.dataTable[i][j].click == true) {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
              "lightgrey";
          } else if (this.dataTable[i][j].click == false) {
            //let bodyTag = document.getElementsByClassName("seat");
            if (this.dataTable[i][j].grade == "R") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "yellow";
            } else if (this.dataTable[i][j].grade == "S") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "green";
            } else if (this.dataTable[i][j].grade == "VIP") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "purple";
            }
            //bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "white";
          }
        }
      }
    },

    modify() {
      console.log(this.radioGroup);
      for (let i = 0; i < this.rowArr.length; i++) {
        let x = this.rowArr[i];
        let y = this.colArr[i];
        this.dataTable[x][y].grade = this.radioGroup;
      }

      console.log(this.dataTable);

      this.paintSeatWithGrade();
      this.reset();
    },
    reset() {
      this.rowArr = [];
      this.colArr = [];
      console.log(this.rowArr, this.colArr);
      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          this.dataTable[i][j].click = false;
          //let bodyTag = document.getElementsByClassName("seat");

          //bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "white";
          this.paintSeatWithGrade();
        }
      }
    },

    paintSeatWithGrade() {
      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (this.dataTable[i][j].grade == "R") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "yellow";
          } else if (this.dataTable[i][j].grade == "S") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "green";
          } else if (this.dataTable[i][j].grade == "VIP") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "purple";
          }
        }
      }
    },
  },
};
</script>

<style scoped>
.yellow {
  width: 10px;
  height: 10px;
  background-color: yellow;
}
.green {
  width: 10px;
  height: 10px;
  background-color: green;
}
.purple {
  width: 10px;
  height: 10px;
  background-color: purple;
}
.grey {
  width: 10px;
  height: 10px;
  background-color: lightgrey;
}
</style>
