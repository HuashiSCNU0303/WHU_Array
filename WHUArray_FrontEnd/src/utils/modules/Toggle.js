export default {
  handleCourseSwitch (self, role, id) {
    var data = {
      courseId: id,
    }
    self.api.course.getCourseDetail(data,).then((res) => {
      var response = res.data;
      // 处理response，不知道长啥样，先不管
      var item = {
        id: response.courseId,
        name: response.courseName,
        teacher: response.teacher.name, // ?
        grade: response.grade,
        time: response.courseTime,
        description: response.description,
        status: response.status,
      }
      self.$store.dispatch("setCurrentCourse", item);
      self.$router.push({
        path: "/" + role + "/course/" + id,
      });
    });
  },

  handleWorkSwitch (self, role, id, courseName) {
    var data = {
      workId: id,
    }
    self.api.work.getWorkDetail(data).then((res) => {
      var response = res.data;
      // 处理response，不知道长啥样，先不管
      var item = {
        id: response.homeworkId,
        courseId: response.courseId,
        name: response.homeworkName,
        startTime: response.startTime,
        endTime: response.endTime,
        status: response.status,
        type: response.isExam == 0 ? "Homework" : "Exam",
      }
      if (typeof courseName !== 'undefined') {
        var course = {
          id: response.courseId,
          courseName: courseName,
        }
        self.$store.dispatch("setCurrentCourse", course);
      }
      self.$store.dispatch("setCurrentWork", item);
      self.$router.push({
        path: "/" + role + "/" + item.type.toLowerCase() + "/" + id,
      });
    });
  },

  // 这个用item就可以了，不需要每一次都查
  handleProblemSwitch (self, item, prePage) {
    self.$store.dispatch("setCurrentProblem", item);
    var role = self.$store.state.curObj.user.user.role; // 获取当前用户的身份（学生/老师）
    self.$router.push({
      path: "/" + role + "/problem/" + item.id,
      query: {
        source: prePage,
      },
    });
  },
};
