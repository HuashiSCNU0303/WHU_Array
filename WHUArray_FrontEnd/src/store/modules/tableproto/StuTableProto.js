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
        dataIndex: "name",
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
        scopedSlots: { customRender: "score" },
      },
    ],
    homeworkListCol: [
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
        dataIndex: "name",
        key: "name",
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
        scopedSlots: { customRender: "score" },
      },
    ],
    problemListCol: [
      {
        title: "题目号",
        dataIndex: "id",
        key: "id",
      },
      {
        title: "题目名",
        dataIndex: "name",
        key: "name",
        scopedSlots: { customRender: "questionName" },
      },
      {
        title: "来源课程",
        dataIndex: "courseName",
        key: "problemCourse",
      },
      {
        title: "来源作业/考试名",
        dataIndex: "workName",
        key: "problemWork",
      },
      {
        title: "标签",
        dataIndex: "tags",
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
    problemListBgCol: [
      {
        title: "题目号",
        dataIndex: "questionId",
        key: "questionId",
      },
      {
        title: "题目名",
        dataIndex: "questionName",
        key: "questionName",
        scopedSlots: { customRender: "questionName" },
      },
      {
        title: "标签",
        dataIndex: "tags",
        key: "problemTags",
        scopedSlots: { customRender: "problemTags" },
      },
    ],
    courseListCol: [
      {
        title: "课程号",
        dataIndex: "id",
        key: "courseId",
      },
      {
        title: "课程名",
        dataIndex: "name",
        key: "courseName",
        scopedSlots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        title: "教师",
        dataIndex: "teacher",
        key: "teacher",
        scopedSlots: {
          filterDropdown: 'filterDropdown',
          filterIcon: 'filterIcon',
          customRender: 'customRender',
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        title: "年级",
        dataIndex: "grade",
        key: "grade",
        filters: [],
        onFilter: "",
      },
      {
        title: "课程描述",
        dataIndex: "description",
        key: "courseDescription",
      },
      {
        title: "是否已选",
        dataIndex: "isSelected",
        key: "isSelect",
        filters: [
          {
            text: "已选",
            value: "true",
          },
          {
            text: "未选",
            value: "false",
          },
        ],
        onFilter: "",
        scopedSlots: { customRender: "isSelected" },
      },
      {
        title: "操作",
        dataIndex: "operation",
        key: "operation",
        scopedSlots: { customRender: "operation" },
      }
    ],
  },
  mutations: {},
  actions: {},
};
