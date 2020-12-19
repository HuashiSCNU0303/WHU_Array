import Vue from "vue";

import Cards from "./cards/index";
import Lists from "./lists/index";
import Panels from "./panels/index";
import Widgets from "./widgets/index";

import CourseInfoModal from "./modals/CourseInfoModal";

Vue.component("course-info-modal", CourseInfoModal);

export default {
  Cards,
  Lists,
  Panels,
  Widgets,
  CourseInfoModal,
};
