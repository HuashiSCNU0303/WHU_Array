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
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.courseListCol));
    if (typeof this.currentPage !== "undefined" && this.currentPage == "Course") {
      this.columns.splice(2, 2);
    }
  },
};
</script>

<style scoped></style>
