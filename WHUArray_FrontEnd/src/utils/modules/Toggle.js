export default {
  // 这里全部都要改成用id查询！！！
  handleCourseSwitch (self, role, item) {
    const promise = new Promise(function (resolve, reject) {
      // 调接口，异步操作，下面类似
      resolve("hello");
    });
    promise.then((res) => {
      if (item.description == "") {
        item.description = item.name;
      }
      self.$store.dispatch("setCurrentCourse", item);
      self.$router.push({
        path: "/" + role + "/course/" + item.id,
      });
    });
  },

  handleWorkSwitch (self, role, item) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
      console.log(item);
      self.$store.dispatch("setCurrentWork", item);
      self.$router.push({
        path: "/" + role + "/" + item.type.toLowerCase() + "/" + item.id,
      });
    });
  },

  handleProblemSwitch (self, item, prePage) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
      self.$store.dispatch("setCurrentProblem", item);
      var role = self.$store.state.curObj.user.user.role; // 获取当前用户的身份（学生/老师）
      self.$router.push({
        path: "/" + role + "/problem/" + item.id,
        query: {
          source: prePage,
        },
      });
    });
  },
};
