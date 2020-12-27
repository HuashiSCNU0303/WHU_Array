<template>
  <a-layout>
    <stu-nav-bar :showCountdown="showCountdown" />
    <router-view />
  </a-layout>
</template>

<script>
import StuNavBar from "@/components/header/StudentHeader";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      header: (state) => state.curObj.page.header,
      pageType: (state) => state.curObj.page.type,
      user: (state) => state.curObj.user.user,
    }),
    showCountdown: function () {
      return this.pageType == "ProblemInWork";
    },
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  components: {
    StuNavBar,
  },
  created() {
    this.initWebSocket();
    this.getCurUserInfo();
  },
  destroyed() {
    this.websock.close(); // 离开路由之后断开websocket连接
  },
  methods: {
    getCurUserInfo() {
      var data = {
        studentId: this.user.id,
      };
      var _this = this;
      this.api.student.getCurrentStudentInfo(data, this.headers).then((res) => {
        var response = res.data;
        console.log(response);
        /*var item = {
          username: _this.user.username,
          id: _this.user.Id,
          role: "student",
          nickname: response.nickname,
          userFace: response.userFace,
        };*/
        _this.$store.dispatch("setCurrentUserNickname", response.nickname);
        _this.$store.dispatch("setCurrentUserFace", response.userFace);
      });
    },

    initWebSocket() {
      //初始化weosocket
      const wsuri = "ws://39.106.97.180:8009/wsSever/" + this.user.id;
      this.websock = new WebSocket(wsuri);
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen() {
      // 连接建立之后执行send方法发送数据
      // let actions = { test: "12345" };
      // this.websocketsend(JSON.stringify(actions));
    },
    websocketonerror() {
      // 连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e) {
      // 数据接收
      // const redata = JSON.parse(e.data);
      if (e.data == "连接成功") {
      } else if (e.data == "新消息") {
        console.log("收到");
        this.openNotification();
      }
    },
    websocketsend(Data) {
      // 数据发送
      this.websock.send(Data);
    },
    websocketclose(e) {
      // 关闭
      console.log("断开连接", e);
    },

    openNotification() {
      const key = `open${Date.now()}`;
      this.$notification.open({
        icon: <a-icon type="message" style="color: #108ee9" />,
        message: "新消息",
        description: "你收到一条消息提醒，快去消息中心看看吧",
        onClick: () => {
          this.$router.push({
            path: "/student/mgmt/msg",
          });
        },
        key,
      });
    },
  },
};
</script>

<style scoped></style>
