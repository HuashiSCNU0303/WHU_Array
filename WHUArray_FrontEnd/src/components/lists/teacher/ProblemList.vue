<!--教师端布置作业/考试时的题目列表-->
<template>
  <div>
    <a-table :columns="columns" :data-source="data" :row-key="getRecordId">
      <span slot="edit" slot-scope="text, record, index">
        <span @click="openEditModal(record, index)" style="color: #1890ff"
          ><a-icon type="edit" />&nbsp;编辑</span
        >
        &nbsp; &nbsp;
        <span @click="deleteProblem(record, index)" style="color: #ff4d4f"
          ><a-icon type="delete" />&nbsp;删除</span
        >
      </span>
      <span slot="see" slot-scope="text, record">
        <span @click="switchToProblem(record)" style="color: #52c41a"
          ><a-icon type="eye" />&nbsp;查看</span
        >
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
    switchToProblem: {
      type: Function,
    },
    deleteProblem: {
      type: Function,
    },
    openEditModal: {
      type: Function,
    },
    editable: {
      type: Boolean,
    },
  },
  computed: {
    ...mapState({
      problemListCol: (state) => state.tableProto.teacher.problemListCol,
    }),
  },
  mounted() {
    this.columns = JSON.parse(JSON.stringify(this.problemListCol));
    if (this.editable == false) {
      this.columns.splice(2, 1);
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
