<template>
  <div class="background">
    <v-container>
      <v-row justify="center" class="logo"
        ><div style="color: skyblue">T</div>
        <div style="color: pink">issue</div></v-row
      >
      <v-layout column class="mx-auto" justify-center>
        <v-form ref="form" name="loginForm" @submit.prevent="onSubmit">
          <v-layout>
            <v-flex>
              <v-text-field
                v-model="memberId"
                style="width: 350px"
                label="아이디"
                color="pink lighten-3"
                required
                outlined
                :rules="idRules"
              ></v-text-field>
            </v-flex>
            <v-flex>
              <v-btn
                type="button"
                style="zoom: 1.16; margin-top: 3px"
                @click="checkId()"
                :color="
                  this.checkIdCondition == true
                    ? 'pink lighten-4'
                    : 'blue lighten-3'
                "
                dark
                depressed
                >check</v-btn
              >
            </v-flex>
          </v-layout>
          <v-text-field
            type="password"
            v-model="memberPw"
            label="비밀번호"
            color="pink lighten-3"
            outlined
            append-icon="mdi-lock"
            :rules="pwRules"
          ></v-text-field>
          <v-text-field
            type="password"
            v-model="ckPw"
            label="비밀번호 재확인"
            color="pink lighten-3"
            outlined
            append-icon="mdi-lock-check"
            :rules="pwRules2"
          ></v-text-field>
          <v-text-field
            v-model="memberName"
            label="이름"
            color="pink lighten-3"
            outlined
            :rules="nameRules"
          ></v-text-field>
          <v-text-field
            type="date"
            v-model="memberBirth"
            label="생일"
            color="pink lighten-3"
            outlined
          ></v-text-field>
          <v-layout>
            <v-flex>
              <v-text-field
                v-model="memberPhone"
                style="width: 350px"
                label="휴대폰"
                placeholder=" ' - '를 제외하고 입력해주세요."
                color="pink lighten-3"
                outlined
                :rules="phRules"
              ></v-text-field>
            </v-flex>
            <v-flex>
              <v-btn
                type="button"
                style="zoom: 1.16; margin-top: 3px"
                @click="checkPhone()"
                color="blue lighten-3"
                dark
                depressed
                >Check</v-btn
              >
            </v-flex>
          </v-layout>
          <v-layout justify-center v-if="this.sendAuth == true">
            <v-flex class="ml-8">
              <v-text-field
                v-model="checkNum"
                style="width: 300px"
                label="인증번호"
                color="pink lighten-3"
                outlined
                dense
              ></v-text-field>
            </v-flex>
            <v-flex>
              <v-btn
                type="button"
                style="margin-top: 3px"
                @click="checkAuthNum()"
                :color="
                  this.checkPhoneCondition == true
                    ? 'pink lighten-4'
                    : 'blue lighten-3'
                "
                dark
                rounded
                depressed
                ><v-icon>mdi-check</v-icon></v-btn
              >
            </v-flex>
          </v-layout>
          <div class="ml-2 mb-4 mt-2 mr-5" style="color: grey">주소</div>
          <v-layout>
            <v-text-field
              @click="searchAddress()"
              style="width: 1px; zoom: 0.85"
              v-model="addZipCode"
              id="zipcode"
              color="pink lighten-3"
              outlined
              placeholder="우편번호"
            ></v-text-field>
            <v-text-field
              @click="searchAddress()"
              v-model="memberAddress"
              id="memberAddress"
              color="pink lighten-3"
            ></v-text-field>
          </v-layout>
          <v-text-field
            name="address_detail"
            v-model="addDetail"
            color="pink lighten-3"
            placeholder="상세주소"
            :rules="addRules"
          ></v-text-field>
          <v-text-field
            class="mt-3"
            v-model="memberEmail"
            label="이메일"
            color="pink lighten-3"
            outlined
            :rules="emailRules"
          ></v-text-field>
          <v-row justify="center"
            ><v-btn class="joinBtn" type="submit" color="blue lighten-3" dark
              >JOIN</v-btn
            ></v-row
          >
        </v-form>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MemberJoinForm",
  data() {
    return {
      memberId: "",
      memberPw: "",
      memberName: "",
      memberPhone: "",
      memberEmail: "",
      memberAddress: "",
      ckPw: "",
      addZipCode: "",
      addDetail: "",
      memberBirth: "",

      authNum: "",
      checkNum: "",
      phonePass: false,
      idPass: false,
      checkIdCondition: false,
      checkPhoneCondition: false,
      sendAuth: false,

      idRules: [
        (v) => !!v || "아이디를 입력해주세요!",
        (v) =>
          /^[a-zA-Z0-9]*$/.test(v) || "아이디는 영문 + 숫자만 입력 가능합니다.",
        (v) => !(v.length >= 15) || "아이디는 15자 이상 입력할 수 없습니다.",
      ],
      pwRules: [
        (v) => !!v || "비밀번호를 입력해주세요!",
        (v) =>
          /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/.test(v) ||
          "비밀번호는 영문 + 숫자로 구성되어야합니다.",
        (v) => !(v.length >= 20) || "비밀번호는 20자 이상 입력할 수 없습니다.",
      ],
      pwRules2: [
        (v) => !!v || "비밀번호 확인을 해주세요!",
        (v) => v === this.memberPw || "비밀번호가 일치하지 않습니다.",
      ],
      nameRules: [
        (v) => !!v || "이름을 입력해주세요",
        (v) => !(v.length >= 10) || "이름은 10자 이상 입력할 수 없습니다.",
      ],
      phRules: [
        (v) => !!v || "휴대폰을 입력해주세요",
        (v) => /^[0-9]*$/.test(v) || "숫자만 입력해주세요",
      ],
      addRules: [(v) => !!v || "주소를 입력해주세요"],
      emailRules: [(v) => !!v || "이메일을 입력해주세요"],
    };
  },
  methods: {
    onSubmit() {
      const validate = this.$refs.form.validate();
      const {
        memberId,
        memberPw,
        memberName,
        memberPhone,
        memberEmail,
        memberAddress,
        addZipCode,
        addDetail,
        memberBirth,
      } = this;
      if (this.idPass == false || this.phonePass == false) {
        alert("아이디와 핸드폰 검사를 진행해주세요.");
      } else if (validate) {
        this.$emit("submit", {
          memberId,
          memberPw,
          memberName,
          memberPhone,
          memberEmail,
          memberAddress,
          addZipCode,
          addDetail,
          memberBirth,
        });
      } else {
        alert("필수항목을 모두 작성해주세요");
      }
    },
    checkPhone() {
      const { memberPhone } = this;
      axios
        .get(`http://localhost:7777/phone/check/${memberPhone}`)
        .then((res) => {
          console.log(memberPhone);
          alert("인증번호가 전송되었습니다.");
          this.authNum = res.data;
          this.sendAuth = true;
        })
        .catch(() => {
          console.log("전송 실패!");
        });
    },
    checkId() {
      const { memberId } = this;
      axios
        .get(`http://localhost:7777/Member/checkId/${memberId}`)
        .then((res) => {
          this.temp = res.data;
          if (res.data) {
            alert("사용 가능한 아이디 입니다.");
            this.idPass = true;
            this.checkIdCondition = true;
          } else {
            alert("중복된 아이디 입니다.");
            this.idPass = false;
          }
        });
    },
    checkAuthNum() {
      if (this.checkNum == this.authNum) {
        this.phonePass = true;
        this.checkPhoneCondition = true;
        alert("인증되셨습니다.");
      } else {
        alert("인증번호가 올바르지 않습니다.");
      }
    },
    searchAddress() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          this.addZipCode = data.zonecode;
          this.memberAddress = data.address;
          document.querySelector("input[name=address_detail]").focus();
        },
      }).open();
    },
  },
};
</script>

<style scoped>
.background {
  background-color: rgb(241, 241, 241);
}
.logo {
  font-size: 50pt;
  margin-top: 5%;
  margin-bottom: 3%;
  font-family: "Pacifico", cursive;
}
.mx-auto {
  position: relative;
  margin-top: 50px;
  width: 40%;
}
.joinBtn {
  margin-top: 9%;
  margin-bottom: 10%;
  width: 95%;
  zoom: 1.5;
}
</style>
>
