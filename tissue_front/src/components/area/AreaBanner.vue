<template>
    <v-container class="mt-5 mb-10">
        <div class="carousel">
            <v-carousel
            height="350px"
            class="slider"
            delimiter-icon="mdi-minus"
            hide-delimiter-background
            :cycle="true"
            :interval="3000"
            >
                <v-carousel-item v-for="(image, i) in images" :key="i"
                class="cImg">
                <img :src="require(`@/assets/event/${image.name}`)">
                </v-carousel-item>
            </v-carousel>
        </div>
        <div>
            <swiper class="swiper" :options="swiperOption">
                <swiper-slide v-for="img in eventImgs" :key="img.name">
                    <img :src="require(`@/assets/event/${img.name}`)" height="350">
                </swiper-slide>
                <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
        </div>
    </v-container>
</template>

<script>
import { Swiper, SwiperSlide} from 'vue-awesome-swiper'
import 'swiper/css/swiper.css'

export default {
    name:'AreaBanner',
    components: {
        Swiper,
        SwiperSlide
    },
     data () {
        return {
            swiperOption: {
                autoHeight:true,
                loop:true,
                effect: 'fade',
                centeredSlides: true,
                slidesPerView: 'auto',

                coverflowEffect: {
                    rotation: 50,
                    stretch: 0,
                    depth: 100,
                    // 회전을 많이 먹이고 싶은 경우 높이도록 한다.
                    modifier: 0.5,
                    slideShadows: true
                },
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true
                },
                autoplay: {
                    delay: 3000
                },
                navigation: { 
                nextEl: '.swiper-button-next', 
                prevEl: '.swiper-button-prev' 
                } 
            },
            eventImgs: [
                {name:'area_banner1.jpg'},
                {name:'area_banner2.jpg'},
                {name:'area_banner3.jpg'},
            ],
            images: [
                {name:'area_big_banner1.jpg'},
                {name:'area_big_banner2.jpg'},
                {name:'area_big_banner3.jpg'}
            ],
        }
    }
}
</script>

<style lang="scss">
.swiper-pagination-bullet {
    background-color: rgb(248, 248, 248) !important;
}
</style>
<style  lang="scss" scoped>
.swiper {
    height: 40%;
    width: 35%;
    .swiper-slide {
        justify-content: center;
        align-items: center;
        width: 500px;
        text-align: center;
    }
    .swiper-button-next,
    .swiper-button-prev {
        right:10px;
        padding: 30px;
        color: rgba(255, 255, 255, 0.863) !important;
        fill: black !important;
        stroke: black !important;
    }
}

.carousel {
    width:65%;
    position: relative;
    float: left;
}
</style>