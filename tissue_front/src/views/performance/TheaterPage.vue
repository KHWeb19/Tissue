<template>
  <div>
    <theater-form :theaterList="theaterList" />
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
