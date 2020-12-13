<template>
  <div>
    <big-title><p>课程管理</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers">
      <div slot="panel1_content">
        <course-card-list
          v-if="currentCourseList.length > 0"
          :data="currentCourseList"
          @courseClicked="openModifyModal"
        />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <course-list
          v-if="endCourseList.length > 0"
          :data="endCourseList"
          :currentPage="currentPage"
        />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
    <course-info-modal :role="role" :visible="modalVisible">
      <template slot="footer">
        <a-button key="back" @click="modalVisible = false"> 返回 </a-button>
        <a-button key="endCourse" type="primary" ghost @Click="handleCourseChange">
          结课
        </a-button>
        <a-button key="submit" type="primary" @Click="handleCourseChange">
          提交
        </a-button>
      </template>
    </course-info-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      role: 2,
      selectedKey: "courseMgmt",
      currentPage: "CourseMgmt",
      headers: ["教授中的课程", "已结束的课程"],
      emptyHints: ["当前没有教授中的课程", "当前没有已结束的课程"],
      currentCourseList: [],
      endCourseList: [],
      modalVisible: false,
      isLoading: true,
    };
  },
  computed: {
    ...mapState({
      courseList: (state) => state.courseList,
    }),
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      // 获取课程列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.currentCourseList = this.courseList.currentCourseList;
        this.endCourseList = this.courseList.endCourseList;
        this.isLoading = false;
      }, 1000);
    },
    openModifyModal(item) {
      this.modalVisible = true;
    },
    handleCourseChange() {},
  },
};
</script>

<style scoped></style>
