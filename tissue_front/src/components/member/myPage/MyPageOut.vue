<template>
    <v-container class="mt-10">
        <v-row justify="center">
            <div style="font-size:20pt">
                <v-icon large color="red">mdi-exclamation-thick</v-icon> 
                    아래 내용을 꼭 확인해 주세요
                <v-icon large color="red">mdi-exclamation-thick</v-icon> 
            </div>
        </v-row>
        <v-row justify="center" >
            <v-col cols="12" class="content">
            <div>
                1. 회원탈퇴 즉시 회원정보는 모두 <span class="point">삭제</span>되며, 재가입시에도 복원되지 않습니다. <p/>
                <div class="pl-7 pr-7 mb-7">
                     회원탈퇴 시 모든 마일리지와 쿠폰은 사용이 불가능합니다.<p/>
                     회원을 탈퇴하셔도 비회원으로 예매서비스는 이용이 가능합니다.
                </div>
                2. 탈퇴 후 정보보관 <p/>
                <div class="pl-7 pr-7">
                    전자상거래 등에서의 소비자보호에 관한 법률 제6조에 의거 성명, 주소 등 거래의 주체를 식별할 수 있는 정보에 한하여 서비스
                    이용에 관한 동의를 철회한 경우에도 이를 보존할 수 있으며, 동법 시행령 제6조에 의거 다음과 같이 거래 기록을 보관합니다.
                    <p/>
                    표시, 광고에 관한 기록 : 6개월 <p/>
                    계약 또는 청약철회 등에 관한 기록 : 5년 <p/>
                    대금결제 및 재화등의 공급에 관한 기록 : 5년 <p/>
                    소비자의 불만 또는 분쟁처리에 관한 기록 : 3년 <p/>
                </div>
            </div>
            </v-col>
        </v-row>
        <v-row class="mt-15 mb-5" justify="center">
            정확한 본인확인을 위해 비밀번호를 확인해주세요.
        </v-row>
        <v-row justify="center">
            <v-col cols="4">
                <v-text-field type="password" color="pink lighten-3" outlined dense
                v-model="checkPw"> </v-text-field>
            </v-col>
        </v-row>
        <v-row justify="center" no-gutters>
            <v-btn @click="signOut(memberNo)" color="blue lighten-3" dark 
            large depressed width="150">회원 탈퇴</v-btn>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
export default {
    name:'MyPageOut',
    props:{
        memberNo: {
            type:Number,
            required: true
        }
    },
    data() {
        return {
            checkPw:''
        }
    },
    methods: {
        signOut(memberNo){
            let result = confirm ('정말 탈퇴하시겠습니까?')
            const {checkPw} = this

            if(result) {
                axios.delete(`Member/remove/${memberNo}`, { memberNo, params:{checkPw: checkPw} })
                .then((res) => {
                    if(res.data == true){
                        alert('탈퇴되셨습니다.')
                        localStorage.removeItem('token')
                        this.$router.replace("/")
                    } else {
                        alert('비밀번호가 일치하지 않습니다.')
                        console.log(checkPw)
                    }
                })
                .catch(() => {
                    console.log(memberNo)
                    alert('회원 탈퇴 실패')
                })
            }
        },
    }
}
</script>

<style scoped>
.content{
    border:1px solid rgb(189, 189, 189);
    background-color: rgb(241, 241, 241);
    margin-top:50px;
    padding:30px;
    max-width: 700px;
}
.point{
    color:rgb(255, 87, 87)
}
</style>