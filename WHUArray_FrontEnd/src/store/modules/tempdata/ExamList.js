export default {
  state: {
    examList: [
      {
        id: 1004,
        status: "published",
        courseId: 10047,
        courseName: "Windows原理与应用",
        teacher: "刘纪平",
        name: "期末考试",
        startTime: "2020-12-14 18:30:00",
        endTime: "2020-12-14 20:30:00",
        remainingTime: "4天18小时",
        score: -1,
        type: "Exam",
      },
      {
        id: 1005,
        status: "unpublished",
        courseId: 10048,
        courseName: "Windows原理与应用",
        teacher: "刘敏忠",
        name: "期中考试",
        startTime: "2020-12-14 21:30:00",
        endTime: "2020-12-14 23:30:00",
        remainingTime: "4天18小时",
        score: 98,
        type: "Exam",
      },
    ],
  },
  mutations: {

  },
  actions: {

  }
}