// 与用户操作相关的所有接口
import axios from 'axios';

export default {
  // 登录
  login (parameter) {
    return axios({
      url: api.ExamQuestionList,
      method: 'get',
      params: parameter
    });
  },

  // 注册
  register (parameter) {

    return axios({
      url: api.ExamQuestionList,
      method: 'get',
      params: parameter
    });
  },

  // 自动登录
  autoLogin (parameter) {
    return axios({
      url: api.ExamQuestionList,
      method: 'get',
      params: parameter
    });
  },
}