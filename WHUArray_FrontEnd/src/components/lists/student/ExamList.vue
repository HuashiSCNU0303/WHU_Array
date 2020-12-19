<template>
  <div>
    <a-table :columns="columns" :data-source="data" :row-key="getRecordId">
      <span slot="courseName" slot-scope="text, record">
        <a @click="handleCourseSwitch(record)">{{ text }}</a>
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
      <span slot="score" slot-scope="score">
        <div v-if="score != -1">{{ score }}</div>
      </span>
    </a-table>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      columns: [{}],
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
  computed: {
    ...mapState({
      examListCol: (state) => state.tableProto.student.examListCol,
    }),
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.examListCol));
    if (typeof this.currentPage !== "undefined" && this.currentPage == "CourseExam") {
      this.columns.splice(1, 2);
    }
  },
  methods: {
    handleCourseSwitch(record) {
      // 跳转到id对应的课程
      var item = {
        id: record.courseId,
        name: record.courseName,
        teacher: record.teacher,
        time: "2019-2020",
        description: "",
      };
      this.utils.toggle.handleCourseSwitch(this, item);
    },
    getRecordId(record) {
      return record.id;
    },
  },
};
</script>

<style scoped></style>
