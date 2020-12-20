<template>
  <div class="hint">
    <p><a-avatar :icon="icon" /> &nbsp; {{ hint }}</p>
  </div>
</template>

<script>
export default {
  props: {
    icon: {
      type: String,
    },
    hint: {
      type: String,
    },
  },
};
</script>

<style scoped>
.hint {
  color: rgba(0, 0, 0, 0.65);
  font-size: 16px;
  text-align: center;
  /*margin: 30px 0;*/
  align-self: center;
  margin: 0 auto;
}
</style>
