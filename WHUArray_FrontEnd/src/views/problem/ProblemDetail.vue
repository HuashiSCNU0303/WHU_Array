<template>
  <div style="padding: 0 8px; overflow: auto; max-height: 685px">
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <!--题目详情-->
      <big-title
        ><h1>{{ problem.name }}</h1></big-title
      >
      <a-divider />
      <p v-html="problem.description" />
      <span>
        <b>标签：&nbsp;</b>
        <a-tag v-for="tag in problem.tags" :key="tag" :color="'volcano'">
          {{ tag }}
        </a-tag>
      </span>
      <a-divider />

      <div
        v-for="(item, index) in problem.testCases"
        :key="index"
        class="testcase-display"
      >
        <p>
          <b>例子{{ index + 1 }}</b>
        </p>
        样例输入
        <div class="testcase-textarea" v-html="item.input" style="margin-bottom: 8px" />
        样例输出
        <div class="testcase-textarea" v-html="item.output" />
        <a-divider />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      problem: {
        name: "",
        description: "",
        tags: [],
        testCases: [],
      },
      isLoading: true,
    };
  },
  mounted() {
    this.getProblemDetail();
  },
  computed: {
    problemId() {
      return this.$route.params.id;
    },
  },
  props: {
    showAllTestCase: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    getProblemDetail() {
      // 获取题目的所有信息
      setTimeout(() => {
        var problem_temp = {
          name: "两数之和",
          // 需要注意把\n变成<br />
          description:
            "给定一个整数数组 nums 和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。<br />你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。",
          // 空格转&nbsp; \n转<br />
          testCases: [
            {
              input: "1&nbsp;2",
              output: "3",
            },
            {
              input: "4&nbsp;5",
              output: "6",
            },
            {
              input: "7&nbsp;8",
              output: "9",
            },
          ],
          tags: ["算法", "数据结构"],
        };
        if (!this.showAllTestCase) {
          problem_temp.testCases = problem_temp.testCases.splice(0, 2);
        }
        this.problem = problem_temp;
        this.isLoading = false;
        clearInterval();
      }, 1000);
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
