<template>
  <div>
    <big-title><p>我的课程</p></big-title>
    <all-expand-col-panel :isLoading="isLoading" :headers="headers">
      <div slot="panel1_content">
        <course-card-list
          v-if="currentCourseList.length > 0"
          :data="currentCourseList"
          @courseClicked="switchToCourse"
        />
        <div v-else><icon-hint :hint="emptyHints[0]" /></div>
      </div>
      <div slot="panel2_content">
        <course-card-list v-if="endCourseList.length > 0" :data="endCourseList" />
        <div v-else><icon-hint :hint="emptyHints[1]" /></div>
      </div>
    </all-expand-col-panel>
  </div>
</template>

<script>
const currentCourseList_temp = [
  {
    id: "1",
    name: "计算机组成与设计",
    time: "2019-2020",
    teacher: "赵小刚",
    description: "计组",
  },
  {
    id: "2",
    name: "数据结构",
    time: "2019-2020",
    teacher: "喻丹丹",
    description: "数据结构",
  },
  {
    id: "3",
    name: "面向对象程序设计",
    time: "2019-2020",
    teacher: "林馥",
    description: "JAVA",
  },
  {
    id: "4",
    name: "概率论与数理统计",
    time: "2019-2020",
    teacher: "钟六一",
    description: "概率论",
  },
  {
    id: "5",
    name: "大学英语3",
    time: "2019-2020",
    teacher: "喻士贵",
    description: "大英",
  },
];

const endCourseList_temp = [
  {
    id: "6",
    name: "计算机组成与设计",
    time: "2019-2020",
    teacher: "赵小刚",
    description: "计组",
  },
  {
    id: "7",
    name: "数据结构",
    time: "2019-2020",
    teacher: "喻丹丹",
    description: "数据结构",
  },
  {
    id: "8",
    name: "面向对象程序设计",
    time: "2019-2020",
    teacher: "林馥",
    description: "JAVA",
  },
  {
    id: "9",
    name: "概率论与数理统计",
    time: "2019-2020",
    teacher: "钟六一",
    description: "概率论",
  },
  {
    id: "10",
    name: "大学英语3",
    time: "2019-2020",
    teacher: "喻士贵",
    description: "大英",
  },
];

export default {
  data() {
    return {
      headers: ["进行中的课程", "已结束的课程"],
      emptyHints: ["当前没有进行中的课程", "当前没有已结束的课程"],
      currentCourseList: [],
      endCourseList: [],
      isLoading: true,
    };
  },
  mounted() {
    this.getCourses();
  },
  methods: {
    getCourses() {
      // console.log(this.$route);
      // 获取课程列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.currentCourseList = currentCourseList_temp;
        this.endCourseList = endCourseList_temp;
        this.isLoading = false;
      }, 1000);
    },
    switchToCourse(item) {
      this.utils.toggle.handleCourseSwitch(this, item);
    },
  },
};
</script>

<style scoped></style>
