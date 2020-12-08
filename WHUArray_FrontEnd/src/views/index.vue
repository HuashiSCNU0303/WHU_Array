<template>
  <div>
    <navbar></navbar>
    <router-view />
  </div>
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
