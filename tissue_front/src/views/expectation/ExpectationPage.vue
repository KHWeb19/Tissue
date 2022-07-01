<template>
    <v-container>
        <v-row>
            <v-col>
                <h1>{{ event.eventCategory }}</h1>
            </v-col>
        </v-row>
        <h2>{{ event.eventTitle }}</h2>
        <v-img :src="require(`@/assets/thumbNail/${event.performance.performThumbnail}`)" class="img"></v-img>

        <!-- 댓글 --> 
        <expectation-read-form :eventNo="eventNo" :memberInfo="memberInfo"/>
    </v-container>
</template>

<script>
import ExpectationReadForm from '@/components/expectation/ExpectationReadForm.vue'
import { mapActions, mapState } from 'vuex'
export default {
    name: 'ExpectationPage',
    props: {
        eventNo: {

        }
    },
    data() {
        return {
            token: localStorage.getItem('token'),
        }
    },
    components: {
        ExpectationReadForm
    },
    computed: {
        ...mapState(['event', 'memberInfo'])
    },
    methods: {
        ...mapActions(['fetchEvent', 'fetchMemberInfo'])
    },
    created() {
        this.fetchEvent(this.eventNo)
        this.fetchMemberInfo(this.token)
    }
}
</script>

<style scoped>
h1 {
    text-decoration: underline; 
    text-underline-position: under;
    text-decoration-thickness: 8px;
    text-decoration-color: #F48FB1;
}
.img {
    width: 800px;
    height: 100%;
}
</style>