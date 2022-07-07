<template>
  <div>
    <event-register-form :performances="performances" @submit="onSubmit" />
  </div>
</template>

<script>
import EventRegisterForm from "@/components/event/EventRegisterForm.vue";
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "EventRegisterPage",
  components: {
    EventRegisterForm,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["performances"]),
  },
  mounted() {
    this.fetchPerformanceList();
  },
  methods: {
    ...mapActions(["fetchPerformanceList"]),
    onSubmit(payload) {
      console.log('payload받은것', payload)
      const {
        performNo,
        eventTitle,
        eventCategory,
        eventStart,
        eventEnd,
        eventContent,
        eventGoods,
        eventWinnerDate,
        eventWinnerCnt,
      } = payload;

      axios
        .post("event/register", {
          performNo,
          eventTitle,
          eventCategory,
          eventStart,
          eventEnd,
          eventContent,
          eventGoods,
          eventWinnerDate,
          eventWinnerCnt,
        })
        .then((res) => {
          alert("이벤트가 등록되었습니다.");
          console.log(res);
          this.$router.push({ name: "EventListPage" });
        })
        .catch((res) => {
          alert("처리 결과: " + res.message);
        });
    },
  },
};
</script>
