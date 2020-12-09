<template>
  <div>
    <work-list-panel></work-list-panel>
  </div>
</template>

<script>
import WorkListPanel from "@/components/WorkListPanel";
export default {
  data() {
    return {
      pageTitle: "待办事项列表",
      description: "下面是你最近需要完成的作业或考试列表，默认按照剩余时间从小到大排序",
      extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
    };
  },
  components: {
    WorkListPanel,
  },
};
</script>

<style lang="less" scoped></style>
