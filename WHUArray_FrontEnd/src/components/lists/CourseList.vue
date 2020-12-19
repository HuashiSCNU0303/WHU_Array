<template>
  <div>
    <a-table :columns="columns" :data-source="data" :row-key="getRecordId"> </a-table>
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
  computed: {
    ...mapState({
      courseListCol: (state) => state.tableProto.student.courseListCol,
    }),
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
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.courseListCol));
    if (typeof this.currentPage == "undefined") {
      return;
    }
    if (this.currentPage == "CourseMgmt") {
      this.columns.splice(2, 1);
      this.columns.splice(4, 1);
    }
  },
  methods: {
    getRecordId(record) {
      return record.id;
    },
  },
};
</script>

<style scoped></style>
