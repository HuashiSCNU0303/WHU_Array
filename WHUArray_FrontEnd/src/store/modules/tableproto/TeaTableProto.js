export default {
  state: {
    problemListCol: [
      {
        title: "题目号",
        dataIndex: "id",
        key: "questionId",
      },
      {
        title: "题目名",
        dataIndex: "name",
        key: "questionName",
      },
      {
        title: "",
        dataIndex: "edit",
        scopedSlots: { customRender: "edit" },
      },
      {
        title: "",
        dataIndex: "see",
        scopedSlots: { customRender: "see" },
      },
    ],
    submitRecordCol: [
      {
        title: "题目号",
        dataIndex: "id",
        key: "questionId",
      },
      {
        title: "题目名",
        dataIndex: "name",
        key: "questionName",
      },
      {
        title: "分数",
        dataIndex: "score",
        scopedSlots: { customRender: "score" },
      },
    ],
    testCaseListCol: [
      {
        title: "输入",
        dataIndex: "input",
        key: "input",
        scopedSlots: { customRender: "input" },
      },
      {
        title: "输出",
        dataIndex: "output",
        key: "output",
        scopedSlots: { customRender: "output" },
      },
      {
        title: "",
        dataIndex: "delete",
        key: "delete",
        scopedSlots: { customRender: "delete" },
      },
    ],
  },
  mutations: {},
  actions: {},
};
