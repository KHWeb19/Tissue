<template>
  <div>
    <musical-form :musicalList="musicalList" />
  </div>
</template>

<script>
import MusicalForm from "@/components/performance/MusicalForm.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "MusicalPage",
  components: {
    MusicalForm,
  },
  computed: {
    ...mapState(["musicalList"]),
  },
  mounted() {
    this.fetchMusicalList();
  },
  methods: {
    ...mapActions(["fetchMusicalList"]),
  },
};
</script>
