<template>
  <div>
    <big-title><p>加入课程</p></big-title>
    <center-loading v-if="isLoading == true" />
    <div v-else-if="courseList.length > 0">
      <!--搜索模块-->
      <course-list :data="courseList" />
    </div>
    <icon-hint v-else :hint="emptyHint" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      courseList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有可以选择的课程",
    };
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.courseList = this.$store.state.courseList.addCourseList;
        this.isLoading = false;
      }, 1000);
    },
  },
};
</script>
