<template>
    <v-container>
        <v-data-table
        :headers="headers"
        :items="myQna"
        >
        </v-data-table>
    </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
    name:'MyPageQnA',
    props: {
        memberNo : {
            type: Number
        }
    },
    data() {
        return {
            headers: [
                {text:'답변', value:'qnaCheck', width:'60'},
                {text:'No', value:'qnaNo', width:'40'},
                {text:'카테고리', value:'qnaCategory', width:'100'},
                {text:'제목', value:'qnaTitle', width:'250'},
                {text:'문의 날짜', value:'qnaDate',width:'130'}

            ]
        }
    },
    computed: {
        ...mapState(['myQna'])
    },
    mounted() {
        this.fetchMyQna(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyQna'])
    }


}
</script>

<style scoped>

</style>