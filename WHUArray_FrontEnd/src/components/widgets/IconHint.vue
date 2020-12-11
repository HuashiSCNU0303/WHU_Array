<template>
  <div class="hint">
    <p><a-avatar :icon="icon" /> &nbsp; &nbsp; {{ hint }}</p>
  </div>
</template>

<script>
export default {
  props: {
    icon: {
      type: String
    },
    hint: {
      type: String,
    },
  },
};
</script>

<style scoped>
.hint {
  text-align: center;
  margin: 30px 0;
}
</style>
