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
                            <v-text-field type="password" v-model="memberPw" color="pink lighten-3" placeholder="비밀번호" outlined></v-text-field>
                        </v-col>
                    </v-row> 
                    <v-row justify="center">
                        <v-btn color="blue lighten-3" dark large width="95%" @click="login">로그인</v-btn>
                    </v-row>
                    <v-row>
                        <v-col style="font-size:11pt" class="rinkColor mt-2">
                            <router-link to="/findId"> 아이디 찾기 </router-link>
                            │
                            <router-link to="findPw"> 비밀번호 찾기 </router-link>
                        </v-col>
                    </v-row>
                    <v-row justify="center" class="mt-10">
                        <button class="socialBtn" @click="kakaoLogin"><v-img src="@/assets/button/kakao_login_medium_wide.png"></v-img></button>
                    </v-row>
                    <v-row justify="center" class="mt-5">
                        <button class="socialBtn btnBorder" @click="googleLogin"><v-img src="@/assets/button/google_login.png"></v-img></button>
                    </v-row>
                    <br/>
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
import { KakaoAuthUri } from '../OAuth/KakaoOAuth.js'
import { GoogleAuthUri } from '../OAuth/GoogleOAuth.js'

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
            try {
                const response = await axios.post('Member/login', {
                memberId : this.memberId,
                memberPw : this.memberPw
                })

                if (response.data) {
                    localStorage.setItem("token", response.data)
                    this.$store.state.token = localStorage.getItem("token")
                    this.$router.push('/')
                    console.log(localStorage)
                } else {
                    alert("아이디와 비밀번호를 확인해주세요.")
                }
            }catch(error){
                alert(error)
            }
        },
        kakaoLogin() {
            location.href=KakaoAuthUri
        },
        googleLogin() {
            location.href=GoogleAuthUri
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
.rinkColor >a {
    color:grey;
}
@media (min-width: 1800px) {
    .socialBtn {
        width:330px;
    }
}
</style>