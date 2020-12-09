<template>
  <div>
    <a-table :columns="columns" :data-source="data">
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
    // 可以进入考试的题目列表（当然并没有开放）
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
    title: "剩余时间",
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
    type: "考试",
    status: "准备开始",
    courseName: "Windows原理与应用",
    teacher: "刘纪平",
    examName: "期末考试",
    startTime: "2020-12-14 18:30",
    endTime: "2020-12-14 20:30",
    remainingTime: "4天18小时",
    score: "/",
  },
  {
    type: "考试",
    status: "已结束",
    courseName: "Windows原理与应用",
    teacher: "刘敏忠",
    examName: "期中考试",
    startTime: "2020-12-14 18:30",
    endTime: "2020-12-14 20:30",
    remainingTime: "4天18小时",
    score: "/",
  },
];

export default {
  data() {
    return {
      data,
      columns,
    };
  },
};
</script>

<style scoped></style>
