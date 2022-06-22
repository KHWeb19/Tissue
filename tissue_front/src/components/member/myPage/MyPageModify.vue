<template>
    <v-container fluid>
        <div v-if="visible">
            <v-form ref="form">
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
                        <v-col cols="5" v-if="!able">
                            <v-text-field v-model="memberPhone" color="pink lighten-3" dense outlined :rules="phRules" disabled style="float:left" class="mr-2"> </v-text-field>
                            <v-btn color="blue lighten-3" dark @click="ableChange" depressed>변경</v-btn>
                        </v-col>
                        <v-col cols="5" v-else>
                            <v-row>
                                <v-col cols="8">
                                    <v-text-field v-model="memberPhone" color="pink lighten-3" dense outlined :rules="phRules"> </v-text-field>
                                </v-col>
                                <v-col cols="2">
                                    <v-btn color="blue lighten-3" dark @click="checkPhone" depressed>인증번호</v-btn>
                                </v-col>
                            </v-row>
                            <v-row v-if="sendAuth" justify="center">
                                <v-col cols="6">
                                    <v-text-field v-model="checkNum" color="pink lighten-3" dense outlined placeholder="인증번호" style="float:left" class="mr-2"></v-text-field>
                                </v-col>
                                <v-col cols="2">
                                    <v-btn :color="this.checkPhoneCondition == true ? 'pink lighten-4' : 'blue lighten-3'" dark @click="checkAuthNum" depressed>확인</v-btn>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="3" class="label">
                            이메일
                        </v-col>
                        <v-divider vertical class="mr-12"/>
                        <v-col cols="5">
                            <v-text-field v-model="memberEmail" color="pink lighten-3" dense outlined :rules="emailRules"> </v-text-field>
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
                                    v-model="addZipCode" color="pink lighten-3" outlined placeholder="우편번호" dense>
                                </v-text-field>
                                </v-col>
                                <v-col cols="7">
                                    <v-text-field @click="searchAddress()" dense color="pink lighten-3" v-model="memberAddress" :rules="addRules"></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field name=address_detail v-model="addDetail" placeholder="상세주소" dense color="pink lighten-3"></v-text-field>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row justify="center" class="mt-15">
                        <v-btn color="blue lighten-3" x-large dark depressed width="250" @click='modify'>회원정보 수정</v-btn>
                    </v-row>
                </v-form>
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
            able:false,
            authNum:'',
            sendAuth:false,
            checkNum:'',
            checkPhoneCondition: false,
            phonePass:true,

             pwRules: [
                v => !!v || '비밀번호를 입력해주세요!',
                v =>  /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/.test(v) || '비밀번호는 영문 + 숫자로 구성되어야합니다.',
                v => !(v.length >= 20) || '비밀번호는 20자 이상 입력할 수 없습니다.'
            ],
            pwRules2: [
                v => !!v || '비밀번호 확인을 해주세요!',
                v => v === this.memberPw || '비밀번호가 일치하지 않습니다.'
            ],
            emailRules: [
                v => !!v || '이메일을 입력해주세요',
                v => /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(v) || '이메일 형식으로 입력해주세요'
            ],
            phRules: [
                v => !!v || '휴대폰을 입력해주세요',
                v => /^[0-9]*$/.test(v) || '숫자만 입력해주세요',
            ],
            addRules: [
                v => !!v || '주소를 입력해주세요',
            ],

            token: localStorage.getItem('token'),
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
        searchAddress() {
            new window.daum.Postcode({
                oncomplete: (data) => {
                    this.addZipCode = data.zonecode
                    this.memberAddress = data.address
                    document.querySelector("input[name=address_detail]").focus() 
                }
            }).open()
        },
        async modify() {
            try {
                const validate = this.$refs.form.validate(); 
                if(this.phonePass == false) {
                    alert("휴대폰 인증이 필요합니다.")
                } else if(validate) {
                    const response = await axios.put('Member/modify', {
                        memberId: this.memberInfo.memberId,
                        memberName: this.memberInfo.memberName,
                        memberPw: this.memberPw,
                        memberBirth: this.memberBirth,
                        memberPhone: this.memberPhone,
                        memberEmail: this.memberEmail,
                        addZipCode: this.addZipCode,
                        memberAddress: this.memberAddress,
                        addDetail: this.addDetail })

                    if(response.data){
                        alert("회원정보가 수정되셨습니다.")
                        history.go(0)
                    }
                } else {alert ("필수항목을 모두 작성해주세요.")}
            }catch(error) {
                alert(error)
            }
        },
        ableChange() {
            this.able = true
        },
        checkPhone() {
            const {memberPhone} = this
            axios.get(`phone/check/${memberPhone}`)
             .then((res) => {
                 console.log(memberPhone)
                alert("인증번호가 전송되었습니다.");
                this.authNum = res.data
                this.sendAuth = true
                this.phonePass = false
             })
             .catch(() => {
                 console.log("전송 실패!")
             })
        },
        checkAuthNum() {
            if(this.checkNum == this.authNum) {
                this.phonePass = true
                this.checkPhoneCondition = true
                alert("인증되셨습니다.")
            }else {
                alert("인증번호가 올바르지 않습니다.")
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