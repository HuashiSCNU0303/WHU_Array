<template>
  <div>
    <a-list :grid="{ gutter: 24, lg: 3, md: 2, sm: 1, xs: 1 }" :dataSource="data">
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
  },
};
</script>

<style scoped>

</style>
