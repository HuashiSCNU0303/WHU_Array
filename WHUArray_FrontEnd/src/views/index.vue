<template>
  <a-layout>
    <navbar></navbar>
    <router-view />
  </a-layout>
</template>

<script>
import navbar from "@/components/header";
export default {
  data() {
    return {
      flag: false,
      user: {},
    };
  },
  components: {
    navbar,
  },
};
</script>

<style scoped>
</style>
