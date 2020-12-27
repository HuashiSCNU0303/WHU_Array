<template>
  <div style="overflow: auto">
    <big-title><p>学生列表</p></big-title>
    <center-loading v-if="isLoading" />
    <tea-student-list
      v-else-if="studentList.length > 0"
      :grid="grid"
      :data="studentList"
      :pagination="pagination"
    >
      <div slot="content" slot-scope="props">
        <a-card :hoverable="true" :bordered="false" @click="openModal(props.item)">
          <a-avatar :src="props.item.userFace" /> &nbsp;{{ props.item.nickname }}
        </a-card>
      </div>
    </tea-student-list>
    <div v-else><icon-hint :hint="emptyHint" /></div>
    <user-info-modal
      :visible="modalVisible"
      :studentInfo="curItem"
      :closeModal="closeModal"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      studentList: [],
      grid: { gutter: 8, lg: 3, md: 2, sm: 1, xs: 1 },
      pagination: {
        onChange: (page) => {
          // 切换页面的处理函数
        },
        pageSize: 24,
      },
      modalVisible: false,
      curItem: {},
      isLoading: true,
      emptyHint: "该课程尚未有学生加入",
    };
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
  mounted() {
    this.getStudents();
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
          var student_ = students[i];
          _this.studentList.push(student_);
        }
        _this.isLoading = false;
      });
    },

    openModal(item) {
      this.modalVisible = true;
      this.curItem = item;
    },

    closeModal() {
      this.modalVisible = false;
    },
  },
};
</script>

<style scoped></style>
