<template>
  <div>
    <big-title><p>考试列表</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers">
      <div slot="panel1_content">
        <exam-card v-if="currentExamData != null" :currentExamData="currentExamData" />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <stu-exam-list
          v-if="examList.length > 0"
          :data="examList"
          :currentPage="currentPage"
        />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emptyHints: ["当前没有进行中的考试", "当前没有其他考试"],
      headers: ["当前进行中的考试", "其他考试"],
      currentExamData: {},
      examList: [],
      isLoading: true,
      currentPage: "CourseExam",
    };
  },
  mounted() {
    this.getExams();
  },
  methods: {
    getExams() {
      // 获取考试列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.currentExamData = {
          title: "期末考试",
          content: "韩波",
          currentScore: "46",
          fullScore: "100",
          remainingTime: "30",
        };
        this.examList = this.$store.state.tempData.examList.examList;
        this.isLoading = false;
      }, 1000);
    },
  },
};
</script>

<style scoped></style>
