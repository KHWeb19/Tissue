<template>
    <div class="background">
        <v-container>
               <v-row justify="center" class="logo">Tissue</v-row>
            <v-row>
                <form name ="loginForm" @submit.prevent="onSubmit">
                아이디<v-text-field v-model="memberId"></v-text-field>
                비번<v-text-field type="password" v-model="memberPw"></v-text-field>
                이름<v-text-field v-model="memberName"></v-text-field>
                생일<v-text-field type="date" v-model="memberBirth"></v-text-field>
                핸드폰<v-text-field v-model="memberPhone"></v-text-field>
                <v-btn type="button" @click="checkPhone()" color="red darken-3" dark>본인인증</v-btn>
                <br/>
                인증번호 <v-text-field v-model="checkNum"></v-text-field>
                <v-btn type="button"  @click="checkAuthNum()" color="red darken-3" dark>확인</v-btn>
                <br/>
                주소
                <v-text-field id=zipcode style="width:150px" v-model="zipcode"></v-text-field>
                <v-text-field @click="searchAddress()" id=address_kakao v-model="memberAddress"></v-text-field>
                상세입력
                <v-text-field name=address_detail></v-text-field>
                이메일<v-text-field v-model="memberEmail"></v-text-field>
                <v-btn type="submit" color="red darken-3" dark>JOIN</v-btn>
                </form>
            </v-row>
        </v-container>
    </div>

</template>

<script>
import axios from 'axios'
export default {
    name: 'MemberJoinForm',
    data () {
        return {
            memberId: '',
            memberPw: '',
            memberName: '',
            memberPhone:'',
            memberEmail:'',
            memberAddress:'',
            memberBirth: '',
            zipcode:'',
            authNum:'',
            checkNum:'',
            phonePass:false,
            idPass: false,
            checkCondition:false,

        }
    },
    methods: {
        onSubmit() {
            const { memberId, memberPw, memberName, memberPhone,
        memberEmail, memberAddress, memberBirth} = this
                this.$emit('submit', { memberId, memberPw, memberName, memberPhone,
        memberEmail, memberAddress, memberBirth })
        },
        checkPhone() { 
            const {memberPhone} = this
            axios.get(`http://localhost:7777/phone/check/${memberPhone}`)
             .then((res) => {
                 console.log(memberPhone)
                alert("인증번호가 전송되었습니다.");
                this.authNum = res.data
             })
             .catch(() => {
                 console.log("전송 실패!")
             })
        },
        checkAuthNum() {
            if(this.checkNum == this.authNum) {
                alert("인증 성공")
                this.phonePass = true
            }
        },
        searchAddress() {
            new window.daum.Postcode({
                oncomplete: function(data) {
                    document.getElementById("zipcode").value = data.zonecode
                    document.getElementById("address_kakao").value = data.address
                    document.querySelector("input[name=address_detail]").focus() 
                }
            }).open();
        }
    }
}
</script>

<style scoped>
.background {
    background-color: rgb(238, 238, 238);
}
.logo {
    font-size: 50pt;
    margin-top:5%;
    margin-bottom:3%;
    font-family: 'Pacifico', cursive;
}
</style>>