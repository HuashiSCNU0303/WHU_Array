<template>
  <div class="content">
    <a-row>
      <a-col :span="12" style="border-right: 2px solid #e8e8e8">
        <div id="problem-info"><!--题目信息区域-->col-12</div>
      </a-col>
      <a-col :span="12">
        <div id="code-block"><!--写代码区域-->col-12</div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      isLoading: true,
      showCountdown: true,
      header: {
        breadCrumbLayer: "",
        pageTitle: "",
        description: "",
        extraType: "score",
        score: -1,
      },
    };
  },
  computed: {
    ...mapState({
      problem: (state) => state.currentProblem.problem,
      course: (state) => state.currentCourse.course,
      homework: (state) => state.currentHomework.homework,
      exam: (state) => state.currentExam.exam,
    }),
  },
  mounted() {
    const source = this.$route.query.source;
    if (source == "repos") {
      this.showCountdown = false;
    }
    const data = this.getData(source);
    this.header.breadCrumbLayer = data[0];
    this.header.pageTitle = this.problem.name;
    this.header.description = "来源：" + data[1];
    this.header.score = this.problem.score;
    this.$store.dispatch("setCurrentPageHeader", this.header);
  },
  methods: {
    getData(source) {
      var layer = "", sourceStr = "";
      switch (source) {
        case "repos": {
          layer = "ProblemInRepos";
          sourceStr = this.problem.course + "\n" + this.problem.work;
          break;
        }
        case "homework": {
          layer = "ProblemInHomework";
          sourceStr = this.course.name + "\n" + this.homework.name;
          break;
        }
        case "exam": {
          layer = "ProblemInExam";
          sourceStr = this.course.name + "\n" + this.exam.name;
          break;
        }
      }
      return [layer, sourceStr];
    },
  },
};
</script>

<style scoped>
.content {
  padding: 24px;
}
#problem-info {
  margin-right: 8px;
  background: #00a0e9;
}
#code-block {
  margin-left: 8px;
  background: #0000e8;
}
</style>
