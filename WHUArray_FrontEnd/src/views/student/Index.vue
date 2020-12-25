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
    }),
    showCountdown: function () {
      return this.pageType == "ProblemInWork";
    },
  },
  components: {
    StuNavBar,
  },
  /*created() {
    this.initWebSocket();
  },
  destroyed() {
    this.websock.close(); //离开路由之后断开websocket连接
  },
  methods: {
    initWebSocket() {
      //初始化weosocket，url没搞好
      const wsuri = "ws://127.0.0.1:8080";
      this.websock = new WebSocket(wsuri);
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen() {
      //连接建立之后执行send方法发送数据
      let actions = { test: "12345" };
      this.websocketsend(JSON.stringify(actions));
    },
    websocketonerror() {
      //连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e) {
      //数据接收
      const redata = JSON.parse(e.data);
    },
    websocketsend(Data) {
      //数据发送
      this.websock.send(Data);
    },
    websocketclose(e) {
      //关闭
      console.log("断开连接", e);
    },
  },*/
};
</script>

<style scoped></style>
