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
import { mapState } from 'vuex';
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
      if (typeof this.$route.meta.type === "undefined") {
        return this.$route.params.id;
      } else {
        return this.$route.params.problemId;
      }
    },
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
    ...mapState({
      user: (state) => state.curObj.user.user,
    })
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
      var _this = this;
      var data = {
        problemId: this.problemId,
      };
      this.api.problem.getProblemDetail(data, this.headers).then((res) => {
        var response = res.data;
        // 对response做处理
        console.log(response);
        var problem_ = response.question;
        var problemTags = problem_.tag.split(";");
        problemTags.splice(problemTags.length - 1, 1);
        // problem_temp是获取到的problem对象
        var problem_temp = {
          id: _this.problemId,
          name: problem_.questionName,
          // 需要注意把\n变成<br />
          description: problem_.questionContent,
          // 空格转&nbsp; \n转<br />
          testCases: problem_.useCases,
          tags: problemTags,
        };
        _this.utils.statusHandler.handleProblemRecord(problem_temp, response.records, _this.user.id);
        if (!_this.showAllTestCase) {
          problem_temp.testCases = problem_temp.testCases.splice(0, 2);
        }
        _this.problem = problem_temp;
        _this.isLoading = false;
      });
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
