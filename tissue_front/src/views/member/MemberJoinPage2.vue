<template>
    <div class="background">
        <v-container>
            <v-row justify="center" class="logo"><div style="color:skyblue">T</div><div style="color:pink">issue</div></v-row>
            <member-join-form @submit="onSubmit" />
            <join-footer class="mt-16 mb-10 pt-5"/>
        </v-container>
    </div>
</template>

<script>
import MemberJoinForm from '@/components/member/MemberJoinForm.vue'
import axios from 'axios';
import JoinFooter from '@/components/Layout/JoinFooter.vue';

export default {
  components: { MemberJoinForm, JoinFooter },
  name: 'MemberJoinPage2',
  methods: {
        onSubmit (payload) {
            const { memberId, memberPw, memberName, memberBirth,
            memberEmail, memberPhone, memberAddress, addZipCode, addDetail } = payload
            axios.post('Member/register', { memberId, memberPw, memberName, memberBirth,
             memberEmail, memberPhone, memberAddress, addZipCode, addDetail})
            .then(()=> {
                alert ('환영합니다 !')
                
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