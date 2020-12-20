<template>
  <div>
    <big-title><p>题目列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <a-row v-if="editable == true" style="margin-bottom: 16px">
        <a-button type="primary" icon="user" @click="addProblem" style="float: left"
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
      <icon-hint v-else :hint="hints[0]" />
      <a-row v-if="editable == false" style="margin-top: 16px">
        <a-button type="primary" icon="user" @click="switchToRecord" style="float: left"
          >学生做题记录</a-button
        >
      </a-row>
    </div>
    <problem-edit-modal
      :visible="modalVisible"
      :problemId="curEditProblemId"
      :handleOk="editProblem"
      :handleCancel="closeEditModal"
    />
  </div>
</template>

<script>
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
  mounted() {
    this.getProblems();
  },
  methods: {
    getProblems() {
      // 获取这个作业/考试的题目列表
      setTimeout(() => {
        this.problemList = JSON.parse(
          JSON.stringify(this.$store.state.tempData.problemList.problemList)
        );
        this.isLoading = false;
      }, 1000);
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
          _this.problemList.splice(index, 1);
        },
        onCancel() {},
      });
    },

    addProblem() {
      // 先传上服务器，得到一个新的ID以后再回来
      this.problemList.push({
        id: 3,
        name: "设计树",
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

    editProblem(problem) {
      // 提交编辑好的题目（problem）到后台

      var _this = this;
      this.$success({
        title: "编辑成功",
        onOk() {
          _this.problemList[_this.curEditProblemIndex].name = problem.name;
          _this.closeEditModal();
        },
      });
    },
  },
};
</script>

<style scoped></style>
