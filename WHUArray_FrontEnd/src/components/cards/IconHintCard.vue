<template>
  <a-card :hoverable="true">
    <span class="hint">
      <p><a-icon :type="icon" /> &nbsp; {{ hint }}</p>
    </span>
  </a-card>
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
  font-size: 16px;
  text-align: center;
}
</style>
