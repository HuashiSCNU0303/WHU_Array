import Vue from "vue";
import VueX from "vuex";

Vue.use(VueX);

import CurrentPageHeader from "./modules/currentobj/CurrentPageHeader";
import CurrentCourse from "./modules/currentobj/CurrentCourse";
import CurrentHomework from "./modules/currentobj/CurrentHomework";
import CurrentExam from "./modules/currentobj/CurrentExam";
import CurrentProblem from "./modules/currentobj/CurrentProblem";

import CourseList from "./modules/tempdata/CourseList"

import TableColPrototypes from "./modules/TableColPrototypes";

export default new VueX.Store({
  modules: {
    currentCourse: CurrentCourse,
    currentPageHeader: CurrentPageHeader,
    currentExam: CurrentExam,
    currentHomework: CurrentHomework,
    currentProblem: CurrentProblem,
    tableColProto: TableColPrototypes,
    courseList: CourseList,
  },
});
