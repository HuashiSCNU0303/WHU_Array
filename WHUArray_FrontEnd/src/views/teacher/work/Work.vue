<template>
  <div class="content">
    <a-row>
      <a-col :span="10" style="padding: 0 16px">
        <problem-list
          :editable="work.status == 'published' ? false : true"
          :workId="work.id"
          :type="type"
        />
      </a-col>
      <a-col :span="14" class="problem-edit">
        <router-view :hint="emptyHint" />
      </a-col>
    </a-row>
    <work-info-modal
      :visible="modalVisible"
      :item="homework"
      :type="type == 'Homework' ? '作业' : '考试'"
      :handleOk="editWork"
      :handleCancel="
        () => {
          this.modalVisible = false;
        }
      "
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
import ProblemList from "./ProblemList";
import axios from "axios";

export default {
  components: {
    ProblemList,
  },
  data() {
    return {
      modalVisible: false,
      editable: true, // 由作业状态控制
      header: {
        extraType: "workOperation",
        editInfo: this.editInfo,
        delWork: this.delWork,
        publishWork: this.publishWork,
        editStatus: false,
        pageTitle: "",
        description: "",
      },
      emptyHint: "当前尚未选择题目",
      breadCrumb: [{}],
    };
  },
  computed: {
    ...mapState({
      pageType: (state) => state.curObj.page.type,
      course: (state) => state.curObj.course.course,
      homework: (state) => state.curObj.homework.homework,
      exam: (state) => state.curObj.exam.exam,
    }),
    type() {
      return this.$route.meta.type;
    },
    work() {
      return this.type == "Homework" ? this.homework : this.exam;
    },
  },
  mounted() {
    this.setHeader();
    this.setBreadCrumb();
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", this.type);
  },
  methods: {
    setHeader() {
      this.header.pageTitle = this.course.name + "\n" + this.work.name;
      this.header.description = "截止时间：" + this.work.endTime;
      this.header.editStatus = this.work.status == "unpublished" ? true : false;
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
          name: this.work.name,
        },
      ];
    },

    editInfo() {
      // 编辑作业信息，弹出作业信息框
      this.modalVisible = true;
    },

    delWork() {
      // 删除作业
    },

    publishWork() {
      // 发布作业
      var _homework = this.work;
      _homework["status"] = "published";
      this.editWork(_homework);
    },

    editWork(work) {
      // 将编辑好的作业发送给后台
      this.modalVisible = false;
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
