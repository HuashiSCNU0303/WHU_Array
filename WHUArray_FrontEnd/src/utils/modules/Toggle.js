export default {
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
  handleHomeworkSwitch (self, role, item) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
      self.$store.dispatch("setCurrentHomework", item);
      self.$router.push({
        path: "/" + role + "/homework/" + item.id,
      });
    });
  },
  handleExamSwitch (self, examId) { },
  handleProblemSwitch (self, role, item, prePage) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
      self.$store.dispatch("setCurrentProblem", item);
      self.$router.push({
        path: "/" + role + "/problem/" + item.id,
        query: {
          source: prePage,
        },
      });
    });
  },
};
