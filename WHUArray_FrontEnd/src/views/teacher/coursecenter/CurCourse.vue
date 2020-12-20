<template>
  <div>
    <big-title><p>教授中课程</p></big-title>
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <a-row>
        <a-button
          type="primary"
          icon="user"
          @click="
            () => {
              this.modalVisible = true;
            }
          "
          style="float: left"
          >添加课程</a-button
        >
      </a-row>
      <course-card-list
        v-if="currentCourseList.length > 0"
        :data="currentCourseList"
        style="margin: 16px 0"
      >
        <div slot="content" slot-scope="props" @click="switchToCourse(props.item)">
          <tea-course-card :item="props.item" />
        </div>
      </course-card-list>
      <icon-hint v-else :hint="emptyHint" />
    </div>
    <course-info-modal
      :visible="modalVisible"
      :handleOk="addCourse"
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
      emptyHint: "当前没有教授中课程",
      currentCourseList: [],
      isLoading: true,
      modalVisible: false,
    };
  },
  computed: {
    ...mapState({
      courseList: (state) => state.tempData.courseList.curList,
    }),
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      setTimeout(() => {
        this.currentCourseList = this.courseList;
        this.isLoading = false;
        clearInterval();
      }, 1000);
    },
    switchToCourse(item) {
      this.utils.toggle.handleCourseSwitch(this, "teacher", item);
    },
    addCourse(course) {
      var date = new Date();
      // 设置创建时间与初始状态
      var beginYear = date.getMonth() < 8 ? date.getFullYear() - 1 : date.getFullYear();
      course["time"] = beginYear + "-" + (beginYear + 1);
      course["status"] = "on";
      course["id"] = 4;
      // 把要添加的课程（就是这个course），提交到后台，给我返回一个课程号！！

      this.currentCourseList.push(course);
      this.modalVisible = false;
      var _this = this;
      this.$success({
        title: "添加成功",
        onOk() {
          // 添加成功以后页面reload一下
        },
      });
    },
  },
};
</script>

<style scoped></style>
