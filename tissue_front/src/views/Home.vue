<template>
  <div>
    <new-nav-bar />
    <div>
      <v-carousel
        height="100%"
        class="slider"
        delimiter-icon="mdi-minus"
        hide-delimiter-background
        :cycle="true"
        :interval="3000"
      >
        <v-carousel-item v-for="(image, i) in images" :key="i" >
            <img width="100%" :src="require(`@/assets/main/${image.name}`)">
        </v-carousel-item>
      </v-carousel>
    </div>
    <div class="side_bar">
      <router-link :to="{ name: 'EventMainPage' }">
        <v-img
          :src="require('@/assets/event.png')"
          max-width="150px"
          max-height="150px"
          style="zoom: 0.85"
        />
      </router-link>
    </div>
      <v-row>
          <v-col>
            <div class="content_title">
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid #F48FB1"
              ></div>
              R A N K
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid #F48FB1"
              ></div>
            </div>
            <div class="content_thumbnail">이미지</div>
        </v-col>
        </v-row>
        <v-divider class="mt-13 mb-15"/>
        <v-row justify="center">
            <v-col class="ml-10 mr-10">
                <div class="content_title">
                    <div class="content_title_deco" style="border-bottom: 5px solid #90CAF9"></div>
                        CONCERT
                    <div class="content_title_deco" style="border-bottom: 5px solid #90CAF9"></div>
                </div>
                <div class="content_thumbnail">
                    <v-row justify="center">
                        <v-col v-for="perform in newConcert" :key="perform.title" cols="2">
                            <router-link
                                :to="{
                                name: 'PerformanceDetailPage',
                                params: { performNo: perform.performNo.toString() },
                                }"
                            >
                                <v-card class=" card mx-auto" max-width="216" flat>
                                    <v-img
                                    class="mb-8 hoverImg"
                                        :src="
                                        require(`@/assets/thumbNail/${perform.performThumbnail}`)
                                        "
                                        height="300px"
                                    ></v-img>
                                    <div class="performTitle">
                                        {{ perform.performName }}
                                    </div>
                                </v-card>
                            </router-link>
                        </v-col>
                    </v-row>
                </div>
            </v-col>
        </v-row>
        <v-divider class="mt-13 mb-15"/>
        <v-row>
            <v-col>
                <div class="content_title">
                    <div
                        class="content_title_deco"
                        style="border-bottom: 5px solid #F48FB1"
                    ></div>
                    MUSICAL
                    <div
                        class="content_title_deco"
                        style="border-bottom: 5px solid #F48FB1"
                    ></div>
                </div>
                <div class="content_thumbnail">
                    <v-row justify="center">
                        <v-col v-for="perform in newMusical" :key="perform.title" cols="2">
                            <router-link
                                :to="{
                                name: 'PerformanceDetailPage',
                                params: { performNo: perform.performNo.toString() },
                                }"
                            >
                                <v-card class="card mx-auto" max-width="216" flat>
                                    <v-img
                                    class="mb-8 hoverImg"
                                        :src="
                                        require(`@/assets/thumbNail/${perform.performThumbnail}`)
                                        "
                                        height="300px"
                                    ></v-img>
                                    <div class="performTitle2">
                                        {{ perform.performName }}
                                    </div>
                                </v-card>
                            </router-link>
                        </v-col>
                    </v-row>
                </div>
            </v-col>
        </v-row>
        <v-divider class="mt-13 mb-15"/>
        <v-row>
            <v-col>
                <div class="content_title">
                    <div
                        class="content_title_deco"
                        style="border-bottom: 5px solid #90CAF9"
                    ></div>
                    THEATER
                    <div
                        class="content_title_deco"
                        style="border-bottom: 5px solid #90CAF9"
                    ></div>
                </div>
                <div class="content_thumbnail">
                    <v-row justify="center">
                        <v-col v-for="perform in newTheater" :key="perform.title" cols="2">
                            <router-link
                                :to="{
                                name: 'PerformanceDetailPage',
                                params: { performNo: perform.performNo.toString() },
                                }"
                            >
                                <v-card class="card mx-auto" max-width="216" flat>
                                    <v-img
                                    class="mb-8 hoverImg"
                                        :src="
                                        require(`@/assets/thumbNail/${perform.performThumbnail}`)
                                        "
                                        height="300px"
                                    ></v-img>
                                    <div class="performTitle">
                                        {{ perform.performName }}
                                    </div>
                                </v-card>
                            </router-link>
                        </v-col>
                    </v-row>
                </div>
            </v-col>
        </v-row>
        <v-divider class="mt-13 mb-15"/>
        <v-row>
            <v-col>
                <div class="content_title">
                    <div
                        class="content_title_deco"
                        style="border-bottom: 5px solid #F48FB1"
                    ></div>
                    EXHIBITION
                    <div
                        class="content_title_deco"
                        style="border-bottom: 5px solid #F48FB1"
                    ></div>
                </div>
            <div class="content_thumbnail">
                <v-row justify="center" class="mb-10">
                        <v-col v-for="perform in newExhibition" :key="perform.title" cols="2">
                            <router-link
                                :to="{
                                name: 'PerformanceDetailPage',
                                params: { performNo: perform.performNo.toString() },
                                }"
                            >
                                <v-card class=" card mx-auto" max-width="216" flat>
                                    <v-img
                                    class="mb-8 hoverImg"
                                        :src="
                                        require(`@/assets/thumbNail/${perform.performThumbnail}`)
                                        "
                                        height="300px"
                                    ></v-img>
                                    <div class="performTitle2">
                                        {{ perform.performName }}
                                    </div>
                                </v-card>
                            </router-link>
                        </v-col>
                    </v-row>
            </div>
          </v-col>
      </v-row>
  </div>
