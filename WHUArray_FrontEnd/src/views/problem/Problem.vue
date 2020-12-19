<template>
  <div class="content">
    <a-row>
      <a-col :span="11">
        <div id="problem-info">
          <a-row>
            <a-tabs type="card" @change="handleTabSwitch">
              <a-tab-pane key="detail" tab="题目详情" />
              <a-tab-pane key="comment" tab="评论" />
            </a-tabs>
          </a-row>
          <router-view />
        </div>
      </a-col>
      <a-col :span="13" style="border-left: 2px solid #e8e8e8">
        <div id="code-block">
          <a-row>
            <span>选择语言：</span>
            <a-select default-value="cpp" style="width: 120px" @change="handleLangChange">
              <a-select-option value="cpp"> C++ </a-select-option>
              <a-select-option value="java"> Java </a-select-option>
              <a-select-option value="python"> Python </a-select-option>
            </a-select>
          </a-row>
          <MonacoEditor
            :language="selectedLang"
            theme="vs"
            class="editor"
            v-model="code"
            :options="options"
          >
          </MonacoEditor>
          <div class="result-display">
            <a-row>
              <a-tabs>
                <a-tab-pane key="input" tab="测试输入">
                  <a-textarea
                    placeholder="可以在此输入测试样例"
                    :rows="8"
                    style="max-height: 200px"
                  />
                </a-tab-pane>
                <a-tab-pane key="output" tab="输出结果">
                  <a-textarea
                    placeholder="测试结果示例……………………"
                    :rows="8"
                    style="max-height: 200px"
                    read-only
                  />
                </a-tab-pane>
              </a-tabs>
            </a-row>
            <a-row style="margin: 16px 0">
              <a-button type="primary" class="buttons" style="margin-left: 16px"
                >提交</a-button
              >
              <a-button type="primary" icon="caret-right" class="buttons" ghost
                >运行结果</a-button
              >
            </a-row>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import MonacoEditor from "vue-monaco";

export default {
  data() {
    return {
      isLoading: true,
      showCountdown: true,
      code: "",
      editor: null,
      selectedLang: "cpp",
      options: {
        selectOnLineNumbers: true,
        roundedSelection: false,
        readOnly: false,
        cursorStyle: "line",
        automaticLayout: true,
        glyphMargin: true,
        minimap: {
          enabled: false,
        },
      },
      header: {
        pageTitle: "",
        description: "",
        extraType: "score",
        score: -1,
      },
      pageType: "",
      breadCrumb: [{}],
    };
  },
  components: {
    MonacoEditor,
  },
  computed: {
    ...mapState({
      problem: (state) => state.curObj.problem.problem,
      course: (state) => state.curObj.course.course,
      homework: (state) => state.curObj.homework.homework,
      exam: (state) => state.curObj.exam.exam,
    }),
  },
  mounted() {
    const source = this.$route.query.source;
    const sourceStr = this.handleSource(source);
    this.setHeader(sourceStr);

    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", this.pageType);
  },
  methods: {
    setHeader(sourceStr) {
      this.header.pageTitle = this.problem.name;
      this.header.description = "来源：" + sourceStr;
      this.header.score = this.problem.score;
    },
    handleSource(source) {
      var sourceStr = "";
      switch (source) {
        case "repos": {
          this.pageType = "ProblemInRepos";
          this.showCountdown = false;
          this.breadCrumb = [
            {
              name: "题库",
              href: "/index/problemrepos",
            },
            {
              name: this.problem.id + "号题目",
            },
          ];
          sourceStr = this.problem.course + "\n" + this.problem.work;
          break;
        }
        case "homework": {
          this.pageType = "ProblemInHomework";
          this.breadCrumb = [
            {
              name: "我的课程",
              href: "/index/course",
            },
            {
              name: this.course.name,
              type: "Course",
              id: this.course.id,
            },
            {
              name: this.homework.name,
              type: "Homework",
              id: this.homework.id,
            },
            {
              name: this.problem.id + "号题目",
            },
          ];
          sourceStr = this.course.name + "\n" + this.homework.name;
          break;
        }
        case "exam": {
          this.pageType = "ProblemInExam";
          this.breadCrumb = [
            {
              name: "我的课程",
              href: "/index/course",
            },
            {
              name: this.course.name,
              type: "Course",
              id: this.course.id,
            },
            {
              name: this.exam.name,
              type: "Exam",
              id: this.exam.id,
            },
            {
              name: this.problem.id + "号题目",
            },
          ];
          sourceStr = this.course.name + "\n" + this.exam.name;
          break;
        }
      }
      return sourceStr;
    },
    handleLangChange(value) {
      this.selectedLang = value;
    },
    handleTabSwitch(value) {
      // 跳转到不同的路由页面
      this.$router.push({
        path: "./" + value,
      });
    },
  },
};
</script>

<style scoped>
.content {
  padding: 16px;
  max-height: 1000px;
}
#problem-info {
  margin-right: 16px;
}
#code-block {
  margin-left: 16px;
}

.editor {
  margin-top: 8px;
  padding-top: 8px;
  height: 400px;
  border-top: 2px solid #e8e8e8;
  border-left: 2px solid #e8e8e8;
  border-right: 2px solid #e8e8e8;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
}

.result-display {
  padding: 0 24px;
  border-bottom: 2px solid #e8e8e8;
  border-left: 2px solid #e8e8e8;
  border-right: 2px solid #e8e8e8;
  border-bottom-left-radius: 3px;
  border-bottom-right-radius: 3px;
}

.buttons {
  float: right;
}
</style>
