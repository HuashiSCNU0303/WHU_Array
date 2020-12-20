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
      this.api.student.getStuHomeworkList().then((res) => {
        // 从列表中取出未提交的作业
        // 数据处理，计算每一个作业的剩余时间
        _this.homeworkList = [];
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
