// 与题目相关的所有接口

const URL = "http://39.106.97.180:8009";

import axios from "axios";

export default {
  // 获取题库中的所有题目，参数为老师/学生id（需要提交记录）
  // 使用的地方：/views/ProblemRepos
  // √ QuestionController "/all" 提交记录？
  getProblems (data, headers) {
    return axios({
      url: URL + "/question/all",
      method: "get",
      headers: headers,
    });
  },

  // 获取题库中某道题目的详情（包含所有测试用例），参数为题目id，老师/学生id（需要提交记录）
  // 使用的地方：/views/problem/ProblemDetail、/components/modals/ProblemEditModal
  // √ QuestionController "/{questionId}" 提交记录
  // 1对接完成
  getProblemDetail (data, headers) {
    return axios({
      url: URL + "/question/" + data.problemId,
      method: "get",
      headers: headers,
    });
  },

  // 提交题目，参数已封装
  // 使用的地方：/views/problem/Problem
  // √ QuestionController "/judge" 
  // 对接完成
  submitProblem (data, headers) {
    return axios({
      url: URL + "/compiler/judge",
      method: "post",
      data: data,
      headers: headers,
    });
  },

  // 调试题目，参数已封装
  // 使用的地方：/views/problem/Problem
  // √ QuestionController "/debug"
  // 对接完成
  debugProblem (data, headers) {
    return axios({
      url: URL + "/compiler/debug",
      method: "post",
      data: data,
      headers: headers,
    });
  },
};
