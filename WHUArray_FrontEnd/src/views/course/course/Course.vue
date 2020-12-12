<template>
  <side-menu-panel :items="items" />
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      items: [
        {
          key: "homework",
          iconType: "setting",
          text: "待完成作业",
        },
        {
          key: "exam",
          iconType: "message",
          text: "待完成考试",
        },
      ],
      header: {
        breadCrumbLayer: "",
        pageTitle: "",
        description: "",
        extraType: "image",
        extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
      },
    };
  },
  computed: {
    ...mapState({
      currentCourse: (state) => state.currentCourse.course,
    }),
  },
  mounted() {
    this.header.breadCrumbLayer = "Course";
    this.header.pageTitle = this.currentCourse.name;
    this.header.description =
      this.currentCourse.time +
      "学年，由" +
      this.currentCourse.teacher +
      "老师授课<br />课程号：" +
      this.currentCourse.id +
      "<br />课程简介：" +
      this.currentCourse.description;
    this.$store.dispatch("setCurrentPageHeader", this.header);
  },
  methods: {},
};
</script>

<style scoped></style>
