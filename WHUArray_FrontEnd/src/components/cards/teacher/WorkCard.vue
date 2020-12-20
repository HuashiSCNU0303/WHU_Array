<template>
  <a-card :hoverable="true">
    <a-card-meta>
      <div style="margin-bottom: 16px" slot="title">
        {{ item.name }}
      </div>
    </a-card-meta>
    <div style="margin-bottom: 8px">
      <a-tag
        :color="
          item.status === 'published'
            ? 'volcano'
            : item.status === 'unpublished'
            ? 'green'
            : 'geekblue'
        "
        style="font-size: 14px"
      >
        {{ item.status == "published" ? "已发布" : "未发布" }}
      </a-tag>
    </div>
    <div>开始时间：{{ item.startTime }}</div>
    <div>截止时间：{{ item.endTime }}</div>
  </a-card>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
    },
  },
  data() {
    return {
    };
  },
};
</script>

<style scoped></style>
