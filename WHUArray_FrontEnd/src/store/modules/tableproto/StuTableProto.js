export default {
  state: {
    examListCol: [
      {
        title: "状态",
        dataIndex: "status",
        key: "status",
        scopedSlots: { customRender: "statusTags" },
      },
      {
        // 搜索
        title: "课程名",
        dataIndex: "courseName",
        key: "courseName",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
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
        // 排序
        title: "距离开始还有",
        dataIndex: "remainingTime",
        key: "remainingTime",
        sorter: "",
        scopedSlots: { customRender: "remainingTime" },
      },
      {
        title: "分数",
        dataIndex: "score",
        key: "score",
        sorter: "",
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
        // 搜索
        title: "课程名",
        dataIndex: "courseName",
        key: "courseName",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
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
        // 排序
        title: "距离截止还有",
        dataIndex: "remainingTime",
        key: "remainingTime",
        sorter: "",
        scopedSlots: { customRender: "remainingTime" },
      },
      {
        title: "分数",
        dataIndex: "score",
        key: "score",
        sorter: "",
        scopedSlots: { customRender: "score" },
      },
    ],
    problemListCol: [
      {
        // 搜索
        title: "题目号",
        dataIndex: "id",
        key: "id",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        // 搜索
        title: "题目名",
        dataIndex: "name",
        key: "name",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
        scopedSlots: { customRender: "questionName" },
      },
      {
        // 搜索
        title: "来源课程",
        dataIndex: "courseName",
        key: "problemCourse",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        title: "来源作业/考试名",
        dataIndex: "workName",
        key: "problemWork",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        // 筛选
        title: "标签",
        dataIndex: "tags",
        key: "problemTags",
        filters: [],
        onFilter: "",
        scopedSlots: { customRender: "problemTags" },
      },
      {
        // 筛选
        title: "是否已做",
        dataIndex: "isDone",
        key: "isDone",
        filters: [
          {
            text: "已做",
            value: "true",
          },
          {
            text: "未做",
            value: "false",
          },
        ],
        onFilter: "",
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
        dataIndex: "id",
        key: "courseId",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        title: "课程名",
        dataIndex: "name",
        key: "courseName",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
        },
        onFilter: "",
        onFilterDropdownVisibleChange: "",
      },
      {
        title: "教师",
        dataIndex: "teacher",
        key: "teacher",
        scopedSlots: {
          filterDropdown: "filterDropdown",
          filterIcon: "filterIcon",
          customRender: "customRender",
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
      },
    ],
  },
  mutations: {},
  actions: {},
};
