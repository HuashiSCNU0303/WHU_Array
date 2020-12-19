// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import "ant-design-vue/dist/antd.css";
import Antd from "ant-design-vue";
import VueBreadcrumbs from "vue-breadcrumbs";
import store from "./store/index";
import utils from "./utils/index";
import CommonComs from "./components/index";
import moment from 'moment'



Vue.use(VueBreadcrumbs);
Vue.use(Antd);
// Vue.use(CommonComs);

Vue.config.productionTip = false;
Vue.prototype.utils = utils;
Vue.prototype.$moment = moment;

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  store,
  components: { App },
  template: "<App/>",
});
