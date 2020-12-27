<template>
  <div>
    <big-title><p>作业列表</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers" :keys="keys">
      <div slot="panel1_content">
        <stu-homework-list
          v-if="todoHomeworkList.length > 0"
          :currentPage="currentPages[0]"
          :data="todoHomeworkList"
        />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <stu-homework-list
          v-if="finishHomeworkList.length > 0"
          :currentPage="currentPages[1]"
          :data="finishHomeworkList"
        />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
      course: (state) => state.curObj.course.course,
      headers_() {
        return {
          Authorization: localStorage.getItem("token"),
        };
      },
    }),
  },
  data() {
    return {
      todoHomeworkList: [], // 未提交的作业列表
      finishHomeworkList: [], // 已提交的作业列表
      isLoading: true, // 标识一下正在加载的状态
      emptyHints: ["当前没有未提交的作业", "当前没有已提交/已结束的作业"],
      headers: ["未提交的作业", "已提交/已结束的作业"],
      currentPages: ["CourseTodoHomework", "CourseHomework"],
      keys: ["1", "2"],
    };
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      var _this = this;
      var data = {
        userId: this.user.id,
        courseId: this.course.id,
      };
      this.api.student.getCourseHomework(data, this.headers_).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的homeworks
        var homeworks = response;
        console.log(homeworks);
        for (var i = 0; i < homeworks.length; i++) {
          var homework_ = homeworks[i];
          console.log(homework_);
          if (homework_.isExam != 0 || homework_.status == "unpublished") {
            continue;
          }
          var homework = {
            id: homework_.homeworkId,
            status: homework_.status,
            name: homework_.homeworkName,
            startTime: homework_.startTime,
            endTime: homework_.endTime,
            remainingTime: "/",
            score: homework_.grade,
            type: "Homework",
          };
          console.log(homework);
          _this.utils.statusHandler.handleStudentHomework(_this, homework);
          if (homework.status == "未提交") {
            _this.todoHomeworkList.push(homework);
          } else {
            _this.finishHomeworkList.push(homework);
          }
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
