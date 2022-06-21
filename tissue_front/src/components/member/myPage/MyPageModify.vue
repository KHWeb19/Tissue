<template>
    <v-container fluid>
        <div v-if="visible">
            <v-row justify="center">
                <v-col cols="3" class="label">
                    아이디
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field :value="memberInfo.memberId" disabled dense></v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    비밀번호
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field type="password" v-model="memberPw" color="pink lighten-3" outlined dense
                    :rules="pwRules"> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    비밀번호 재확인
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field type="password" v-model="memberPw2" color="pink lighten-3" outlined dense
                    :rules="pwRules2"> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    이름
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field :value="memberInfo.memberName" disabled dense> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    생일
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field v-model="memberBirth" type="date"  color="pink lighten-3" dense outlined> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    휴대폰
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field v-model="memberPhone" color="pink lighten-3" dense outlined> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    이메일
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-text-field v-model="memberEmail" color="pink lighten-3" dense outlined> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col cols="3" class="label">
                    주소
                </v-col>
                <v-divider vertical class="mr-12"/>
                <v-col cols="5">
                    <v-row>
                        <v-col cols="5">
                            <v-text-field style="zoom:0.85" @click="searchAddress()" 
                            v-model="addZipCode" id="zipcode" color="pink lighten-3" outlined placeholder="우편번호" dense>
                        </v-text-field>
                        </v-col>
                        <v-col cols="7">
                            <v-text-field dense color="pink lighten-3" v-model="memberAddress"></v-text-field>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="12">
                            <v-text-field v-model="addDetail" placeholder="상세주소" dense color="pink lighten-3"></v-text-field>
                        </v-col>
                    </v-row>
                </v-col>
            </v-row>
            <v-row justify="center" class="mt-15">
                <v-btn color="blue lighten-3" x-large dark depressed width="250">회원정보 수정</v-btn>
            </v-row>
        </div>
        <div v-else>
            <v-container>
            <v-row justify="center" class="mb-5" style="font-size:15pt">
                비밀번호를 다시 한번 입력해주세요.
            </v-row>
            <v-row justify="center">
                <v-col cols="5">
                <v-text-field type="password" color="pink lighten-3"
                v-model="checkPw"> </v-text-field>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-btn @click="checkMyPw" color="blue lighten-3" dark large depressed>next</v-btn>
            </v-row>
            </v-container>
        </div>
    </v-container>
</template>

<script>
import axios from 'axios'
export default {
    name:'MyPageModify',
    props: {
        memberInfo: { 
            type: Object,
            required: true
        }
    },
    data () {
        return {
            visible: false,
            checkPw:'',
            memberPw:'',
            memberPw2:'',
            memberBirth:'',
            memberPhone: '',
            memberEmail:'',
            memberAddress:'',
            addZipCode:'',
            addDetail:'',

             pwRules: [
                v => !!v || '비밀번호를 입력해주세요!',
                v =>  /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/.test(v) || '비밀번호는 영문 + 숫자로 구성되어야합니다.',
                v => !(v.length >= 20) || '비밀번호는 20자 이상 입력할 수 없습니다.'
            ],
            pwRules2: [
                v => !!v || '비밀번호 확인을 해주세요!',
                v => v === this.memberPw || '비밀번호가 일치하지 않습니다.'
            ],
        }
    },
    created () {
        this.memberBirth = this.memberInfo.memberBirth
        this.memberPhone = this.memberInfo.memberPhone
        this.memberEmail = this.memberInfo.memberEmail
        this.memberAddress = this.memberInfo.memberAddress
        this.addZipCode = this.memberInfo.addZipCode
        this.addDetail = this.memberInfo.addDetail
    },
    methods : {
        async checkMyPw(){
            try {
                const response = await axios.post('Member/checkPw', {
                memberId: this.memberInfo.memberId,
                memberPw : this.checkPw
                })
                if (response.data == true) {
                    this.visible=true
                } else {
                    alert("비밀번호를 확인해주세요.")
                    console.log(response.data)
                }
            }catch(error){
                alert(error)
            }
         },
    }
}
</script>

<style scoped>
.label{
    background: rgb(241, 241, 241);
    text-align: center;
}
</style>