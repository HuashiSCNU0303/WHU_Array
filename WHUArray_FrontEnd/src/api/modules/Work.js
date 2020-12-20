// 与作业/考试相关的所有接口

import axios from "axios";

export default {
  // 获取作业/考试信息，参数为作业/考试的id
  getWorkDetail(parameter) {},

  // 获取作业/考试下面的题目列表（还有用户是否提交每一道题），参数为作业/考试的id，学生的id
  getWorkProblemList(parameter) {},
};
