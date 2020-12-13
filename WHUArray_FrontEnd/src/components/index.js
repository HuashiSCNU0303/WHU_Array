import Vue from "vue";

import ExamCard from "./cards/ExamCard"

Vue.component("exam-card", ExamCard);

import CourseCardList from "./lists/CourseCardList"
import CourseList from "./lists/CourseList"
import ExamList from "./lists/ExamList"
import HomeworkList from "./lists/HomeworkList"
import ProblemList from "./lists/ProblemList"

Vue.component("course-card-list", CourseCardList);
Vue.component("course-list", CourseList);
Vue.component("exam-list", ExamList);
Vue.component("homework-list", HomeworkList);
Vue.component("problem-list", ProblemList);

import AllExpandColPanel from "./panels/AllExpandColPanel"
import SideMenuPanel from "./panels/SideMenuPanel"

Vue.component("all-expand-col-panel", AllExpandColPanel);
Vue.component("side-menu-panel", SideMenuPanel);

import BigTitle from "./widgets/BigTitle"
import CenterLoading from "./widgets/CenterLoading"
import Countdown from "./widgets/Countdown"
import IconHint from "./widgets/IconHint"
import ScoreDisplay from "./widgets/ScoreDisplay"

Vue.component("big-title", BigTitle);
Vue.component("center-loading", CenterLoading);
Vue.component("countdown", Countdown);
Vue.component("icon-hint", IconHint);
Vue.component("score-display", ScoreDisplay);

export default {
  ExamCard,
  CourseCardList, CourseList, ExamList, HomeworkList, ProblemList,
  AllExpandColPanel, SideMenuPanel,
  BigTitle, CenterLoading, Countdown, IconHint, ScoreDisplay,
}