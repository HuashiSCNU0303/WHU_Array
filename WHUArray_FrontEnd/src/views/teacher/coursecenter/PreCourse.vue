<template>
  <div>
    <big-title><p>已结束课程</p></big-title>
    <center-loading v-if="isLoading == true" />
    <course-list
      v-else-if="endCourseList.length > 0"
      :data="endCourseList"
      :currentPage="currentPage"
    />
    <icon-hint v-else :hint="emptyHint" />
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      emptyHint: "当前没有已结束课程",
      endCourseList: [],
      currentPage: "CourseMgmt",
      isLoading: true,
    };
  },
  computed: {
    ...mapState({
      courseList: (state) => state.tempData.courseList.endList,
    }),
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      setTimeout(() => {
        this.endCourseList = this.courseList;
        this.isLoading = false;
        clearInterval();
      }, 1000);
    },
  },
};
</script>

<style scoped></style>
