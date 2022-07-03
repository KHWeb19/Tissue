<template>
    <v-container class="mt-10">
        <swiper class="swiper" :options="swiperOption">
            <swiper-slide>
                광고1
            </swiper-slide>
            <swiper-slide>
                광고2
            </swiper-slide>
            <swiper-slide>
                광고3
            </swiper-slide>
            <swiper-slide>
                광고4
            </swiper-slide>
            <div class="swiper-pagination" slot="pagination"></div>
            <div class="swiper-button-prev" slot="button-prev"></div>
            <div class="swiper-button-next" slot="button-next"></div>
        </swiper>
    </v-container>
</template>

<script>
import { Swiper, SwiperSlide} from 'vue-awesome-swiper'
import 'swiper/css/swiper.css'

export default {
    name:'EventBanner',
     components: {
        Swiper,
        SwiperSlide
    },
    data () {
        return {
            swiperOption: {
                loop: true,
                effect: 'coverflow',
                grabCursor: true,
                centeredSlides: true,
                slidesPerView: 'auto',
                coverflowEffect: {
                    rotation: 50,
                    stretch: 0,
                    depth: 50,
                    // 회전을 많이 먹이고 싶은 경우 높이도록 한다.
                    modifier: 0.3,
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
    height: 100%;
    width: 100%;
    .swiper-slide {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 700px;
        height: 330px;
        text-align: center;
        font-weight: bold;
        font-size: 1.7rem;
        background-color: rgb(178, 220, 247);
        background-position: center;
        background-size: cover;
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

</style>