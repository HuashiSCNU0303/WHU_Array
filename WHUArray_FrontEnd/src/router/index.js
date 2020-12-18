import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

import CommonPageView from "@/components/layout/CommonPageView";

export default new Router({
  routes: [
    {
      path: "/",
      component: () => import("@/views/Login"),
    },
    {
      // 学生端功能
      path: "/student",
      redirect: "/student/todolist",
      component: () => import("@/views/student/Index"),
      children: [
        {
          path: "todolist",
          component: CommonPageView,
          children: [
            {
              path: "",
              redirect: "/student/todolist/homework",
              component: () => import("@/views/student/todolist/TodoList"),
              children: [
                {
                  path: "homework",
                  name: "homeworkInTodoList",
                  component: () => import("@/views/student/todolist/HomeworkTodoList"),
                },
                {
                  path: "exam",
                  name: "examInTodoList",
                  component: () => import("@/views/student/todolist/ExamTodoList"),
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
              redirect: "/student/course/mycourse",
              component: () => import("@/views/student/course/coursecenter/CourseCenter"),
              children: [
                {
                  path: "mycourse",
                  component: () => import("@/views/student/course/coursecenter/MyCourse"),
                  meta: {
                    breadcrumb: "我的课程",
                  },
                },
                {
                  path: "addcourse",
                  component: () =>
                    import("@/views/student/course/coursecenter/AddCourse"),
                },
              ],
            },
            {
              // 具体某个ID表示的课程
              path: ":id",
              redirect: "/student/course/:id/homework",
              component: () => import("@/views/student/course/course/Course"),
              children: [
                {
                  path: "homework",
                  component: () => import("@/views/student/course/course/CourseHomework"),
                },
                {
                  path: "exam",
                  component: () => import("@/views/student/course/course/CourseExam"),
                },
              ],
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
              component: () => import("@/views/student/course/Homework"),
            },
          ],
        },
        {
          // 具体某个id指定的考试
          path: "exam/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/student/course/Exam"),
            },
          ],
        },
        {
          // 管理中心
          path: "mgmt",
          component: () => import("@/views/management/MgmtCenter"),
          children: [
            {
              path: "usersetting",
              component: () => import("@/views/management/UserSetting"),
            },
            {
              path: "msg",
              component: () => import("@/views/management/msg/MsgNotification"),
            },
          ],
        },
        {
          // 题库
          path: "repos",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/ProblemRepos"),
            },
          ],
        },
        {
          // 具体某个id指定的题目
          path: "problem/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              redirect: "./detail",
              component: () => import("@/views/problem/Problem"),
              children: [
                {
                  path: "detail",
                  component: () => import("@/views/problem/ProblemDetail"),
                },
                {
                  path: "comment",
                  component: () => import("@/views/problem/ProblemComment"),
                },
              ],
            },
          ],
        },
      ],
    },
    {
      // 老师端功能
      path: "/teacher",
      redirect: "/teacher/course",
      component: () => import("@/views/teacher/Index"),
      children: [
        {
          path: "course",
          component: CommonPageView,
          children: [
            {
              path: "",
              redirect: "/teacher/course/curcourse",
              component: () => import("@/views/teacher/coursecenter/CourseCenter"),
              children: [
                {
                  path: "curcourse",
                  component: () => import("@/views/teacher/coursecenter/CurCourse"),
                  meta: {
                    breadcrumb: "我的课程",
                  },
                },
                {
                  path: "precourse",
                  component: () => import("@/views/teacher/coursecenter/PreCourse"),
                },
              ],
            },
            {
              // 具体某个ID表示的课程
              path: ":id",
              redirect: "/teacher/course/:id/homework",
              component: () => import("@/views/teacher/course/Course"),
              children: [
                {
                  path: "homework",
                  component: () => import("@/views/teacher/course/CourseHomework"),
                },
                {
                  path: "exam",
                  component: () => import("@/views/teacher/course/CourseExam"),
                },
              ],
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
              component: () => import("@/views/teacher/Homework"),
            },
          ],
        },
        {
          // 具体某个id指定的考试
          path: "exam/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/teacher/Exam"),
            },
          ],
        },
        {
          // 管理中心
          path: "mgmt",
          component: () => import("@/views/management/MgmtCenter"),
          children: [
            {
              path: "usersetting",
              component: () => import("@/views/management/UserSetting"),
            },
            {
              path: "msg",
              component: () => import("@/views/management/msg/MsgNotification"),
            },
          ],
        },
        {
          // 题库
          path: "repos",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/ProblemRepos"),
            },
          ],
        },
        {
          // 具体某个id指定的题目
          path: "problem/:id",
          component: CommonPageView,
          children: [
            {
              path: "",
              redirect: "./detail",
              component: () => import("@/views/problem/Problem"),
              children: [
                {
                  path: "detail",
                  component: () => import("@/views/problem/ProblemDetail"),
                },
                {
                  path: "comment",
                  component: () => import("@/views/problem/ProblemComment"),
                },
              ],
            },
          ],
        },
      ],
    },
  ],
});
