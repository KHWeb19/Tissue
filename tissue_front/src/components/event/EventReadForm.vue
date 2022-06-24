<template>
    <div>
        <v-container><br><br><br>
            <v-row>
                <v-col>
                    <form>
                        <div>
                            <label>공연번호 확인</label>
                            <input type="text" :value="performance.performNo" readonly/>
                        </div>
                        <div>
                            <label>공연썸네일</label>
                            <img :src="require(`@/assets/thumbNail/${this.performance.performThumbnail}`)"/>
                        </div>
                        <div>
                            <label>제목</label>
                            <input type="text" :value="event.eventTitle" readonly/>
                        </div>
                        <div>
                            <label>카테고리</label>
                            <input type="text" :value="event.eventCategory" readonly/>
                        </div>
                        <div>
                            <label>시작일</label>
                            <input type="text" :value="event.eventStart" readonly/>
                        </div>
                        <div>
                            <label>종료일</label>
                            <input type="text" :value="event.eventEnd" readonly/>
                        </div>
                    </form>
                </v-col>
            </v-row>
            
            <br>

            <!-- <v-row>
                <v-btn color="blue lighten-3" dark @click="onSubmit" value="Upload">
                    등록
                </v-btn>
            </v-row> -->
        </v-container>
    </div>
</template>

<script>
export default {
    name: 'EventReadForm',
    props: {
        event: {
            type: Object,
            required: true
        },
        performance: {
            type: Object,
            required: true
        },
    },
    data() {
        return {

        }
    }
}
</script>