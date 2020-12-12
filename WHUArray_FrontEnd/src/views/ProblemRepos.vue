<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <problem-list v-else :data="problemList" :currentPage="currentPage"></problem-list>
  </div>
</template>

<script>
const problemList_temp = [
  {
    problemId: 1,
    problemName: "两数之和",
    problemCourse: "算法设计与分析",
    problemWork: "第一次作业",
    problemTags: ["算法",],
    isDone: true,
    score: 90,
  },
  {
    problemId: 2,
    problemName: "设计链表",
    problemCourse: "数据结构",
    problemWork: "期末考试",
    problemTags: ["数据结构",],
    isDone: false,
    score: -1,
  },
];

export default {
  data() {
    return {
      problemList: [],
      isLoading: true,
      currentPage: "ProblemRepos",
      header: {
        pageTitle: "题库",
        description: "这里有以前各个课程的作业与考试题，供大家参考",
        extraType: "image",
        extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
      },
    };
  },
  mounted() {
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
