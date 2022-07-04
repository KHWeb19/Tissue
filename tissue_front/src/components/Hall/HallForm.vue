<template>
  <div>
    <v-container>
      <div style="display: flex; justify-content: center; align-items: center">
        <div>
          <div class="stage">S T A G E</div>

          <div style="display: flex">
            <div style="width: 50px">
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
            <div>
              <table v-if="dataTable" style="margin: auto">
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
                      <div v-if="hall.rowCnt <= 5">
                        <div v-if="indexes == 0" style="width: 50px"></div>
                        <div v-if="indexes == 3" style="width: 50px"></div>
                      </div>
                      <div v-if="hall.rowCnt > 5">
                        <div v-if="indexes == 2" style="width: 50px"></div>
                        <div v-if="indexes == 7" style="width: 50px"></div>
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
                    lightsalmon: item.color == 'lightsalmon',
                    lightgreen: item.color == 'lightgreen',
                    mediumpurple: item.color == 'mediumpurple',
                  }"
                ></div>
                <span class="ml-3"> {{ item.grade }} </span>
                <span class="ml-3"
                  >{{ gradePrice[index].price | comma }} 원</span
                >
                <span class="ml-3">({{ seatGradeCnt[index].cnt }} 석) </span>
              </div>
            </div>
          </div>
          <div class="ml-2 mt-2 infoTitle">선택한 좌석</div>
          <div class="showSelectSeat">
            <span v-for="(index, show) in showSelectInfo" :key="show">
              {{ index.info }}
            </span>
          </div>
          <div class="ml-2 mt-2 infoTitle">결제 금액</div>
          <div class="showSelectPrice">
            <div>{{ selectPrice | comma }} 원</div>
          </div>
          <div class="mt-3">
            <v-btn rounded class="white--text" color="blue lighten-3"
              >뒤로</v-btn
            >
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
  },
  data() {
    return {
      gradePrice: [
        { price: this.performance.performPriceR },
        { price: this.performance.performPriceS },
        { price: this.performance.performPriceVip },
      ],
      gradeColor: [
        { color: "lightsalmon", grade: "R" },
        { color: "lightgreen", grade: "S" },
        { color: "mediumpurple", grade: "VIP" },
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
    };
  },

  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
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

    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        if (this.dataTable[i][j].grade == "R") {
          this.seatGradeCnt[0].cnt += 1;
        }
        if (this.dataTable[i][j].grade == "S") {
          this.seatGradeCnt[1].cnt += 1;
        }
        if (this.dataTable[i][j].grade == "VIP") {
          this.seatGradeCnt[2].cnt += 1;
        }
      }
    }

    console.log(this.dataTable);
  },

  mounted() {
    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        if (this.dataTable[i][j].grade == "R") {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
            "lightsalmon";
        } else if (this.dataTable[i][j].grade == "S") {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
            "lightgreen";
        } else if (this.dataTable[i][j].grade == "VIP") {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
            "mediumpurple";
        }
      }
    }
  },

  methods: {
    clickSeat(e) {
      let row = Number(e.target.getAttribute("row-index"));
      let col = Number(e.target.getAttribute("cell-index"));

      console.log(
        row,
        col,
        this.hall.seats[row * this.hall.colCnt + col].seatGrade
      );

      let temp = `${row + 1}열 ${col + 1}번, `;
      let priceGradeTemp =
        this.hall.seats[row * this.hall.colCnt + col].seatGrade;

      if (this.dataTable[row][col].click == false) {
        this.dataTable[row][col].click = true;
        if (this.showSelectInfo.length < 10) {
          this.showSelectInfo.push({
            info: temp,
            grade: this.dataTable[row][col].grade,
          });
          if (priceGradeTemp == "R") {
            this.selectPrice += this.performance.performPriceR;
          } else if (priceGradeTemp == "S") {
            this.selectPrice += this.performance.performPriceS;
          } else if (priceGradeTemp == "VIP") {
            this.selectPrice += this.performance.performPriceVip;
          }
        } else {
          alert("최대 10개만 선택 가능합니다.");
          this.dataTable[row][col].click = false;
        }
      } else if (this.dataTable[row][col].click == true) {
        this.dataTable[row][col].click = false;

        for (let i = 0; i < this.showSelectInfo.length; i++) {
          if (this.showSelectInfo[i].info == temp) {
            this.showSelectInfo.splice(i, 1);
            if (priceGradeTemp == "R") {
              this.selectPrice -= this.performance.performPriceR;
            } else if (priceGradeTemp == "S") {
              this.selectPrice -= this.performance.performPriceS;
            } else if (priceGradeTemp == "VIP") {
              this.selectPrice -= this.performance.performPriceVip;
            }
          }
        }
      }

      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (this.dataTable[i][j].click == true) {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "skyblue";
          } else if (this.dataTable[i][j].click == false) {
            if (this.dataTable[i][j].grade == "R") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "lightsalmon";
            } else if (this.dataTable[i][j].grade == "S") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "lightgreen";
            } else if (this.dataTable[i][j].grade == "VIP") {
              let bodyTag = document.getElementsByClassName("seat");

              bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
                "mediumpurple";
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
          if (this.dataTable[i][j].grade == "R") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
              "lightsalmon";
          } else if (this.dataTable[i][j].grade == "S") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
              "lightgreen";
          } else if (this.dataTable[i][j].grade == "VIP") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
              "mediumpurple";
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
.lightsalmon {
  width: 25px;
  height: 25px;
  background-color: lightsalmon;
}
.lightgreen {
  width: 25px;
  height: 25px;
  background-color: lightgreen;
}
.mediumpurple {
  width: 25px;
  height: 25px;
  background-color: mediumpurple;
}
.grey {
  width: 10px;
  height: 10px;
  background-color: lightgrey;
}
.seat {
  border-radius: 15px;
  margin: 5px;
  margin-right: 0;
  width: 50px;
  height: 50px;
}

.stage {
  border-radius: 15px;
  font-size: 30px;
  margin: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 500px;
  height: 300px;
  background-color: lightgrey;
  margin-bottom: 30px;
}

.showSelectSeat {
  width: 390px;
  height: 100px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
}
.showSelectPrice {
  width: 390px;
  height: 100px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
}

.showCol {
  width: 50px;
  height: 50px;
  margin: 5px;
  margin-right: 0;
  padding-top: 12px;
}
.showSeatPrice {
  width: 390px;
  height: 200px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
}
.infoTitle {
  font-size: 20px;
}
</style>
