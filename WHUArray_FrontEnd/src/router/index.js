import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

import CommonPageView from "@/components/CommonPageView"

export default new Router({
  routes: [
    {
      path: "/",
      name: "Login",
      component: () => import("@/views/login")
    },
    {
      path: "/index",
      component: () => import("@/views/index"),
      children: [
        {
          path: "",
          name: "TodoList",
          component: CommonPageView,
          children: [
            {
              path: "",
              component: () => import("@/views/todolist"),
            }
          ]
        },
        {
          path: "mgmtcenter",
          name: "MgmtCenter",
          component: () => import("@/views/mgmtcenter")
        }
      ]
    }
  ]
});
