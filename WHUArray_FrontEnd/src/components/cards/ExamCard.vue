<template>
  <div>
    <a-card :hoverable="true" style="width: 400px">
      <a-card-meta>
        <div style="margin-bottom: 3px" slot="title">{{ title }}</div>
        <div class="meta-content" slot="description">{{ content }}</div>
      </a-card-meta>
      <template class="ant-card-actions" slot="actions">
        <a>已完成：{{ currentScore }} / {{ score }}分</a>
        <a>剩余：{{ elapse }}分钟</a>
      </template>
    </a-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "系统级程序设计 期末考试",
      content: "韩波",
      currentScore: "46",
      score: "100",
      elapse: "30",
    };
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
