<template>
  <div>
    <big-title><p>作业列表</p></big-title>
    <homework-list v-if="homeworkList.length > 0" :data="homeworkList"></homework-list>
    <!--没有作业的这个样式还得改改-->
    <div v-else><p>当前没有要交的作业</p></div>
  </div>
</template>

<script>
import HomeworkList from "@/components/lists/HomeworkList.vue";
import BigTitle from "@/components/BigTitle";

const homeworkList_temp = [
  {
    type: "作业",
    status: "进行中",
    courseName: "Linux原理与应用",
    teacher: "李文海",
    homeworkName: "期末大作业",
    endTime: "2020-12-15 20:30",
    remainingTime: "5天9小时",
    score: "/",
  },
  {
    type: "作业",
    status: "已完成",
    courseName: "数据结构",
    teacher: "文卫东",
    homeworkName: "第三次作业",
    endTime: "2019-12-15 20:30",
    remainingTime: "/",
    score: "/",
  },
];

export default {
  data() {
    return {
      homeworkList: [],
    };
  },
  components: {
    HomeworkList,
    BigTitle,
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.homeworkList = homeworkList_temp;
      }, 2000);
    },
  },
};
</script>

<style scoped></style>
