// 与老师相关的所有接口，更新个人信息后面再补

import axios from "axios";

export default {

  // 获取老师现在在教的课程，参数为教师id
  // 使用的地方：/views/teacher/coursecenter/CurCourse
  getCurCourseList (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取老师已经结束的课程，参数为教师id
  // 使用的地方：/views/teacher/coursecenter/PreCourse
  getPreCourseList (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 添加课程，参数为一个课程对象，返回一个课程id
  // 使用的地方：/views/teacher/coursecenter/CurCourse
  addCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 删除课程，参数为课程id
  // 使用的地方：/views/teacher/course/Course
  delCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 编辑课程，参数为课程id与一个新的课程对象
  // 使用的地方：/views/teacher/course/Course
  editCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },


  // 获取某门课的所有作业/考试（都能获取），参数为课程id，以及考试/作业的标识
  // 使用的地方：/views/teacher/course/CourseWork
  getCourseWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 增加作业/考试，参数为课程id，以及考试/作业的标识，返回一个作业/考试的id
  // 使用的地方：/views/teacher/course/CourseWork
  addWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取某个课程下的学生列表（包括每个学生的信息），参数为课程id
  // 使用的地方：/views/teacher/work/SubmitRecord，/views/teacher/course/StudentList，
  getCourseStudentList (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取某个作业/考试题下的题目列表，参数为作业/考试id（不用提交记录）
  // 使用的地方：/views/teacher/work/ProblemList
  getWorkProblems (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 删除作业，参数为作业/考试id
  // 使用的地方：/views/teacher/work/Work
  delWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 编辑作业，参数为作业/考试id与一个新的作业/考试对象
  // 使用的地方：/views/teacher/work/Work
  editWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 给作业增加题目，没有参数
  // 使用的地方：/views/teacher/work/ProblemList
  addProblem (headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      method: "get",
      headers: headers,
    });
  },

  // 删除题目，参数为题目id
  // 使用的地方：/views/teacher/work/ProblemList
  delProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "get",
      // data: 
      headers: headers,
    });
  },

  // 编辑题目，参数为一个完整的题目对象
  // 使用的地方：/views/teacher/work/ProblemList
  editProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "get",
      // data:
      headers: headers,
    });
  },

}