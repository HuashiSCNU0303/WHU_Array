<template>
  <div>
    <big-title><p>我的课程</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :type="type" :items="items" />
  </div>
</template>

<script>
const currentCourseList_temp = [
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

const endCourseList_temp = [
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

import BigTitle from "@/components/BigTitle.vue";
import CenterLoading from "@/components/CenterLoading.vue";
import AllExpandColPanel from "../../components/AllExpandColPanel.vue";
export default {
  data() {
    return {
      items: [
        {
          header: "进行中的课程",
          currentCourseList: [],
          emptyHint: "当前没有进行中的课程",
        },
        {
          header: "已结束的课程",
          endCourseList: [],
          emptyHint: "当前没有已结束的课程",
        },
      ],
      type: "mycourse",
      isLoading: true,
    };
  },
  components: {
    BigTitle,
    CenterLoading,
    AllExpandColPanel,
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      // 获取课程列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.items[0].currentCourseList = currentCourseList_temp;
        this.items[1].endCourseList = endCourseList_temp;
        this.isLoading = false;
      }, 2000);
    },
  },
};
</script>

<style scoped></style>
