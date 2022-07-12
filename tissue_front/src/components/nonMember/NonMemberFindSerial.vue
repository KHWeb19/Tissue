<template>
    <div>
        <div v-if="show">
            <v-row class="showTitle mb-15">
                <v-icon large>mdi-check</v-icon> &nbsp;
                인증에 성공하셨습니다.
            </v-row>
            <v-row>
                회원님의 예매번호는
            </v-row> 
            <v-row v-for="serial in serialSet" :key="serial" justify="center">
                <span style="color:lightgrey; font-size:20pt" class="mt-5">{{ serial }}fff</span>
            </v-row>
            <v-row justify="end"> 
                입니다. 
            </v-row>
        </div>
        <div v-else>
            <v-row>
                <v-text-field v-model="phone" placeholder="휴대폰 ('-') 없이 입력" outlined color="pink lighten-3"></v-text-field>
                <v-btn class="mt-1 ml-1 mb-5" large color="blue lighten-3" dark @click="checkMember(phone.toString())">인증번호</v-btn>
            </v-row>
            <v-row>
                <v-text-field v-model="numStr" outlined color="pink lighten-3" dense placeholder="인증번호 4자리"></v-text-field>
            </v-row>
            <v-row justify="center">
                <v-btn color="blue lighten-3" large dark width="100%" @click="findId">확인</v-btn>
            </v-row>
            <v-row class="mt-12">
                <v-icon>mdi-alert-circle</v-icon> &nbsp;
                <div style="color:grey">예매 시 등록된 휴대폰으로만 인증 가능합니다.</div>
            </v-row>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'MemberFindId',
    data () {
        return {
            phone:'',
            serialSet:[],
            numStr:'',
            getNumStr:'none',
            show:false
        }
    },
    methods : {
        async checkMember (phone) {
            try {
                const response = await axios.post(`nonMember/s/${phone}`)
                if (response.data) {
                    console.log(response.data)
                    console.log(response.data.length)
                   this.getNumStr = response.data[response.data.length-1]

                   for(let i = 0; i <response.data.length-1; i++) {
                        this.serialSet[i] = response.data[i]
                   }
                } else {
                    alert("등록되지 않은 번호입니다.")
                }
            }catch(error){
                alert(error)
            }
        },
        async findId () {
            if (this.numStr == this.getNumStr) {
                this.show = true
            } else {
                alert("인증번호가 일치하지 않습니다.")
            }
        }
    }
}
</script>

<style scoped>
.showTitle {
    font-size: 20pt;
    color: skyblue;
}
</style>