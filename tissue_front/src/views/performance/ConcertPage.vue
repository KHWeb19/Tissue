<template>
  <div>
    <concert-form v-if="concertList" :concertList="concertList" />
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

  created() {
    this.$store.state.concertList = null;
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
