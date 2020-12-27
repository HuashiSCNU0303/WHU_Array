<template>
  <div>
    <big-title><p>考试列表</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers" :keys="keys">
      <div slot="panel1_content">
        <exam-card v-if="currentExamData != null" :currentExamData="currentExamData" />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <stu-exam-list v-if="examList.length > 0" :data="examList" />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      currentExamData: null,
      examList: [],
      emptyHints: ["当前没有进行中的考试", "当前没有准备开始的考试"],
      headers: ["当前进行中的考试", "准备开始的考试"],
      keys: ["1", "2"],
      isLoading: true,
    };
  },
  mounted() {
    this.getExams();
  },
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
  },
  methods: {
    getExams() {
      var _this = this;
      var data = {
        userId: this.user.id,
      };
      var headers = {
        Authorization: localStorage.getItem("token"),
      };
      this.api.student.getExamList(data, headers).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的exams;
        var exams = response;
        for (var i = 0; i < exams.length; i++) {
          var exam_ = exams[i];
          if (exam_.isExam != 1) {
            continue;
          }
          var exam = {
            id: exam_.homeworkId,
            status: exam_.status,
            courseId: exam_.course.courseId,
            courseName: exam_.course.courseName,
            name: exam_.homeworkName,
            startTime: exam_.startTime,
            endTime: exam_.endTime,
            remainingTime: "/",
            score: exam_.grade,
            type: "Exam",
          };
          _this.utils.statusHandler.handleStudentExam(_this, exam);
          if (exam.status == "进行中") {
            exam.name = exam.courseName + " " + exam.name;
            _this.currentExamData = _this.utils.statusHandler.handleStudentCurExam(exam);
          } else if (exam.status == "准备开始") {
            _this.examList.push(exam);
          }
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped>
.content {
  margin-left: -16px !important;
  margin-right: -16px !important;
}

.ant-collapse > .ant-collapse-item > .ant-collapse-header .ant-collapse-arrow {
  left: 0px !important;
}
</style>
