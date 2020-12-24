// 与题目相关的所有接口

import axios from "axios";

export default {

  // 获取题库中的所有题目，参数为老师/学生id（需要提交记录）
  // 使用的地方：/views/ProblemRepos
  getProblems (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取题库中某道题目的详情（包含所有测试用例），参数为题目id，老师/学生id（需要提交记录）
  // 使用的地方：/views/problem/ProblemDetail、/components/modals/ProblemEditModal
  getProblemDetail (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 提交题目，参数已封装
  // 使用的地方：/views/problem/Problem
  submitProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 调试题目，参数已封装
  // 使用的地方：/views/problem/Problem
  debugProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  }

}