</template>

<script>
import NewNavBar from "@/components/Layout/NewNavBar.vue";
import { mapActions, mapState } from 'vuex';

export default {
  name: "HomeView",
  components: {
    NewNavBar,
  },

  data() {
    return {
      ...mapActions(['fetchConcertList','fetchMusicalList','fetchTheaterList','fetchExhibitionList']),
      // 이미지 크기는 1920 * 720 권장
      images: [
        { name: "main1.jpg" },
        { name: "main2.jpg" },
        { name: "main3.jpg" },
        { name: "main4.jpg" },
        { name: "main5.jpg" },
      ],
      concertCnt: 0,
      musicalCnt: 0,
      theaterCnt: 0,
      exhibitionCnt: 0
    };
  },
  computed: {
      ...mapState(['newConcert', 'newMusical', 'newTheater', 'newExhibition'])
  },
  mounted () {
      this.fetchNewConcert('콘서트')
      this.fetchNewMusical('뮤지컬')
      this.fetchNewTheater('연극')
      this.fetchNewExhibition('전시회')
  },
  methods: {
      ...mapActions(['fetchNewConcert', 'fetchNewMusical', 'fetchNewTheater', 'fetchNewExhibition'])
  }
};
</script>

<style scoped>
.slider {
  z-index: 1;
  margin-top: 0;
}
.side_bar {
  position: sticky;
  float: unset;
  left: 1700px;
  width: 150px;
  height: 150px;
  text-align: center;
  bottom: 10px;
  top: 500px;
  background-color: transparent;
  z-index: 2;
  border-radius: 80px;
  margin-top: 25px;
}
.content_title {
  font-size: 50px;
  text-align: center;
  font-weight: bold;
  display: flex;
  justify-content: center;
  font-family: 'Pacifico', cursive;
}
.content_thumbnail {
  margin-top:65px;
  text-align: center;
}
.content_title_deco {
  width: 100px;
  height: 55px;
  margin-right: 5px;
  margin-left: 5px;
}
/* .perform-img-wrap{
    text-align:center;
    display:table;
    width:280px;
    height:250px;
} */
.img-div {
  display:table-cell;
  vertical-align:middle;
}
.perform-img {
  max-width:200px;
  max-height:200px;
}
.peformName{
  font-size: 18px;
  line-height: 21px;
}
.card{
    font-family: 'Nanum Gothic', sans-serif !important;
}
.card:hover  .hoverImg {
  transform: scale(1.1);
  transition: 0.3s;
  cursor: pointer;
  
}
.card:hover  .performTitle {
    font-weight: bold;
    color: #F48FB1;
}
.card:hover  .performTitle2 {
    font-weight: bold;
    color: #90CAF9;
}
</style>
