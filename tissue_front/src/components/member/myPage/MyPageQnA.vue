<template>
    <v-container>
        <span class="ml-3" style="font-size:20pt;"> My Qna </span>
        <p/>
        <hr color="#90CAF9" width="95%">
        <v-data-table
        class="qnaMain mr-10"
        :headers="headers"
        :items="myQna"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        @page-count="pageCount = $event"
         hide-default-footer
        >
        <template v-slot:[`item.qnaCheck`]="{ item }">
              <v-chip color="blue lighten-3"
                outlined
                v-if="item.qnaCheck === false">
                  답변대기중
              </v-chip>
              <v-chip v-else
                color="pink lighten-3"
                outlined>
                  답변완료
              </v-chip>
            </template>
             <template v-slot:[`item.qnaTitle`]="{ item }">
                <router-link 
                    style="color:black"
                    :to="{ name: 'QnaReadPage', params: { qnaNo: item.qnaNo } }">
                    {{ item.qnaTitle }}
                </router-link>
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
    name:'MyPageQnA',
    props: {
        memberNo : {
            type: Number
        }
    },
    data() {
        return {
            page: 1,
      pageCount: 0,
      itemsPerPage: 7,
            headers: [
                {text:'답변', value:'qnaCheck', width:'10%'},
                {text:'구분', value:'qnaCategory', width:'100'},
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
.title{
    font-size: 25pt;
}
</style>