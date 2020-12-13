<template>
  <div style="padding: 0 8px; overflow: auto; max-height: 685px">
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <!--题目详情-->
      <big-title
        ><h1>{{ name }}</h1></big-title
      >
      <a-divider />
      <p v-html="description" />
      <span>
        <b>标签：&nbsp;</b>
        <a-tag v-for="problemTag in problemTags" :key="problemTag" :color="'volcano'">
          {{ problemTag }}
        </a-tag>
      </span>
      <a-divider />

      <div v-for="(item, index) in testCases" :key="index" class="testcase-display">
        <p>
          <b>例子{{ index + 1 }}</b>
        </p>
        样例输入
        <div
          class="testcase-textarea"
          v-html="item.input"
          style="margin-bottom: 8px"
        ></div>
        样例输出
        <div class="testcase-textarea" v-html="item.output"></div>
        <a-divider />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      description: "",
      problemTags: [],
      testCases: [],
      isLoading: true,
    };
  },
  mounted() {
    this.getProblemDetail();
  },
  methods: {
    getProblemDetail() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.name = "两数之和";
        this.description =
          "给定一个整数数组 nums 和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。<br />你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。";
        this.setTestCases();
        this.setTags();
        this.isLoading = false;
      }, 1000);
    },
    setTestCases() {
      for (var i = 0; i < 10; i++) {
        this.testCases.push({
          input: "1&nbsp;2",
          output: "3",
        });
      }
    },
    setTags() {
      for (var i = 0; i < 5; i++) {
        this.problemTags.push("算法");
      }
    },
  },
};
</script>

<style scoped>
.ant-divider-horizontal {
  margin: 8px 0 !important;
}
.testcase-textarea {
  background: #f5f5f5;
  padding: 8px;
}
.testcase-display {
  margin: 16px 0;
}
</style>
