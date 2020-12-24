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
      course: (state) => state.curObj.course.course,
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
      // let courseId = this.course.id;
      // let _this = this;
      // let getUrl = "http://localhost:8009/course/" + courseId + "/allHomework";
      // axios
      //   .get(getUrl, {
      //     headers: {
      //       Authorization: localStorage.getItem("token"),
      //     },
      //   })
      //   .then((res) => {
      //     console.log(res.data);
      //     this.homeworkList = res.data;
      //     this.isLoading = false;
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      // 在这里分成两个表，一个是已提交/已结束的，一个是未提交的

      var _this = this;
      var data = {
        id: -1,
      };
      this.api.student.getHomeworkList(data).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的homeworks
        var homeworks;
        for (var i = 0; i < homeworks.length; i++) {
          var homework = homeworks[i];
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
