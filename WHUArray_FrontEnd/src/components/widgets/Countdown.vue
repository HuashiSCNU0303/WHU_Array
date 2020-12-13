<template>
  <div>
    <div v-if="showTime == true">
      <div class="title">
        <span><a-icon type="clock-circle" spin />&nbsp;倒计时</span>
        <span>&nbsp; </span>
        <span v-html="remainingTimeStr"></span>
      </div>
    </div>
    <icon-hint v-else :hint="hint" :icon="user" style="margin: 0" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      remainingTime: -1,
      remainingTimeStr: "/&nbsp;分&nbsp;/&nbsp;秒",
      showTime: true,
      hint: "已经结束啦",
      user: "user",
    };
  },
  props: {
    time: {
      type: Number,
    },
  },
  mounted() {
    var timeStamp = new Date().valueOf();
    this.remainingTime = this.time - timeStamp;
    if (this.remainingTime <= 0) {
      this.showTime = false;
    } else {
      var timer = setInterval(() => {
        this.remainingTime -= 1000;
        this.remainingTimeStr = this.utils.countdown.getFullLeftTime(this.remainingTime);
        if (this.remainingTime <= 0) {
          this.showTime = false;
          clearInterval();
        }
      }, 1000);
    }
  },
};
</script>

<style scoped>
.title {
  color: #1890ff;
  font-size: 18px;
  line-height: 28px;
  padding-bottom: 12px;
}
</style>
