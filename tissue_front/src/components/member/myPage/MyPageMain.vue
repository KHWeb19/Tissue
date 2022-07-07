<template>
    <v-container style="zoom:0.876">
        <div class="tem1">
            <v-row class="menuTitle ml-5" no-gutters>
                <v-icon class="mr-5" color="pink lighten-3" large>mdi-check-bold</v-icon>
                최근 예매 내역
                <v-spacer/>
                        <router-link class="more mt-4 mr-16" :to="{name:'MyPageTicket'}"> 더보기 ></router-link>
            </v-row>
            <hr class="hrLine mt-6 mb-5" color='#90CAF9' width="94%" >
                <v-data-table class="mr-12"
                :headers="headers3"
                :items="myTicket"
                hide-default-footer
                >
                </v-data-table>
        </div>
        <div class="tem2">
            <v-row no-gutters>
                <v-col >
                    <v-row no-gutters>
                        <v-icon class="ml-3 mr-5" color="pink lighten-3">mdi-check-bold</v-icon>
                        <span> 최근 찜 목록 </span>
                        <v-spacer/>
                        <router-link class="more mt-2" :to="{name:'MyPageStar'}"> 더보기 ></router-link>
                    <hr class="hrLine mt-3 mb-5" color='#90CAF9' width="100%">
                    </v-row>
                        <v-data-table 
                        :headers="headers"
                        :items="myLike"
                        hide-default-footer
                        hide-default-header
                        :items-per-page="3"
                      >
                        <template v-slot:[`item.performance.performThumbnail`] ="{item}">
                             <img class="imgwidth"
                            :src="require(`@/assets/thumbNail/${item.performance.performThumbnail}`)"
                            >
                        </template>
                        <template v-slot:[`item.performance.performName`]="{ item }">
                            <router-link 
                                style="color:black"
                                :to="{ name: 'PerformanceDetailPage', params: { performNo: item.performance.performNo.toString()} }">
                                {{ item.performance.performName }}
                            </router-link>
                        </template>
                        </v-data-table>
                </v-col>
                <v-divider class='ml-7 mr-7' vertical/>
                <v-col >
                    <v-row no-gutters>
                        <v-icon class="ml-3 mr-5" color="pink lighten-3">mdi-check-bold</v-icon>
                        <span> 최근 QnA 목록 </span>
                        <v-spacer/>
                        <router-link class="more mt-2 mr-12" :to="{name:'MyPageQnA'}"> 더보기 ></router-link>
                        <hr class="hrLine mt-3 mb-5" color='#90CAF9' width="90%" >
                    </v-row>
        
                        <v-data-table class="mr-10"
                        :headers="headers2"
                        :items="myQna"
                        hide-default-footer
                        hide-default-header
                        :items-per-page="5"
                        >
                        <template v-slot:[`item.qnaTitle`]="{ item }">
                            <router-link 
                                style="color:black"
                                :to="{ name: 'QnaReadPage', params: { qnaNo: item.qnaNo} }">
                                {{ item.qnaTitle }}
                            </router-link>
                        </template>
                        </v-data-table>
                
                </v-col>
            </v-row>
        </div>
    </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyPageMain',
    props: {
        memberNo: {
            type:Number
        },
    },
    data () {
        return {
            headers: [
                {text:'썸네일', value:'performance.performThumbnail'},
                {text:'공연명', value:'performance.performName', width:'150'},
            ],
            headers2: [
                {text:'구분', value:'qnaCategory', width:'50'},
                {text:'제목', value:'qnaTitle', width:'200'},
                {text:'등록날짜', value:'qnaDate', width:'120'}
            ],
            headers3: [
                {text:'예매일', value:'reg_date', width:'150'},
                {text:'예매번호', value:'ticketing_no', width:'100'},
                {text:'공연명', value:'performName'},
                {text:'관람일시', value:'performShowDate'},
                {text:'매수', value:'seat.length'},
            ],
        }
    },
    computed: {
        ...mapState(['myLike','myQna', 'myTicket'])
    },
    mounted() {
        this.fetchMyLike(this.memberNo)
        this.fetchMyQna(this.memberNo)
        this.fetchMyTicket(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyLike', 'fetchMyQna', 'fetchMyTicket']),

    }
}
</script>

<style scoped>
.menuTitle{
    font-size: 18pt;
}
.hrLine{
    border:0;
    height:2px;
}
.ver{
    float: left;
}
.tem2 {
    margin-top:80px;
    margin-bottom:30px;
}
.imgwidth {
    width:50px;
    margin-top:5px;
}
.more {
    font-size:8pt;
    color: grey;
}
</style>