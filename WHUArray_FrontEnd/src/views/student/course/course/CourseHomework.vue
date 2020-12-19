<template>
  <div>
    <big-title><p>作业列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <stu-homework-list
      v-else-if="homeworkList.length > 0"
      :currentPage="currentPage"
      :data="homeworkList"
    />
    <div v-else><icon-hint :hint="emptyHint" /></div>
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
      homeworkList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未完成的作业",
      currentPage: "CourseHomework",
    };
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      let courseId = this.course.id;
      let _this = this;
      let getUrl = "http://localhost:8009/course/" + courseId + "/allHomework";
      axios
        .get(getUrl, {
          headers: {
            Authorization: localStorage.getItem("token"),
          },
        })
        .then((res) => {
          console.log(res.data);
          this.homeworkList = res.data;
          this.isLoading = false;
        })
        .catch((error) => {
          console.log(error);
        });
      // // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      // setTimeout(() => {
      //   this.homeworkList = this.$store.state.homeworkList.homeworkList;
      //   this.isLoading = false;
      // }, 1000);
    },
  },
};
</script>

<style scoped></style>
