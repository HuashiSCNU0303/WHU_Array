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
export default {
  data() {
    return {
      homeworkList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未完成的作业",
    };
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      // let _this = this;
      // axios.get("http://localhost:8009/homework/all", {
      //   headers: {
      //     'Authorization': localStorage.getItem("token")
      //   }
      // }).then((res) => {
      //   _this.homeworkList = res.data;
      //   _this.isLoading = false;
      // }).catch((error) => {
      //   console.log(error);
      // })
      // 获取这个学生的所有作业，给一个学生的id

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
