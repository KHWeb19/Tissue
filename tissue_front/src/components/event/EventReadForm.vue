<template>
    <div>
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
                </v-col>
            </v-row>
            <br>
            <div>
                <v-btn class="mr-5" color="blue lighten-3" dark router-link :to="{ name: 'EventModifyPage', params: { eventNo: event.eventNo } }">
                    수정
                </v-btn>
                <v-btn color="blue lighten-3" dark @click="onDelete(event.eventNo)">
                    삭제
                </v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'EventReadForm',
    props: {
        event: {
            type: Object,
            required: true
        }
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
                    .catch(() => {
                        alert('삭제 실패!')
                    })
        }
    },
}
</script>

<style scoped>
img {
   position: relative;
    max-height: 500px;
    max-width: 500px;
}
</style>