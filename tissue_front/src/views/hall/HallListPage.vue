<template>
  <div>
    <hall-list v-if="halls" :halls="halls" />
  </div>
</template>

<script>
import HallList from "@/components/Hall/HallList.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "HallListPage",
  components: {
    HallList,
  },

  computed: {
    ...mapState(["halls"]),
  },
  mounted() {
    this.fetchHallList();
  },
  methods: {
    ...mapActions(["fetchHallList"]),
  },
};
</script>
