<template>
  <div>
    <side-menu-panel :items="items" />
    <course-info-modal
      :visible="modalVisible"
      :item="course"
      :handleOk="editCourse"
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
          key: "studentList",
          iconType: "user",
          text: "学生列表",
        },
      ],
      header: {
        pageTitle: "",
        description: "",
        extraType: "courseOperation",
        editInfo: this.editInfo,
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
  },
  mounted() {
    this.setHeader();
    this.setBreadCrumb();
    this.$store.dispatch("setCurrentPageHeader", this.header);
    this.$store.dispatch("setCurrentBreadCrumb", this.breadCrumb);
    this.$store.dispatch("setCurrentPageType", "Course");
  },
  methods: {
    setHeader() {
      this.header.pageTitle = this.course.name;
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
          name: this.course.name,
        },
      ];
    },
    endCourse() {
      var course_ = this.course;
      course_["status"] = "off";
      this.editCourse(course_);
    },
    delCourse() {
      // 删除课程
    },
    editInfo() {
      this.modalVisible = true;
    },
    editCourse(course) {
      this.modalVisible = false;
      console.log(course);
      // 把编辑好的course发送到后台
    },
  },
};
</script>

<style scoped></style>
