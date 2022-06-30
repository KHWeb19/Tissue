<template>
  <div>
    <exhibition-form v-if="exhibitionList" :exhibitionList="exhibitionList" />
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
  created() {
    this.$store.state.exhibitionList = null;
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
