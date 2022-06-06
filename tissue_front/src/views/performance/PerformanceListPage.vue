<template>
    <div>
        <performance-list-form :performances = "performances"/>
        <v-btn absolute right plain router-link :to="{ name: 'PerformanceRegisterPage' }">
            <v-icon>mdi-clipboard-edit-outline</v-icon>
        </v-btn>
    </div>
</template>

<script>
import PerformanceListForm from '@/components/performance/PerformanceListForm.vue'
import { mapState, mapActions } from 'vuex'


export default {
    name: 'PerformanceListPage',
    components: {
        PerformanceListForm
    },
    computed: {
        ...mapState(['performances'])
    },
    mounted () {
        this.fetchPerformanceList()
    },
    methods: {
        ...mapActions(['fetchPerformanceList'])
    }
}

</script>