<template>
    <v-container>
        <v-app-bar app elevation="3">
            <v-toolbar-title class="ml-3">
                📌 이벤트 관리
            </v-toolbar-title>
        </v-app-bar>
        <v-container><br><br><br>
            <v-row>
                <v-col>
                    <img :src="require(`@/assets/thumbNail/${this.event.performance.performThumbnail}`)"/>
                </v-col>
                <v-col>
                    <div>
                        <label>제목</label>
                        <v-text-field outlined color="pink lighten-3" type="text" :value="event.eventTitle" readonly></v-text-field>
                    </div>
                    <div>
                        <label>설명</label>
                        <v-text-field outlined color="pink lighten-3" type="text" :value="event.eventContent" readonly></v-text-field>
                    </div>
                    <div>
                        <label>경품</label>
                        <v-text-field outlined color="pink lighten-3" type="text" :value="event.eventGoods" readonly></v-text-field>
                    </div>
                    <div>
                        <label>카테고리</label>
                        <v-text-field outlined  color="pink lighten-3" type="text" :value="event.eventCategory" readonly></v-text-field>
                    </div>
                    <div>
                        <label>시작일</label>
                        <v-text-field  outlined color="pink lighten-3" type="text" :value="event.eventStart" readonly></v-text-field>
                    </div>
                    <div>
                        <label>종료일</label>
                        <v-text-field  outlined color="pink lighten-3" type="text" :value="event.eventEnd" readonly></v-text-field>
                    </div>
                    <div>
                        <label>당첨자 발표일</label>
                        <v-text-field  outlined color="pink lighten-3" type="text" :value="event.eventWinnerDate" readonly></v-text-field>
                    </div>
                    <div>
                        <label>당첨 인원</label>
                        <v-text-field  outlined color="pink lighten-3" type="text" :value="event.eventWinnerCnt" readonly></v-text-field>
                    </div>
                </v-col>
            </v-row>
            <br>
            <v-row justify="center" class="mt-7 mb-10">
                <v-btn class="mr-5" large color="blue lighten-3" dark router-link :to="{ name: 'EventModifyPage', params: { eventNo: event.eventNo } }">
                    수정
                </v-btn>
                <v-btn color="blue lighten-3" large dark @click="onDelete(event.eventNo)">
                    삭제
                </v-btn>
            </v-row>
        </v-container>
    </v-container>
</template>

<script>
import axios from 'axios'
export default {
    name: 'EventReadForm',
    props: {
        event: {
            type: Object,
            required: true
        },
    },
    data() {
        return {

        }
    },
    methods: {
        onDelete (eventNo) {
            axios.delete(`event/${eventNo}`)
                    .then(() => {
                        alert('이벤트를 삭제했습니다.')
                        this.$router.push({ name: 'EventListPage' })
                    })
        }
    },
}
</script>

<style scoped>
img {
   position: relative;
   height: 100%;
    width: 100%;
}
</style>