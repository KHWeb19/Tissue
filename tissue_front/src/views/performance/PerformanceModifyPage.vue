<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          <form>
            <div>
              <label>공연명</label>
              <v-text-field
                outlined
                v-model="performName"
                type="text"
                required
              ></v-text-field>
            </div>
            <div>
              <label>공연장</label>
              <v-select
                v-model="performHall"
                :items="halls"
                item-text="hallName"
                item-value="hallNo"
              ></v-select>
            </div>
            <div>
              <label>공연날짜</label>
              <v-text-field
                outlined
                v-model="performShowDate"
                type="Date"
                required
              ></v-text-field>
            </div>
            <div>
              <label>공연시작일</label>
              <v-text-field
                outlined
                v-model="performStart"
                type="Date"
                required
              ></v-text-field>
            </div>
            <div>
              <label>공연마감일</label>
              <v-text-field
                outlined
                v-model="performEnd"
                type="Date"
                required
              ></v-text-field>
            </div>
            <div>
              <label>공연시간</label>
              <v-text-field
                outlined
                v-model="performTime"
                type="Time"
                required
              ></v-text-field>
            </div>
            <div>
              <label>S석가격</label>
              <v-text-field
                outlined
                v-model="performPriceS"
                type="text"
                required
              ></v-text-field>
            </div>
            <div>
              <label>R석가격</label>
              <v-text-field
                outlined
                v-model="performPriceR"
                type="text"
                required
              ></v-text-field>
            </div>
            <div>
              <label>vip석가격</label>
              <v-text-field
                outlined
                v-model="performPriceVip"
                type="text"
                required
              ></v-text-field>
            </div>
            <div>
              <label>지역</label>
              <v-select
                v-model="performArea"
                :items="areaList"
                item-text="area"
                item-value="area"
              ></v-select>
            </div>
            <div>
              <label>공연카테고리</label>
              <v-select
                v-model="performCategory"
                :items="list"
                item-text="category"
                item-value="category"
              ></v-select>
            </div>
            <div>
              <label>등급</label>
              <v-select
                v-model="performGrade"
                :items="ageList"
                item-text="age"
                item-value="age"
              ></v-select>
            </div>
            <div>
              <label>출연자</label>
              <v-text-field
                outlined
                v-model="performer"
                type="text"
                required
              ></v-text-field>
            </div>

            <div class="information-container">
              <div class="file-upload-wrapper">
                <label
                  >ThumNail
                  <input
                    type="file"
                    ref="file"
                    id="file"
                    @change="thumbNailUpload"
                  />
                </label>
                <div v-if="file" class="file-preview-container">
                  <div class="file-preview-wrapper">
                    <div
                      class="file-close-button"
                      @click="thumbNailDeleteButton"
                    >
                      x
                    </div>
                    <img :src="file.preview" />
                  </div>
                </div>
              </div>
            </div>

            <div class="information-container">
              <div class="file-upload-wrapper">
                <label
                  >DetailImages
                  <input
                    type="file"
                    ref="files"
                    multiple
                    @change="imageUpload"
                  />
                </label>
                <div class="file-preview-content-container">
                  <div class="file-preview-container">
                    <div
                      v-for="(file, index) in files"
                      :key="index"
                      class="file-preview-wrapper"
                    >
                      <div
                        class="file-close-button"
                        @click="imageDeleteButton"
                        :name="file.number"
                      >
                        x
                      </div>
                      <img :src="file.preview" />
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- 지도 -->
            <div>
              <label>지도첨부</label>
              <add-map @selectMap="selectMap" />
            </div>
          </form>
        </v-col>
      </v-row>
      <br />
      <!-- 업로드 버튼 -->
      <v-row justify="center">
        <v-btn color="blue lighten-3 mr-8" dark @click="[modifyFiles(), modifyMap()]" value="Upload"
          >수정 완료</v-btn
        >
        <v-btn
          color="blue lighten-3 mb-3" dark
          router-link
          :to="{
            name: 'PerformanceListPage',
          }"
        >
          취소
        </v-btn>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import AddMap from "@/components/map/AddMap.vue";
import { mapState, mapActions } from "vuex";

