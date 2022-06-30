<template>
    <v-container>
        <div v-for="list in myLike" :key="list.memberNo">
            {{list.performance.performNo}}
        </div>
    </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
    name:'MyPageStar',
    props: {
        memberNo: {
            type:Number
        }
    },
    computed: {
        ...mapState(['myLike'])
    },
    mounted() {
        this.fetchMyLike(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyLike'])
    }
}
</script>

<style scoped>

</style>