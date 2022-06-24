<template>
    <div>
        <event-list-form :events="events"/>
    </div>
</template>

<script>
import EventListForm from '@/components/event/EventListForm.vue'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'EventListPage',
    components: {
        EventListForm
    },
    computed: {
        ...mapState(['events'])
    },
    mounted() {
        this.fetchEventList()
    },
    methods: {
        ...mapActions(['fetchEventList'])
    }
}
</script>