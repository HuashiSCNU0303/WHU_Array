<template>
  <div>
    <big-title><p>考试列表</p></big-title>
    <a-collapse :default-active-key="selectedKeys" :bordered="false">
      <template #expandIcon="props">
        <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
      </template>
      <a-collapse-panel key="1" header="当前进行中的考试" :style="customStyle">
        <div class="content">
          <exam-card
            v-if="hasCurrentExam == true"
            :currentExamData="currentExamData"
          ></exam-card>
          <!--没有考试的这个样式还得改改-->
          <div v-else><p>当前没有进行中的考试</p></div>
        </div>
      </a-collapse-panel>
      <a-collapse-panel key="2" header="准备开始的考试" :style="customStyle">
        <div class="content">
          <exam-list v-if="examList.length > 0" :data="examList"></exam-list>
          <!--没有考试的这个样式还得改改-->
          <div v-else><p>当前没有准备开始的考试</p></div>
        </div>
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
import ExamList from "@/components/lists/ExamList";
import ExamCard from "@/components/cards/ExamCard.vue";
import BigTitle from "@/components/BigTitle.vue";

const examList_temp = [
  {
    type: "考试",
    status: "准备开始",
    courseName: "Windows原理与应用",
    teacher: "刘纪平",
    examName: "期末考试",
    startTime: "2020-12-14 18:30",
    endTime: "2020-12-14 20:30",
    remainingTime: "4天18小时",
    score: "/",
  },
  {
    type: "考试",
    status: "已结束",
    courseName: "Windows原理与应用",
    teacher: "刘敏忠",
    examName: "期中考试",
    startTime: "2020-12-14 18:30",
    endTime: "2020-12-14 20:30",
    remainingTime: "4天18小时",
    score: "/",
  },
];

export default {
  data() {
    return {
      currentExamData: {},
      hasCurrentExam: null,
      examList: [],
      selectedKeys: ["1", "2"],
      customStyle:
        "background: #fff; padding-bottom: 18px; border-bottom: 1px solid #e8e8e8; overflow: hidden",
    };
  },
  components: {
    ExamList,
    ExamCard,
    BigTitle,
  },
  mounted() {
    this.getExams();
  },
  methods: {
    getExams() {
      // 获取考试列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.currentExamData = {
          title: "系统级程序设计 期末考试",
          content: "韩波",
          currentScore: "46",
          fullScore: "100",
          remainingTime: "30",
        };
        this.hasCurrentExam = true;
        this.examList = examList_temp;
      }, 2000);
    },
  },
};
</script>

<style scoped>
.content {
  margin-left: -16px !important;
  margin-right: -16px !important;
}

.ant-collapse > .ant-collapse-item > .ant-collapse-header .ant-collapse-arrow {
  left: 0px !important;
}
</style>
