<template>
  <div>
    <concert-form :concertList="concertList" />
  </div>
</template>

<script>
import ConcertForm from "@/components/performance/ConcertForm.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "ConcertPage",
  components: {
    ConcertForm,
  },
  computed: {
    ...mapState(["concertList"]),
  },
  mounted() {
    this.fetchConcertList();
  },
  methods: {
    ...mapActions(["fetchConcertList"]),
  },
};
</script>
