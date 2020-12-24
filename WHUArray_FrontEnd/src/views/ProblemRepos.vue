<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <stu-problem-list v-else :data="problemList" :currentPage="currentPage" />
  </div>
</template>

<script>
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
    this.$store.dispatch("setCurrentBreadCrumb", null);
    this.$store.dispatch("setCurrentPageType", "ProblemRepos");
    this.getProblems();
  },
  methods: {
    getProblems() {
      // 获取题目列表，下面只是模拟一下请求后端获得结果而已
      var _this = this;
      var data = {
        role: "",
        id: -1
      };
      this.api.problem.getProblems(data, ).then((res) => {
        var response = res.data;
        // 对response做处理
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped>
.content {
  padding: 24px;
}
</style>
