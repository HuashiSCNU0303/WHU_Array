<template>
  <a-layout>
    <nav-bar />
    <router-view />
  </a-layout>
</template>

<script>
import NavBar from "@/components/header";
import CommonPageView from "@/components/CommonPageView";
export default {
  components: {
    NavBar,
  },
};
</script>

<style scoped>
</style>
