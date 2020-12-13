import Vue from "vue";
import VueX from "vuex";

Vue.use(VueX);

import CurrentPage from "./modules/currentobj/CurrentPage";
import CurrentCourse from "./modules/currentobj/CurrentCourse";
import CurrentHomework from "./modules/currentobj/CurrentHomework";
import CurrentExam from "./modules/currentobj/CurrentExam";
import CurrentProblem from "./modules/currentobj/CurrentProblem";
import CurrentUser from "./modules/currentobj/CurrentUser";

import CourseList from "./modules/tempdata/CourseList";
import HomeworkList from "./modules/tempdata/HomeworkList";
import ProblemList from "./modules/tempdata/ProblemList";
import ExamList from "./modules/tempdata/ExamList";

import TableColPrototypes from "./modules/TableColPrototypes";


export default new VueX.Store({
  modules: {
    currentCourse: CurrentCourse,
    currentPage: CurrentPage,
    currentExam: CurrentExam,
    currentHomework: CurrentHomework,
    currentProblem: CurrentProblem,
    currentUser: CurrentUser,

    tableColProto: TableColPrototypes,

    courseList: CourseList,
    homeworkList: HomeworkList,
    problemList: ProblemList,
    examList: ExamList,
  },
});
