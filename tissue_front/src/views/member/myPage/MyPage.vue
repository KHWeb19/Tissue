<template>
    <v-container>
        <my-page-navi :memberInfo="memberInfo" :couponsLength="couponsLength"/> 
    </v-container>
</template>
<script>
import MyPageNavi from '@/components/member/myPage/MyPageNavi.vue'
import { mapActions, mapState } from 'vuex'

export default {
  components: { MyPageNavi },
    name:'MyPageView',
    data() {
        return {
             token: localStorage.getItem('token'),
        }
    },
    computed: {
        ...mapState(['memberInfo']),
        couponsLength : {
            get() {
                return this.memberInfo.coupons.length
            }
        }
    },
    created () {
        this.fetchMemberInfo(this.token)
    },
    methods: {
        ...mapActions(['fetchMemberInfo']),
    }
}
</script>

<style scoped>


</style>