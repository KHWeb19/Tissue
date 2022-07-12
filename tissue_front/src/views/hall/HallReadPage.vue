<template>
  <div>
    <hall-read v-if="hall" :hall="hall" />
  </div>
</template>

<script>
import HallRead from "@/components/Hall/HallRead.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "NoticeReadPage",
  components: {
    HallRead,
  },
  props: {
    hallNo: {
      type: String,
      required: true,
    },
  },
  computed: {
    ...mapState(["hall"]),
  },
  created() {
    this.$store.state.hall = null;

    this.fetchHall(this.hallNo).catch(() => {
      //alert("게시물 요청 실패");
      this.$router.push();
    });
  },
  methods: {
    ...mapActions(["fetchHall"]),
  },
};
</script>
