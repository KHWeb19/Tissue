<template>
    <v-container fluid class="infoContent">
        <v-row justify="center" dense no-gutters> 
            <v-col cols="8" class="mt-5">
                <v-btn v-if="performance.status === '예매완료'" 
                 color="blue lighten-3" dark depressed
                @click="cancleTicket(performance.ticketing_no)">
                    <strong>예매취소</strong>
                </v-btn>
                <v-btn v-else-if="performance.status === '취소 대기'"
                 color="blue lighten-3" depressed disabled 
                    >
                <strong>환불 대기</strong>
                </v-btn>
            </v-col>
        </v-row>
        <v-row class="infoTable" justify="center">
            <v-col cols="6">
            <table class=" mb-10">
                <tr class="topTr">
                    <td class="tdTitle">
                        예매번호
                    </td>
                    <td>
                        {{ performance.ticketing_no }}
                    </td>
                    <td class="tdTitle">
                        예매일
                    </td>
                    <td>
                        {{ performance. reg_date}}
                    </td>
                </tr>
                <tr>
                    <td class="tdTitle">
                        공연명
                    </td>
                    <td colspan="3" >
                        {{ performance.performName }}
                    </td>
                </tr>
                <tr>
                    <td class="tdTitle">
                        관람일
                    </td>
                    <td colspan="3" >
                        {{ performance.performShowDate }}
                    </td>
                </tr>
                <tr>
                    <td class="tdTitle">
                        공연장
                    </td>
                    <td colspan="3" >
                        {{ performance.performArea }}
                    </td>
                </tr>
                <tr>
                    <td class="tdTitle">
                        좌석
                    </td>
                    <td colspan="3" >
                        {{ performance.seat }}
                    </td>
                </tr>
            </table>
            </v-col>
            <v-col cols="2">
                    <img class="thumbImg" :src="require(`@/assets/thumbNail/${performance.performThumbnail}`)">
            </v-col>
        </v-row>
        <v-row justify="end" no-gutters>
            <v-col class="price mr-5" cols="3">
                <strong>결제 금액</strong> : {{performance.final_price}}원
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
export default {
    name:'NonMemberFindRv',
    props: {
        performance: {
            type:Object
        }
    },
    data () {
        return {
            memberName:'',
            memberPhone:'',
            memberId:'',
            numStr:'',
            getNumStr:'none',
            showId:false
        }
    },
    methods : {
        cancleTicket(ticketingNo) {
            axios.post(`nonMember/request/${ticketingNo}`)
            .then(() =>{
                alert('예매가 취소요청 되셨습니다. 환불을 예매 취소일 기준 1~2일 소요됩니다.')
                 this.$router.push('/')
            })
        }
    }
}
</script>

<style scoped>
table {
    width: 100%;
    text-align: center;
    border-collapse: collapse;
    border-spacing: 0;
}
.infoContent {
    font-family: 'Nanum Gothic', sans-serif !important;
}
td {
    border-bottom: 1px solid rgb(196, 196, 196);
    padding: 10px;
}
tr{
    height: 30px;
}
.thumbImg {
    zoom:0.4;
}
.tdTitle {
    background-color: rgb(250, 215, 221);
    border-right: 1px solid rgb(252, 147, 165);
    font-weight: bold;
}
.topTr {
    border-top: 2px dashed rgb(253, 106, 130);
}
.price {
    font-size: 15pt;
}
</style>