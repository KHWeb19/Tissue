<template>
    <div>
        <event-read-form v-if="event" :event="event"/>
    </div>
</template>

<script>
import EventReadForm from '@/components/event/EventReadForm.vue'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'EventReadPage',
    data() {
        return {

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
        this.fetchEvent(this.eventNo)
            .catch(() => {
                        alert('이벤트 게시물 요청 실패!')
                        console.log(this.eventNo)
                    })
    },
    computed: {
        ...mapState(['event'])
    },
    methods: {
        ...mapActions(['fetchEvent']),
    }
}
</script>

<style scoped>

</style>