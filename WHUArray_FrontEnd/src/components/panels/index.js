import Vue from "vue";

import AllExpandColPanel from "./AllExpandColPanel"
import SideMenuPanel from "./SideMenuPanel"

Vue.component("all-expand-col-panel", AllExpandColPanel);
Vue.component("side-menu-panel", SideMenuPanel);

export default {
  AllExpandColPanel, SideMenuPanel,
}