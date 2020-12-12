<template>
  <div>
    <center-loading v-if="isLoading == true" />
    <a-list v-else item-layout="horizontal" :data-source="data">
      <a-list-item slot="renderItem" slot-scope="item" style="padding: 0px">
        <a-comment :author="item.author" :avatar="item.avatar" style="padding: 0px">
          <p slot="content" class="para">
            {{ item.content }}
          </p>
          <a-tooltip slot="datetime" :title="item.datetime.format('YYYY-MM-DD HH:mm:ss')">
            <span>{{ item.datetime.fromNow() }}</span>
          </a-tooltip>
        </a-comment>
      </a-list-item>
    </a-list>
  </div>
</template>

<script>
import moment from "moment";
export default {
  data() {
    return {
      data: [],
      isLoading: true,
      moment,
    };
  },
  mounted() {
    this.getCommentList();
  },
  methods: {
    getCommentList() {
      // 获取评论列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.createCommentList();
        this.isLoading = false;
      }, 1000);
    },
    createCommentList() {
      for (var i = 1; i <= 10; i++) {
        this.data.push({
          author: "Han Solo",
          avatar: "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png",
          content:
            "We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.",
          datetime: moment().subtract(i, "days"),
        });
      }
    },
  },
};
</script>

<style scoped>
.ant-comment-inner {
  padding: 0 !important;
}

.para {
  margin-bottom: 0;
}
</style>
