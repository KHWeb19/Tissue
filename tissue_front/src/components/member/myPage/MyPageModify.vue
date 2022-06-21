<template>
    <div>
        <v-row v-for="item in items"
                    :key="item.title"
                    link>
            <v-col>
                {{item.title}}
            </v-col>
            <v-col>
                {{item.value}}
            </v-col>
        </v-row>
    </div>
</template>

<script>
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
            items: [
                {title:'아이디', value: this.memberInfo.memberId},
                {title:'비밀번호', value: this.memberInfo.memberPw},
                {title:'이름', value: this.memberInfo.memberName},
                {title:'생년월일', value: this.memberInfo.memberBirth},
                {title:'휴대폰', value: this.memberInfo.memberPhone},
                {title:'이메일', value: this.memberInfo.memberEmail},
                {title:'주소', value: this.memberInfo.memberAddress},
              
            ]
        }
    }
}
</script>