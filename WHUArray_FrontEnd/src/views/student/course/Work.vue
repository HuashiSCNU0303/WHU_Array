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
          href: "/index/course",
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
      // 获取题目列表，下面只是模拟一下请求后端获得结果而已
      // let _this = this;
      // let homeworkId = this.homework.homeworkId;
      // let getUrl = "http://localhost:8009/homework/" + homeworkId + "/allQuestion";
      // axios
      //   .get(getUrl, {
      //     headers: {
      //       Authorization: localStorage.getItem("token"),
      //     },
      //   })
      //   .then((res) => {
      //     // console.log(res.data);
      //     _this.problemList = res.data;
      //     _this.isLoading = false;
      //   });
      var _this = this;
      var data = {
        studentId: "",
        workId: "",
      };
      this.api.student.getWorkProblems(data).then((res) => {
        var response = res.data;
        // 对response进行处理，变成problemList_temp;
        var problemList_temp = [];
        _this.problemList = problemList_temp;
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
