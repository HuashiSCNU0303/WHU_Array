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
      currentPage: "TeaPreCourse",
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
      var _this = this;
      var data = {
        id: -1,
      };
      this.api.teacher.getPreCourseList(data).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的courses;
        var courses;
        for (var i = 0; i < courses.length; i++) {
          var course = courses[i];
          _this.endCourseList.push(course);
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
