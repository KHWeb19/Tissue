<template>
    <div>
        <v-row class="infoTable" justify="center">
            <v-col cols="7">
            <table class="mt-10 mb-10">
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
            <v-col cols="3">
                    <img class="thumbImg" :src="require(`@/assets/thumbNail/${performance.performThumbnail}`)">
            </v-col>
        </v-row>
    </div>
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
        async checkMember () {
            try {
                const response = await axios.post('Member/findId', {
                memberName : this.memberName,
                memberPhone : this.memberPhone,
                })

                if (response.data) {
                   this.getNumStr = response.data.numStr
                   this.memberId = response.data.memberId
                } else {
                    alert("등록되지 않은 회원입니다.")
                }
            }catch(error){
                alert(error)
            }
        },
        async findId () {
            if (this.numStr == this.getNumStr) {
                this.showId = true
            } else {
                alert("인증번호가 일치하지 않습니다.")
            }
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
</style>