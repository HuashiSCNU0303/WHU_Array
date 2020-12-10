<template>
  <div>
    <side-menu-panel :items="items"></side-menu-panel>
  </div>
</template>

<script>
import SideMenuPanel from "@/components/SideMenuPanel.vue";
export default {
  components: { SideMenuPanel },
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
      pageTitle: "课程管理",
      description: "你可以通过已选的课程，查看该课程布置的作业与考试",
      extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
    };
  },
};
</script>

<style scoped></style>
