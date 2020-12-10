<template>
  <div>
    <a-table :columns="columns" :data-source="data">
      <span slot="typeTags" slot-scope="typeTags">
        <a-tag :key="typeTags" :color="typeTags === '考试' ? 'green' : 'volcano'">
          {{ typeTags }}
        </a-tag>
      </span>
      <span slot="statusTags" slot-scope="statusTags">
        <a-tag :key="statusTags" :color="statusTags === '进行中' ? 'volcano' : 'green'">
          {{ statusTags }}
        </a-tag>
      </span>
    </a-table>
  </div>
</template>

<script>
const columns = [
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
    // 可以点击进入看课程详情
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
    // 可以进入作业的题目列表（已开放）
    title: "作业名",
    dataIndex: "homeworkName",
    key: "homeworkName",
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
];

const data = [
  {
    type: "作业",
    status: "进行中",
    courseName: "Linux原理与应用",
    teacher: "李文海",
    homeworkName: "期末大作业",
    endTime: "2020-12-15 20:30",
    remainingTime: "5天9小时",
    score: "/",
  },
  {
    type: "作业",
    status: "已完成",
    courseName: "数据结构",
    teacher: "文卫东",
    homeworkName: "第三次作业",
    endTime: "2019-12-15 20:30",
    remainingTime: "/",
    score: "/",
  },
];

export default {
  data() {
    return {
      columns,
    };
  },
  props: {
    data: {
      type: Array, //指定传入的类型
    },
  },
};
</script>

<style scoped></style>
