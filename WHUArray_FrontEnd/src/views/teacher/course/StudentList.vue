<template>
  <div>
    <big-title><p>学生列表</p></big-title>
    <center-loading v-if="isLoading" />
    <tea-student-list
      v-else-if="studentList.length > 0"
      :grid="grid"
      :data="studentList"
      :pagination="pagination"
    >
      <a-popover slot="content" slot-scope="props">
        <template slot="title">
          <span style="font-size: 16px">学生信息</span>
        </template>
        <template slot="content">
          <div>
            <p>学号：{{ props.item.studentId }}</p>
          </div>
          <div>
            <p>邮箱：{{ props.item.mail }}</p>
          </div>
          <div>
            <p>电话：{{ props.item.telephone }}</p>
          </div>
        </template>
        <a-card :hoverable="true" :bordered="false">
          <a-avatar :src="props.item.userFace" /> &nbsp;{{ props.item.nickname }}
        </a-card>
      </a-popover>
    </tea-student-list>
    <div v-else><icon-hint :hint="emptyHint" /></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      studentList: [],
      grid: { gutter: 24, lg: 4, md: 2, sm: 1, xs: 1 },
      pagination: {
        onChange: (page) => {
          // 切换页面的处理函数
        },
        pageSize: 32,
      },
      isLoading: true,
      emptyHint: "该课程尚未有学生加入",
    };
  },
  mounted() {
    this.getStudents();
  },
  computed: {
    ...mapState({
      course: (state) => state.curObj.course.course,
    }),
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  methods: {
    getStudents() {
      var _this = this;
      var data = {
        courseId: this.course.id,
      };
      this.api.teacher.getCourseStudentList(data, this.headers).then((res) => {
        var response = res.data;
        // 对response进行处理，变成students
        var students = response;
        for (var i = 0; i < students.length; i++) {
          var student = students[i];
          // 数据处理
          _this.studentList.push(student);
        }
        _this.isLoading = false;
      });
    },
  },
};
</script>

<style scoped></style>
