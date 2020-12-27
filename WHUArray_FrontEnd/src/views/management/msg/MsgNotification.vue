<template>
  <div>
    <big-title><p>消息提醒</p></big-title>
    <a-collapse :default-active-key="['1']" :bordered="false">
      <template #expandIcon="props">
        <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
      </template>
      <a-collapse-panel key="1" header="新消息" :style="customStyle">
        <center-loading v-if="isLoading == true" />
        <msg-list v-else-if="newMsgList.length > 0" :data="newMsgList" />
        <icon-hint v-else :hint="emptyHints[0]" />
      </a-collapse-panel>
      <a-collapse-panel key="2" header="已读消息" :style="customStyle">
        <center-loading v-if="isLoading == true" />
        <msg-list v-else-if="oldMsgList.length > 0" :data="oldMsgList"></msg-list>
        <icon-hint v-else :hint="emptyHints[1]" />
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
import MsgList from "@/views/management/msg/MsgList.vue";
import { mapState } from "vuex";
import IconHint from "../../../components/widgets/IconHint.vue";

export default {
  data() {
    return {
      newMsgList: [], // 未读消息
      oldMsgList: [], // 已读消息
      isLoading: true,
      emptyHints: ["当前没有新消息", "当前没有已读消息"],
      selectedKey: "msg",
      customStyle:
        "background: #fff; padding-bottom: 18px; border-bottom: 1px solid #e8e8e8; overflow: hidden",
    };
  },
  computed: {
    ...mapState({}),
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  components: {
    MsgList,
  },
  mounted() {
    this.getMsg();
  },
  methods: {
    getMsg() {
      this.newMsgList = [];
      this.oldMsgList = [];
      var _this = this;
      this.api.student.getMessage(this.headers).then((res) => {
        var response = res.data;
        var msgList = response;
        console.log(msgList);
        for (var i = msgList.length - 1; i >= 0; i--) {
          var msgString = msgList[i].messageContent;
          console.log(msgString);
          var msg = JSON.parse(msgString);
          console.log(msg);
          var item = _this.handleMessage(msg);
          _this.newMsgList.push(item);
        }
        _this.isLoading = false;
      });
    },

    handleMessage(msg) {
      var item = {
        title: "",
        avatar: "",
        description: "",
        workId: msg.homeworkId,
        courseId: msg.courseId,
        type: "",
      };
      if (msg.isExam == 0) {
        item.title = "有新作业了！";
        item.avatar = "";
        item.description =
          "你的课程" + msg.courseName + "布置了" + msg.homeworkName + "，点击查看";
        item.type = 0;
        item.src = "../../../assets/img/homework_start.png";
      } else if (msg.isExam == 1 && msg.type == "Create") {
        item.title = "有新考试了！";
        item.avatar = "";
        item.description =
          "你的课程" +
          msg.courseName +
          "新增了即将开始的" +
          msg.homeworkName +
          "，点击查看";
        item.type = 1;
        item.src = "../../../assets/img/exam_create.png";
      } else if (msg.isExam == 1 && msg.type == "Publish") {
        item.title = "考试开始了！";
        item.avatar = "";
        item.description =
          "你的课程" +
          msg.courseName +
          "下的" +
          msg.homeworkName +
          "已经开始了，点击去完成";
        item.type = 2;
        item.src = "../../../assets/img/exam_start.png";
      }
      return item;
    },
  },
};
</script>

<style scoped></style>
