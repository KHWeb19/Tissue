<template>
  <div>
    <v-container>
      <div style="display: flex; justify-content: center; align-items: center">
        <div>
          <div class="stage">S T A G E</div>

          <div style="display: flex; justify-content: center">
            <div
              style="
                width: 50px;
                position: absolute;
                padding-right: 400px;
                z-index: 0;
              "
            >
              <table v-if="dataTable" style="margin: 0">
                <tr
                  v-for="(line, index) in dataTable"
                  :key="index"
                  :row-index="index"
                >
                  <div class="showCol">{{ index + 1 }}열</div>
                </tr>
              </table>
            </div>
            <div style="display: flex; justify-content: center; z-index: 1">
              <table v-if="dataTable" style="">
                <tr
                  v-for="(line, index) in dataTable"
                  :key="index"
                  :row-index="index"
                >
                  <td
                    v-for="(item, indexes) in line"
                    :key="indexes"
                    :cell-index="indexes"
                  >
                    <div class="d-flex">
                      <div
                        @click="clickSeat"
                        class="seat"
                        :row-index="index"
                        :cell-index="indexes"
                      ></div>
                      <div v-if="hall.colCnt <= 5">
                        <div v-if="indexes == 0" style="width: 20px"></div>
                        <div
                          v-if="indexes == hall.colCnt - 2"
                          style="width: 20px"
                        ></div>
                      </div>
                      <div v-if="hall.colCnt > 5">
                        <div v-if="indexes == 1" style="width: 20px"></div>
                        <div
                          v-if="indexes == hall.colCnt - 3"
                          style="width: 20px"
                        ></div>
                      </div>
                    </div>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="ml-10">
          <div class="ml-2 infoTitle">좌석 등급 / 가격</div>
          <div class="showSeatPrice">
            <div class="ml-1">
              <div
                class="d-flex mt-3 mb-10"
                v-for="(item, index) in gradeColor"
                :key="item.grade"
              >
                <div
                  :class="{
                    crimson: item.color == 'crimson',
                    seagreen: item.color == 'seagreen',
                    blueviolet: item.color == 'blueviolet',
                  }"
                ></div>
                <span class="ml-3" style="font-size: 13px">
                  {{ item.grade }}
                </span>
                <span class="ml-3" style="font-size: 13px"
                  >{{ gradePrice[index].price | comma }} 원</span
                >
                <span class="ml-3" style="font-size: 13px"
                  >({{ seatGradeCnt[index].cnt }} 석)
                </span>
              </div>
            </div>
          </div>
          <div class="ml-2 mt-2 infoTitle">선택한 좌석</div>
          <div class="showSelectSeat">
            <span
              v-for="(index, show) in showSelectInfo"
              :key="show"
              style="font-size: 13px"
            >
              {{ index.info }} <br />
            </span>
          </div>
          <div class="ml-2 mt-2 infoTitle">결제 금액</div>
          <div class="showSelectPrice">
            <div>{{ selectPrice | comma }} 원</div>
          </div>
          <div class="mt-3" align="center">
            <v-btn
              rounded
              class="white--text"
              color="blue lighten-3"
              @click="reset"
              >초기화</v-btn
            >
          </div>
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "HallForm",
  props: {
    hall: {
      type: Object,
      required: true,
    },
    performance: {
      type: Object,
      required: true,
    },
    ticketingList: {
      type: Array,
    },
  },
  data() {
    return {
      gradePrice: [
        { price: this.performance.performPriceS },
        { price: this.performance.performPriceR },
        { price: this.performance.performPriceVip },
      ],
      gradeColor: [
        { color: "crimson", grade: "S" },
        { color: "seagreen", grade: "R" },
        { color: "blueviolet", grade: "VIP" },
      ],
      isClick: "",
      dataTable: null,
      modifyData: this.hall,
      seatNo: "",
      seatName: String,
      seatGrade: String,
      hallNo: this.hall.hallNo,
      showSelectInfo: [],
      seatGradeCnt: [{ cnt: 0 }, { cnt: 0 }, { cnt: 0 }],
      selectPrice: 0,
      ticketingListCopy: [],
    };
  },

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },

  created() {
    console.log("3홀폼");
    console.log(this.$store.state.ticketingList);
    this.ticketingListCopy = [];

    if (this.ticketingList.length == 0) {
      this.ticketingListCopy = [{ ticketing: 0 }];
    } else {
      this.ticketingListCopy = this.ticketingList;
    }

    this.dataTable = new Array(this.hall.rowCnt);

    for (let i = 0; i < this.dataTable.length; i++) {
      this.dataTable[i] = new Array(this.hall.colCnt);
    }
    let cnt = 0;

    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        for (let k = 0; k < this.ticketingListCopy.length; k++) {
          if (
            i * this.hall.colCnt + j + 1 !=
            this.ticketingListCopy[k].seatName
          ) {
            this.dataTable[i][j] = {
              name: this.hall.seats[cnt].seatName,
              grade: this.hall.seats[cnt].seatGrade,
              click: false,
              ticketing: false,
            };
          } else {
            this.dataTable[i][j] = {
              name: this.hall.seats[cnt].seatName,
              grade: this.hall.seats[cnt].seatGrade,
              click: false,
              ticketing: true,
            };
            break;
          }
        }
        cnt = cnt + 1;
      }
    }

    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        if (
          this.dataTable[i][j].grade == "S" &&
          this.dataTable[i][j].ticketing == false
        ) {
          this.seatGradeCnt[0].cnt += 1;
        }
        if (
          this.dataTable[i][j].grade == "R" &&
          this.dataTable[i][j].ticketing == false
        ) {
          this.seatGradeCnt[1].cnt += 1;
        }
        if (
          this.dataTable[i][j].grade == "VIP" &&
          this.dataTable[i][j].ticketing == false
        ) {
          this.seatGradeCnt[2].cnt += 1;
        }
      }
    }

    console.log(this.dataTable);
  },

  mounted() {
    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        if (this.dataTable[i][j].ticketing == true) {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "lightgrey";
        } else {
          if (this.dataTable[i][j].grade == "S") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "crimson";
          } else if (this.dataTable[i][j].grade == "R") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
              "seagreen";
          } else if (this.dataTable[i][j].grade == "VIP") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
              "blueviolet";
          }
        }
      }
    }
  },

  methods: {
    clickSeat(e) {
      let row = Number(e.target.getAttribute("row-index"));
      let col = Number(e.target.getAttribute("cell-index"));

      // console.log(
      //   row,
      //   col,
      //   this.hall.seats[row * this.hall.colCnt + col].seatGrade
      // );

      let temp = `${row + 1}열 ${col + 1}번 `;
      let priceGradeTemp =
        this.hall.seats[row * this.hall.colCnt + col].seatGrade;

      if (
        this.dataTable[row][col].click == false &&
        this.dataTable[row][col].ticketing == false
      ) {
        this.dataTable[row][col].click = true;
        if (this.showSelectInfo.length < 10) {
          this.showSelectInfo.push({
            info: temp,
            grade: this.dataTable[row][col].grade,
            seatName: row * this.hall.colCnt + col + 1,
          });
          if (priceGradeTemp == "S") {
            this.selectPrice += this.performance.performPriceS;
          } else if (priceGradeTemp == "R") {
            this.selectPrice += this.performance.performPriceR;
          } else if (priceGradeTemp == "VIP") {
            this.selectPrice += this.performance.performPriceVip;
          }
        } else {
          alert("최대 10개만 선택 가능합니다.");
          this.dataTable[row][col].click = false;
        }
      } else if (
        this.dataTable[row][col].click == true &&
        this.dataTable[row][col].ticketing == false
      ) {
        this.dataTable[row][col].click = false;

        for (let i = 0; i < this.showSelectInfo.length; i++) {
          if (this.showSelectInfo[i].info == temp) {
            this.showSelectInfo.splice(i, 1);
            if (priceGradeTemp == "S") {
              this.selectPrice -= this.performance.performPriceS;
            } else if (priceGradeTemp == "R") {
              this.selectPrice -= this.performance.performPriceR;
            } else if (priceGradeTemp == "VIP") {
              this.selectPrice -= this.performance.performPriceVip;
            }
          }
        }
      }

      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (
            this.dataTable[i][j].click == true &&
            this.dataTable[i][j].ticketing == false
          ) {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "skyblue";
          } else if (
            this.dataTable[i][j].click == false &&
            this.dataTable[i][j].ticketing == false
          ) {
            if (this.dataTable[i][j].grade == "S") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "crimson";
            } else if (this.dataTable[i][j].grade == "R") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "seagreen";
            } else if (this.dataTable[i][j].grade == "VIP") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "blueviolet";
            }
          }
        }
      }
      console.log(this.showSelectInfo);
      this.$emit("showSelectInfo", this.showSelectInfo);
      this.$emit("selectPrice", this.selectPrice);
    },

    reset() {
      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          this.dataTable[i][j].click = false;
          if (this.dataTable[i][j].ticketing == false) {
            if (this.dataTable[i][j].grade == "S") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "crimson";
            } else if (this.dataTable[i][j].grade == "R") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "seagreen";
            } else if (this.dataTable[i][j].grade == "VIP") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "blueviolet";
            }
          }
        }
      }
      this.showSelectInfo = [];
      this.selectPrice = 0;
      this.$emit("showSelectInfo", this.showSelectInfo);
      this.$emit("selectPrice", this.selectPrice);
    },

    resetCheckBox() {
      this.checkbox = false;
    },
  },
};
</script>

<style scoped>
.crimson {
  width: 20px;
  height: 20px;
  background-color: crimson;
}
.seagreen {
  width: 20px;
  height: 20px;
  background-color: seagreen;
}
.blueviolet {
  width: 20px;
  height: 20px;
  background-color: blueviolet;
}
.grey {
  width: 10px;
  height: 10px;
  background-color: lightgrey;
}
.seat {
  border-radius: 5px;
  margin: 5px;
  margin-right: 0;
  width: 20px;
  height: 20px;
}

.stage {
  border-radius: 15px;
  font-size: 30px;
  margin: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 300px;
  height: 150px;
  background-color: lightgrey;
  margin-bottom: 30px;
}

.showSelectSeat {
  width: 250px;
  height: 100px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
  overflow: auto;
}
.showSelectPrice {
  width: 250px;
  height: 50px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
}

.showCol {
  width: 26px;
  height: 20px;
  margin: 5px;
  margin-right: 0;
  padding-top: 2px;
  font-size: 12px;
}
.showSeatPrice {
  width: 250px;
  height: 200px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
}
.infoTitle {
  font-size: 15px;
}
</style>
