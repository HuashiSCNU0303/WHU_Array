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
      {
        id: 10003,
        status: "published",
        courseId: 21,
        courseName: "计算机组成与设计",
        teacher: "赵小刚",
        name: "第一次作业",
        startTime: "2020-11-11 18:00:00",
        endTime: "2020-12-16 12:00:00",
        remainingTime: "/",
        score: 94,
        type: "Homework",
      },
      {
        id: 10004,
        status: "published",
        courseId: 21,
        courseName: "数据库系统",
        teacher: "李石君",
        name: "第一次作业",
        startTime: "2020-12-25 21:00:00",
        endTime: "2021-01-12 16:00:00",
        remainingTime: "/",
        score: -1,
        type: "Homework",
      },
    ],
  },
  mutations: {},
  actions: {},
};
