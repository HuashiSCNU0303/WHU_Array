<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <problem-list v-else :data="problemList" :currentPage="pageType" />
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      problemList: [],
      isLoading: true,
      header: {
        extraType: "countdown",
        pageTitle: "",
        description: "",
      },
      breadCrumb: [{}],
    };
  },
  computed: {
    ...mapState({
      pageType: (state) => state.currentPage.type,
      course: (state) => state.currentCourse.course,
      homework: (state) => state.currentHomework.homework,
    }),
  },
  mounted() {
    this.setHeader();
    this.setBreadCrumb();
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", "Homework");
    this.getProblems();
  },
  methods: {
    setHeader() {
      this.header.pageTitle = this.course.name + "\n" + this.homework.name;
      this.header.description = "截止时间：" + this.homework.endTime;
    },
    setBreadCrumb() {
      this.breadCrumb = [
        {
          name: "我的课程",
          href: "/index/course",
        },
        {
          name: this.course.name,
          type: "Course",
          id: this.course.id,
        },
        {
          name: this.homework.name,
        },
      ];
    },
    getProblems() {
      // 获取题目列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.problemList = this.$store.state.problemList.problemList;
        this.isLoading = false;
      }, 1000);
    },
  },
};
</script>

<style scoped>
.content {
  padding: 24px;
}
</style>
