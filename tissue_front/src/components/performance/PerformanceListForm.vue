<template>
    <v-container>
        <v-app-bar app elevation="3">
            <v-toolbar-title class="ml-3">
                📌 공연 관리
            </v-toolbar-title>
        </v-app-bar>
        <v-container>
            <v-row class="ml-3 mt-10" style="font-size:18pt">
                전체 공연 수&nbsp;<span style="color:skyblue">{{ performances.length }}</span> 개
            </v-row>
             <v-row justify="end">
                <v-col cols="5">
                    <v-text-field
                        v-model="keyword"
                        append-icon="search"
                        label="공연 검색"
                        single-line
                        color="pink lighten-3"
                    ></v-text-field>
                </v-col>
            </v-row>

            <v-data-table
                :headers="headers"
                :items="performances"
                hide-default-footer
                :search="keyword"
                :page.sync="page"
                :items-per-page="itemsPerPage"
                @page-count="pageCount = $event"
                >
                <template v-slot:[`item.performName`]="{ item }">
                    <router-link style="color: black" :to="{ name: 'PerformanceReadPage',
                                            params: { performNo: item.performNo } }">
                        {{ item.performName }}
                        </router-link>
                </template>
                <template v-slot:[`item.delete`]="{item}">
                    <v-icon small @click="onDelete(item.performNo, item.performThumbnail, item.performDetailImg1,
                        item.performDetailImg2, item.performDetailImg3, item.performDetailImg4, item.performDetailImg5)"> delete </v-icon>
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
                to="PerformanceRegisterPage"
                >공연 등록</v-btn
                >
            </div>
          
        </v-container>
    </v-container>
</template>

<script>
import axios from 'axios'

export default {
    name: 'PerformanceListForm',
    props: {
        performances: {
            type: Array
        },
    },
    data() {
        return {
            page: 1,
            pageCount: 0,
            itemsPerPage: 10,
            headers: [
                { text: '번호', value: 'performNo', width: '8%' },
                { text: '카테고리', value: 'performCategory', width: '10%' },
                { text: '공연명', value: 'performName', width: '30%' },
                { text: '지역', value: 'performArea', width: '20%' },
                { text: '시작일', value: 'performStart', width: '10%' },
                { text: '종료일', value: 'performEnd', width: '10%' },
                {text: 'Action', value: 'delete', width:'8%'}
            ],
            keyword:''
        }
    },
    methods: {
        onDelete(performNo, performThumbnail, performDetailImg1, performDetailImg2,
                 performDetailImg3,performDetailImg4, performDetailImg5) {
            
            let result = confirm ('공연을 삭제하시겠습니까?')
            if(result) {
                axios.delete(`performance/${performNo}`, {
                    performThumbnail,
                    performDetailImg1,
                    performDetailImg2,
                    performDetailImg3,
                    performDetailImg4,
                    performDetailImg5,
             })
            .then(() => {
                alert("공연을 삭제하였습니다.");
                history.go()
            })
            .catch((err) => {
                alert("삭제 실패!", err);
            });
            }
        },
    }
}

</script>

<style scoped>
.background {
    background-color: rgb(241, 241, 241);
}
</style>>