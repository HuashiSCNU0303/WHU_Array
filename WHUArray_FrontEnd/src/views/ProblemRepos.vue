<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <stu-problem-list v-else :data="problemList" :currentPage="currentPage" />
  </div>
</template>

<script>
import { mapState } from 'vuex';
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
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  mounted() {
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", null);
    this.$store.dispatch("setCurrentPageType", "ProblemRepos");
    this.getProblems();
  },
  methods: {
    getProblems() {
      this.problemList = [];
      var _this = this;
      var data = {};
      var headers = {
        Authorization: localStorage.getItem("token"),
      };
      this.api.problem.getProblems(data, headers).then((res) => {
        var response = res.data;
        console.log(response);
        var problemDTOs = response;
        for (var i = 0; i < problemDTOs.length; i++) {
          var problem_ = problemDTOs[i].question;
          var problemTags = problem_.tag.split(";");
          problemTags.splice(problemTags.length - 1, 1);
          var problem = {
            id: problem_.questionId,
            name: problem_.questionName,
            description: problem_.questionContent,
            isDone: "",
            score: "",
            courseName: problemDTOs[i].courseName,
            workName: problemDTOs[i].homeworkName,
            tags: problemTags,
          };

          var records = problemDTOs[i].records;
          _this.utils.statusHandler.handleProblemRecord(problem, records, _this.user.id);
          _this.problemList.push(problem);
        }
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
