<template>
  <div>
    <theater-form v-if="theaterList" :theaterList="theaterList" />
  </div>
</template>

<script>
import TheaterForm from "@/components/performance/TheaterForm.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "TheaterPage",
  components: {
    TheaterForm,
  },
  created() {
    this.$store.state.theaterList = null;
  },
  computed: {
    ...mapState(["theaterList"]),
  },
  mounted() {
    this.fetchTheaterList();
  },
  methods: {
    ...mapActions(["fetchTheaterList"]),
  },
};
</script>
