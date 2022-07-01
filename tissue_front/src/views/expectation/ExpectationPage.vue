<template>
    <v-container>
        <v-row>
            <v-col>
                <h1>{{ event.eventCategory }}</h1>
                <br>
                <div align="center" class="mb-5">
                <h2>{{ event.eventTitle }}</h2>
                </div>
            </v-col>
        </v-row>

        <v-row>
            <v-col>
                <v-img :src="require(`@/assets/thumbNail/${event.performance.performThumbnail}`)" class="img"></v-img>
            </v-col>
            <v-col>
                <div>
                    <label>제목</label>
                    <v-text-field outlined color="pink lighten-3" type="text" :value="event.eventTitle" readonly></v-text-field>
                </div>
                <div>
                    <label>카테고리</label>
                    <v-text-field outlined  color="pink lighten-3" type="text" :value="event.eventCategory" readonly></v-text-field>
                </div>
                <div>
                    <label>시작일</label>
                    <v-text-field  outlined color="pink lighten-3" type="text" :value="event.eventStart" readonly></v-text-field>
                </div>
                <div>
                    <label>종료일</label>
                    <v-text-field  outlined color="pink lighten-3" type="text" :value="event.eventEnd" readonly></v-text-field>
                </div>
                <div align="center">
                    <div class="to-detail-page" style="font-size:20px">
                        <router-link :to="{ name: 'PerformanceDetailPage', params: { performNo: event.performance.performNo} }">
                        <p style="color:black">공연정보 보러가기</p>
                        </router-link>
                    </div>
                </div>
            </v-col>
        </v-row>

            

        <hr class="mt-10 mb-10">

        <!-- 댓글영역 -->
        <expectation-read-form :eventNo="eventNo" :memberInfo="memberInfo"/>
    </v-container>
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
    }
}
</script>

<style scoped>
h1 {
    text-decoration: underline; 
    text-underline-position: under;
    text-decoration-thickness: 8px;
    text-decoration-color: #F48FB1;
}
.img {
    width: 100%;
    height: 100%;
}
.to-detail-page{
    width: 300px;
    height: 60px;
    background-color: #F48FB1;
    /* border: 1px solid; */
    border-radius: 50px;
}
p {
    margin-top:5px;
}


</style>