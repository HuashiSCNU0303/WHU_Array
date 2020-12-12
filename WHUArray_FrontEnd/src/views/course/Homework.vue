<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <problem-list v-else :data="problemList" :currentPage="currentPage" />
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
    problemTag: ["算法",],
    isDone: true,
    score: 90,
  },
  {
    problemId: 2,
    problemName: "设计链表",
    problemCourse: "数据结构",
    problemWork: "期末考试",
    problemTag: ["数据结构",],
    isDone: false,
    score: 0,
  },
];

export default {
  data() {
    return {
      problemList: [],
      isLoading: true,
      currentPage: "Homework",
      header: {
        countdownIndicator: true,
        breadCrumbLayer: "Homework",
        pageTitle: "",
        description: "",
      },
    };
  },
  computed: {
    ...mapState({
      currentCourse: (state) => state.currentCourse.course,
      currentHomework: (state) => state.currentHomework.homework,
    }),
  },
  mounted() {
    this.header.breadCrumbLayer = "Homework";
    this.header.pageTitle = this.currentCourse.name + "\n" + this.currentHomework.name;
    this.header.description = "截止时间：" + this.currentHomework.endTime;
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.getProblems();
  },
  methods: {
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
