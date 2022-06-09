<template>
  <div>
    <div style="height: 90px; border-bottom: 1px solid black"></div>
    <v-container>
      <v-row>
        <v-col>
          <div>
            <v-text-field
              style="width: 500px"
              v-model="hallName"
              label="공연장명"
              clearable
              outlined
            />
            <v-text-field
              style="width: 500px"
              v-model="rowCnt"
              label="행 개수"
              clearable
              outlined
            />
            <v-text-field
              style="width: 500px"
              v-model="colCnt"
              label="열 개수"
              clearable
              outlined
            />
            <v-btn @click="onSubmit">등록</v-btn>
          </div>
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
          alert("등록성공");
        })
        .catch(() => {
          alert("실패");
        });
    },
  },
};
</script>
