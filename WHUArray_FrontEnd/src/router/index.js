import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

import CommonPageView from "@/components/CommonPageView"

export default new Router({
  routes: [
    {
      path: "/",
      name: "Login",
      component: () => import("@/views/Login")
    },
    {
      path: "/index",
      component: () => import("@/views/Index"),
      children: [
        {
          path: "",
          component: CommonPageView,
          children: [
            {
              path: "",
              name: "todoList",
              component: () => import("@/views/TodoList"),
            }
          ]
        },
        {
          path: "course",
          component: CommonPageView,
          children: [
            {
              path: "",
              name: "course",
              component: () => import("@/views/Course"),
            }
          ]
        },
        {
          path: "problemrepos",
          component: CommonPageView,
          children: [
            {
              path: "",
              name: "problemRepos",
              component: () => import("@/views/ProblemRepos"),
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
              component: () => import("@/views/management/UserSetting")
            },
            {
              path: "msg",
              name: "msgNotification",
              component: () => import("@/views/management/MsgNotification")
            },
            {
              path: "coursemgmt",
              name: "courseMgmt",
              component: () => import("@/views/management/CourseMgmt")
            },
            {
              path: "homeworkmgmt",
              name: "homeworkMgmt",
              component: () => import("@/views/management/HomeworkMgmt")
            },
            {
              path: "exammgmt",
              name: "examMgmt",
              component: () => import("@/views/management/ExamMgmt")
            },
          ]
        }
      ]
    }
  ]
});
