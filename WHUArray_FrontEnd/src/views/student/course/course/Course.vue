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
          iconType: "form",
          text: "所有作业",
        },
        {
          key: "exam",
          iconType: "copy",
          text: "所有考试",
        },
      ],
      header: {
        pageTitle: "",
        description: "",
        extraType: "image",
        extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
      },
      breadCrumb: [{}]
    };
  },
  computed: {
    ...mapState({
      course: (state) => state.curObj.course.course,
    }),
  },
  mounted() {
    this.setHeader();
    this.setBreadCrumb();
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", "Course");
  },
  methods: {
    setHeader() {
      this.header.pageTitle = this.course.name;
      this.header.description = this.course.time + "学年，由" + this.course.teacher + "老师授课<br />课程号：" + this.course.id + "<br />课程简介：" + this.course.description;
    },
    setBreadCrumb() {
      this.breadCrumb = [
        {
          name: "我的课程",
          href: "/student/course",
        },
        {
          name: this.course.name,
        },
      ]
    }
  },
};
</script>

<style scoped></style>
