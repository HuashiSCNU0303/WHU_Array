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

            <a-popover title="在线编译系统使用帮助" trigger="click">
              <p slot="content">
                你可以到<a>https://oj.bnuz.edu.cn/helper/faq</a>了解编译系统的详情，<br />本系统的实现与使用方法与该系统非常类似。
              </p>
              <a-icon
                type="question-circle"
                style="float: right; font-size: 30px"
                theme="twoTone"
              />
            </a-popover>
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
              <a-tabs :active-key="showOutput" @change="handleResultTabSwitch">
                <a-tab-pane key="input" tab="测试输入">
                  <a-textarea
                    v-model="input"
                    placeholder="可以在此输入测试样例"
                    :rows="8"
                    style="max-height: 200px"
                  />
                </a-tab-pane>
                <a-tab-pane key="output" tab="输出结果">
                  <center-loading v-if="isLoading" />
                  <div v-else>
                    <a-alert
                      v-if="outputStatus == 1"
                      :key="refreshKey"
                      message="运行成功"
                      type="success"
                      show-icon
                    >
                      <span slot="description" class="result-info">
                        {{ output }}
                      </span>
                    </a-alert>
                    <a-alert
                      v-else-if="outputStatus == 3"
                      :key="refreshKey"
                      message="结果错误"
                      type="warning"
                      show-icon
                    >
                      <p slot="description" class="result-info">
                        {{ output }}
                      </p>
                    </a-alert>
                    <a-alert
                      v-else-if="outputStatus == 0"
                      :key="refreshKey"
                      message="编译错误"
                      type="error"
                      show-icon
                    >
                      <p slot="description" class="result-info">
                        {{ output }}
                      </p>
                    </a-alert>
                    <a-alert
                      v-else-if="outputStatus == 2"
                      :key="refreshKey"
                      message="运行时错误"
                      type="error"
                      show-icon
                    >
                      <span slot="description" class="result-info">
                        {{ output }}
                      </span>
                    </a-alert>
                  </div>
                </a-tab-pane>
              </a-tabs>
            </a-row>
            <a-row style="margin: 16px 0">
              <a-button
                type="primary"
                class="buttons"
                style="margin-left: 16px"
                @click="handleSubmit"
                >提交</a-button
              >
              <a-button
                type="primary"
                icon="caret-right"
                class="buttons"
                ghost
                @click="handleDebug"
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
      isLoading: false,
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
      output: "",
      input: "",
      outputStatus: -1,
      refreshKey: 0,
      showOutput: "input",
      toolTipVisible: false,
    };
  },
  components: {
    MonacoEditor,
  },
  computed: {
    ...mapState({
      problem: (state) => state.curObj.problem.problem,
      course: (state) => state.curObj.course.course,
      work: (state) => state.curObj.work.work,
      user: (state) => state.curObj.user.user,
      headers() {
        return {
          Authorization: localStorage.getItem("token"),
        };
      },
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
              href: "/" + this.user.role + "/repos",
            },
            {
              name: this.problem.id + "号题目",
            },
          ];
          sourceStr = this.problem.courseName + "\n" + this.problem.workName;
          break;
        }
        case "work": {
          this.pageType = "ProblemInWork";
          this.breadCrumb = [
            {
              name: "我的课程",
              href: "/student/course",
            },
            {
              name: this.course.name,
              type: "Course",
              id: this.course.id,
            },
            {
              name: this.work.name,
              type: "Work",
              id: this.work.id,
            },
            {
              name: this.problem.id + "号题目",
            },
          ];
          sourceStr = this.course.name + "\n" + this.work.name;
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
    handleResultTabSwitch(value) {
      this.showOutput = value;
    },
    showToolTip() {},
    handleSubmit() {
      // 提交代码
      this.isLoading = true;
      // 需要提交的数据
      var data = {
        userId: this.user.id,
        questionId: this.problem.id,
        lang: this.selectedLang == "python" ? "py" : this.selectedLang,
        recordContent: this.code,
      };
      var _this = this;
      this.api.problem.submitProblem(data, this.headers).then((res) => {
        var response = res.data;
        // 显示运行结果
        _this.refreshKey++;
        console.log(response);
        var status = Number(response.status);
        switch (status) {
          case 0: {
            // 编译错误
            _this.output = "错误信息：\n\n" + response.info;
            break;
          }
          case 1: {
            // 结果正确
            _this.output = "代码运行成功，结果正确！\n\n耗时：\n" + response.time + "ms";
            break;
          }
          case 2: {
            // 运行错误
            _this.output =
              "代码运行过程中出错！\n\n程序出错时的输入：\n" +
              response.wrongTestCaseInput +
              "\n错误信息：\n" +
              response.info;
            break;
          }
          case 3: {
            // 计算结果出错
            _this.output =
              "代码运行成功，但结果算错了！\n\n出现错误结果的输入：\n" +
              response.wrongTestCaseInput +
              "\n错误结果：\n" +
              response.wrongAnswer +
              "\n正确结果应当为：\n" +
              response.rightAnswer;
          }
        }
        _this.$store.dispatch("setCurrentProblemScore", response.score);
        _this.outputStatus = status;
        _this.handleResultTabSwitch("output");
        _this.isLoading = false;
      });
    },
    handleDebug() {
      // 运行结果
      this.isLoading = true;
      // 需要提交的数据
      var data = {
        userId: this.user.id,
        problemID: this.problem.id,
        lang: this.selectedLang == "python" ? "py" : this.selectedLang,
        src: this.code,
        input: this.input,
      };
      var _this = this;
      this.api.problem.debugProblem(data, this.headers).then((res) => {
        var response = res.data;
        // 显示运行结果
        // {"result":"7\n","time":"2","status":"1"}
        _this.refreshKey++;
        console.log(response);
        var status = Number(response.status);
        switch (status) {
          case 0: {
            // 编译错误
            _this.output = "错误信息：\n\n" + response.info;
            break;
          }
          case 1: {
            // 结果正确
            _this.output =
              "运行结果如下：\n\n结果：\n" +
              response.result +
              "耗时：\n" +
              response.time +
              "ms";
            break;
          }
          case 2: {
            // 运行错误
            _this.output = "错误信息：\n\n" + response.info;
            break;
          }
        }
        _this.outputStatus = status;
        _this.handleResultTabSwitch("output");
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped>
.content {
  padding: 16px;
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

.result-info {
  word-wrap: break-word;
  white-space: pre-wrap;
  height: 200px;
  overflow-y: auto;
  overflow-x: hidden;
}

.buttons {
  float: right;
}
</style>
