// 与作业/考试相关的所有接口

import axios from "axios";

const URL = "http://39.106.97.180:8009";


export default {
  // 获取作业/考试描述信息，参数为作业/考试的id
  // 使用的地方：/utils/toggle/Toggle
  // √ HomeworkController "/{homeworkId}"
  // 对接完成
  getWorkDetail (data, headers) {
    return axios({
      url: URL + "/homework/" + data.workId,
      method: "get",
      headers: headers,
    });
  },

  // 学生交作业/交卷，参数为学生id与作业/考试id，返回一个分数
  // 使用的地方：/components/widgets/ScoreDisplay
  // 
  submitWork (data, headers) {
    return axios({
      url: URL + "/homework/submitHomework/" + data.workId,
      method: "post",
      headers: headers,
    });
  },
};
