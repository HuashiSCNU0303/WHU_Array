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
          key: "myCourse",
          iconType: "setting",
          text: "我的课程",
        },
        {
          key: "addCourse",
          iconType: "message",
          text: "加入课程",
        },
      ],
      pageTitle: "题库",
      description: "这里有以前各个课程的作业与考试题，供大家参考",
      extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
    };
  },
};
</script>

<style scoped></style>
