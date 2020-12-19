<template>
  <div>
    <big-title><p>作业列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <stu-homework-list v-else-if="homeworkList.length > 0" :data="homeworkList" />
    <div v-else><icon-hint :hint="emptyHint" /></div>
  </div>
</template>

<script>
import axios from 'axios';
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
      let _this = this;
      axios.get("http://localhost:8009/homework/all", {
        headers: {
          'Authorization': localStorage.getItem("token")
        }
      }).then((res) => {
        _this.homeworkList = res.data;
        _this.isLoading = false;
      }).catch((error) => {
        console.log(error);
      })
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      // setTimeout(() => {
      //   this.homeworkList = this.$store.state.homeworkList.homeworkList;
      //   this.isLoading = false;
      // }, 1000);
    },
  },
};
</script>

<style scoped></style>
