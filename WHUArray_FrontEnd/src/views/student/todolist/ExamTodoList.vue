<template>
  <div>
    <big-title><p>考试列表</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers">
      <div slot="panel1_content">
        <exam-card v-if="currentExamData != null" :currentExamData="currentExamData" />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <stu-exam-list v-if="examList.length > 0" :data="examList" />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentExamData: null,
      examList: [],
      emptyHints: ["当前没有进行中的考试", "当前没有准备开始的考试"],
      headers: ["当前进行中的考试", "准备开始的考试"],
      isLoading: true,
    };
  },
  mounted() {
    this.getExams();
  },
  methods: {
    getExams() {
      var _this = this;
      getStuExamList().then((res) => {
        // 从列表中取出未发布的考试
        // 数据处理，计算每一个考试的剩余时间
        // 如果有已发布的考试，证明这个考试正在进行中，进行数据处理，计算这个考试的currentExamData
        _this.examList = [];
        _this.isLoading = false;
      });
      setTimeout(() => {
        this.examList = this.$store.state.tempData.examList.examList;
        this.currentExamData = {
          title: "系统级程序设计 期末考试",
          content: "韩波",
          currentScore: "46",
          fullScore: "100",
          remainingTime: "30",
        };
        this.isLoading = false;
      }, 1000);
    },
  },
};
</script>

<style scoped>
.content {
  margin-left: -16px !important;
  margin-right: -16px !important;
}

.ant-collapse > .ant-collapse-item > .ant-collapse-header .ant-collapse-arrow {
  left: 0px !important;
}
</style>
