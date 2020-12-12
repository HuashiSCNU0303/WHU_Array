export default {
  getSimpleLeftTime (milliseconds) {
    // 传入的milliseconds是作业截止/考试开始的时间戳（仅用于计算todolist列表中的剩余时间，不做倒计时）
    var timeStamp = (new Date()).valueOf();
    var remainingTime = milliseconds - timeStamp;
    var days = parseInt(remainingTime / 1000 / 60 / 60 / 24, 10); //计算剩余的天数
    var hours = parseInt(remainingTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时
    var result = "";
    if (days != 0) {
      result += (days + "&nbsp;天&nbsp;");
    }
    if (hours != 0) {
      result += (hours + "&nbsp;小时&nbsp;");
    }
    if (days == 0 && hours == 0) {
      result = "<&nbsp;1小时"
    }
    return result;
  },
  getFullLeftTime (remainingTime) {
    // 传入的remainingTime是剩余的时间毫秒数
    var days = parseInt(remainingTime / 1000 / 60 / 60 / 24, 10); //计算剩余的天数
    var hours = parseInt(remainingTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时
    var minutes = parseInt(remainingTime / 1000 / 60 % 60, 10);//计算剩余的分钟
    var seconds = parseInt(remainingTime / 1000 % 60, 10);//计算剩余的秒数
    var result = "";
    if (days != 0) {
      result += (days + "&nbsp;天&nbsp;");
    }
    if (hours != 0) {
      result += (hours + "&nbsp;小时&nbsp;");
    }
    if (minutes != 0) {
      result += (minutes + "&nbsp;分&nbsp;");
    }
    if (seconds != 0) {
      result += (seconds + "&nbsp;秒&nbsp;");
    }
    /*if (days == 0 && hours == 0 && minutes == 0) {
      result = "<&nbsp;1分钟"
    }*/
    return result;
  }
  // TODO: 时间戳与日期字符串的转化
};
