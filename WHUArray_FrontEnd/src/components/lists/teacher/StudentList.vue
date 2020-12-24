<template>
  <div>
    <a-list :grid="grid" :dataSource="data" :pagination="pagination">
      <a-list-item slot="renderItem" slot-scope="item">
        <slot name="content" :item="item" />
      </a-list-item>
    </a-list>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: {
    data: {
      type: Array,
    },
    grid: {
      type: Object,
    },
    pagination: {
      type: Object,
    },
  },
};
</script>

<style scoped></style>
