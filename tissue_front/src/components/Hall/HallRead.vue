<template>
  <v-container>
    <v-app-bar app elevation="3">
      <v-toolbar-title class="ml-3"> 📌 공연장 관리 </v-toolbar-title>
    </v-app-bar>
    <v-container class="mt-10">
      <div style="display: flex; justify-content: center; align-items: center">
        <div class="mr-10">
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
                        <div v-if="indexes == 0" style="width: 20px"></div>
                        <div
                          v-if="indexes == hall.rowCnt - 2"
                          style="width: 20px"
                        ></div>
                      </div>
                      <div v-if="hall.rowCnt > 5">
                        <div v-if="indexes == 1" style="width: 20px"></div>
                        <div
                          v-if="indexes == hall.rowCnt - 3"
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
          <div>
            <div class="mb-10 showHallInfo">
              <v-row>
                <v-col>
                  <div>
                    <div style="font-size: 18 px">공연장명</div>
                    <div>{{ hall.hallName }}</div>
                    <div style="font-size: 18px">총 좌석 수</div>
                    <div>{{ hall.colCnt * hall.rowCnt }}</div>
                  </div>
                </v-col>
              </v-row>
            </div>

            <div class="d-flex ml-1">
              <div class="mr-10" v-for="item in gradeColor" :key="item.grade">
                <div
                  :class="{
                    crimson: item.color == 'crimson',
                    seagreen: item.color == 'seagreen',
                    blueviolet: item.color == 'blueviolet',
                  }"
                ></div>
                <div style="text-align: center">
                  {{ item.grade }}
                </div>
              </div>
            </div>
            <div style="display: flex">
              <div class="mr-7">
                <div>{{ seatGradeCnt[0].cnt }} 석</div>
              </div>
              <div class="mr-7">
                <div>{{ seatGradeCnt[1].cnt }} 석</div>
              </div>
              <div>
                <div>{{ seatGradeCnt[2].cnt }} 석</div>
              </div>
            </div>

            <v-radio-group v-model="radioGroup" row>
              <v-radio
                v-for="kind in kindsGrade"
                :key="kind"
                :label="`${kind}`"
                :value="kind"
                color="pink lighten-3"
              >
              </v-radio>
            </v-radio-group>
          </div>
          <div class="showSelectSeat">
            <span v-for="(index, show) in showSelectInfo" :key="show">
              {{ index.info }}
            </span>
          </div>
          <div class="d-flex mt-5">
            <v-btn
              color="blue lighten-3"
              class="white--text mr-2 ml-2"
              rounded
              @click="modify"
              >수정</v-btn
            >
            <v-btn
              color="blue lighten-3"
              class="white--text mr-2"
              rounded
              @click="reset"
              >초기화</v-btn
            >
            <template>
              <div>
                <v-dialog v-model="dialogDeleteHall" width="460">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      color="blue lighten-3"
                      class="white--text mr-2"
                      rounded
                      v-bind="attrs"
                      v-on="on"
                      @click="resetCheckBox"
                    >
                      공연장 삭제
                    </v-btn>
                  </template>

                  <v-card>
                    <v-card-title> 공연장 삭제 </v-card-title>
                    <v-card-text class="mt-5 pb-0">
                      정말로 공연을 삭제하시겠습니까? <br />
                      삭제를 원하시면 동의버튼을 체크하시고 <br />
                      삭제버튼을 클릭해주세요.
                    </v-card-text>
                    <v-container fluid>
                      <v-checkbox
                        v-model="checkbox"
                        label="동의합니다."
                      ></v-checkbox>
                    </v-container>
                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn text :disabled="!checkbox" @click="remove">
                        삭제
                      </v-btn>
                      <v-btn text @click="dialogDeleteHall = false">
                        취소
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </template>
            <v-btn
              color="blue lighten-3"
              class="white--text mr-2"
              rounded
              to="/hallList"
              >리스트로</v-btn
            >
          </div>
        </div>
      </div>
    </v-container>
  </v-container>
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
      dialogDeleteHall: false,
      checkbox: false,
      isClick: "",
      dataTable: null,
      radioGroup: "S",
      kindsGrade: ["S", "R", "VIP"],
      gradeColor: [
        { color: "crimson", grade: "S" },
        { color: "seagreen", grade: "R" },
        { color: "blueviolet", grade: "VIP" },
      ],
      modifyData: this.hall,
      seatNo: "",
      seatName: String,
      seatGrade: String,
      hallNo: this.hall.hallNo,
      showSelectInfo: [{ info: "" }],
      seatGradeCnt: [{ cnt: 0 }, { cnt: 0 }, { cnt: 0 }],
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

    for (let i = 0; i < this.hall.rowCnt; i++) {
      for (let j = 0; j < this.hall.colCnt; j++) {
        if (this.dataTable[i][j].grade == "S") {
          this.seatGradeCnt[0].cnt += 1;
        }
        if (this.dataTable[i][j].grade == "R") {
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
        if (this.dataTable[i][j].grade == "S") {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "crimson";
        } else if (this.dataTable[i][j].grade == "R") {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "seagreen";
        } else if (this.dataTable[i][j].grade == "VIP") {
          let bodyTag = document.getElementsByClassName("seat");

          bodyTag[i * this.hall.colCnt + j].style.backgroundColor =
            "blueviolet";
        }
      }
    }
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

      let temp = `${row + 1}열 ${col + 1}번, `;

      if (this.dataTable[row][col].click == false) {
        this.dataTable[row][col].click = true;
        this.showSelectInfo.push({ info: temp });
      } else if (this.dataTable[row][col].click == true) {
        this.dataTable[row][col].click = false;

        for (let i = 0; i < this.showSelectInfo.length; i++) {
          if (this.showSelectInfo[i].info == temp) {
            this.showSelectInfo.splice(i, 1);
          }
        }
      }

      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (this.dataTable[i][j].click == true) {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "skyblue";
          } else if (this.dataTable[i][j].click == false) {
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
            this.seatName =
              this.modifyData.seats[i * this.hall.colCnt + j].seatName;
            this.seatGrade =
              this.modifyData.seats[i * this.hall.colCnt + j].seatGrade;

            console.log(this.seatNo, this.seatName, this.seatGrade);

            const { seatNo, seatName, seatGrade, hallNo } = this;
            axios
              .put(`http://localhost:7777/hallSeat/${this.hallNo}`, {
                seatNo,
                seatName,
                seatGrade,
                hallNo,
              })
              .catch(() => {
                alert("수정 실패");
              });
          }
        }
      }
      console.log(this.modifyData);
      //this.paintSeatWithGrade();
      this.reset();
    },
    reset() {
      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          this.dataTable[i][j].click = false;
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
      this.showSelectInfo = [{ info: "" }];
    },
    remove() {
      const { hallNo } = this;
      axios.delete(`http://localhost:7777/hall/${hallNo}`).then(() => {
        alert("공연삭제가 완료되었습니다.");
        this.$router.push("/hallList");
      });
    },
    resetCheckBox() {
      this.checkbox = false;
    },

    /* paintSeatWithGrade() {
      for (let i = 0; i < this.hall.rowCnt; i++) {
        for (let j = 0; j < this.hall.colCnt; j++) {
          if (this.dataTable[i][j].grade == "R") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "crimson";
          } else if (this.dataTable[i][j].grade == "S") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "seagreen";
          } else if (this.dataTable[i][j].grade == "VIP") {
            let bodyTag = document.getElementsByClassName("seat");

            bodyTag[i * this.hall.colCnt + j].style.backgroundColor = "blueviolet";
          }
        }
      }
    },*/
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
  width: 390px;
  height: 150px;
  border: 1px solid #f48fb1;
  padding: 10px;
  border-radius: 15px;
  overflow: auto;
}
.showHallInfo {
  width: 390px;
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

.titleBox {
  width: 100%;
  height: 168.91px;
  padding-top: 0;
  padding-bottom: 0;
  background-color: #fce4ec;
  border-bottom: 1px solid lightgrey;
}
</style>
