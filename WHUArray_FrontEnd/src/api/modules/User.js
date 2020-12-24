// 与用户操作相关的所有接口，都在/views/Login内使用
import axios from 'axios';

export default {
  // 登录
  login (data) {
    return axios({
      url: "",
      method: 'post',
      data: data,
    });
  },

  // 注册
  register (data) {
    return axios({
      url: "",
      method: 'post',
      data: data,
    });
  },

  // 自动登录
  autoLogin (data) {
    return axios({
      url: "",
      method: 'post',
      data: data,
    });
  },
}