<template>
  <a-popover>
    <div slot="content" class="countdown">
      <countdown :currentAnchorTime="1607788621000" />
      <score-display :data="type" />
    </div>
    <a-button type="primary" icon="clock-circle" class="countdown-btn" ghost
      >倒计时</a-button
    >
  </a-popover>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState({
      header: (state) => state.currentPageHeader.header,
    }),
    type: function () {
      if (this.header.breadCrumbLayer == "ProblemInHomework") {
        return "Homework";
      } else if (this.header.breadCrumbLayer == "ProblemInHomework") {
        return "Exam";
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
