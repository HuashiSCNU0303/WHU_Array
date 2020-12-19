import Vue from "vue";

import CourseInfoModal from "./CourseInfoModal";
import ProblemEditModal from "./ProblemEditModal";

Vue.component("course-info-modal", CourseInfoModal);
Vue.component("problem-edit-modal", ProblemEditModal);

export default {
  CourseInfoModal, ProblemEditModal,
}