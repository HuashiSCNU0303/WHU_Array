<template>
  <div>
    <big-title><p>消息提醒</p></big-title>
    <a-collapse :default-active-key="['1']" :bordered="false">
      <template #expandIcon="props">
        <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
      </template>
      <a-collapse-panel key="1" header="新消息" :style="customStyle">
        <msg-list :data="newMsgList"></msg-list>
      </a-collapse-panel>
      <a-collapse-panel key="2" header="已读消息" :style="customStyle">
        <msg-list :data="oldMsgList"></msg-list>
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
import BigTitle from "@/components/BigTitle.vue";
import MsgList from "@/components/lists/MsgList.vue";
const newMsgList_temp = [
  {
    title: "Ant Design Title 1",
    description: "Ant Design Description 1",
  },
  {
    title: "Ant Design Title 2",
    description: "Ant Design Description 2",
  },
  {
    title: "Ant Design Title 3",
    description: "Ant Design Description 3",
  },
  {
    title: "Ant Design Title 4",
    description: "Ant Design Description 4",
  },
];
const oldMsgList_temp = [
  {
    title: "Ant Design Title 5",
    description: "Ant Design Description 5",
  },
  {
    title: "Ant Design Title 6",
    description: "Ant Design Description 6",
  },
  {
    title: "Ant Design Title 7",
    description: "Ant Design Description 7",
  },
  {
    title: "Ant Design Title 8",
    description: "Ant Design Description 8",
  },
];
export default {
  data() {
    return {
      newMsgList: [], // 未读消息
      oldMsgList: [], // 已读消息
      selectedKey: "msgNotification",
      customStyle:
        "background: #fff; padding-bottom: 18px; border-bottom: 1px solid #e8e8e8; overflow: hidden",
    };
  },
  components: {
    BigTitle,
    MsgList,
  },
  mounted() {
    this.getMsg();
  },
  methods: {
    getMsg() {
      // 获取消息列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        console.log("等待结束");
        this.newMsgList = newMsgList_temp;
        this.oldMsgList = oldMsgList_temp;
      }, 2000);
      console.log(this.newMsgList);
    },
  },
};
</script>

<style scoped></style>
