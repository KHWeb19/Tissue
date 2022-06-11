<template>
    <div>
        <v-tabs color="blue lighten-3">
            <v-tab>
                회원 로그인
            </v-tab>
            <v-tab>
                비회원 조회
            </v-tab>
            <v-tab-item class="mt-5">
                <v-container>
                    <v-row dense no-gutters>
                        <v-col>
                            <v-text-field v-model="memberId" color="pink lighten-3" placeholder="아이디" outlined></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row dense no-gutters>
                        <v-col>
                            <v-text-field v-model="memberPw" color="pink lighten-3" placeholder="비밀번호" outlined></v-text-field>
                        </v-col>
                    </v-row> 
                    <v-row justify="center">
                        <v-btn color="blue lighten-3" dark large width="95%">로그인</v-btn>
                    </v-row>
                    <v-row>
                        <v-col style="color:grey; font-size:11pt" class="mt-2">
                            아이디 찾기
                            │
                            비밀번호 찾기
                        </v-col>
                    </v-row>
                    <v-row justify="center" class="mt-10">
                        <button><v-img src="@/assets/button/kakao_login_medium_wide.png"></v-img></button>
                    </v-row>
                    <v-row justify="center" class="mt-5">
                        <button class="btnBorder"><v-img src="@/assets/button/google_login.png"></v-img></button>
                    </v-row>
                </v-container>
            </v-tab-item>
            <v-tab-item class="mt-5">
                <v-container>
                    <v-row dense no-gutters>
                        <v-col>
                            <v-text-field v-model="nonMemberName" color="pink lighten-3" placeholder="이름" outlined></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row dense no-gutters>
                        <v-col>
                            <v-text-field v-model="nonMemberPhone" color="pink lighten-3" placeholder="휴대폰" outlined></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-btn color="blue lighten-3" dark large width="95%">확인</v-btn>
                    </v-row>    
                </v-container>
            </v-tab-item>
        </v-tabs>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'MemberLogin',
    data () {
        return {
            memberId:'',
            memberPw:'',
            nonMemberName:'',
            nonMemberPhone:'',

            items: ['회원 로그인', '비회원 조회']
        }
    },
    methods: {
        async login(){
            const response = await axios.post('Member/login', {
                memberId : this.memberId,
                memberPw : this.memberPw

            })

            localStorage.setItem('token', response.data)
            console.log(localStorage)

        }
    }
}
</script>

<style scoped>
.background {
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(197, 197, 197);
    width:82%;
    margin: auto;
}
.btnBorder{
    border:1px solid rgb(194, 193, 193);
    border-radius: 5px;
}
</style>