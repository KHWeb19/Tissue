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
      <table v-if="dataTable">
        <tr v-for="(line, index) in dataTable" :key="index" :row-index="index">
          <td
            v-for="(item, indexes) in line"
            :key="indexes"
            :cell-index="indexes"
          >
            <div
              style="width: 100px; height: 100px; border: 1px solid black"
              @click="clickSeat"
              class="seat"
              :row-index="index"
              :cell-index="indexes"
            ></div>
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
import axios from "axios";
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
      isClick: "",
      dataTable: null,
      radioGroup: "R",
      kindsGrade: ["R", "S", "VIP"],
      gradeColor: [
        { color: "yellow", grade: "R" },
        { color: "green", grade: "S" },
        { color: "purple", grade: "VIP" },
      ],
      modifyData: this.hall,
      seatNo: "",
      mdSeatName: String,
      mdSeatGrade: String,
      hallNo: this.hall.hallNo,
    };
  },

  created() {
    this.dataTable = new Array(this.hall.rowCnt);

    for (let i = 0; i < this.dataTable.length; i++) {
      this.dataTable[i] = new Array(this.hall.colCnt);
    }
    let cnt = 0;
    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        this.dataTable[i][j] = {
          name: this.hall.seats[cnt].seatName,
          grade: this.hall.seats[cnt].seatGrade,
          click: false,
        };
        cnt = cnt + 1;
      }
    }

    console.log(this.dataTable);
  },
  mounted() {
    this.paintSeatWithGrade();
  },
  methods: {
    clickSeat(e) {
      let row = Number(e.target.getAttribute("row-index"));
      let col = Number(e.target.getAttribute("cell-index"));

      console.log(
        this.radioGroup,
        row,
        col,
        this.hall.seats[row * this.hall.colCnt + col].seatName
      );

      if (this.dataTable[row][col].click == false) {
        this.dataTable[row][col].click = true;
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
          }
        }
      }
    },

    modify() {
      console.log(this.radioGroup);

      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (this.dataTable[i][j].click == true) {
            this.dataTable[i][j].grade = this.radioGroup;
            this.modifyData.seats[i * this.modifyData.colCnt + j].seatGrade =
              this.radioGroup;

            this.seatNo =
              this.modifyData.seats[i * this.hall.colCnt + j].seatNo;
            this.mdSeatName =
              this.modifyData.seats[i * this.hall.colCnt + j].seatName;
            this.mdSeatGrade =
              this.modifyData.seats[i * this.hall.colCnt + j].seatGrade;

            console.log(this.seatNo, this.mdSeatName, this.mdSeatGrade);

            const { seatNo, mdSeatName, mdSeatGrade } = this;
            axios
              .put(`http://localhost:7777/hallSeat/${this.hallNo}`, {
                seatNo,
                mdSeatName,
                mdSeatGrade,
              })
              .catch(() => {
                alert("수정 실패");
              });
          }
        }
      }
      console.log(this.modifyData);
      this.paintSeatWithGrade();
      this.reset();
    },
    reset() {
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
