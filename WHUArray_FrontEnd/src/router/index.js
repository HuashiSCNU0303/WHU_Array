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
          name: "mgmtCenter",
          component: () => import("@/views/MgmtCenter")
        }
      ]
    }
  ]
});
