<template>
    <div>
        <event-read-form v-if="event" :event="event" :performance="performance"/>
    </div>
</template>

<script>
import EventReadForm from '@/components/event/EventReadForm.vue'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'EventReadPage',
    data() {
        return {
            performance: ''
        }
    },
    components: {
        EventReadForm
    },
    props: {
        eventNo: {
            
        }
    },
    created() {
        this.performance = this.event.performance
    },
    computed: {
        ...mapState(['event'])
    },
    mounted() {
        this.fetchEvent(this.eventNo)
            .catch(() => {
                        alert('이벤트 게시물 요청 실패!')
                        console.log(this.eventNo)
                    })
    },
    methods: {
        ...mapActions(['fetchEvent'])
    }
}
</script>