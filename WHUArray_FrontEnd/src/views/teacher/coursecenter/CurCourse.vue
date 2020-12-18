<template>
  <div>
    <big-title><p>教授中课程</p></big-title>
    <center-loading v-if="isLoading == true" />
    <course-card-list
      v-else-if="currentCourseList.length > 0"
      :data="currentCourseList"
      @courseClicked="switchToCourse"
      style="margin: 16px 0"
    />
    <icon-hint v-else :hint="emptyHint" />
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      emptyHint: "当前没有教授中课程",
      currentCourseList: [],
      isLoading: true,
    };
  },
  computed: {
    ...mapState({
      courseList: (state) => state.courseList.currentCourseList,
    }),
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      setTimeout(() => {
        this.currentCourseList = this.courseList;
        this.isLoading = false;
        clearInterval();
      }, 1000);
    },
    switchToCourse(item) {
      this.utils.toggle.handleCourseSwitch(this, "teacher", item);
    },
  },
};
</script>

<style scoped></style>
