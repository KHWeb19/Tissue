<template>
  <div>
    <v-carousel
      height="720px"
      class="slider"
      delimiter-icon="mdi-minus"
      hide-delimiter-background
    >
      <v-carousel-item v-for="(image, i) in images" :key="i" :src="image.src">
      </v-carousel-item>
    </v-carousel>
    <div style="height: 1000px"></div>
    <div class="side_bar">
      <v-img
        :src="require('@/assets/event.png')"
        max-width="150px"
        max-height="150px"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",

  data() {
    return {
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

      colors: [
        "green",
        "secondary",
        "yellow darken-4",
        "red lighten-2",
        "orange darken-1",
      ],
      cycle: false,
      slides: ["First", "Second", "Third", "Fourth", "Fifth"],
    };
  },
};
</script>

<style scoped>
.slider {
  z-index: 0;
}
.side_bar {
  position: fixed;
  widows: 150px;
  height: 150px;
  text-align: center;
  right: 50px;
  top: 800px;
  background-color: lightgrey;
  z-index: 1;
}
</style>
