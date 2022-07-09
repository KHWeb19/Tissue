<template>
  <v-container class="mt-10 mb-10">
    <v-app-bar app elevation="3">
      <v-toolbar-title class="ml-3"> 📌 쿠폰 관리 </v-toolbar-title>
    </v-app-bar>
    <v-container class="white">
      <v-row justify="center">
        <v-col cols="auto">
          <form @submit.prevent="onSubmit">
            <table style="margin: auto">
              <tr>
                <td>
                  <v-img :src="image" class="preview">
                    <div v-if="image == ''" class="previewText">
                      이미지 미리보기
                    </div>
                  </v-img>
                </td>
              </tr>
              <tr>
                <td>
                  <div style="display: flex">
                    <h2 class="mb-5 mr-5">쿠폰 이미지 등록</h2>
                    <div>
                      <input
                        placeholder="대표"
                        type="file"
                        id="file"
                        ref="file"
                        v-on:change="handlerFileUpload()"
                        class="mt-1"
                      />
                    </div>
                  </div>
                </td>
              </tr>

              <tr>
                <td>
                  <v-text-field
                    v-model="name"
                    label="쿠폰명"
                    clearable
                    outlined
                    color="pink lighten-3"
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <v-select
                    v-model="selectCategory"
                    :items="category"
                    item-text="value"
                    item-value="value"
                    label="카테고리"
                    outlined
                    color="pink lighten-3"
                  ></v-select>
                </td>
              </tr>
              <tr>
                <td>
                  <v-text-field
                    v-model="price"
                    label="쿠폰 금액"
                    clearable
                    outlined
                    color="pink lighten-3"
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <v-select
                    v-model="selectCondition"
                    :items="condition"
                    item-text="value"
                    item-value="value"
                    label="사용 조건(금액)"
                    color="pink lighten-3"
                    outlined
                  ></v-select>
                </td>
              </tr>
              <tr>
                <td>
                  <v-text-field
                    v-model="startDate"
                    label="시작일"
                    type="date"
                    clearable
                    outlined
                    color="pink lighten-3"
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <v-text-field
                    v-model="endDate"
                    label="종료일"
                    type="date"
                    clearable
                    outlined
                    color="pink lighten-3"
                  />
                </td>
              </tr>
            </table>

            <div class="d-flex" style="justify-content: center">
              <v-btn
                type="submit"
                class="mr-5 white--text"
                large
                rounded
                color="blue lighten-3"
              >
                등록
              </v-btn>
              <v-btn
                color="blue lighten-3"
                class="white--text ml-5"
                large
                rounded
                to="/couponList"
              >
                취소
              </v-btn>
            </div>
          </form>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
export default {
  name: "CouponRegister",
  data() {
    return {
      image: "",
      file: "",
      name: "",
      selectCategory: "",
      price: "",
      selectCondition: "",
      startDate: "",
      endDate: "",
      category: [
        { value: "콘서트" },
        { value: "뮤지컬" },
        { value: "연극" },
        { value: "전시회" },
      ],
      condition: [
        { value: "1만원 이상" },
        { value: "3만원 이상" },
        { value: "5만원 이상" },
        { value: "10만원 이상" },
      ],
    };
  },

  methods: {
    onSubmit() {
      const {
        name,
        selectCategory,
        price,
        selectCondition,
        startDate,
        endDate,
      } = this;
      const file = this.$refs.file.files[0];
      if (file == null) {
        alert("쿠폰 이미지를 등록해주세요.");
        return;
      }
      this.$emit("submit", {
        name,
        selectCategory,
        price,
        selectCondition,
        startDate,
        endDate,
        file,
      });
    },

    handlerFileUpload() {
      var image = this.$refs.file.files[0];
      const url = URL.createObjectURL(image);
      this.image = url;

      this.file = this.$refs.file.files[0];
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

.preview {
  max-width: 80%;
  max-height: 50%;
  width: 400px;
  height: 150px;
  border: 1px dotted black;
  background-color: lightgray;
  margin-bottom: 20px;
  margin-left: 50px;
}
.previewText {
  display: flex;
  justify-content: center;
  padding-top: 63px;
}
</style>
