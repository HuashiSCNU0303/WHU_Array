<template>
  <div class="content">
    <a-row>
      <a-col :span="10" style="padding: 0 16px">
        <problem-list
          :editable="work.status == 'published' ? false : true"
          :switchToProblem="switchToProblem"
          :switchToRecord="switchToRecord"
        />
      </a-col>
      <a-col :span="14" class="problem-edit">
        <router-view
          :hint="emptyHint"
          :showAllTestCase="showAllTestCase"
          :key="routerKey"
        />
      </a-col>
    </a-row>
    <work-info-modal
      :visible="modalVisible"
      :item="work"
      :type="typeName"
      :handleOk="editWork"
      :handleCancel="closeEditModal"
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
      header: {
        extraType: "workOperation",
        editInfo: this.openEditModal,
        delWork: this.delWork,
        publishWork: this.publishWork,
        editStatus: false,
        pageTitle: "",
        description: "",
      },
      emptyHint: "当前尚未选择题目",
      showAllTestCase: true,
      breadCrumb: [{}],
      routerKey: -1,
    };
  },
  computed: {
    ...mapState({
      pageType: (state) => state.curObj.page.type,
      course: (state) => state.curObj.course.course,
      work: (state) => state.curObj.work.work,
    }),
    type() {
      return this.$route.meta.type;
    },
    typeName() {
      return this.type == "Homework" ? "作业" : "考试";
    },
  },
  mounted() {
    this.setPageHeader();
  },
  methods: {
    setPageHeader() {
      this.setHeader();
      this.setBreadCrumb();
      this.$store.dispatch("setCurrentPageHeader", this.header);
      this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
      this.$store.dispatch("setCurrentPageType", "Work");
    },
    setHeader() {
      this.header.pageTitle = this.course.name + "\n" + this.work.name;
      this.header.description = "截止时间：" + this.work.endTime;
      this.header.editStatus = this.work.status == "unpublished" ? true : false;
    },
    setBreadCrumb() {
      this.breadCrumb = [
        {
          name: "我的课程",
          href: "/teacher/course",
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

    switchToProblem(record) {
      this.routerKey = record.id;
      this.$router.push({
        path:
          "/teacher/" + this.type.toLowerCase() + "/" + this.work.id + "/" + record.id,
      });
    },

    switchToRecord() {},

    openEditModal() {
      // 编辑作业信息，弹出作业信息框
      this.modalVisible = true;
    },

    delWork() {
      // 删除作业/考试
      var _this = this;
      this.$confirm({
        title: "你确定要删除这个" + this.typeName + "吗？",
        content: "所有同学的作答记录将一并被删除",
        okText: "确定",
        okType: "danger",
        cancelText: "取消",
        onOk() {
          // 删除这个作业/考试，调后端接口

          // 跳转回课程
          _this.$success({
            title: "删除成功",
            onOk() {
              _this.utils.toggle.handleCourseSwitch(_this, "teacher", _this.course);
            },
          });
        },
        onCancel() {},
      });
    },

    // 发布作业/考试
    publishWork() {
      var _this = this;
      this.$confirm({
        title: "你确定要发布这个" + _this.typeName + "吗？",
        content: "发布后，你不可以再修改作业，在课上的同学可以见到布置的这些题目",
        onOk() {
          var _work = _this.work;
          _work.status = "published";
          _this.editWork(_work, false);
        },
      });
    },

    editWork(work, flag) {
      // 将编辑好的作业发送给后台

      var _this = this;
      // flag表示是否打开了编辑对话框
      if (flag) {
        work.startTime = this.utils.countdown.transPickerToString(work.startTime);
        work.endTime = this.utils.countdown.transPickerToString(work.endTime);
      }
      this.$success({
        title: "操作成功",
        onOk() {
          // 后面再调整一下把Homework和Exam统一吧
          _this.$store.dispatch("setCurrentHomework", work);
          _this.setPageHeader();
          _this.closeEditModal();
        },
      });
    },

    closeEditModal() {
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
