<template>
  <div>
    <span v-if="score != -1" class="content"
      ><a-tag color="red"><a-icon type="smile" />&nbsp;已提交</a-tag>&nbsp; 分数：{{
        score
      }}</span
    >
    <a-tag v-else><a-icon type="frown" />&nbsp;未提交</a-tag>
    <a-button
      v-if="type == 'Homework' || type == 'Exam'"
      type="primary"
      @click="handleClick"
      :disabled="btnDisabled"
      class="submit-btn"
    >
      <span v-if="score == -1 && !btnDisabled">提交</span>
      <span v-else-if="!btnDisabled">再次提交</span>
      <span v-else>已经结束</span>
    </a-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      btnDisabled: false,
    };
  },
  props: {
    score: {
      type: Number,
    },
    time: {
      type: Number,
    },
    type: {
      type: String,
    }
  },
  mounted() {
    this.setBtnDisabled();
  },
  methods: {
    setBtnDisabled() {
      var timeStamp = new Date().valueOf();
      var remainingTime = this.time - timeStamp;
      if (remainingTime <= 0) {
        this.btnDisabled = true;
      } else {
        var timer = setInterval(() => {
          remainingTime -= 1000;
          if (remainingTime <= 0) {
            this.btnDisabled = true;
            clearInterval();
          }
        }, 1000);
      }
    },
    handleClick() {
      if (this.type == "Homework") {
        // 提交作业
        // this.$store.dispatch("set");
      } else if (this.type == "Exam") {
        // 交卷
      }
    },
  },
};
</script>

<style scoped>
.content {
  font-size: 16px;
  line-height: 30px;
  color: #ff4d4f;
}

.ant-tag {
  font-size: 16px;
  line-height: 30px;
}

.submit-btn {
  line-height: 30px;
  margin-left: 48px;
}
</style>
