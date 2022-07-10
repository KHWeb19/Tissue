<template>
    <v-container>
        <v-app-bar app elevation="3">
            <v-toolbar-title class="ml-3">
                📌 이벤트 관리
            </v-toolbar-title>
        </v-app-bar>
        <v-container>
            <v-row class="ml-3 mt-10" style="font-size:18pt">
                전체 이벤트 수&nbsp;<span style="color:skyblue">{{ events.length }}</span> 개
            </v-row>
             <v-row justify="end">
                <v-col cols="5">
                    <v-text-field
                        v-model="keyword"
                        append-icon="search"
                        label="이벤트 검색"
                        single-line
                        color="pink lighten-3"
                    ></v-text-field>
                </v-col>
            </v-row>
           
                <v-data-table
                    :headers="headers"
                    :items="events"
                    hide-default-footer
                    :search="keyword"
                    :page.sync="page"
                    :items-per-page="itemsPerPage"
                    @page-count="pageCount = $event"
                    >
                    <template v-slot:[`item.eventTitle`]="{ item }">
                        <router-link style="color: black" :to="{ name: 'EventReadPage',
                                                params: { eventNo: item.eventNo } }">
                            {{ item.eventTitle }}
                            </router-link>
                    </template>
                </v-data-table>


                 <div class="text-center pt-10">
                    <v-pagination
                    v-model="page"
                    total-visible="5"
                    :length="pageCount"
                    color="pink lighten-3"
                    circle
                    ></v-pagination>
                </div>
                <div style="float: right">
                    <v-btn
                    rounded
                    color="blue lighten-3"
                    style="color: white"
                    to="/EventRegisterPage"
                    >이벤트 등록</v-btn
                    >
                </div>
        </v-container>
    </v-container>
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
            ],
            keyword:''
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

</style>