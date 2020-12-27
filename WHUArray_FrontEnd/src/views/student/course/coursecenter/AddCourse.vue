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
  computed: {
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  methods: {
    getAllCourses() {
      var _this = this;
      var data = {};
      this.api.student.getAllCourseList(data, this.headers).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的courses;
        var courses = response[0];
        console.log(courses);
        console.log(response[1]);
        for (var i = 0; i < courses.length; i++) {
          var course_ = courses[i];
          var course = {
            id: course_.courseId,
            name: course_.courseName,
            teacher: course_.teacherName,
            grade: course_.grade,
            time: course_.courseTime,
            description: course_.description,
            isSelected: this.judgeSelected(response[1], course_.courseId),
            status: course_.status,
          };
          _this.courseList.push(course);
        }
        _this.isLoading = false;
      });
    },

    judgeSelected(list, id) {
      for (var i = 0; i < list.length; i++) {
        var course_ = list[i];
        if (course_.courseId == id) {
          list.splice(i, 1);
          return true;
        }
      }
      return false;
    },
  },
};
</script>
