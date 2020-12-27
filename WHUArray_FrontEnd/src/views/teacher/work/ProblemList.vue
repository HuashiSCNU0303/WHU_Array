<template>
  <div>
    <big-title><p>题目列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <a-row v-if="editable == true" style="margin-bottom: 16px">
        <a-button type="primary" icon="plus" @click="addProblem" style="float: left"
          >添加题目</a-button
        >
      </a-row>
      <tea-problem-list
        v-if="problemList.length > 0"
        :data="problemList"
        :editable="editable"
        :switchToProblem="switchToProblem"
        :deleteProblem="deleteProblem"
        :openEditModal="openEditModal"
      />
      <icon-hint v-else :hint="emptyHint" />
      <a-row v-if="editable == false" style="margin-top: 16px">
        <a-button type="primary" icon="user" @click="switchToRecord" style="float: left"
          >学生做题记录</a-button
        >
      </a-row>
    </div>
    <problem-edit-modal
      :visible="modalVisible"
      :problemId="curEditProblemId"
      :workId="work.id"
      :handleOk="editProblem"
      :handleCancel="closeEditModal"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      modalVisible: false,
      curEditProblemId: -1,
      curEditProblemIndex: -1,
      problemList: [],
      isLoading: true,
      emptyHint: "当前尚未添加题目",
    };
  },
  props: {
    editable: {
      type: Boolean,
    },
    switchToProblem: {
      type: Function,
    },
    switchToRecord: {
      type: Function,
    },
  },
  computed: {
    ...mapState({
      work: (state) => state.curObj.work.work,
      headers() {
        return {
          Authorization: localStorage.getItem("token"),
        };
      },
    }),
  },
  mounted() {
    this.getProblems();
  },
  methods: {
    getProblems() {
      // 获取这个作业/考试的题目列表
      var _this = this;
      var data = {
        workId: this.work.id,
      };
      this.api.teacher.getWorkProblems(data, this.headers).then((res) => {
        var response = res.data;
        // 对response做处理，搞出problemList
        console.log(response);
        var problems = response;
        for (var i = 0; i < problems.length; i++) {
          var problem_ = problems[i].question;
          var problemTags = problem_.tag.split(";");
          problemTags.splice(problemTags.length - 1, 1);
          var problem = {
            id: problem_.questionId,
            name: problem_.questionName,
            // tags: ["算法", "数据结构", "链表", "位表示"],
            tags: problemTags,
          };
          _this.problemList.push(problem);
        }
        _this.isLoading = false;
      });
    },

    deleteProblem(record, index) {
      var _this = this;
      this.$confirm({
        title: "你确定要删除该题目吗？",
        okText: "确定",
        okType: "danger",
        cancelText: "取消",
        onOk() {
          // 删除这道题，调后端接口
          var data = {
            id: record.id,
          };
          _this.api.teacher.delProblem(data, _this.headers).then((res) => {
            _this.problemList.splice(index, 1);
          });
        },
        onCancel() {},
      });
    },

    addProblem() {
      // 先传上服务器，得到一个新的ID以后再回来
      var _this = this;
      var data = {
        homeworkId: this.work.id,
        questionName: "",
        questionContent: "",
        tag: "",
        status: "unpublished",
      };
      console.log(data);
      this.api.teacher.addProblem(data, this.headers).then((res) => {
        var response = res.data;
        // 对response进行处理，得到题号
        var newId = response;
        this.problemList.push({
          id: newId,
          name: "",
        });
      });
    },

    openEditModal(record, index) {
      this.curEditProblemId = record.id;
      this.curEditProblemIndex = index;
      this.modalVisible = true;
    },

    closeEditModal() {
      this.modalVisible = false;
    },

    editProblem(problem, testCaseList) {
      // 提交编辑好的题目（problem）到后台
      var _this = this;
      var tagStr = "";
      problem.tags.forEach((tag) => {
        tagStr += tag + ";";
      });
      var data = {
        questionId: problem.id,
        homeworkId: this.work.id,
        questionName: problem.name,
        questionContent: problem.description,
        tag: tagStr,
        useCases: testCaseList,
      };
      console.log(data);
      this.api.teacher.editProblem(data, this.headers).then((res) => {
        _this.$success({
          title: "编辑成功",
          onOk() {
            _this.problemList[_this.curEditProblemIndex].name = problem.name;
            _this.closeEditModal();
          },
        });
      });
    },
  },
};
</script>

<style scoped></style>
