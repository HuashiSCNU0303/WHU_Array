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
          item.status === '未发布'
            ? 'volcano'
            : item.status === '已发布'
            ? 'green'
            : 'geekblue'
        "
        style="font-size: 14px"
      >
        {{ item.status }}
      </a-tag>
    </div>
    <div>开始时间：{{ curTime }}</div>
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
      curTime: "2020-12-14 09:00",
    };
  },
};
</script>

<style scoped></style>
