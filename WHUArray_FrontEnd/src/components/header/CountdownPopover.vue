<template>
  <a-popover>
    <div slot="content" class="countdown">
      <countdown :time="parent.time" />
      <score-display :score="parent.score" :time="parent.time" :type="parent.type" />
    </div>
    <a-button type="primary" icon="clock-circle" class="countdown-btn" ghost
      >{{ work.type == "Homework" ? "作业" : "考试" }}倒计时</a-button
    >
  </a-popover>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState({
      work: (state) => state.curObj.work.work,
    }),
    parent: function () {
      return {
        type: this.work.type,
        time: this.work.endTime,
        score: this.work.score,
      };
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
