<template>
  <div>
    <p>考试</p>
    <!--使用一个折叠面板，上面用一张卡片展示当前的考试，下面用一个表就可以了-->
    <a-collapse :default-active-key="selectedKeys" :bordered="false">
      <template #expandIcon="props">
        <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
      </template>
      <a-collapse-panel key="1" header="当前进行中的考试" :style="customStyle">
        <exam-card></exam-card>
      </a-collapse-panel>
      <a-collapse-panel key="2" header="准备开始的考试" :style="customStyle">
        <exam-list></exam-list>
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
import ExamList from "@/components/ExamList";
import ExamCard from "@/components/cards/ExamCard.vue";
export default {
  data() {
    return {
      selectedKeys: ["1", "2"],
      customStyle:
        "background: #fff; padding-bottom: 18px; border-bottom: 1px solid #e8e8e8; overflow: hidden",
    };
  },
  components: {
    ExamList,
    ExamCard,
  },
};
</script>

<style scoped></style>
