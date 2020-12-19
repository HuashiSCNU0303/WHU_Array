<template>
  <a-layout>
    <tea-nav-bar />
    <router-view />
  </a-layout>
</template>

<script>
import TeaNavBar from "@/components/header/TeacherHeader";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      header: (state) => state.curObj.page.header,
    }),
  },
  components: {
    TeaNavBar,
  },
};
</script>

<style scoped></style>

