<template>
  <div>
    <big-title><p>考试列表</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers" :keys="keys">
      <div slot="panel1_content">
        <exam-card v-if="currentExamData != null" :currentExamData="currentExamData" />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <stu-exam-list
          v-if="todoExamList.length > 0"
          :data="todoExamList"
          :currentPage="currentPages[0]"
        />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
      <div slot="panel3_content">
        <stu-exam-list
          v-if="finishExamList.length > 0"
          :data="finishExamList"
          :currentPage="currentPages[1]"
        />
        <div v-else><icon-hint :hint="emptyHints[2]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      emptyHints: [
        "当前没有进行中的考试",
        "当前没有准备开始的考试",
        "当前没有已结束的考试",
      ],
      headers: ["当前进行中的考试", "准备开始的考试", "已结束的考试"],
      currentExamData: {},
      todoExamList: [],
      finishExamList: [],
      isLoading: true,
      currentPages: ["CourseTodoExam", "CourseExam"],
      keys: ["1", "2", "3"],
    };
  },
  mounted() {
    this.getExams();
  },
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
      course: (state) => state.curObj.course.course,
    }),
  },
  methods: {
    getExams() {
      var _this = this;
      var data = {
        userId: this.user.id,
        courseId: this.course.id,
      };
      this.api.student.getCourseExam(data).then((res) => {
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
            _this.todoExamList.push(exam);
          } else {
            _this.finishExamList.push(exam);
          }
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
