import Vue from "vue";

import ExamCard from "./student/ExamCard"
import StuCourseCard from "./student/CourseCard"

Vue.component("exam-card", ExamCard);
Vue.component("stu-course-card", StuCourseCard);

import WorkCard from "./teacher/WorkCard"
import TeaCourseCard from "./teacher/CourseCard"

Vue.component("work-card", WorkCard);
Vue.component("tea-course-card", TeaCourseCard);

export default {
  ExamCard, StuCourseCard,
  WorkCard, TeaCourseCard,
}