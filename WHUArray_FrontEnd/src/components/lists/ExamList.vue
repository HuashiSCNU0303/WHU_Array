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
      examListCol: (state) => state.tableColProto.examListCol,
    }),
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.examListCol));
    if (typeof this.currentPage !== "undefined" && this.currentPage == "CourseExam") {
      this.columns.splice(2, 2);
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
