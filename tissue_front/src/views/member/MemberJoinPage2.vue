<template>
    <div class="background">
        <v-container>
            <v-row justify="center" class="logo"><div style="color:skyblue">T</div><div style="color:pink">issue</div></v-row>
            <member-join-form @submit="onSubmit" class="mb-12"/>
        </v-container>
    </div>
</template>

<script>
import MemberJoinForm from '@/components/member/MemberJoinForm.vue'
import axios from 'axios';

export default {
  components: { MemberJoinForm},
  name: 'MemberJoinPage2',
  methods: {
        onSubmit (payload) {
            const { memberId, memberPw, memberName, memberBirth,
            memberEmail, memberPhone, memberAddress, addZipCode, addDetail } = payload
            axios.post('Member/register', { memberId, memberPw, memberName, memberBirth,
             memberEmail, memberPhone, memberAddress, addZipCode, addDetail})
            .then(()=> {
                alert ('환영합니다 !')
                this.$router.push('/login')
            })
            .catch(res => {
                alert(res.response.data.message)
            })
        }
  }
}
</script>

<style scoped>
.background {
    background-color: rgb(241, 241, 241);
}
.logo {
    font-size: 50pt;
    margin-top:5%;
    font-family: 'Pacifico', cursive;
}
</style>