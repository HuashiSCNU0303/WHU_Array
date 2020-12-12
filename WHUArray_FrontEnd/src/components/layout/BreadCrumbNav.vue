<template>
  <a-breadcrumb>
    <a-breadcrumb-item v-for="item in items" :key="item.name">
      <router-link v-if="typeof item.href !== 'undefined'" :to="item.href">
        {{ item.name }}
      </router-link>
      <a v-else-if="item.type != ''" @click="handleSwitch(item)">{{ item.name }}</a>
      <div v-else>{{ item.name }}</div>
    </a-breadcrumb-item>
  </a-breadcrumb>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      courseItemData: [
        {
          name: "我的课程",
          href: "/index/course",
        },
      ],
      problemReposItemData: [
        {
          name: "题库",
          href: "/index/problemrepos",
        },
      ],
      items: [{}],
    };
  },
  props: {
    currentLayer: {
      type: String,
    },
  },
  computed: {
    ...mapState({
      course: (state) => state.currentCourse.course,
      homework: (state) => state.currentHomework.homework,
      exam: (state) => state.currentExam.exam,
      problem: (state) => state.currentProblem.problem,
    }),
  },
  methods: {
    handleSwitch(item) {
      const type = item.type;
      if (type == "Course") {
        this.utils.toggle.handleCourseSwitch(this, item);
      } else if (type == "Homework") {
        this.utils.toggle.handleHomeworkSwitch(this, item);
      } else if (type == "Exam") {
        this.utils.toggle.handleExamSwitch(this, item);
      }
    },
    createBreadCrumb() {
      switch (this.currentLayer) {
        case "Course": {
          // 改成课程样式的面包屑
          this.courseItemData.push({
            name: this.course.name,
          });
          break;
        }
        case "Homework": {
          this.courseItemData.push({
            name: this.course.name,
            type: "Course",
            id: this.course.id,
          });
          this.courseItemData.push({
            name: this.homework.name,
          });
          break;
        }
        case "Exam": {
          this.courseItemData.push({
            name: this.course.name,
            type: "Course",
            id: this.course.id,
          });
          this.courseItemData.push({
            name: this.exam.name,
          });
          break;
        }
        case "ProblemInHomework": {
          this.courseItemData.push({
            name: this.course.name,
            type: "Course",
            id: this.course.id,
          });
          this.courseItemData.push({
            name: this.homework.name,
            type: "Homework",
            id: this.homework.id,
          });
          this.courseItemData.push({
            name: this.problem.name,
          });
          break;
        }
        case "ProblemInExam": {
          this.courseItemData.push({
            name: this.course.name,
            type: "Course",
            id: this.course.id,
          });
          this.courseItemData.push({
            name: this.exam.name,
            type: "Exam",
            id: this.exam.id,
          });
          this.courseItemData.push({
            name: this.problem.name,
          });
          break;
        }
        case "ProblemInRepos": {
          this.problemReposItemData.push({
            name: this.problem.name,
          });
          break;
        }
      }
      this.items =
        this.currentLayer == "ProblemInRepos"
          ? this.problemReposItemData
          : this.courseItemData;
    },
  },
  mounted() {
    this.createBreadCrumb();
  },
  updated() {},
};
</script>

<style scoped></style>
