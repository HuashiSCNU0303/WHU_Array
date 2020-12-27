<template>
  <div>
    <side-menu-panel :items="items"></side-menu-panel>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          key: "homework",
          iconType: "form",
          text: "待完成作业",
        },
        {
          key: "exam",
          iconType: "copy",
          text: "待完成考试",
        },
      ],
      header: {
        pageTitle: "待办事项列表",
        description: "下面是你最近需要完成的作业或考试列表，默认按照剩余时间从小到大排序",
        extraType: "image",
        extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
      },
    };
  },
  mounted() {
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", null);
    this.$store.dispatch("setCurrentPageType", "TodoList");
  },
};
</script>

<style lang="less" scoped></style>
