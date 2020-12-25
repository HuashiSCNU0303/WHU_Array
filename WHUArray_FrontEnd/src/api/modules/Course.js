// 与课程相关的所有接口

import axios from "axios";

export default {

  // 获取课程的描述信息，参数为课程id
  // 使用的地方：/utils/toggle/Toggle
  // √ CourseController "/{courseId}"
  // 对接完成
  getCourseDetail (data, headers) {
    return axios({
      url: "http://localhost:8009/course/" + data.courseId,
      method: "get",
      headers: headers,
    });
  },
};