<template>
  <div>
    <a-modal
      v-model="visible"
      :title="title"
      @ok="handleOk(problem)"
      @cancel="handleCancel"
      ok-text="提交"
      cancel-text="取消"
    >
      <a-form>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="题目号"
        >
          {{ problem.id }}
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="题目名"
        >
          <a-input v-model="problem.name" />
        </a-form-item>
        <a-form-item label="题目内容">
          <a-textarea v-model="problem.description" rows="4" />
        </a-form-item>
        <a-form-item label="测试样例">
          <a-row>
            <a-button type="primary" @click="addTestCase" icon="plus-circle" size="small"
              >添加测试用例</a-button
            >
          </a-row>
          <a-table
            :columns="columns"
            :data-source="problem.testCases"
            :row-key="getRecordId"
            size="small"
          >
            <span slot="input" slot-scope="text, record">
              <a-input v-model="record.input" />
            </span>
            <span slot="output" slot-scope="text, record">
              <a-input v-model="record.output" />
            </span>
            <span slot="delete" slot-scope="text, record, index">
              <a-icon
                type="delete"
                theme="twoTone"
                two-tone-color="#ff4d4f"
                @click="deleteTestCase(index)"
              />
            </span>
          </a-table>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      title: "编辑题目",
      formItemLayout: {
        labelCol: { span: 4 },
        wrapperCol: { span: 20 },
      },
      problem: {
        id: "",
        name: "",
        description: "",
        testCases: [
          {
            input: "1 2",
            output: "3",
          },
          {
            input: "1 3",
            output: "4",
          },
          {
            input: "1 4",
            output: "5",
          },
        ],
      },
    };
  },
  computed: {
    ...mapState({
      columns: (state) => state.tableProto.teacher.testCaseListCol,
    }),
  },

  props: {
    problemId: {
      type: Number,
    },
    visible: {
      type: Boolean,
    },
    handleOk: {
      type: Function,
    },
    handleCancel: {
      type: Function,
    },
  },

  watch: {
    problemId: function (newVal) {
      this.getProblemInfo();
    },
  },

  methods: {
    getProblemInfo() {
      // 根据problemId获取题目信息
      this.problem = {
        id: this.problemId,
        name: "",
        description: "",
        testCases: [
          {
            input: "1 2",
            output: "3",
          },
          {
            input: "1 3",
            output: "4",
          },
          {
            input: "1 4",
            output: "5",
          },
          {
            input: "1 5",
            output: "6",
          },
        ],
      };
    },
    deleteTestCase(index) {
      this.problem.testCases.splice(index, 1);
    },
    addTestCase() {
      this.problem.testCases.push({
        input: "",
        output: "",
      });
    },
    getRecordId(record) {
      return record.input;
    },
  },
};
</script>

<style scoped></style>
