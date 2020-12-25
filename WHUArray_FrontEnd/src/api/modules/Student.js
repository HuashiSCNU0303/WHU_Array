// 与学生有关的所有接口，更新个人信息后面再补

import axios from "axios";

export default {

  // 获取学生的所有作业，参数为学生id
  // 使用GET/POST未确定，下同
  // 使用的地方：/views/student/todolist/HomeworkTodoList
  // √ HomeworkController "/homeworkTodoList/{userId}"
  // 对接完成
  getHomeworkList (data, headers) {
    return axios({
      url: "http://localhost:8009/homeworkTodoList/" + data.userId,
      method: "get",
      headers: headers,
    });
  },

  // 获取学生的所有考试，参数为学生id
  // 使用的地方：/views/student/todolist/ExamTodoList
  // √ HomeworkController "/homeworkTodoList/{userId}" 筛选一下，要考试的
  // 对接完成
  getExamList (data, headers) {
    return axios({
      url: "http://localhost:8009/homeworkTodoList/" + data.userId,
      method: "get",
      headers: headers,
    });
  },

  // 获取学生的所有课程，参数为学生id
  // 使用的地方：/views/student/coursecenter/MyCourse
  // √ CourseController /findCourseByStudentId/{studentId}
  getCourseList (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取全部课程（进行中的课程），参数为学生id（连带着要获取学生的选课记录）
  // 使用的地方：/views/student/coursecenter/AddCourse
  // √ CourseController "/all"
  getAllCourseList (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 学生选课，参数为学生id、课程id
  // 使用的地方：/components/lists/CourseList
  // √ CourseController "/chooseCourse"
  addCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 学生撤课，参数为学生id、课程id
  // 使用的地方：/components/lists/CourseList
  // ？
  dropCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取学生某门课的所有作业（只能获取已发布的），参数为学生id、课程id，还要有提交记录
  // 使用的地方：/views/student/course/course/CourseHomework
  // ？ 学生id和提交记录有吗
  // 改完，但是还没有学生id与提交记录
  getCourseHomework (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取学生某门课的所有考试（都能获取），参数为学生id、课程id，还要有提交记录
  // 使用的地方：/views/student/course/course/CourseExam
  // ？ 学生id和提交记录有吗
  // 改完，但是还没有学生id与提交记录
  getCourseExam (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取某个作业/考试题下的题目列表，参数为学生id、作业/考试id，还要有题目的提交记录（后端处理）
  // 使用的地方：/views/student/course/Work
  // √ /{homeworkId}/allQuestion 学生id和提交记录有吗？
  getWorkProblems (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/all",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 更改学生信息
  // 使用的地方：/views/management/UserSetting
  // 对接完成
  updateStudent (data, headers) {
    return axios({
      url: "http://localhost:8009/student/updateStudent",
      method: "put",
      data: data,
      headers: headers,
    });
  },

  // 获取数据库旧信息
  getOldMessage (data, headers) {
    return axios({
      url: "http://localhost:8009/student/updateStudent",
      method: "put",
      data: data,
      headers: headers,
    });
  }

};


