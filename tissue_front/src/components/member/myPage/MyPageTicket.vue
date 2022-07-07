<template>
    <v-container>
        <span class="ml-3" style="font-size:20pt;"> My 예매내역 </span>
        <p/>
        <hr color="#90CAF9" width="95%">
        <v-data-table class="elevation-0 mr-12"
            :headers="headers"
            :items="myTicket"
            item-key="ticketing_no"
            :expanded.sync="expanded"
            :single-expand="singleExpand"
            hide-default-footer
            show-expand
            :items-per-page="7"
            :page.sync="page"
            @page-count="pageCount = $event"
        >
            <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length">
                    <v-row class="infoTable" justify="center">
                        <v-col cols="7">
                        <table class="mt-10 mb-10">
                            <tr class="topTr">
                                <td class="tdTitle">
                                    예매번호
                                </td>
                                <td>
                                    {{ item.ticketing_no }}
                                </td>
                                <td class="tdTitle">
                                    예매일
                                </td>
                                <td>
                                    {{ item. reg_date}}
                                </td>
                            </tr>
                            <tr>
                                <td class="tdTitle">
                                    공연명
                                </td>
                                <td colspan="3" >
                                    {{ item.performName }}
                                </td>
                            </tr>
                            <tr>
                                <td class="tdTitle">
                                    관람일
                                </td>
                                <td colspan="3" >
                                    {{ item.performShowDate }}
                                </td>
                            </tr>
                            <tr>
                                <td class="tdTitle">
                                    공연장
                                </td>
                                <td colspan="3" >
                                    {{ item.performArea }}
                                </td>
                            </tr>
                            <tr>
                                <td class="tdTitle">
                                    좌석
                                </td>
                                <td colspan="3" >
                                    {{ item.seat }}
                                </td>
                            </tr>
                        </table>
                        </v-col>
                        <v-col cols="3">
                             <img class="thumbImg" :src="require(`@/assets/thumbNail/${item.performThumbnail}`)">
                        </v-col>
                    </v-row>
                    <div class="infoContent">
                        <v-row justify="center" no-gutters >
                            <v-col cols="7">
                            <span> 사용한 쿠폰 : 
                                <span v-if="item.used_coupon == null"> 없음 </span>
                                {{ item.used_coupon }} </span>
                            </v-col>
                            <v-col class="price" cols="2">
                                <strong>결제 금액</strong> : {{item.final_price}}원
                            </v-col>
                        </v-row>
                        <v-row justify="center" class="mb-10" no-gutters>
                            <v-col cols="9">
                            <span> 사용한 마일리지 : {{ item.used_mileage }} </span>
                            </v-col>
                        </v-row>
                        <v-row justify="end" class="pb-5 pr-5"> 
                            <v-btn small color="blue lighten-3" dark depressed block
                            @click="cancleTicket">
                                <strong>예매취소</strong>
                            </v-btn>
                        </v-row>
                    </div>
                </td>
            </template>
        </v-data-table>
        <v-pagination
            class="mt-10"
            v-model="page"
            :length="pageCount"
            total-visible="5"
            color="pink lighten-3"
            circle>
        </v-pagination>
    </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
    name:'MyPageTicket',
    memberNo : {
        type: Number
    },
    data () {
        return {
            headers : [
                {text:'예매일', value:'reg_date', width:'150', align: 'start'},
                {text:'예매번호', value:'ticketing_no', width:'100'},
                {text:'공연명', value:'performName'},
                {text:'관람날짜', value:'performShowDate', width:'140'},
                {text:'매수', value:'seat.length', width:'80'},
                {text:'', value:'data-table-expand' ,width:'50'}
            ],
            expanded: [],
            singleExpand: true,
            page: 1,
            pageCount: 0,
        }
    },
    computed: {
        ...mapState(['myTicket'])
    },
    mounted() {
        this.fetchMyTicket(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyTicket']),
        cancleTicket() {

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
    font-family: 'Nanum Gothic', sans-serif !important;
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
    margin-top:60px;
    zoom:0.4;
    float: left;
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
.v-data-table >>> .v-data-table__wrapper tbody tr.v-data-table__expanded__content {
  box-shadow: none;
}
</style>