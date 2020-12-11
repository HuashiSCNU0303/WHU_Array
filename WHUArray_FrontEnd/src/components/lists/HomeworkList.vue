<template>
  <div>
    <a-table :columns="columns" :data-source="data">
      <span slot="courseName" slot-scope="courseName">
        <a @click="handleCourseSwitch">{{ courseName }}</a>
      </span>
      <span slot="homeworkName" slot-scope="homeworkName">
        <a @click="handleHomeworkSwitch">{{ homeworkName }}</a>
      </span>
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
];

export default {
  data() {
    return {
      columns,
    };
  },
  props: {
    currentPage: {
      type: String,
    },
    data: {
      type: Array,
    },
  },
  mounted() {
    if (typeof this.currentPage !== "undefined" && this.currentPage == "CourseHomework") {
      columns.splice(2, 2);
    }
  },
  methods: {
    handleHomeworkSwitch() {
      // 跳转到具体作业
    },
    handleCourseSwitch() {
      // 跳转到具体课程
    },
  },
};
</script>

<style scoped></style>
