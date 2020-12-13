export default {
  handleCourseSwitch (self, item) {
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
        path: "/index/course/" + item.id,
      });
    });
  },
  handleHomeworkSwitch (self, item) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
      self.$store.dispatch("setCurrentHomework", item);
      self.$router.push({
        path: "/index/homework/" + item.id,
      });
    });
  },
  handleExamSwitch (self, examId) {

  },
  handleProblemSwitch (self, item, prePage) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
      self.$store.dispatch("setCurrentProblem", item);
      self.$router.push({
        path: "/index/problem/" + item.id,
        query: {
          source: prePage,
        },
      });
    });
  },
}