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
    this.getAllCourses();
  },
  methods: {
    getAllCourses() {
      var _this = this;
      var data = {
        id: -1,
      };
      this.api.student.getAllCourseList(data).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的courses;
        var courses;
        for (var i = 0; i < courses.length; i++) {
          var course = courses[i];
          // 相关处理（是否已选）
          _this.courseList.push(course);
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>
