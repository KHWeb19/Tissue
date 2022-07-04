<template>
    <div class="mt-10 mb-10">
        <swiper class="swiper" :options="swiperOption">
            <swiper-slide v-for="img in eventImgs" :key="img.name">
                <img :src="require(`@/assets/event/${img.name}`)" height="350">
            </swiper-slide>
            <div class="swiper-pagination" slot="pagination"></div>
            <div class="swiper-button-prev" slot="button-prev"></div>
            <div class="swiper-button-next" slot="button-next"></div>
        </swiper>
    </div>
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
                autoHeight:true,
                loop:true,
                effect: 'coverflow',
                centeredSlides: true,
                slidesPerView: 'auto',
                spaceBetween: 150,
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
                {name:'ev_mv_NO.jpg'},
                {name:'ev_mv_3.png'},
                {name:'ev_chicken.jpg'},
                {name:'ev_sns.jpg'}
            ]
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
        width: 800px;
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

</style>