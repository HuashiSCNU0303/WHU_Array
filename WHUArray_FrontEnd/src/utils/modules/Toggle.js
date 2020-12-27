var headers = {
  Authorization: localStorage.getItem("token"),
};

export default {
  handleCourseSwitch (self, role, id) {
    var data = {
      courseId: id,
    };
    self.api.course.getCourseDetail(data, headers).then((res) => {
      var response = res.data;
      // 处理response，不知道长啥样，先不管
      var item = {
        id: response.courseId,
        name: response.courseName,
        teacher: response.teacherName,
        grade: response.grade,
        time: response.courseTime,
        description: response.description,
        status: response.status,
      };
      console.log(item);
      self.$store.dispatch("setCurrentCourse", item);
      self.$router.push({
        path: "/" + role + "/course/" + id,
      });
    });
  },

  handleCourseSwitchByItem (self, role, item) {
    self.$store.dispatch("setCurrentCourse", item);
    self.$router.push({
      path: "/" + role + "/course/" + item.id,
    });
  },

  handleWorkSwitch (self, role, id, courseName) {
    var data = {
      workId: id,
    };
    self.api.work.getWorkDetail(data, headers).then((res) => {
      var response = res.data;
      var item = {
        id: response.homeworkId,
        courseId: response.courseId,
        name: response.homeworkName,
        startTime: response.startTime,
        endTime: response.endTime,
        status: response.status,
        type: response.isExam == 0 ? "Homework" : "Exam",
      };
      if (typeof courseName !== "undefined") {
        var course = {
          id: response.courseId,
          name: courseName,
        };
        self.$store.dispatch("setCurrentCourse", course);
      }

      if (role == "teacher") {
        self.utils.statusHandler.handleTeacherWork(self, item);
      } else {
        if (item.type == "Homework") {
          self.utils.statusHandler.handleStudentHomework(self, item);
        } else {
          self.utils.statusHandler.handleStudentExam(self, item);
        }
      }

      self.$store.dispatch("setCurrentWork", item);
      self.$router.push({
        path: "/" + role + "/" + item.type.toLowerCase() + "/" + id,
      });
    });
  },

  handleWorkSwitchByItem (self, role, item) {
    self.$store.dispatch("setCurrentWork", item);
    self.$router.push({
      path: "/" + role + "/" + item.type.toLowerCase() + "/" + item.id,
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
