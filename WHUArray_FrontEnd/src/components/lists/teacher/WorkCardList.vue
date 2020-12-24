<template>
  <div>
    <a-list :grid="{ gutter: 24, lg: 3, md: 2, sm: 1, xs: 1 }" :dataSource="data" :key="refreshKey">
      <a-list-item slot="renderItem" slot-scope="item" v-if="typeof item.visible == 'undefined' || item.visible == true">
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
    refreshKey: {
      type: Number,
      default: 0,
    }
  },
};
</script>

<style scoped>

</style>
