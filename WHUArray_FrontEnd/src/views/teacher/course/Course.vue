<template>
  <div>
    <side-menu-panel :items="items" />
    <course-info-modal
      :visible="modalVisible"
      :item="course"
      :handleOk="editCourse"
      :handleCancel="closeEditModal"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      modalVisible: false,
      items: [
        {
          key: "homework",
          iconType: "setting",
          text: "所有作业",
        },
        {
          key: "exam",
          iconType: "message",
          text: "所有考试",
        },
        {
          key: "students",
          iconType: "user",
          text: "学生列表",
        },
      ],
      header: {
        pageTitle: "",
        description: "",
        extraType: "courseOperation",
        editInfo: this.openEditModal,
        delCourse: this.delCourse,
        endCourse: this.endCourse,
        editStatus: false,
      },
      breadCrumb: [{}],
    };
  },
  computed: {
    ...mapState({
      course: (state) => state.curObj.course.course,
    }),
    courseName() {
      return this.course.grade + " " + this.course.name;
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
      this.$store.dispatch("setCurrentPageType", "Course");
    },
    setHeader() {
      this.header.pageTitle = this.courseName;
      this.header.description =
        this.course.time +
        "学年<br />课程号：" +
        this.course.id +
        "<br />课程简介：" +
        this.course.description;
      this.header.editStatus = this.course.status == "on" ? true : false;
    },
    setBreadCrumb() {
      this.breadCrumb = [
        {
          name: "我的课程",
          href: "/teacher/course",
        },
        {
          name: this.courseName,
        },
      ];
    },

    // 结束课程
    endCourse() {
      var _this = this;
      this.$confirm({
        title: "你确定要结束这个课程吗？",
        content: "结束课程后，你将不能在这个课程中继续发布作业/考试",
        onOk() {
          var _course = _this.course;
          _course.status = "off";
          _this.editCourse(_course);
        },
      });
    },

    // 删除课程
    delCourse() {
      var _this = this;
      this.$confirm({
        title: "你确定要删除这个课程吗？",
        content: "课程下的所有作业/考试将一并被删除",
        okText: "确定",
        okType: "danger",
        cancelText: "取消",
        onOk() {
          // 删除这个课程，调后端接口
          var data = {
            courseId: this.course.id,
          };
          _this.api.teacher.delCourse(data).then((res) => {
            // 跳转回课程中心
            _this.$success({
              title: "删除成功",
              onOk() {
                _this.$router.push({
                  path: "/teacher/course",
                });
              },
            });
          });
        },
        onCancel() {},
      });
    },

    // 打开编辑课程信息对话框
    openEditModal() {
      this.modalVisible = true;
    },

    // 将编辑好的课程信息传回后台
    editCourse(course) {
      // 把编辑好的course发送到后台
      var _this = this;

      var data = {
        courseId: course.id,
        courseName: course.name,
        teacherId: this.user.id,
        grade: course.grade,
        courseTime: course.time,
        description: course.description,
        status: course.status,
      };

      this.api.teacher.editCourse(data).then((res) => {
        _this.$success({
          title: "操作成功",
          onOk() {
            _this.$store.dispatch("setCurrentCourse", course);
            _this.setPageHeader();
            _this.closeEditModal();
          },
        });
      });
    },
    closeEditModal() {
      this.modalVisible = false;
    },
  },
};
</script>

<style scoped></style>
