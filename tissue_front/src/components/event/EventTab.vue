<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          <div
            style="
              text-align: center;
              font-size: 30px;
              font-weight: lighter;
              margin: 30px;
            "
          >
            <b style="font-size: 45px; font-weight: bold">E</b> V E N T &nbsp;
            <b style="font-size: 45px; font-weight: bold">Z</b> O N E
          </div>
          <v-divider></v-divider>
        </v-col>
      </v-row>
      
      <v-row class="mt-5 mb-5">
        <v-col
          v-for="event in paginatedData"
          :key="event.eventNo"
          lg="4"
          sm="6"
        >
        <router-link style="color: black" :to="{ name: 'PerformanceReadPage', params: { performNo: event.performance.performNo} }">
          <div width="500" height="450">
            <div class="imgWrap">
              <div class="pt-10">
                <v-img
                  :src="require(`@/assets/thumbNail/${event.performance.performThumbnail}`)"
                  class="img"
                >
                    <h1 class="imgText" v-if="event.eventCategory === '기대평'">기대평 이벤트</h1>
                    <h1 class="reviewImgText" v-else> 관람후기 이벤트</h1>
                </v-img>
              </div>
            </div>

            <v-card-title class="eventCategory mt-3 blue--text text--lighten-3" style="font-weight: lighter">
                {{ event.eventCategory }}
            </v-card-title>
            <v-card-subtitle class="eventTitle mt-5">
                {{ event.eventTitle }}
            </v-card-subtitle>

            
            <v-card-text class="pb-3 pt-3 subContent">
                <b class="subTitle"></b> 이벤트 기간: {{ event.eventStart }} ~
                {{ event.eventEnd }}
            </v-card-text>
          </div>
          <div v-for="dday in fetchDdayCount" :key="dday.eventNo">
            <div v-if="event.eventNo == dday.eventNo">
                <v-progress-linear :value="dday.result"></v-progress-linear>
                <h2 class="ddayTxt">{{dday.result}}일 남음</h2>
            </div>
          </div>
        </router-link>
        </v-col>
        
      </v-row>

      <v-row>
        <v-col>
          <div class="btn-cover">
            <v-btn
              rounded
              :disabled="pageNum === 0"
              @click="prevPage"
              class="page-btn"
              color="blue lighten-3"
            >
              이전
            </v-btn>
            <span class="page-count"
              >{{ pageNum + 1 }} / {{ pageCount }} 페이지</span
            >
            <v-btn
              rounded
              :disabled="pageNum >= pageCount - 1"
              @click="nextPage"
              class="page-btn"
              color="blue lighten-3"
            >
              다음
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>

export default {
  name: "EventTab",
  props: {
    eventPageArray: {
      type: Array,
      required: true,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 12,
    },
  },
  data() {
    return {
      pageNum: 0,
    };
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  computed: {
    pageCount() {
      let listLeng = this.eventPageArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);

      if (listLeng % listSize > 0) page += 1;
      return page;
    },
    paginatedData() {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.eventPageArray.slice(start, end);
    },
    fetchDdayCount() {
        const resultList = []
        for(let i=0; i < this.eventPageArray.length; i++) {
            var Dday = new Date(this.eventPageArray[i].eventEnd);  //이벤트마감일
            var now = new Date() // 현재 시스템 날짜 
            var gap = now.getTime() - Dday.getTime();  
            resultList.push({ eventNo: this.eventPageArray[i].eventNo, result: Math.floor(gap / (1000 * 60 * 60 * 24)) * -1})
        }
        return resultList
    }
  },
  methods: {
    nextPage() {
      this.pageNum += 1;
    },
    prevPage() {
      this.pageNum -= 1;
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
.couponPrice {
  font-size: 50px;
  padding-top: 25px;
  padding-left: 30px;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}

.couponTitle {
  font-size: 18px;
  line-height: 23px;
  color: #333;
  font-weight: 700;
  height: 46px;
  overflow: hidden;
}
.subTitle {
  float: left;
  width: 60px;
  font-size: 13px;
  line-height: 21px;
  color: #666;
  font-weight: 700;
  margin-right: 10px;
}
.subContent {
  clear: right;
  font-size: 13px;
  line-height: 21px;    
  color: #999;
}
/* .imgWrap {
  display: block;
  background: #f6f6f6;
  width: 100%;
  height: 208px;
  position: relative;
  z-index: 1;
} */
.img {
  width: 500px;
  height: 250px;
  margin: auto;
}
.imgText {
    font-size: 20px;
    padding: 5px 10px;
	background-color: #BCAAA4;
	text-align: center;
	top: 85%;
	/* left: 50%; */
}
.reviewImgText{
    font-size: 20px;
    padding: 5px 10px;
	background-color: #B0BEC5;
	text-align: center;
	top: 85%;
	/* left: 50%; */
}
.eventCategory{
  width : 74px;
  height: 38px;
  font-size: 13px;
  border-style: solid;
  border-color: #90CAF9;
  padding-bottom: 0px;
  padding-top: 0px;
  text-align: center;
}
.eventTitle{
  font-size: 18px;
  line-height: 21px;    
}
.ddayTxt {
  font-size: 13px;
  line-height: 21px;
  color: #666;
  text-align: right;
}
</style>
