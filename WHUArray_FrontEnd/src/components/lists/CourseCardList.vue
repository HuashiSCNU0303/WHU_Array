<template>
  <div>
    <a-list :grid="{ gutter: 24, lg: 3, md: 2, sm: 1, xs: 1 }" :dataSource="data">
      <a-list-item slot="renderItem" slot-scope="item">
        <a-card :hoverable="true" @click="switchToCourse(item)">
          <a-card-meta>
            <div style="margin-bottom: 3px" slot="title">{{ item.name }}</div>
            <div class="meta-content" slot="description">课程号：{{ item.id }}</div>
          </a-card-meta>
          <template class="ant-card-actions" slot="actions">
            <a>学年：{{ item.time }}</a>
            <a>任课老师：{{ item.teacher }}</a>
          </template>
        </a-card>
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
  methods: {
    switchToCourse(item) {
      // 跳转到item.id指向的课程
      this.$router.push({
        path: "./" + item.id,
      });
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
