<template>
  <div>
    <big-title><p>我的课程</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :type="type" :items="items" />
  </div>
</template>

<script>
const currentCourseList_temp = [
  {
    id: "1",
    name: "计算机组成与设计",
    time: "2019-2020",
    teacher: "赵小刚",
  },
  {
    id: "2",
    name: "数据结构",
    time: "2019-2020",
    teacher: "喻丹丹",
  },
  {
    id: "3",
    name: "面向对象程序设计",
    time: "2019-2020",
    teacher: "林馥",
  },
  {
    id: "4",
    name: "概率论与数理统计",
    time: "2019-2020",
    teacher: "钟六一",
  },
  {
    id: "5",
    name: "大学英语3",
    time: "2019-2020",
    teacher: "喻士贵",
  },
];

const endCourseList_temp = [
  {
    id: "6",
    name: "计算机组成与设计",
    time: "2019-2020",
    teacher: "赵小刚",
  },
  {
    id: "7",
    name: "数据结构",
    time: "2019-2020",
    teacher: "喻丹丹",
  },
  {
    id: "8",
    name: "面向对象程序设计",
    time: "2019-2020",
    teacher: "林馥",
  },
  {
    id: "9",
    name: "概率论与数理统计",
    time: "2019-2020",
    teacher: "钟六一",
  },
  {
    id: "10",
    name: "大学英语3",
    time: "2019-2020",
    teacher: "喻士贵",
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
      console.log(this.$route);
      // 获取课程列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.items[0].currentCourseList = currentCourseList_temp;
        this.items[1].endCourseList = endCourseList_temp;
        this.isLoading = false;
      }, 1000);
    },
  },
};
</script>

<style scoped></style>
