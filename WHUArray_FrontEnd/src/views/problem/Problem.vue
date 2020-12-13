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
        breadCrumbLayer: "",
        pageTitle: "",
        description: "",
        extraType: "score",
        score: -1,
      },
    };
  },
  components: {
    MonacoEditor,
  },
  computed: {
    ...mapState({
      problem: (state) => state.currentProblem.problem,
      course: (state) => state.currentCourse.course,
      homework: (state) => state.currentHomework.homework,
      exam: (state) => state.currentExam.exam,
    }),
  },
  mounted() {
    const source = this.$route.query.source;
    if (source == "repos") {
      this.showCountdown = false;
    }
    const data = this.getData(source);
    this.header.breadCrumbLayer = data[0];
    this.header.pageTitle = this.problem.name;
    this.header.description = "来源：" + data[1];
    this.header.score = this.problem.score;
    this.$store.dispatch("setCurrentPageHeader", this.header);
  },
  methods: {
    getData(source) {
      var layer = "",
        sourceStr = "";
      switch (source) {
        case "repos": {
          layer = "ProblemInRepos";
          sourceStr = this.problem.course + "\n" + this.problem.work;
          break;
        }
        case "homework": {
          layer = "ProblemInHomework";
          sourceStr = this.course.name + "\n" + this.homework.name;
          break;
        }
        case "exam": {
          layer = "ProblemInExam";
          sourceStr = this.course.name + "\n" + this.exam.name;
          break;
        }
      }
      return [layer, sourceStr];
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
