// 与老师相关的所有接口，更新个人信息后面再补

// 老师只有id名字和密码，传学生/老师对象的时候要把role也传进去

import axios from "axios";

export default {

  // 获取老师现在在教的课程，参数为教师id
  // 使用的地方：/views/teacher/coursecenter/CurCourse
  // √ CourseController "/findCurCourse"
  // 对接完成
  getCurCourseList (data, headers) {
    return axios({
      url: "http://localhost:8009/course/findCurCourse",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 获取老师已经结束的课程，参数为教师id
  // 使用的地方：/views/teacher/coursecenter/PreCourse
  // √ CourseController "/findPreCourse"
  // 对接完成
  getPreCourseList (data, headers) {
    return axios({
      url: "http://localhost:8009/course/findPreCourse",
      // method: "post",
      // data: data,
      headers: headers,
    });
  },

  // 添加课程，参数为一个课程对象，返回一个课程id
  // 使用的地方：/views/teacher/coursecenter/CurCourse
  // √ CourseController "/addCourse"
  // 对接完成
  addCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/course/addCourse",
      method: "post",
      data: data,
      headers: headers,
    });
  },

  // 删除课程，参数为课程id
  // 使用的地方：/views/teacher/course/Course
  // √ CourseController "/{courseId}"
  // 对接完成
  delCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/course/" + data.courseId,
      method: "delete",
      headers: headers,
    });
  },

  // 编辑课程，参数为课程id与一个新的课程对象
  // 使用的地方：/views/teacher/course/Course
  // √ CourseController "/updateCourse"
  // 对接完成
  editCourse (data, headers) {
    return axios({
      url: "http://localhost:8009/course/updateCourse",
      method: "put",
      data: data,
      headers: headers,
    });
  },


  // 获取某门课的所有作业/考试（都能获取），参数为课程id，以及考试/作业的标识
  // 使用的地方：/views/teacher/course/CourseWork
  // √ CourseController "/{courseId}/allHomework"
  // 对接完成
  getCourseWork (data, headers) {
    return axios({
      url: "http://localhost:8009/course/" + data.courseId + "/allHomework",
      method: "get",
      headers: headers,
    });
  },

  // 增加作业/考试，参数为一个完整作业/考试对象，以及考试/作业的标识，返回一个作业/考试的id
  // 使用的地方：/views/teacher/course/CourseWork
  // √ HomeworkController "/addHomework"
  // 对接完成
  addWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/addHomework",
      method: "post",
      data: data,
      headers: headers,
    });
  },

  // 获取某个课程下的学生列表（包括每个学生的信息），参数为课程id
  // 使用的地方：/views/teacher/work/SubmitRecord，/views/teacher/course/StudentList，
  // √ "/{courseId}/allStudent"
  // 尚未完成
  getCourseStudentList (data, headers) {
    return axios({
      url: "http://localhost:8009/course/" + data.courseId + "/allStudent",
      method: "get",
      headers: headers,
    });
  },

  // 获取某个作业/考试题下的题目列表，参数为作业/考试id（不用提交记录）
  // 使用的地方：/views/teacher/work/ProblemList
  // √ HomeworkController "/{homeworkId}/allQuestion"
  // 对接完成
  getWorkProblems (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/" + data.workId + "/allQuestion",
      method: "get",
      headers: headers,
    });
  },

  // 删除作业，参数为作业/考试id
  // 使用的地方：/views/teacher/work/Work
  // √ HomeworkController "/{homeworkId}"
  // 对接完成
  delWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/" + data.workId,
      method: "delete",
      headers: headers,
    });
  },

  // 编辑作业，参数为作业/考试id与一个新的作业/考试对象
  // 使用的地方：/views/teacher/work/Work
  // √ HomeworkController "/updateHomework"
  // 对接完成
  editWork (data, headers) {
    return axios({
      url: "http://localhost:8009/homework/updateHomework",
      method: "put",
      data: data,
      headers: headers,
    });
  },

  // 给作业增加题目，给一个空的就行
  // 使用的地方：/views/teacher/work/ProblemList
  // √ QuestionController "/addQuestion"
  // 对接完成
  addProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/question/addQuestion",
      method: "post",
      data: data,
      headers: headers,
    });
  },

  // 删除题目，参数为题目id
  // 使用的地方：/views/teacher/work/ProblemList
  // √ QuestionController "/{questionId}"
  // 对接完成
  delProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/question/" + data.id,
      method: "delete",
      headers: headers,
    });
  },

  // 编辑题目，参数为一个完整的题目对象
  // 使用的地方：/views/teacher/work/ProblemList
  // √ QuestionController "/updateQuestion"
  // 对接完成
  editProblem (data, headers) {
    return axios({
      url: "http://localhost:8009/question/updateQuestion",
      method: "put",
      data: data,
      headers: headers,
    });
  },

}