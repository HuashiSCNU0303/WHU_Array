<template>
  <div>
    <a-table :row-key="getRecordId" :columns="columns" :data-source="data">
      <span slot="courseName" slot-scope="text, record">
        <a @click="handleCourseSwitch(record)">{{ text }}</a>
      </span>
      <span slot="homeworkName" slot-scope="text, record">
        <a @click="handleHomeworkSwitch(record)">{{ text }}</a>
      </span>
      <span slot="statusTags" slot-scope="statusTags">
        <a-tag :key="statusTags" :color="statusTags === '进行中' ? 'volcano' : 'green'">
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
    currentPage: {
      type: String,
    },
    data: {
      type: Array,
    },
  },
  computed: {
    ...mapState({
      homeworkListCol: (state) => state.tableProto.student.homeworkListCol,
    }),
  },
  mounted() {
    this.columns = JSON.parse(JSON.stringify(this.homeworkListCol));
    if (typeof this.currentPage !== "undefined" && this.currentPage == "CourseHomework") {
      this.columns.splice(1, 2);
    }
  },
  methods: {
    handleHomeworkSwitch(record) {
      // 跳转到具体作业
      // 还要设置当前课程，待后端接口完成后再做
      this.utils.toggle.handleWorkSwitch(this, "student", record);
    },
    handleCourseSwitch(record) {
      // 跳转到具体课程，待后端接口完成后再做
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
