<template>
  <div>
    <div v-if="showTime == true">
      <div class="title">
        <span><a-icon type="clock-circle" spin />&nbsp;倒计时</span>
      </div>
      <div v-html="remainingTimeStr" class="content"></div>
    </div>
    <icon-hint v-else :hint="hint" :icon="user" />
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
    };
  },
  props: {
    currentAnchorTime: {
      type: Number,
    },
  },
  mounted() {
    var timeStamp = new Date().valueOf();
    this.remainingTime = this.currentAnchorTime - timeStamp;
    console.log(this.currentAnchorTime);
    console.log(this.remainingTime);
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
  font-size: 20px;
  line-height: 28px;
  padding-bottom: 12px;
  margin-top: -40px;
}
.content {
  color: rgba(0, 0, 0, 0.65);
  font-size: 16px;
}
</style>
