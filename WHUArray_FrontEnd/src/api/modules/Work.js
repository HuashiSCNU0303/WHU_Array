// 与作业/考试相关的所有接口

import axios from "axios";

export default {
  // 获取作业/考试描述信息，参数为作业/考试的id
  // 使用的地方：/utils/toggle/Toggle
  getWorkDetail (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 学生交作业/交卷，参数为学生id与作业/考试id，返回一个分数
  // 使用的地方：/components/widgets/ScoreDisplay
  submitWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },
};
