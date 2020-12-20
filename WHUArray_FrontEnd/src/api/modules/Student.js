// 与学生有关的所有接口

import axios from "axios";

export default {
  // 获取学生的所有作业，参数为学生id
  getStuHomeworkList (parameter) {
    return axios({
      url: "http://localhost:8009/homework/all",
      method: "get",
      params: parameter,
    });
  },
  // 获取学生的所有考试，参数为学生id

  // 获取学生的所有课程，参数为学生id

  // 学生选课，参数为学生id、课程id

  // 学生撤课，参数为学生id、课程id

};


