<template>
  <a-layout>
    <nav-bar :showCountdown="showCountdown" />
    <router-view />
  </a-layout>
</template>

<script>
import NavBar from "@/components/header/Header";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      header: (state) => state.currentPage.header,
      pageType: (state) => state.currentPage.type,
    }),
    showCountdown: function () {
      return this.pageType == "ProblemInHomework" || this.pageType == "ProblemInExam";
    },
  },
  components: {
    NavBar,
  },
};
</script>

<style scoped></style>
