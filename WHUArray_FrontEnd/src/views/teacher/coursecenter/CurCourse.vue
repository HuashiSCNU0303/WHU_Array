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
        <div style="float: right">
          <a-select style="width: 120px" v-model="curFilterGrade">
            <a-select-option v-for="option in gradeOptions" :value="option" :key="option">
              {{ option }}
            </a-select-option>
          </a-select>
          <a-button
            type="primary"
            icon="user"
            @click="
              () => {
                refreshKey = 1;
                curFilterGrade = '';
              }
            "
            ghost
            >重置</a-button
          >
        </div>
      </a-row>
      <course-card-list
        v-if="currentCourseList.length > 0"
        :data="currentCourseList"
        :key="refreshKey"
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
      gradeOptions: [],
      curFilterGrade: "",
      refreshKey: 1,
    };
  },
  computed: {
    ...mapState({
      courseList: (state) => state.tempData.courseList.curList,
    }),
  },
  mounted() {
    this.setGradeOptions();
    this.getCourses();
  },
  watch: {
    curFilterGrade: function () {
      if (this.curFilterGrade == "") {
        for (var i = 0; i < this.currentCourseList.length; i++) {
          this.currentCourseList[i].visible = true;
        }
      } else {
        for (var i = 0; i < this.currentCourseList.length; i++) {
          this.currentCourseList[i].visible =
            this.currentCourseList[i].grade == this.curFilterGrade ? true : false;
        }
      }
      this.refreshKey++;
    },
  },
  methods: {
    getCourses() {
      var _this = this;
      var data = {
        id: -1,
      };
      this.api.teacher.getCurCourseList(data).then((res) => {
        var response = res.data;
        // 对response做处理，变成下面的courses;
        var courses;
        for (var i = 0; i < courses.length; i++) {
          var course = courses[i];
          course["visible"] = true;
          _this.currentCourseList.push(course);
        }
        _this.isLoading = false;
      });
    },

    setGradeOptions() {
      var date = new Date();
      var latestGrade = date.getMonth() < 8 ? date.getFullYear() - 1 : date.getFullYear();
      for (var i = 0; i < 8; i++) {
        this.gradeOptions.push(latestGrade - i + "级");
      }
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

      var _this = this;
      this.api.teacher.addCourse(course).then((res) => {
        var response = res.data;
        // 对response进行处理，获得课程号
        course["id"] = 4;
        _this.currentCourseList.push(course);
        _this.modalVisible = false;
        _this.$success({
          title: "添加成功",
        });
      });
    },
  },
};
</script>

<style scoped></style>
