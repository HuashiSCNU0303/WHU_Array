<!--教师端布置作业/考试时的题目列表-->
<template>
  <a-table
    :columns="columns"
    :data-source="data"
    :custom-row="handleProblemSwitch"
    :row-key="getRecordId"
  />
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
  },
  computed: {
    ...mapState({
      problemListCol: (state) => state.tableColProto.problemListBgCol,
    }),
  },
  methods: {
    handleProblemSwitch(record, index) {
      return {
        on: {
          click: () => {
            // 右边的框跳转
            // this.utils.toggle.handleProblemSwitch(this, record, source);
          },
        },
      };
    },
    getRecordId(record) {
      return record.id;
    },
  },
};
</script>

<style scoped></style>
