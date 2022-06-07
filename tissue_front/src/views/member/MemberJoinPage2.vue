<template>
    <div>
      <member-join-form @submit="onSubmit" />
    </div>
</template>

<script>
import MemberJoinForm from '@/components/member/MemberJoinForm.vue'
import axios from 'axios';
export default {
  components: { MemberJoinForm },
  name: 'MemberJoinPage2',
  methods: {
        onSubmit (payload) {
            const { memberId, memberPw, memberName, memberBirth,
            memberEmail, memberPhone, memberAddress, addZipCode, addDetail } = payload
            axios.post('http://localhost:7777/Member/register', { memberId, memberPw, memberName, memberBirth,
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