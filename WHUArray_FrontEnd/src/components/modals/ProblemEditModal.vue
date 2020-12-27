<template>
  <div>
    <a-modal
      v-model="visible"
      :title="title"
      @ok="handleOk(problem, testCaseList)"
      @cancel="handleCancel"
      ok-text="提交"
      cancel-text="取消"
    >
      <a-form :self-update="true">
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
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="选择标签"
        >
          <div>
            <template v-for="(tag, index) in problem.tags">
              <a-tooltip v-if="tag.length > 20" :key="tag" :title="tag">
                <a-tag :key="tag" :closable="true" @close="handleClose(index)">
                  {{ `${tag.slice(0, 20)}...` }}
                </a-tag>
              </a-tooltip>
              <a-tag v-else :key="tag" :closable="true" @close="handleClose(index)">
                {{ tag }}
              </a-tag>
            </template>
            <a-input
              v-if="inputVisible"
              ref="input"
              type="text"
              size="small"
              :style="{ width: '78px' }"
              :value="inputValue"
              @change="handleInputChange"
              @blur="handleInputConfirm"
              @keyup.enter="handleInputConfirm"
            />
            <a-tag
              v-else
              style="background: #fff; borderstyle: dashed"
              @click="showInput"
            >
              <a-icon type="plus" /> New Tag
            </a-tag>
          </div>
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
            <span slot="input" slot-scope="text, record, index">
              <!--<a-input v-model="record.input" />-->
              <a-input @change="(e) => handleChange(e.target.value, index, 'input')" />
            </span>
            <span slot="output" slot-scope="text, record, index">
              <!-- <a-input v-model="record.output" /> -->
              <a-input @change="(e) => handleChange(e.target.value, index, 'output')" />
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
      inputVisible: false,
      inputValue: "",
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
        tags: [],
      },
      testCaseList: [],
    };
  },
  computed: {
    ...mapState({
      columns: (state) => state.tableProto.teacher.testCaseListCol,
    }),
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },

  props: {
    problemId: {
      type: Number,
    },
    workId: {
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
      var _this = this;
      // 只传id的时候不需要提交记录了，是老师要编辑题目
      var data = {
        problemId: this.problemId,
      };
      this.api.problem.getProblemDetail(data, this.headers).then((res) => {
        var response = res.data;
        // 对response做处理
        console.log(response);
        var problem_ = response.question;
        // problem_temp是获取到的problem对象
        var problemTags = problem_.tag.split(";");
        problemTags.splice(problemTags.length - 1, 1);
        var problem_temp = {
          id: _this.problemId,
          name: problem_.questionName,
          // 需要注意把\n变成<br />
          description: problem_.questionContent,
          // 空格转&nbsp; \n转<br />
          testCases: problem_.useCases,
          tags: problemTags,
        };
        console.log(problem_temp);
        _this.problem = problem_temp;
        _this.testCaseList = JSON.parse(JSON.stringify(_this.problem.testCases));
      });
    },
    deleteTestCase(index) {
      this.problem.testCases.splice(index, 1);
      this.problem.testCaseList.splice(index, 1);
    },
    addTestCase() {
      this.problem.testCases.push({
        input: "",
        output: "",
        homeworkId: this.workId,
        questionId: this.problemId,
      });
      this.testCaseList.push({
        input: "",
        output: "",
        homeworkId: this.workId,
        questionId: this.problemId,
      });
    },

    handleClose(index) {
      this.problem.tags.splice(index, 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(function () {
        this.$refs.input.focus();
      });
    },

    handleInputChange(e) {
      this.inputValue = e.target.value;
    },

    handleInputConfirm() {
      const inputValue = this.inputValue;
      let tags = this.problem.tags;
      if (inputValue && tags.indexOf(inputValue) === -1) {
        tags = [...tags, inputValue];
      }
      console.log(tags);
      this.inputVisible = false;
      this.inputValue = "";
      this.problem.tags = tags;
    },

    handleChange(value, index, column) {
      if (column == "input") {
        this.testCaseList[index].input = value;
      } else if (column == "output") {
        this.testCaseList[index].output = value;
      }
    },

    getRecordId(record) {
      return record.input;
    },
  },
};
</script>

<style scoped></style>
