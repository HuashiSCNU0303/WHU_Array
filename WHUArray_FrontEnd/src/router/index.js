import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

import CommonPageView from "@/components/layout/CommonPageView";

export default new Router({
  routes: [
    {
      path: "/",
      name: "Login",
      component: () => import("@/views/Login"),
    },
    {
      path: "/index",
      redirect: "/index/todolist",
      component: () => import("@/views/Index"),
      children: [
        {
          path: "todolist",
          component: CommonPageView,
          children: [
            {
              path: "",
              redirect: "/index/todolist/homework",
              component: () => import("@/views/todolist/TodoList"),
              children: [
                {
                  path: "homework",
                  name: "homeworkInTodoList",
                  component: () => import("@/views/todolist/HomeworkTodoList"),
                },
                {
                  path: "exam",
                  name: "examInTodoList",
                  component: () => import("@/views/todolist/ExamTodoList"),
                },
              ],
            },
          ],
        },
        {
          path: "course",
          component: CommonPageView,
          children: [
            {
              path: "",
              name: "course",
              redirect: "/index/course/mycourse",
              component: () => import("@/views/course/coursecenter/CourseCenter"),
              children: [
                {
                  path: "mycourse",
                  component: () => import("@/views/course/coursecenter/MyCourse"),
                  meta: {
                    breadcrumb: "我的课程",
                  },
                },
                {
                  path: "addcourse",
                  component: () => import("@/views/course/coursecenter/AddCourse"),
                },
              ],
            },
            {
              // 具体某个ID表示的课程
              path: ":id",
              redirect: "/index/course/:id/homework",
              component: () => import("@/views/course/course/Course"),
              children: [
                {
                  path: "homework",
                  component: () => import("@/views/course/course/CourseHomework"),
                },
                {
                  path: "exam",
                  component: () => import("@/views/course/course/CourseExam"),
                },
              ],
            },
          ],
        },
        {
          path: "problemrepos",
          component: CommonPageView,
          children: [
            {
              path: "",
              name: "problemRepos",
              component: () => import("@/views/ProblemRepos"),
            },
          ],
        },
        {
          // 具体某个id指定的作业
          path: "homework/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/course/Homework"),
            }
          ]
        },
        {
          // 具体某个id指定的考试
          path: "exam/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/course/Exam"),
            }
          ]
        },
        {
          // 具体某个id指定的题目
          path: "problem/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/Problem"),
            }
          ]
        },
        {
          path: "mgmtcenter",
          component: () => import("@/views/management/MgmtCenter"),
          children: [
            {
              path: "usersetting",
              name: "userSetting",
              component: () => import("@/views/management/UserSetting"),
            },
            {
              path: "msg",
              name: "msgNotification",
              component: () => import("@/views/management/msg/MsgNotification"),
            },
            {
              path: "coursemgmt",
              name: "courseMgmt",
              component: () => import("@/views/management/CourseMgmt"),
            },
            {
              path: "homeworkmgmt",
              name: "homeworkMgmt",
              component: () => import("@/views/management/HomeworkMgmt"),
            },
            {
              path: "exammgmt",
              name: "examMgmt",
              component: () => import("@/views/management/ExamMgmt"),
            },
          ],
        },
      ],
    },
  ],
});
