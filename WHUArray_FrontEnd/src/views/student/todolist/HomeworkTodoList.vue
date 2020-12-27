<template>
  <div>
    <big-title><p>作业列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <stu-homework-list v-else-if="homeworkList.length > 0" :data="homeworkList" />
    <div v-else><icon-hint :hint="emptyHint" /></div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  data() {
    return {
      homeworkList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未完成的作业",
    };
  },
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      var _this = this;
      var data = {
        userId: this.user.id,
      };
      var headers = {
        Authorization: localStorage.getItem("token"),
      };
      this.api.student.getHomeworkList(data, headers).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的homeworks
        var homeworks = response;
        for (var i = 0; i < homeworks.length; i++) {
          var homework_ = homeworks[i];
          if (homework_.isExam != 0 || homework_.status == "unpublished") {
            continue;
          }
          var homework = {
            id: homework_.homeworkId,
            status: homework_.status,
            courseId: homework_.course.courseId,
            courseName: homework_.course.courseName,
            name: homework_.homeworkName,
            startTime: homework_.startTime,
            endTime: homework_.endTime,
            remainingTime: "/",
            score: homework_.grade,
            type: "Homework",
          };
          _this.utils.statusHandler.handleStudentHomework(_this, homework);
          if (homework.status == "未提交") {
            _this.homeworkList.push(homework);
          }
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
