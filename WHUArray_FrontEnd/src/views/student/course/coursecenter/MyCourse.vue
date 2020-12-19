<template>
  <div>
    <big-title><p>我的课程</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers">
      <div slot="panel1_content">
        <course-card-list
          v-if="currentCourseList.length > 0"
          :data="currentCourseList"
          @courseClicked="switchToCourse"
        />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <course-card-list v-if="endCourseList.length > 0" :data="endCourseList" />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      headers: ["进行中的课程", "已结束的课程"],
      emptyHints: ["当前没有进行中的课程", "当前没有已结束的课程"],
      currentCourseList: [],
      endCourseList: [],
      isLoading: true,
    };
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      let _this = this;
      console.log(localStorage.getItem("token"));
      axios.get("http://127.0.0.1:8009/course/all", {
        headers: {
          'Authorization': localStorage.getItem("token")
        }
      }).then((res) => {
        console.log(res.data);
        _this.currentCourseList = res.data;
        _this.isLoading = false;
      }).catch((error) => {
        console.log(error);
      })
      // console.log(this.$route);
      // 获取课程列表，下面只是模拟一下请求后端获得结果而已
      // setTimeout(() => {
      //   this.currentCourseList = currentCourseList_temp;
      //   this.endCourseList = endCourseList_temp;
      //   this.isLoading = false;
      // }, 1000);
    },
    switchToCourse(item) {
      this.utils.toggle.handleCourseSwitch(this, item);
    },
  },
};
</script>

<style scoped></style>
