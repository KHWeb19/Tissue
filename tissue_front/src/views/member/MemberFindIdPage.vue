<template>
    <div class="background">
        <v-container>
            <div class="mt-7 topBtn">
                    <v-btn class="mr-2" depressed color="blue lighten-3" dark to="/login">로그인</v-btn>
                    <v-btn depressed color="blue lighten-3" dark to="/join">회원가입</v-btn>
            </div>
            <v-row justify="center" class="mt-3 mb-3">
                <v-col cols="9">
                    <div class="logo">
                        <router-link to="/"><div style="color:skyblue; float:left;">T</div><div style="color:pink; float:left;">issue</div></router-link>
                    </div>
                    <div class="findBtn">
                        <div class="findIdBtn mt-10 ml-5">
                            <router-link to="/findId"> 아이디 찾기</router-link>
                        </div>
                        <div class="findPwBtn mt-10 ml-5">
                            <router-link to="/findPw"> 비밀번호 찾기 </router-link>
                        </div>
                    </div>
                </v-col>
            </v-row>
            <div class="box pl-5 pr-5 pt-10">
                <v-row justify="center" class="mt-16" >
                    <v-col cols="5">
                        <member-find-id/>
                    </v-col>
                </v-row>
            </div>
        </v-container>
    </div>
</template>

<script>
import MemberFindId from '@/components/member/MemberFindId.vue'

export default {
  components: { MemberFindId },
    name:'MemberFindIdPage',

}
</script>

<style scoped>
template{
    height:100%
}
.background {
    background-color: rgb(241, 241, 241);
    height: 100%;
}
.logo {
    font-size: 50pt;
    margin-top:5%;
    font-family: 'Pacifico', cursive;
}
.box {
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(197, 197, 197);
    width:82%;
    margin: auto;
    padding-bottom: 120px;
}
.findIdBtn {
    float:left;
    font-size:18pt;
}
.findPwBtn {
    float:left;
    font-size:18pt;
}
.findIdBtn >a {
    color: black;
}
.findPwBtn >a  {
    color:rgb(168, 167, 167);
}
.topBtn{
    position: absolute;
    left:68%;
}

@media (min-width: 1800px) {
    .topBtn{
    position: absolute;
    left:76%;
    zoom:1.2;
    }
    .background {
    background-color: rgb(241, 241, 241);
    height: 88vh;
    }
}
@media (max-width: 580px) {
    .topBtn{
    position: absolute;
    left:60%;
    zoom:0.8;
    }
}
</style>