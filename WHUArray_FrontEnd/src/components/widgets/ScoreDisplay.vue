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
import { mapState } from "vuex";
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
      type: String,
    },
    type: {
      type: String,
    },
  },
  mounted() {
    this.setBtnDisabled();
  },
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
      work: (state) => state.curObj.work.work,
      headers() {
        return {
          Authorization: localStorage.getItem("token"),
        };
      },
    }),
  },
  methods: {
    setBtnDisabled() {
      var timeStamp = new Date().valueOf();
      var anchorTimestamp = this.utils.countdown.transStringToTimestamp(this, this.time);
      var remainingTime = anchorTimestamp - timeStamp;
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
      // 做题记录已经保存，应该只需要传一个作业和学生id表示交卷就可以了
      var _this = this;
      var data = {
        workId: this.work.id,
      };
      this.api.work.submitWork(data, this.headers).then((res) => {
        var response = res.data;
        console.log(response);
        var newScore = response;
        this.$store.dispatch("setCurrentWorkScore", newScore);
      });
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
