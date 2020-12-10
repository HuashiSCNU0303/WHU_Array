<template>
  <div class="hint">
    <p><a-avatar icon="user" />&nbsp;&nbsp;{{ hint }}</p>
  </div>
</template>

<script>
export default {
  props: {
    hint: {
      type: String,
    },
  },
};
</script>

<style scoped>
.hint {
  text-align: center;
  margin: 40px 0;
}
</style>
