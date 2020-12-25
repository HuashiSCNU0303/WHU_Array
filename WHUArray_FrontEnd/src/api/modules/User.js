// 与用户操作相关的所有接口，都在/views/Login内使用
import axios from 'axios';

export default {
  // 登录，已完成
  login (data) {
    return axios({
      url: "http://localhost:8009/auth/login",
      method: 'post',
      data: data,
    });
  },

  // 注册，已完成
  register (data, role) {
    return axios({
      url: "http://localhost:8009/" + role + "/reg",
      method: 'post',
      data: data,
    });
  },

  // 自动登录，已完成
  autoLogin (data) {
    return axios({
      url: "http://localhost:8009/auth/login",
      method: 'post',
      data: data,
    });
  },
}