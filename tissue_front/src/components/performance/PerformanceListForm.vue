<template>
    <v-container class="board-list">
        <v-app-bar app elevation="3">
            <v-toolbar-title class="ml-3">
                📌 공연 관리
            </v-toolbar-title>
        </v-app-bar>
        <v-container>
            <v-row class="ml-3 mt-10" style="font-size:18pt">
                <v-col>
                    전체 공연 수&nbsp;<span style="color:skyblue">{{ performances.length }}</span> 개
                </v-col>
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
            <v-row class="btnReg"> 
                <v-btn color="blue lighten-3" dark :to="{ name: 'PerformanceRegisterPage' }">공연 등록</v-btn>
            </v-row>
            <br><br><br>
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
            </v-data-table>

            <v-pagination
                v-model="page"
                :length="pageCount"
                total-visible="5"
                color="pink lighten-3"
                circle>
            </v-pagination><br><br>
        </v-container>

    </v-container>
</template>

<script>

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
            ],
            keyword:''
        }
    },
}

</script>

<style scoped>
.background {
    background-color: rgb(241, 241, 241);
}
.btnReg {
    float: right;
}
</style>>