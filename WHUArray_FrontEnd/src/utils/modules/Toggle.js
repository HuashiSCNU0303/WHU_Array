export default {
  handleCourseSwitch (self, role, id) {
    self.api.course.getCourseDetail().then((res) => {
      var response = res.data;
      // 处理response，不知道长啥样，先不管
      self.$store.dispatch("setCurrentCourse", item);
      self.$router.push({
        path: "/" + role + "/course/" + id,
      });
    });
  },

  handleWorkSwitch (self, role, id) {
    self.api.work.getWorkDetail().then((res) => {
      var response = res.data;
      // 处理response，不知道长啥样，先不管
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
