<template>
    <div>
        <expectation-form/>
    </div>
</template>

<script>
import ExpectationForm from '@/components/expectataion/ExpectationForm.vue'
import { mapActions, mapState } from 'vuex'
export default {
    name: 'ExpectationPage',
    components: {
        ExpectationForm
    },
    computed: {
        ...mapState(['event'])
    },
    methods: {
        ...mapActions(['fetchPerformance'])
    }
}
</script>