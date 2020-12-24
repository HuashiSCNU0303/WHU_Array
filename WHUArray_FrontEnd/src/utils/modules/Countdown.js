export default {
  getSimpleLeftTime (milliseconds) {
    var remainingTime = milliseconds;
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
    if (days == 0 && hours == 0 && minutes == 0) {
      result = "<&nbsp;1分钟"
    }
    return result;
  },

  // 将设置时间时的picker搞出来的时间转化为字符串
  transPickerToString (dateTime) {
    var result = dateTime.date;
    result.startOf('date');
    result.add({
      hours: dateTime.time.hour(),
      minutes: dateTime.time.minute(),
      seconds: dateTime.time.second(),
    });
    return result.format("YYYY-MM-DD HH:mm:ss");
  },

  // 将字符串转化为设置时间时的picker搞出来的时间
  transStringToPicker (self, dateTimeString) {
    var momentTime = self.$moment(dateTimeString);
    var hour = momentTime.hour();
    var minute = momentTime.minute();
    var second = momentTime.second();
    var result = {
      date: momentTime.startOf('date'),
      time: self.$moment({
        hour: hour,
        minute: minute,
        second: second,
      })
    };
    return result;
  },

  transStringToTimestamp (self, dateTimeString) {
    var momentTime = self.$moment(dateTimeString);
    var timestamp = momentTime.valueOf();
    return timestamp;
  }
};
