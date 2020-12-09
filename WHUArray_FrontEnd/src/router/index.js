import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

import CommonPageView from "@/components/CommonPageView";

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
              component: () => import("@/views/course/Course"),
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
              component: () => import("@/views/management/MsgNotification"),
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
