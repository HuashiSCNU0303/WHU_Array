<template>
  <a-popover>
    <div slot="content" class="countdown">
      <countdown :time="parent.time" />
      <score-display :score="parent.score" :time="parent.time" :type="parent.type" />
    </div>
    <a-button type="primary" icon="clock-circle" class="countdown-btn" ghost
      >{{ parent.type == "Homework" ? "作业" : "考试" }}倒计时</a-button
    >
  </a-popover>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState({
      pageType: (state) => state.currentPage.type,
      homework: (state) => state.currentHomework.homework,
      exam: (state) => state.currentExam.exam,
    }),
    parent: function () {
      if (this.pageType == "ProblemInHomework") {
        return {
          type: "Homework",
          time: this.homework.endTime,
          score: this.homework.score,
        };
      } else if (this.pageType == "ProblemInExam") {
        return {
          type: "Exam",
          time: this.exam.endTime,
          score: this.exam.score,
        };
      }
    },
  },
};
</script>

<style scoped>
.countdown-btn {
  margin-right: 64px;
}

.countdown {
  text-align: center;
}
</style>
