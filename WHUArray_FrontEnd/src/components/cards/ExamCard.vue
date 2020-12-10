<template>
  <div>
    <a-card :hoverable="true" style="width: 400px">
      <a-card-meta>
        <div style="margin-bottom: 3px" slot="title">{{ currentExamData.title }}</div>
        <div class="meta-content" slot="description">{{ currentExamData.content }}</div>
      </a-card-meta>
      <template class="ant-card-actions" slot="actions">
        <a
          >已完成：{{ currentExamData.currentScore }} /
          {{ currentExamData.fullScore }}分</a
        >
        <a>剩余：{{ currentExamData.remainingTime }}分钟</a>
      </template>
    </a-card>
  </div>
</template>

<script>
export default {
  props: {
    currentExamData: {
      type: Object,
    },
  },
};
</script>

<style lang="less" scoped>
.ant-card-actions {
  background: #f7f9fa;

  li {
    float: left;
    text-align: center;
    margin: 12px 0;
    color: rgba(0, 0, 0, 0.45);
    width: 50%;

    &:not(:last-child) {
      border-right: 1px solid #e8e8e8;
    }

    a {
      color: rgba(0, 0, 0, 0.45);
      line-height: 22px;
      display: inline-block;
      width: 100%;

      &:hover {
        color: #1890ff;
      }
    }
  }
}

.new-btn {
  background-color: #fff;
  border-radius: 2px;
  width: 100%;
  height: 188px;
}

.meta-content {
  position: relative;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  height: 36px;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
</style>
