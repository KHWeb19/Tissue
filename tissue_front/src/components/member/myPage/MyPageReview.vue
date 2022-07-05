<template>
    <v-container>
        <span class="ml-3" style="font-size:20pt;"> My 후기 </span>
        <p/>
        <hr color="#90CAF9" width="95%">
        <v-data-table
        class="mr-10 mb-8"
        :headers="headers"
        :items="myReview"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        @page-count="pageCount = $event"
         hide-default-footer
        >
        <template v-slot:[`item.performThumbnail`] ="{item}">
                <img class="imgwidth"
                :src="require(`@/assets/thumbNail/${item.performThumbnail}`)"
               >
        </template>
        <template v-slot:[`item.reviewRating`] ="{item}">
            <v-rating
                :value="item.reviewRating"
                background-color="orange lighten-3"
                color="orange"
                x-small
                hover
                dense
                readonly
            ></v-rating>
        </template>
        <template v-slot:[`item.performName`] ="{item}">
            <span class="pName"> {{item.performName}} </span>
        </template>
        </v-data-table>
        <v-pagination
            class="mt-10"
            v-model="page"
            :length="pageCount"
            total-visible="5"
            color="pink lighten-3"
            circle>
        </v-pagination>
    </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
    name:'MyPageReview',
    props: {
        memberNo :{
            type:Number
        }
    },
    data() {
        return {
            page: 1,
            pageCount: 0,
            itemsPerPage: 7,
            headers: [
                {text:'썸네일', value:'performThumbnail', width:'10%'},
                {text:'공연명', value:'performName', width:'170'},
                {text:'후기', value:'reviewContent', width:'250'},
                {text:'별점', value:'reviewRating',width:'100'},
                {text:'등록날짜', value:'reviewRegDate',width:'150'}

            ]
        }
    },
    computed: {
        ...mapState(['myReview'])
    },
    mounted() {
        this.fetchMyReview(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyReview'])
    }
}
</script>

<style scoped>
.imgwidth{
    width: 70px;
    margin-top:5px;
}
.pName{
    font-family: 'Nanum Gothic', sans-serif !important;
    font-size: 10pt;
}
</style>