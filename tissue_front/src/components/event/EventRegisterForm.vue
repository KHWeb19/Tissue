<template>
  <v-container>
    <v-app-bar app elevation="3">
        <v-toolbar-title class="ml-3">
            📌 이벤트 관리
        </v-toolbar-title>
    </v-app-bar>
    <form @submit.prevent="onSubmit">
      <v-container
        ><br /><br /><br />
        <v-row>
          <v-col>
            <form>
              <div>
                <label>공연명</label>
                <v-select
                  v-model="performNo"
                  color="pink lighten-3"
                  :items="performances"
                  item-text="performName"
                  item-value="performNo"
                ></v-select>
              </div>
              <div>
                <label>제목</label>
                <v-text-field
                  outlined
                  v-model="eventTitle"
                  color="pink lighten-3"
                  type="text"
                  required
                ></v-text-field>
              </div>
              <div>
                <label>설명</label>
                <v-text-field
                  outlined
                  v-model="eventContent"
                  color="pink lighten-3"
                  type="text"
                  required
                ></v-text-field>
              </div>
              <div>
                <label>경품</label>
                <v-text-field
                  outlined
                  v-model="eventGoods"
                  color="pink lighten-3"
                  type="text"
                  required
                ></v-text-field>
              </div>

              <div>
                <label>카테고리</label>
                <v-select
                  v-model="eventCategory"
                  color="pink lighten-3"
                  :items="list"
                  item-text="category"
                  item-value="category"
                ></v-select>
              </div>
              <div>
                <label>시작일</label>
                <v-text-field
                  outlined
                  v-model="eventStart"
                  color="pink lighten-3"
                  type="Date"
                  required
                ></v-text-field>
              </div>
              <div>
                <label>종료일</label>
                <v-text-field
                  outlined
                  v-model="eventEnd"
                  color="pink lighten-3"
                  type="Date"
                  required
                ></v-text-field>
              </div>
              <div>
                <label>당첨자 발표일</label>
                <v-text-field
                  outlined
                  v-model="eventWinnerDate"
                  color="pink lighten-3"
                  type="Date"
                  required
                ></v-text-field>
              </div>
              <div>
                <label>당첨 인원</label>
                <v-text-field
                  outlined
                  v-model="eventWinnerCnt"
                  color="pink lighten-3"
                  type="text"
                  required
                ></v-text-field>
              </div>
            </form>
          </v-col>
        </v-row>

        <br />

        <v-row justify="center" class="mb-5">
          <v-btn color="blue lighten-3" dark @click="onSubmit" value="Upload" x-large>
            등록
          </v-btn>
        </v-row>
      </v-container>
    </form>
  </v-container>
</template>

<script>
export default {
  name: "EventRegisterForm",
  data() {
    return {
      performNo: "",
      eventTitle: "",
      eventContent: "",
      eventCategory: "",
      eventGoods: "",
      list: [{ category: "기대평" }, { category: "후기평" }],
      eventStart: "",
      eventEnd: "",
      eventWinnerDate: "",
      eventWinnerCnt: "",
    };
  },
  props: {
    performances: {
      type: Array,
    },
  },
  methods: {
    onSubmit() {
      const performNo = this.performNo;
      const {
        eventTitle,
        eventCategory,
        eventStart,
        eventEnd,
        eventContent,
        eventGoods,
        eventWinnerDate,
        eventWinnerCnt,
      } = this;

      this.$emit("submit", {
        performNo,
        eventTitle,
        eventCategory,
        eventStart,
        eventEnd,
        eventContent,
        eventGoods,
        eventWinnerDate,
        eventWinnerCnt,
      });
    },
  },
};
</script>

<style scoped>
label {
  font-size: 5px;
}

.information-container {
  margin-top: 50px;
  color: #222222;
  border: 1px solid #dddddd;
}

.file-upload-example {
  height: 100%;
}

.file-preview-content-container {
  height: 100%;
}

.file-upload-wrapper {
  margin: 20px;
  border: 1px solid #dddddd;
  background-color: #f4f4f4;
  min-height: 350px;
  font-size: 15px;
  color: #888888;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.file-upload-example-container {
  display: flex;
  align-items: center;
  justify-content: center;
}

.image-box {
  margin-top: 30px;
  padding-bottom: 20px;
  text-align: center;
}

.image-box input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.image-box label {
  display: inline-block;
  padding: 10px 20px;
  background-color: #232d4a;
  color: #fff;
  vertical-align: middle;
  font-size: 15px;
  cursor: pointer;
  border-radius: 5px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper > img {
  position: relative;
  width: 300px;
  height: 150px;
  z-index: 10;
}

.file-close-button {
  position: absolute;
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper-upload {
  margin: 10px;
  padding-top: 20px;
  background-color: #888888;
  width: 190px;
  height: 130px;
}
</style>