export default {
  components: { AddMap },
  name: "PerformanceModifyPage",
  data() {
    return {
      performNo: "",
      performName: "",
      performStart: "",
      performEnd: "",
      performTime: "",
      performPriceR: "",
      performPriceS: "",
      performPriceVip: "",
      performArea: "",
      areaList: [
        { area: "서울" },
        { area: "경기/인천" },
        { area: "대전/충청/강원" },
        { area: "부산/대구/울산/경상" },
        { area: "광주/전라" },
        { area: "제주" },
      ],
      performCategory: "",
      list: [
        { category: "뮤지컬" },
        { category: "콘서트" },
        { category: "연극" },
        { category: "전시회" },
      ],
      performGrade: "",
      ageList: [{ age: "7세 이상 관람가" }, { age: "15세 이상 관람가" }],
      performer: "",
      files: [], // 다중이미지
      filesPreview: [],
      uploadImageIndex: 0,
      file: "", // 썸네일
      response: "",
      map: {
        x: null,
        y: null,
        name: null,
        address: null,
        phone: null,
        url: null,
        performNo: null,
      },

      hallCopy: "",
      performHall: "",
      performShowDate: "",
    };
  },
  created() {
    console.log(this.$route.params.performNo)
    this.fetchPerformance(this.$route.params.performNo); 
    this.performNo = this.$route.params.performNo;

    this.performName = this.performance.performName;
    this.performStart = this.performance.performStart;
    this.performEnd = this.performance.performEnd;
    this.performTime = this.performance.performTime;
    this.performPriceR = this.performance.performPriceR;
    this.performPriceS = this.performance.performPriceS;
    this.performPriceVip = this.performance.performPriceVip;
    this.performArea = this.performance.performArea;
    this.performCategory = this.performance.performCategory;
    this.performGrade = this.performance.performGrade;
    this.performer = this.performance.performer;
    this.hallName = this.performance.hallName;
    this.performShowDate = this.performance.performShowDate;

    this.hallCopy = this.$store.state.halls;
    console.log(this.performHall);

    
  },

  computed: {
    ...mapState(["performance", "halls"]),
  },
  mounted() {
    this.fetchHallList();
  },
  methods: {
    ...mapActions(["fetchPerformance", "fetchHallList"]),
    thumbNailUpload() {
      this.file = {
        file: this.$refs.file.files[0],
        preview: URL.createObjectURL(this.$refs.file.files[0]),
      };
      console.log(this.file);
    },
    imageUpload() {
      console.log("다중이미지 갯수: ", this.$refs.files.files.length);

      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          {
            file: this.$refs.files.files[i],
            preview: URL.createObjectURL(this.$refs.files.files[i]),
            number: i,
          },
        ];
        num = i;
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장

      console.log("추가 전: ", this.files);
    },
    imageAddUpload() {
      console.log("추가 한 것: ", this.$refs.addFile.files);

      let num = -1;
      for (let i = 0; i < this.$refs.addFile.files.length; i++) {
        // console.log(this.uploadImageIndex);
        this.files = [
          ...this.files,
          {
            file: this.$refs.addFile.files[i],
            preview: URL.createObjectURL(this.$refs.addFile.files[i]),
            number: i + this.uploadImageIndex,
          },
        ];
        num = i;
        console.log(this.files.preview);
      }
      this.uploadImageIndex = this.uploadImageIndex + num + 1;

      console.log("추가 후 ref값: ", this.$refs.addFile.files);
      console.log("추가 후: ", this.files);
    },
    imageDeleteButton(e) {
      const name = e.target.getAttribute("name");
      this.files = this.files.filter((data) => data.number !== Number(name));
      console.log(this.files);
    },
    thumbNailDeleteButton() {
      if (this.file != null) {
        this.file = null;
      }
    },
    selectMap(name, address, x, y, phone, url) {
      this.map.name = name;
      this.map.address = address;
      this.map.x = x;
      this.map.y = y;
      this.map.phone = phone;
      this.map.url = url;
    },
    modifyFiles() {
      let formData = new FormData();

      let performInfo = {
        performName: this.performName,
        performStart: this.performStart,
        performEnd: this.performEnd,
        performTime: this.performTime,
        performPriceR: this.performPriceR,
        performPriceS: this.performPriceS,
        performPriceVip: this.performPriceVip,
        performArea: this.performArea,
        performCategory: this.performCategory,
        performGrade: this.performGrade,
        performer: this.performer,
        hallName: this.performHall,
        performShowDate: this.performShowDate,
      };

      formData.append(
        "performance",
        new Blob([JSON.stringify(performInfo)], { type: "application/json" })
      );

      console.log("performInfo: " + JSON.stringify(performInfo)); // performInfo 콘솔에는 잘 나옴

      formData.append("file", document.getElementById("file").files[0]);

      let files = this.$refs.files.files;
      for (let idx = 0; idx < files.length; idx++) {
        formData.append("fileList", files[idx]);
      }

      axios
        .put(`performance/${this.performNo}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          this.fetchPerformance(this.performNo);
        })
        .catch((res) => {
          alert("등록 실패: " + res.message);
        });
    },
    modifyMap() {
      axios.patch(`map/modify/${this.performNo}`, this.map).then((res) => {
        alert("게시물 수정 성공!");
        console.log(res);
        this.$router.push({
          name: "PerformanceListPage",
        });
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

.file-preview-content-container {
  height: 100%;
}

.file-preview-container {
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper-upload {
  margin: 10px;
  padding-top: 20px;
  background-color: #888888;
  width: 190px;
  height: 130px;
}

.file-preview-wrapper > img {
  position: relative;
  width: 190px;
  height: 130px;
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
</style>
