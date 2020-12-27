<template>
  <div class="content">
    <center-loading v-if="isLoading == true" />
    <stu-problem-list v-else :data="problemList" :currentPage="pageType" />
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

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
      pageType: (state) => state.curObj.page.type,
      course: (state) => state.curObj.course.course,
      work: (state) => state.curObj.work.work,
      user: (state) => state.curObj.user.user,
    }),
    type() {
      return this.$route.meta.type;
    },
    typeName() {
      return this.type == "Homework" ? "作业" : "考试";
    },
  },
  mounted() {
    this.setHeader();
    this.setBreadCrumb();
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", this.type);
    this.getProblems();
  },
  methods: {
    setHeader() {
      this.header.pageTitle = this.course.name + "\n" + this.work.name;
      this.header.description = "截止时间：" + this.work.endTime;
    },
    setBreadCrumb() {
      this.breadCrumb = [
        {
          name: "我的课程",
          href: "/student/course",
        },
        {
          name: this.course.name,
          type: "Course",
          id: this.course.id,
        },
        {
          name: this.work.name,
        },
      ];
    },
    getProblems() {
      var _this = this;
      var data = {
        workId: this.work.id,
      };
      var headers = {
        Authorization: localStorage.getItem("token"),
      };
      this.api.student.getWorkProblems(data, headers).then((res) => {
        var response = res.data;
        // 对response做处理，搞出problemList
        // console.log(response);
        var problems = response;
        for (var i = 0; i < problems.length; i++) {
          var problem_ = problems[i].question;
          var problemTags = problem_.tag.split(";");
          problemTags.splice(problemTags.length - 1, 1);
          var problem = {
            id: problem_.questionId,
            name: problem_.questionName,
            tags: problemTags,
            isDone: "",
            score: "",
          };

          var records = problems[i].records;
          _this.utils.statusHandler.handleProblemRecord(problem, records, _this.user.id);

          _this.problemList.push(problem);
        }
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
