<template>
    <area-main-form v-if="performances" :performances="performances" 
                    :mapList="mapList" />
</template>

<script>
import axios from 'axios'
import AreaMainForm from '@/components/area/AreaMainForm.vue'
// import { mapActions, mapMutations, mapState } from 'vuex'
import { mapActions, mapState } from 'vuex'


export default {
    name: 'AreaPage',
    components: {
        AreaMainForm,
    },
    data() {
        return {
            mapList: []
        }
    },
    created() {
        this.$store.state.performances = null;
    },
    mounted() {
        this.fetchPerformanceList()
        this.getMapList()
    },
    computed: {
        ...mapState(['performances']),
        // copyPerformances: {
        //     ...mapState({get: 'copyPerformances'}),
        //     ...mapMutations({set: 'FETCH_COPY_PERFORMANCE_LIST'})
        // }
    },
    methods: {
        ...mapActions(['fetchPerformanceList']),
        getMapList() {
            axios.get('map/list')
                .then(res => {
                    this.mapList = res.data
                })
        }
    },
}
</script>