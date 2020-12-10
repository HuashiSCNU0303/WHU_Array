import Vue from "vue";
import VueX from "vuex";

Vue.use(VueX);

import CourseHeader from "./modules/CourseHeader"

export default new VueX.Store({
  modules: {
    courseHeader: CourseHeader
  }
})