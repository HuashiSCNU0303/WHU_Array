<template>
  <div>
    <big-title><p>题目列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <a-row style="margin-bottom: 16px">
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
      />
      <icon-hint v-else :hint="hints[0]" />
      <a-row v-if="editable == false" style="margin-top: 16px">
        <a-button type="primary" icon="user" @click="addCourse" style="float: left"
          >学生做题记录</a-button
        >
      </a-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      problemList: [],
      isLoading: true,
      emptyHint: "当前尚未添加题目",
    };
  },
  props: {
    editable: {
      type: Boolean,
    },
    workId: {
      type: Number,
    },
    type: {
      type: String,
    }
  },
  mounted() {
    this.getProblems();
  },
  methods: {
    getProblems() {
      // 获取这个作业的题目列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.problemList = this.$store.state.tempData.problemList.problemList;
        this.isLoading = false;
      }, 1000);
    },

    switchToProblem(record) {
      this.$router.push({
        path: "/teacher/" + this.type.toLowerCase() + "/" + this.workId + "/" + record.id,
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
    
  },
};
</script>

<style scoped></style>
