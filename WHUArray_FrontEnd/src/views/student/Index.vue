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
      header: (state) => state.curObj.page.header,
      pageType: (state) => state.curObj.page.type,
    }),
    showCountdown: function () {
      return this.pageType == "ProblemInWork";
    },
  },
  components: {
    StuNavBar,
  },
};
</script>

<style scoped></style>
