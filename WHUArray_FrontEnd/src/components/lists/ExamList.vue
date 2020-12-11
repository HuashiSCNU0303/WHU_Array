<template>
  <div>
    <a-table :columns="columns" :data-source="data">
      <span slot="courseName" slot-scope="courseName">
        <a @click="handleCourseSwitch">{{ courseName }}</a>
      </span>
      <span slot="typeTags" slot-scope="typeTags">
        <a-tag
          :key="typeTags"
          :color="
            typeTags === '考试' ? 'green' : typeTags === '作业' ? 'volcano' : 'geekblue'
          "
        >
          {{ typeTags }}
        </a-tag>
      </span>
      <span slot="statusTags" slot-scope="statusTags">
        <a-tag
          :key="statusTags"
          :color="
            statusTags === '准备开始'
              ? 'volcano'
              : statusTags === '已结束'
              ? 'green'
              : 'geekblue'
          "
        >
          {{ statusTags }}
        </a-tag>
      </span>
    </a-table>
  </div>
</template>

<script>
const columns = [
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
    currentPage: {
      type: String,
    },
  },
  mounted() {
    if (typeof this.currentPage !== "undefined" && this.currentPage == "CourseExam") {
      columns.splice(2, 2);
    }
  },
  methods: {
    handleCourseSwitch() {
      // 跳转到id对应的课程
    },
  },
};
</script>

<style scoped></style>
