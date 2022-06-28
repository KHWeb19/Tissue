<template>
  <div>
    <musical-form v-if="musicalList" :musicalList="musicalList" />
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
  created() {
    this.$store.state.musicalList = null;
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
