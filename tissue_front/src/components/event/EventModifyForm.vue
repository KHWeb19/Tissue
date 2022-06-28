<template>
    <div>
        <form @submit.prevent="onSubmit">
            <v-container><br><br><br>
                <v-row>
                    <v-col>
                        <img :src="require(`@/assets/thumbNail/${this.event.performance.performThumbnail}`)"/>
                    </v-col>
                    <v-col>
                        <div>
                            <label>제목</label>
                            <v-text-field outlined color="pink lighten-3" type="text" v-model="eventTitle"></v-text-field>
                        </div>
                        <div>
                            <label>카테고리</label>
                            <v-select v-model="eventCategory" color="pink lighten-3"
                                        :items="list" item-text="category" item-value="category"></v-select>
                        </div>
                        <div>
                            <label>시작일</label>
                            <v-text-field  outlined color="pink lighten-3" type="Date" v-model="eventStart"></v-text-field>
                        </div>
                        <div>
                            <label>종료일</label>
                            <v-text-field  outlined color="pink lighten-3" type="Date" v-model="eventEnd"></v-text-field>
                        </div>
                    </v-col>
                </v-row>
                
                <br>

                <div>
                    <v-btn color="mr-3 blue lighten-3" dark type="submit">수정완료</v-btn>
                    <v-btn color="blue lighten-3" dark router-link :to="{ name: 'EventReadPage',
                                        params: { eventNo: event.eventNo.toString() } }">
                        취소
                    </v-btn>
                </div>
            </v-container>
        </form>
    </div>
</template>

<script>
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
            // performNo:'',
            // performThumbnail: '',
            eventTitle: '',
            eventCategory: '',
            list: [
                {category: '기대평'},
                {category: '후기평'}
            ],
            eventStart: '',
            eventEnd: '',
        }
    },
    methods: {
        onSubmit() {
            const performNo = this.event.performance.performNo
            const { eventTitle, eventCategory, eventStart, eventEnd } = this

            this.$emit('submit', {performNo, eventTitle, eventCategory, eventStart, eventEnd})
        }
    },
    created () {
        // this.performThumbnail = this.event.performance.performThumbnail
        this.eventTitle = this.event.eventTitle
        this.eventCategory = this.event.eventCategory
        this.eventStart = this.event.eventStart
        this.eventEnd = this.event.eventEnd
    }
}
</script>

<style scoped>
img {
   position: relative;
    max-height: 500px;
    max-width: 500px;
}
</style>