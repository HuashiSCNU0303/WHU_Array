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
      header: (state) => state.currentPageHeader.header,
    }),
    showCountdown: function () {
      if (typeof this.header.breadCrumbLayer === "undefined") {
        return false;
      }
      return (
        this.header.breadCrumbLayer == "ProblemInHomework" ||
        this.header.breadCrumbLayer == "ProblemInExam"
      );
    },
  },
  components: {
    NavBar,
  },
};
</script>

<style scoped></style>
