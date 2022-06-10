<template>
  <div>
    <hall-register />
  </div>
</template>

<script>
import HallRegister from "@/components/Hall/HallRegister.vue";
import axios from "axios";

export default {
  name: "HallRegisterPage",
  components: {
    HallRegister,
  },
};
</script>
