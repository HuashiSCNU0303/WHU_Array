<template>
  <div>
    <side-menu-panel :items="items" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          key: "curCourse",
          iconType: "setting",
          text: "教授中课程",
        },
        {
          key: "preCourse",
          iconType: "message",
          text: "已结束课程",
        },
      ],
      header: {
        pageTitle: "我的课程",
        description: "你可以在这里查看正在教授的课程与已经结课的课程，还可以查看每个课程下的作业与考试",
        extraType: "image",
        extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
      },
    };
  },
  mounted() {
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", null);
  },
};
</script>

<style scoped></style>
