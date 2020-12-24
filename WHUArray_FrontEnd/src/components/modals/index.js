import Vue from "vue";

import CourseInfoModal from "./CourseInfoModal";
import ProblemEditModal from "./ProblemEditModal";
import WorkInfoModal from "./WorkInfoModal";
import UserInfoModal from "./UserInfoModal";

Vue.component("course-info-modal", CourseInfoModal);
Vue.component("problem-edit-modal", ProblemEditModal);
Vue.component("work-info-modal", WorkInfoModal);
Vue.component("user-info-modal", UserInfoModal);

export default {
  CourseInfoModal, ProblemEditModal, WorkInfoModal, UserInfoModal,
}