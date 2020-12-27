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
      user: (state) => state.curObj.user.user,
      courseList: (state) => state.tempData.courseList.endList,
      headers() {
        return {
          Authorization: localStorage.getItem("token"),
        };
      },
    }),
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      var _this = this;
      var data = {
        teacherId: this.user.id,
      };
      this.api.teacher.getPreCourseList(data, this.headers).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的courses;
        var courses = response;
        for (var i = 0; i < courses.length; i++) {
          var course_ = courses[i];
          var course = {
            id: course_.courseId,
            name: course_.courseName,
            teacher: course_.teacherName,
            grade: course_.grade,
            time: course_.courseTime,
            description: course_.description,
            status: course_.status,
          };
          _this.endCourseList.push(course);
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
