<template>
  <div>
    <event-modify-form v-if="event" :event="event" @submit="onSubmit" />
    <p v-else>로딩중 .......</p>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
import EventModifyForm from "@/components/event/EventModifyForm.vue";

export default {
  name: "QnAModifyPage",
  components: {
    EventModifyForm,
  },
  props: {
    eventNo: {},
  },
  computed: {
    ...mapState(["event"]),
  },
  methods: {
    ...mapActions(["fetchEvent"]),
    onSubmit(payload) {
      console.log("payload받은것: ", payload);
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
        .put(`event/${this.eventNo}`, {
          performNo,
          eventTitle,
          eventCategory,
          eventStart,
          eventEnd,
          eventContent,
          eventGoods,
          eventWinnerDate,
          eventWinnerCnt,
          eventRegDate: this.event.eventRegDate,
        })
        .then((res) => {
          alert("이벤트가 수정되었습니다.");
          console.log(res);

          this.$router.push({ name: "EventListPage" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.fetchEvent(this.eventNo).catch(() => {
      alert("게시물 DB 조회 실패!");
      this.$router.back();
    });
  },
};
</script>
