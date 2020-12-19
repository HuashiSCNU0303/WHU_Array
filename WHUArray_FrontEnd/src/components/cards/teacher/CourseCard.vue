<template>
  <a-card :hoverable="true">
    <a-card-meta>
      <div style="margin-bottom: 3px" slot="title">
        {{ item.grade }}&nbsp;{{ item.name }}
      </div>
      <div class="meta-content" slot="description">
        {{ item.time }}学年<br />课程号：{{ item.id }}
      </div>
    </a-card-meta>
  </a-card>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
    },
  },
};
</script>

<style scoped>

</style>
