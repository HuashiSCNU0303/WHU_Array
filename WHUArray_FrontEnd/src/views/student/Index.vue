<template>
  <a-layout>
    <stu-nav-bar :showCountdown="showCountdown" />
    <router-view />
  </a-layout>
</template>

<script>
import StuNavBar from "@/components/header/StudentHeader";
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
    StuNavBar,
  },
};
</script>

<style scoped></style>
