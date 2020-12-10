<template>
  <a-layout>
    <nav-bar />
    <router-view />
  </a-layout>
</template>

<script>
import NavBar from "@/components/header/Header";
export default {
  components: {
    NavBar,
  },
};
</script>

<style scoped>
</style>
