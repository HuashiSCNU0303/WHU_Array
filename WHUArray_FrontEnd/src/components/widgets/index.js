import Vue from "vue";

import BigTitle from "./BigTitle"
import CenterLoading from "./CenterLoading"
import Countdown from "./Countdown"
import IconHint from "./IconHint"
import ScoreDisplay from "./ScoreDisplay"

Vue.component("big-title", BigTitle);
Vue.component("center-loading", CenterLoading);
Vue.component("countdown", Countdown);
Vue.component("icon-hint", IconHint);
Vue.component("score-display", ScoreDisplay);

export default {
  BigTitle, CenterLoading, Countdown, IconHint, ScoreDisplay,
}