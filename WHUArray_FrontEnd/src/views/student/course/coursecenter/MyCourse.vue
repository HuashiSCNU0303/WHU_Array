<template>
  <div>
    <big-title><p>我的课程</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers" :keys="keys">
      <div slot="panel1_content">
        <course-card-list v-if="currentCourseList.length > 0" :data="currentCourseList">
          <div slot="content" slot-scope="props" @click="switchToCourse(props.item)">
            <stu-course-card :item="props.item" />
          </div>
        </course-card-list>
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <course-card-list v-if="endCourseList.length > 0" :data="endCourseList">
          <div slot="content" slot-scope="props" @click="switchToCourse(props.item)">
            <stu-course-card :item="props.item" />
          </div>
        </course-card-list>
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      headers: ["进行中的课程", "已结束的课程"],
      emptyHints: ["当前没有进行中的课程", "当前没有已结束的课程"],
      currentCourseList: [],
      endCourseList: [],
      isLoading: true,
      keys: ["1", "2"],
    };
  },
  mounted() {
    this.getCourses();
  },
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
    headers_() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  methods: {
    getCourses() {
      // let _this = this;
      // console.log(localStorage.getItem("token"));
      // axios
      //   .get("http://127.0.0.1:8009/course/all", {
      //     headers: {
      //       Authorization: localStorage.getItem("token"),
      //     },
      //   })
      //   .then((res) => {
      //     console.log(res.data);
      //     _this.currentCourseList = res.data;
      //     _this.isLoading = false;
      //   })
      //   .catch((error) => {
      //     console.log(error);
      //   });
      // 获取课程列表，下面只是模拟一下请求后端获得结果而已
      var _this = this;
      var data = {
        userId: this.user.id,
      };
      this.api.student.getCourseList(data, this.headers_).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的courses;
        var courses = response;
        console.log(courses);
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
          if (course.status == "on") {
            _this.currentCourseList.push(course);
          } else {
            _this.endCourseList.push(course);
          }
        }
        _this.isLoading = false;
        console.log(_this.endCourseList);
      });
    },

    switchToCourse(item) {
      this.utils.toggle.handleCourseSwitchByItem(this, "student", item);
    },
  },
};
</script>

<style scoped></style>
