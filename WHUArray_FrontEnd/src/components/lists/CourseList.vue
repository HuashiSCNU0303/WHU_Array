<template>
  <div>
    <a-table :columns="columns" :data-source="data"> </a-table>
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
      courseListCol: (state) => state.tableColProto.courseListCol,
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
    }
    console.log(this.data);
  },
};
</script>

<style scoped></style>
