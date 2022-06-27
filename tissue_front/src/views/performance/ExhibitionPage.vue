<template>
  <div>
    <exhibition-form :exhibitionList="exhibitionList" />
  </div>
</template>

<script>
import ExhibitionForm from "@/components/performance/ExhibitionForm.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ExhibitionPage",
  components: {
    ExhibitionForm,
  },
  computed: {
    ...mapState(["exhibitionList"]),
  },
  mounted() {
    this.fetchExhibitionList();
  },
  methods: {
    ...mapActions(["fetchExhibitionList"]),
  },
};
</script>
