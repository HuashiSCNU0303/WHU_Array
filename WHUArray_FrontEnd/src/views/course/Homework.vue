<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <problem-list v-else :data="problemList" :currentPage="pageType" />
  </div>
</template>

<script>
import { mapState } from "vuex";

const problemList_temp = [
  {
    problemId: 1,
    problemName: "两数之和",
    problemCourse: "算法设计与分析",
    problemWork: "第一次作业",
    problemTags: ["算法"],
    isDone: true,
    score: 90,
  },
  {
    problemId: 2,
    problemName: "设计链表",
    problemCourse: "数据结构",
    problemWork: "期末考试",
    problemTags: ["数据结构"],
    isDone: false,
    score: -1,
  },
];

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
        this.problemList = problemList_temp;
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
