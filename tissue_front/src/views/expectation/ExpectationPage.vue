<template>
    <div class="background">
        <v-container>
            <v-row>
                <v-col>
                    <h1>{{ event.eventCategory }}</h1>
                    <br>
                    <div align="center" class="mb-5" style="font-size:30px">
                        {{ event.eventTitle }}
                    </div>
                    <div style="color:#757575; font-size:15px" align="right">
                        <v-icon> mdi-calendar-month</v-icon>
                        {{ event.eventStart }} ~ {{ event.eventEnd }}
                    </div>
                </v-col>
            </v-row>

            <v-row>
                <v-col>
                    <v-img :src="require(`@/assets/thumbNail/${event.performance.performThumbnail}`)" class="img"></v-img>
                </v-col>
                <v-col>
                    <div class="expect-detail-wrap">
                        <hr class="mt-10 mb-10">
                        <div class="expect-content" align="center" style="font-size:18px; word-break: keep-all">
                            {{ event.eventContent }}
                        </div>
                        <hr class="mt-10 mb-10">

                        <div class="expect-detail">
                            <v-icon color="blue lighten-2">mdi-square-medium</v-icon>
                            <div class="expect-subtitle">경품</div>
                            <div>{{ event.eventGoods }}</div>
                        </div>
                        <div class="expect-detail">
                            <v-icon color="blue lighten-2">mdi-square-medium</v-icon>
                            <div class="expect-subtitle">당첨자 발표일</div>
                            <div> {{ event.eventWinnerDate }}</div>
                        </div>
                        <div class="expect-detail">
                            <v-icon color="blue lighten-2">mdi-square-medium</v-icon>
                            <div class="expect-subtitle">당첨 인원</div>
                            <div> {{ event.eventWinnerCnt }} 명</div>
                        </div>
                        <div class="linkbtn" align="center">
                            <div class="to-detail-page" style="font-size:20px">
                                <router-link :to="{ name: 'PerformanceDetailPage', params: { performNo: event.performance.performNo} }">
                                <p style="color:black">공연정보 보러가기</p>
                                </router-link>
                            </div>
                        </div>
                    </div>
                </v-col>
            </v-row>

            <hr class="mt-10 mb-10">

            <!-- 댓글영역 -->
            <expectation-read-form :eventNo="eventNo" :memberInfo="memberInfo"/>



        </v-container>
    </div>
</template>

<script>
import ExpectationReadForm from '@/components/expectation/ExpectationReadForm.vue'
import { mapActions, mapState } from 'vuex'
export default {
    name: 'ExpectationPage',
    props: {
        eventNo: {

        }
    },
    data() {
        return {
            token: localStorage.getItem('token'),
        }
    },
    components: {
        ExpectationReadForm
    },
    computed: {
        ...mapState(['event', 'memberInfo'])
    },
    methods: {
        ...mapActions(['fetchEvent', 'fetchMemberInfo'])
    },
    created() {
        
        this.fetchEvent(this.eventNo)
        this.fetchMemberInfo(this.token)

        
    },
    mounted() {
        if(this.token === null) {
            this.memberInfo.memberId = null
        }

    }
}
</script>

<style scoped>
.container{
  overflow: hidden;
}
.background {
    background-color: #EEEEEE;
}
.expect-detail-wrap{
    padding: 50px 10px;
}
.expect-content {
    width: 100%;
}
.expect-detail {
    display: flex;
    margin-top: 15px;
    margin-bottom: 15px;
}
.expect-subtitle {
    /* font-size: 15px; */
  color: #F48FB1;
  width: 150px;
}
h1 {
    text-decoration: underline; 
    text-underline-position: under;
    text-decoration-thickness: 8px;
    text-decoration-color: #F48FB1;
}
.container > img {
  position: absolute;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.linkbtn {
    margin-top: 100px;
}
.to-detail-page{
    width: 300px;
    height: 60px;
    background-color: #F48FB1;
    /* border: 1px solid; */
    border-radius: 50px;
    padding : 10px 0;
}
p {
    margin-top:5px;
}


</style>