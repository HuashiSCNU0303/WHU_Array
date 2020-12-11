import Vue from "vue";
import VueX from "vuex";

Vue.use(VueX);

import CurrentPageHeader from "./modules/CurrentPageHeader"
import CurrentCourse from "./modules/CurrentCourse"
import CurrentHomework from "./modules/CurrentHomework"
import CurrentExam from "./modules/CurrentExam"

export default new VueX.Store({
  modules: {
    currentCourse: CurrentCourse,
    currentPageHeader: CurrentPageHeader,
    currentExam: CurrentExam,
    currentHomework: CurrentHomework,
  }
})