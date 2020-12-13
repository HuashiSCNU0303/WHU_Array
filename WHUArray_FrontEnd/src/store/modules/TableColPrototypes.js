export default {
  state: {
    examListCol: [
      /*{
          dataIndex: "name",
          key: "name",
          // title是属性，将column里的title这个属性配置为name="customTitle"的具名槽
          slots: { title: "customTitle" },
          // customRender是属性，将column里的customRender这个属性配置为name="name"的具名作用域槽
          scopedSlots: { customRender: "name" },
        },*/
      {
        title: "类别",
        dataIndex: "type",
        key: "type",
        scopedSlots: { customRender: "typeTags" },
      },
      {
        title: "状态",
        dataIndex: "status",
        key: "status",
        scopedSlots: { customRender: "statusTags" },
      },
      {
        title: "课程名",
        dataIndex: "courseName",
        key: "courseName",
        scopedSlots: { customRender: "courseName" },
      },
      {
        title: "教师",
        dataIndex: "teacher",
        key: "teacher",
      },
      {
        title: "考试名",
        dataIndex: "examName",
        key: "examName",
      },
      {
        title: "开始时间",
        dataIndex: "startTime",
        key: "startTime",
      },
      {
        title: "结束时间",
        dataIndex: "endTime",
        key: "endTime",
      },
      {
        title: "距离开始还有",
        dataIndex: "remainingTime",
        key: "remainingTime",
      },
      {
        title: "分数",
        dataIndex: "score",
        key: "score",
      },
    ],
    homeworkListCol: [
      {
        title: "类别",
        dataIndex: "type",
        key: "type",
        scopedSlots: { customRender: "typeTags" },
      },
      {
        title: "状态",
        dataIndex: "status",
        key: "status",
        scopedSlots: { customRender: "statusTags" },
      },
      {
        title: "课程名",
        dataIndex: "courseName",
        key: "courseName",
        scopedSlots: { customRender: "courseName" },
      },
      {
        title: "教师",
        dataIndex: "teacher",
        key: "teacher",
      },
      {
        title: "作业名",
        dataIndex: "homeworkName",
        key: "homeworkName",
        scopedSlots: { customRender: "homeworkName" },
      },
      {
        title: "结束时间",
        dataIndex: "endTime",
        key: "endTime",
      },
      {
        title: "距离截止还有",
        dataIndex: "remainingTime",
        key: "remainingTime",
      },
      {
        title: "分数",
        dataIndex: "score",
        key: "score",
      },
    ],
    problemListCol: [
      {
        title: "题目号",
        dataIndex: "problemId",
        key: "problemId",
      },
      {
        title: "题目名",
        dataIndex: "problemName",
        key: "problemName",
        scopedSlots: { customRender: "problemName" },
      },
      {
        title: "来源课程",
        dataIndex: "problemCourse",
        key: "problemCourse",
      },
      {
        title: "来源作业/考试名",
        dataIndex: "problemWork",
        key: "problemWork",
      },
      {
        title: "标签",
        dataIndex: "problemTags",
        key: "problemTags",
        scopedSlots: { customRender: "problemTags" },
      },
      {
        title: "是否已做",
        dataIndex: "isDone",
        key: "isDone",
        scopedSlots: { customRender: "isDone" },
      },
      {
        title: "分数",
        dataIndex: "score",
        key: "score",
        scopedSlots: { customRender: "score" },
      },
    ],
    courseListCol: [
      {
        title: "课程号",
        dataIndex: "courseId",
        key: "courseId",
      },
      {
        title: "课程名",
        dataIndex: "courseName",
        key: "courseName",
      },
      {
        title: "教师",
        dataIndex: "teacher",
        key: "teacher",
      },
      {
        title: "年级",
        dataIndex: "grade",
        key: "grade",
      },
      {
        title: "课程描述",
        dataIndex: "courseDescription",
        key: "courseDescription",
      },
    ],
  },
  mutations: {

  },
  actions: {

  }
}