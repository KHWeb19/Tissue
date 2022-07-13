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
                :items="newTicket"
                hide-default-footer
                >
                <template v-slot:[`item.status`]="{ item }">
              <v-chip color="pink lighten-3"
                outlined
                v-if="item.status === '예매완료'">
                  예매완료
              </v-chip>
              <v-chip v-else-if="item.status === '취소 대기'"
                color="blue lighten-3"
                outlined>
                  환불대기
              </v-chip>
              <v-chip v-else
                color="grey"
                outlined>
                  취소완료
              </v-chip>
            </template>
                </v-data-table>
        </div>
        <div class="tem2">
            <v-row no-gutters>
                <v-col>
                    <v-row no-gutters>
                        <v-icon class="ml-3 mr-5" color="pink lighten-3">mdi-check-bold</v-icon>
                        <span> 최근 찜 목록 </span>
                        <v-spacer/>
                        <router-link class="more mt-2" :to="{name:'MyPageStar'}"> 더보기 ></router-link>
                    <hr class="hrLine mt-3 mb-5" color='#90CAF9' width="100%">
                    </v-row>
                        <v-data-table 
                        :headers="headers"
                        :items="newLike"
                        hide-default-footer
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
                <v-col>
                    <v-row no-gutters>
                        <v-icon class="ml-3 mr-5" color="pink lighten-3">mdi-check-bold</v-icon>
                        <span> 최근 QnA 목록 </span>
                        <v-spacer/>
                        <router-link class="more mt-2 mr-12" :to="{name:'MyPageQnA'}"> 더보기 ></router-link>
                        <hr class="hrLine mt-3 mb-5" color='#90CAF9' width="90%" >
                    </v-row>
                        <v-data-table class="mr-12"
                        :headers="headers2"
                        :items="newQna"
                        hide-default-footer
                        calculate-widths
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
                {text:'썸네일', value:'performance.performThumbnail',width:'10'},
                {text:'공연명', value:'performance.performName', width:'370'},
            ],
            headers2: [
                {text:'구분', value:'qnaCategory', width:'110'},
                {text:'제목', value:'qnaTitle', width:'200'},
                {text:'등록날짜', value:'qnaDate', width:'90'}
            ],
            headers3: [
                {text:'예매일', value:'reg_date', width:'150'},
                {text:'예매번호', value:'serial', width:'100'},
                {text:'공연명', value:'performName', width:'270'},
                {text:'관람일시', value:'performShowDate', width:'100'},
                {text:'매수', value:'seat.length', width:'100'},
                {text:'상태', value:'status', width:'70'}
            ],
        }
    },
    watch: {
        memberNo() {
            this.fetchMyNewLike(this.memberNo)
            this.fetchMyNewQna(this.memberNo)
            this.fetchMyNewTicket(this.memberNo)
        }
    },
    computed: {
        ...mapState(['newLike','newQna', 'newTicket'])
    },
    created() {
        this.fetchMyNewLike(this.memberNo)
        this.fetchMyNewQna(this.memberNo)
        this.fetchMyNewTicket(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyNewLike', 'fetchMyNewQna', 'fetchMyNewTicket']),

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