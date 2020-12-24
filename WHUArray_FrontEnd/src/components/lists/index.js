import Vue from "vue";

import CourseCardList from "./CourseCardList"
import CourseList from "./CourseList"
import CommentList from "./CommentList"

Vue.component("course-card-list", CourseCardList);
Vue.component("course-list", CourseList);
Vue.component("comment-list", CommentList);

import StuExamList from "./student/ExamList"
import StuHomeworkList from "./student/HomeworkList"
import StuProblemList from "./student/ProblemList"

Vue.component("stu-exam-list", StuExamList);
Vue.component("stu-homework-list", StuHomeworkList);
Vue.component("stu-problem-list", StuProblemList);

import TeaProblemList from "./teacher/ProblemList"
import TeaWorkCardList from "./teacher/WorkCardList"
import TeaStudentList from "./teacher/StudentList"

Vue.component("tea-problem-list", TeaProblemList);
Vue.component("tea-work-card-list", TeaWorkCardList);
Vue.component("tea-student-list", TeaStudentList);

export default {
  CourseCardList, CourseList,
  StuExamList, StuHomeworkList, StuProblemList,
  TeaProblemList, TeaWorkCardList, TeaStudentList,
}