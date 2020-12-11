export default {
  handleCourseSwitch (self, item) {
    const promise = new Promise(function (resolve, reject) {
      resolve("hello");
    });
    promise.then((res) => {
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
}