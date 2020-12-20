export default {
  state: {
    homeworkList: [
      {
        id: 10001,
        status: "unpublished",
        courseId: 17,
        courseName: "Linux原理与应用",
        teacher: "李文海",
        name: "期末大作业",
        startTime: "2020-10-16 12:00:00",
        endTime: "2020-12-26 18:00:00",
        remainingTime: "/",
        score: -1,
        type: "Homework",
      },
      {
        id: 10002,
        status: "published",
        courseId: 19,
        courseName: "数据结构",
        teacher: "文卫东",
        name: "第三次作业",
        startTime: "2020-11-28 18:00:00",
        endTime: "2020-12-24 12:00:00",
        remainingTime: "/",
        score: 99,
        type: "Homework",
      },
    ],
  },
  mutations: {},
  actions: {},
};
