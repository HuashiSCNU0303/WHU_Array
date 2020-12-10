<template>
  <div>
    <big-title><p>考试列表</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :type="type" :items="items" />
  </div>
</template>

<script>
import ExamList from "@/components/lists/ExamList";
import ExamCard from "@/components/cards/ExamCard.vue";
import BigTitle from "@/components/BigTitle.vue";
import CenterLoading from "@/components/CenterLoading.vue";
import AllExpandColPanel from "@/components/AllExpandColPanel.vue";

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
      items: [
        {
          header: "当前进行中的考试",
          currentExamData: {},
          hasCurrentExam: null,
          emptyHint: "当前没有进行中的考试",
        },
        {
          header: "准备开始的考试",
          examList: [],
          emptyHint: "当前没有准备开始的考试",
        },
      ],
      type: "exam",
      isLoading: true,
    };
  },
  components: {
    ExamList,
    ExamCard,
    BigTitle,
    CenterLoading,
    AllExpandColPanel,
  },
  mounted() {
    this.getExams();
  },
  methods: {
    getExams() {
      // 获取考试列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.items[0].currentExamData = {
          title: "系统级程序设计 期末考试",
          content: "韩波",
          currentScore: "46",
          fullScore: "100",
          remainingTime: "30",
        };
        this.items[0].hasCurrentExam = true;
        this.items[1].examList = examList_temp;
        this.isLoading = false;
      }, 1000);
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
