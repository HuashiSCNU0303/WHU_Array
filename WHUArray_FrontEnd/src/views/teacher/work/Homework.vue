<template>
  <div class="content">
    <a-row>
      <a-col :span="10" style="padding: 0 16px">
        <big-title><p>题目列表</p></big-title>
        <center-loading v-if="isLoading == true" />
        <div v-else>
          <a-row style="margin-bottom: 16px">
            <a-button type="primary" icon="user" @click="addProblem" style="float: left"
              >添加题目</a-button
            >
          </a-row>
          <tea-problem-list
            v-if="problemList.length > 0"
            :data="problemList"
            :editable="editable"
            :switchToProblem="switchToProblem"
            :deleteProblem="deleteProblem"
          />
          <icon-hint v-else :hint="hints[0]" />
          <a-row v-if="editable == false" style="margin-top: 16px">
            <a-button type="primary" icon="user" @click="addCourse" style="float: left"
              >学生做题记录</a-button
            >
          </a-row>
        </div>
      </a-col>
      <a-col :span="14" class="problem-edit">
        <div v-if="isSelectProblem == false" class="not-select-problem-block">
          <icon-hint :hint="hints[1]" />
        </div>
        <router-view v-else />
      </a-col>
    </a-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  data() {
    return {
      problemList: [],
      isLoading: true,
      isSelectProblem: false,
      editable: true,
      header: {
        extraType: "operation",
        editInfo: this.editInfo,
        publishWork: this.publishWork,
        pageTitle: "",
        description: "",
      },
      hints: ["当前尚未添加题目", "当前尚未选择题目"],
      breadCrumb: [{}],
    };
  },
  computed: {
    ...mapState({
      pageType: (state) => state.curObj.page.type,
      course: (state) => state.curObj.course.course,
      homework: (state) => state.curObj.homework.homework,
    }),
  },
  mounted() {
    this.setHeader();
    this.setBreadCrumb();
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", "Homework");
    this.getProblems();
  },
  methods: {
    setHeader() {
      this.header.pageTitle = this.course.name + "\n" + this.homework.name;
      this.header.description = "截止时间：" + this.homework.endTime;
    },
    setBreadCrumb() {
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
        },
      ];
    },

    getProblems() {
      // 获取题目列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.problemList = this.$store.state.tempData.problemList.problemList;
        console.log(this.problemList);
        this.isLoading = false;
      }, 1000);
    },

    switchToProblem(record) {
      this.isSelectProblem = true;
      this.$router.push({
        path: "/teacher/homework/" + this.homework.id + "/" + record.id,
      });
    },
    deleteProblem(record, index) {
      var _this = this;
      this.$confirm({
        title: "你确定要删除该题目吗？",
        okText: "确定",
        okType: "danger",
        cancelText: "取消",
        onOk() {
          // 删除这道题，调后端接口
          _this.problemList.splice(index, 1);
        },
        onCancel() {},
      });
    },
    addProblem() {
      // 先传上服务器，得到一个新的ID以后再回来
      this.problemList.push({
        id: 3,
        name: "设计树",
      });
    },
    editInfo() {
      // 编辑作业信息
    },
    publishWork() {
      // 发布作业
    },
  },
};
</script>

<style scoped>
.content {
  padding: 24px;
}

.problem-edit {
  border-left: 2px solid #e8e8e8;
  padding: 0 16px;
  height: 500px;
  display: flex;
}

.not-select-problem-block {
  align-self: center;
  margin: 0 auto;
}
</style>
