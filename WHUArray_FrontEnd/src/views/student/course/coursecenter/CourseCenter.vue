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
          key: "myCourse",
          iconType: "schedule",
          text: "我的课程",
        },
        {
          key: "addCourse",
          iconType: "team",
          text: "加入课程",
        },
      ],
      header: {
        pageTitle: "课程管理",
        description: "你可以通过已选的课程，查看该课程布置的作业与考试",
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
