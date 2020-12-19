import Vue from "vue";
import VueX from "vuex";

Vue.use(VueX);

import CurObj from "./modules/curobj/index"
import TempData from "./modules/tempdata/index" // 临时数据，前后端对接完成后将会删掉
import TableProto from "./modules/tableproto/index"


export default new VueX.Store({
  modules: {
    curObj: CurObj,
    tempData: TempData,
    tableProto: TableProto,
  },
});
