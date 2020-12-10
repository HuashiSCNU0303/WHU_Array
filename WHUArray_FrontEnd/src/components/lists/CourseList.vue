<template>
  <div>
    <a-table :columns="columns" :data-source="data">
    </a-table>
  </div>
</template>

<script>
const columns = [
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
