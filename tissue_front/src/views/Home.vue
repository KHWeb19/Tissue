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
        <v-carousel-item v-for="(image, i) in images" :key="i" :src="image.src">
        </v-carousel-item>
      </v-carousel>
    </div>

    <div class="side_bar">
        <router-link :to="{ name : 'EventMainPage' }" >
      <v-img
        :src="require('@/assets/event.png')"
        max-width="150px"
        max-height="150px"
        style="zoom:0.85"
      />
        </router-link>
    </div>

    <v-container>
      <v-row>
        <v-col>
          <div>
            <div class="content_title">
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid red"
              ></div>
              TICKET OPEN
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid red"
              ></div>
            </div>
            <div class="content_thumbnail">이미지</div>
          </div>
          <div>
            <div class="content_title">
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid orange"
              ></div>
              R A N K
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid orange"
              ></div>
            </div>
            <div class="content_thumbnail">이미지</div>
          </div>
          <div>
            <div class="content_title">
              <div class="content_title_deco" style="border-bottom: 5px solid yellowgreen"></div>
                CONCERT
              <div class="content_title_deco" style="border-bottom: 5px solid yellowgreen"></div>
            </div>
            <div class="content_thumbnail">
              <v-row>
                <v-col v-for="concert in concertList" :key="concert.performNo">
                  <div>
                    <div class="perform-img-wrap">
                      <div class="img-div">
                        <v-img class="perform-img" :src="require(`@/assets/thumbNail/${concert.performThumbnail}`)">
                          <router-link :to="{ name: 'PerformanceDetailPage', params: { performNo: concert.performNo} }"></router-link>
                        </v-img>
                        </div>
                      </div>

                    <v-card-title class="peformName text--lighten-3" style="font-weight: lighter">
                      {{ concert.performName }}
                    </v-card-title>
                  </div>
                </v-col>
              </v-row>
            </div>
          </div>
          <div>
            <div class="content_title">
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid lightskyblue"
              ></div>
              MUSICAL
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid lightskyblue"
              ></div>
            </div>
            <div class="content_thumbnail">이미지</div>
          </div>
          <div>
            <div class="content_title">
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid blue"
              ></div>
              THEATER
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid blue"
              ></div>
            </div>
            <div class="content_thumbnail">이미지</div>
          </div>
          <div>
            <div class="content_title">
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid plum"
              ></div>
              EXHIBITION
              <div
                class="content_title_deco"
                style="border-bottom: 5px solid plum"
              ></div>
            </div>
            <div class="content_thumbnail">이미지</div>
          </div>
        </v-col>
      </v-row>
    </v-container>
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
        {
          src: "http://tkfile.yes24.com/Upload2/Display/202205/20220509/wel_mv_42155.jpg/dims/quality/70/",
        },
        {
          src: "http://tkfile.yes24.com/Upload2/Display/202203/20220304/wel_mv_41497.jpg/dims/quality/70/",
        },
        {
          src: "http://tkfile.yes24.com/Upload2/Display/202205/20220510/wel_mv_42123_2.jpg/dims/quality/70/",
        },
        {
          src: "http://tkfile.yes24.com/Upload2/Display/202204/20220406/wel_mv_NTN_82569c.jpg/dims/quality/70/",
        },
      ],
      concertCnt: 0,
      musicalCnt: 0,
      theaterCnt: 0,
      exhibitionCnt: 0
    };
  },
  computed: {
    ...mapState(['concertList', 'musicalList', 'theaterList', 'exhibitionList'])
  },
  mounted() {
    this.fetchConcertList()
    this.fetchMusicalList()
    this.fetchTheaterList()
    this.fetchExhibitionList()

    this.concertCnt = Math.min(6, this.concertList.length)
    this.musicalCnt = Math.min(6, this.musicalList.length)
    this.theaterCnt = Math.min(6, this.theaterList.length)
    this.exhibitionCnt = Math.min(6, this.exhibitionList.length)
  },
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
  margin-top:25px;

}
.content_title {
  font-size: 50px;
  text-align: center;
  font-weight: bold;
  display: flex;
  justify-content: center;
}
.content_thumbnail {
  height: 200px;
  text-align: center;
  display:table;
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
</style>
