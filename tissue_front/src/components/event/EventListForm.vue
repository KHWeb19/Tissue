<template>
    <div>
        <v-container>
            <h1 align="center">이벤트</h1>
                <v-row class="btnReg"> 
                    <v-btn color="blue lighten-3" dark :to="{ name: 'EventRegisterPage' }">등록</v-btn>
                </v-row>
                <br><br>
                <v-data-table
                    :headers="headers"
                    :items="events"
                    hide-default-footer
                    :page.sync="page"
                    :items-per-page="itemsPerPage"
                    @page-count="pageCount = $event"
                    >
                     <!-- <template v-slot:[`item.noticeNecessary`]="{ item }">
                      <v-icon style="color: skyblue"
                        v-if="item.noticeNecessary === '필독'"
                      >
                        mdi-check-circle
                      </v-icon>
                  </template> -->
                    <template v-slot:[`item.eventTitle`]="{ item }">
                        <router-link style="color: black" :to="{ name: 'EventReadPage',
                                                params: { eventNo: item.eventNo } }">
                            {{ item.eventTitle }}
                            </router-link>
                    </template>
                </v-data-table>

                <v-pagination
                    v-model="page"
                    :length="pageCount"
                    total-visible="5"
                    color="pink lighten-3"
                    circle>
                </v-pagination><br><br>
        </v-container>
    </div>
</template>

<script>
export default {
    name: 'EventListForm',
    data() {
        return {
            page: 1,
            pageCount: 0,
            itemsPerPage: 10,
            headers: [
                { text: '번호', value: 'eventNo', width: '10%' },
                { text: '제목', value: 'eventTitle', width: '50%' },
                { text: '구분', value: 'eventCategory', width: '10%' },
                { text: '시작일', value: 'eventStart', width: '10%' },
                { text: '종료일', value: 'eventEnd', width: '10%' },
                { text: '등록일', value: 'eventRegDate', width: '10%' }
            ],
        }
    },
    props: {
      events: {
            type: Array
        }
    }
}
</script>

<style scoped>
.btnReg {
    float: right;
}
</style>