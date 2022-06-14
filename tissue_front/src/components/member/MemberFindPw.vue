<template>
    <div>
        <div v-if="showPw">
            <v-row class="showTitle mb-15">
                <v-icon large>mdi-check</v-icon> &nbsp;
                인증에 성공하셨습니다.
            </v-row>
            <v-row>
                회원님의 임시 비밀번호는
            </v-row> 
            <v-row>
                <v-text-field v-model="memberPw" disabled></v-text-field> 
            </v-row>
            <v-row justify="end"> 
                입니다. 
            </v-row>
        </div>

        <div v-else>
            <v-row>
                <v-text-field v-model="memberId" placeholder="아이디" outlined color="pink lighten-3"></v-text-field>
            </v-row>
            <v-row>
                <v-text-field v-model="memberPhone" placeholder="휴대폰 ('-') 없이 입력" outlined color="pink lighten-3"></v-text-field>
                <v-btn class="mt-1 ml-1 mb-5" large color="blue lighten-3" dark @click="checkMember">인증번호</v-btn>
            </v-row>
            <v-row>
                <v-text-field v-model="numStr" outlined color="pink lighten-3" dense placeholder="인증번호 4자리"></v-text-field>
            </v-row>
            <v-row justify="center">
                <v-btn color="blue lighten-3" large dark width="100%" @click="findPw">확인</v-btn>
            </v-row>
            <v-row class="mt-12">
                <v-icon>mdi-alert-circle</v-icon> &nbsp;
                <div style="color:grey">회원 정보에 등록된 휴대폰으로만 인증 가능합니다.</div>
            </v-row>
            <v-row class="mt-5">
                <v-icon>mdi-alert-circle</v-icon> &nbsp;
                <div style="color:grey; font-size:11pt">개인정보 보호를 위해 임시비밀번호가 발급됩니다.</div>
            </v-row>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'MemberFindPw',
    data () {
        return {
            memberName:'',
            memberPhone:'',
            memberId:'',
            numStr:'',
            getNumStr:'none',
            showPw:false
        }
    },
    methods : {
        async checkMember () {
            try {
                const response = await axios.post('Member/findPw', {
                memberId : this.memberId,
                memberPhone : this.memberPhone,
                })

                if (response.data) {
                    alert("인증번호가 발송되었습니다.")
                    this.getNumStr = response.data.numStr
                    this.memberPw = response.data.memberPw
                } else {
                    alert("등록되지 않은 회원입니다.")
                }
            }catch(error){
                alert(error)
            }
        },
        async findPw () {
            if (this.numStr == this.getNumStr) {
                this.showPw = true
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