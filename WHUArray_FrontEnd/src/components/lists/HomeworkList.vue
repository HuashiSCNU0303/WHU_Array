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
      homeworkListCol: (state) => state.tableColProto.homeworkListCol,
    }),
  },
  mounted() {
    this.columns = JSON.parse(JSON.stringify(this.homeworkListCol));
    if (typeof this.currentPage !== "undefined" && this.currentPage == "CourseHomework") {
      this.columns.splice(2, 2);
    }
  },
  methods: {
    handleHomeworkSwitch() {
      // 跳转到具体作业
      var item = {
        id: 1,
        name: "第一次作业",
        status: "进行中",
        endTime: "2020-12-14 08:00",
        score: 0,
      };
      this.utils.toggle.handleHomeworkSwitch(this, item);
    },
    handleCourseSwitch() {
      // 跳转到具体课程
    },
  },
};
</script>

<style scoped></style>
