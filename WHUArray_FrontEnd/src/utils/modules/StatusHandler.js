import countdown from "./Countdown"

export default {
  handleStudentHomework (self, homework) {
    var endTimeStamp = countdown.transStringToTimestamp(self, homework.endTime);
    var curTimeStamp = new Date().valueOf();
    if (endTimeStamp <= curTimeStamp) {
      // 时间已过
      homework.status = "已结束";
      homework.remainingTime = -2;
    } else if (homework.score == -1) {
      homework.status = "未提交";
      homework.remainingTime = endTimeStamp - curTimeStamp;
    } else {
      homework.status = "已提交";
      homework.remainingTime = -1;
    }
  },

  handleStudentExam (self, exam) {
    var endTimeStamp = countdown.transStringToTimestamp(self, exam.endTime);
    var startTimeStamp = countdown.transStringToTimestamp(self, exam.startTime);
    var curTimeStamp = new Date().valueOf();
    if (endTimeStamp <= curTimeStamp) {
      // 时间已过，已结束
      exam.status = "已结束";
      exam.remainingTime = -2;
    }
    else if (startTimeStamp >= curTimeStamp) {
      exam.status = "准备开始";
      exam.remainingTime = startTimeStamp - curTimeStamp;
    }
    else {
      exam.status = "进行中";
      exam.remainingTime = endTimeStamp - curTimeStamp;
    }
  },

  handleStudentCurExam (exam) {
    var curExam = {
      id: exam.id,
      title: exam.name,
      content: exam.teacher,
      currentScore: exam.score,
      fullScore: 100,
      remainingTime: countdown.getFullLeftTime(exam.remainingTime),
    }
    return curExam;
  },

  handleTeacherWork (self, work) {
    var endTimeStamp = countdown.transStringToTimestamp(self, work.endTime);
    var curTimeStamp = new Date().valueOf();
    if (endTimeStamp <= curTimeStamp) {
      // 时间已过
      work.status = "已结束";
    } else if (work.status == "unpublished") {
      work.status = "未发布";
    } else {
      work.status = "已发布";
    }
  },

  handleProblemRecord (problem, records, userId) {
    var recordScore = -1;
    var myRecords = [];
    records.forEach(record => {
      if (record.userId == userId) {
        myRecords.push(record);
      }
    });
    myRecords.forEach(record => {
      if (record.recordGrade > recordScore) {
        recordScore = record.recordGrade;
      }
    })
    problem.isDone = myRecords.length != 0;
    problem.score = recordScore;
  }
}