// 与用户操作相关的所有接口，都在/views/Login内使用
import axios from "axios";

const URL = "http://39.106.97.180:8009";

export default {
  // 登录，已完成
  login (data) {
    return axios({
      url: URL + "/auth/login",
      method: "post",
      data: data,
    });
  },

  // 注册，已完成
  register (data, role) {
    return axios({
      url: URL + "/" + role + "/reg",
      method: "post",
      data: data,
    });
  },
};
