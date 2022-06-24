<template>
  <div>
    <div class="titleBox">
      <div>
        <div
          style="
            font-size: 50px;
            text-align: center;
            font-weight: bold;
            padding-top: 25px;
            padding-bottom: 10px;
          "
        >
          Hall MANAGEMENT
        </div>
        <div
          style="
            font-size: 20px;
            text-align: center;
            font-weight: bold;
            color: grey;
          "
        >
          Tissue 사용자들을 위한 공연장을 관리해주세요.
        </div>
      </div>
    </div>
    <div
      style="height: 20px; background-color: #f8bbd0; margin-bottom: 50px"
    ></div>
    <v-container>
      <v-row justify="center">
        <v-col cols="auto">
          <v-card flat>
            <div>
              <v-text-field
                style="width: 500px"
                v-model="hallName"
                label="공연장명"
                clearable
                outlined
                color="pink lighten-3"
              />
              <v-text-field
                style="width: 500px"
                v-model="rowCnt"
                label="행 개수"
                clearable
                outlined
                color="pink lighten-3"
              />
              <v-text-field
                style="width: 500px"
                v-model="colCnt"
                label="열 개수"
                clearable
                outlined
                color="pink lighten-3"
              />
              <div style="display: flex; justify-content: center">
                <v-btn
                  rounded
                  color="blue lighten-3 mr-3"
                  class="white--text"
                  @click="onSubmit"
                  >등록</v-btn
                >
                <v-btn
                  rounded
                  color="blue lighten-3 ml-3"
                  to="HallList"
                  class="white--text"
                  >리스트로</v-btn
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
import axios from "axios";
export default {
  name: "HallRegister",
  data() {
    return {
      hallName: "",
      rowCnt: "",
      colCnt: "",
    };
  },

  methods: {
    onSubmit() {
      const { hallName, rowCnt, colCnt } = this;
      axios
        .post("http://localhost:7777/hall/register", {
          hallName,
          rowCnt,
          colCnt,
        })
        .then(() => {
          alert(
            "공연장이 등록되었습니다. \n좌석등급은 모두 R로 지정되었습니다. \n좌석 등급을 필수로 수정해주세요."
          );
          this.$router.push("/HallList");
        })
        .catch(() => {
          alert("실패");
        });
    },
  },
};
</script>

<style scoped>
.titleBox {
  width: 100%;
  height: 168.91px;
  padding-top: 0;
  padding-bottom: 0;
  background-color: #fce4ec;
  border-bottom: 1px solid lightgrey;
}
</style>
