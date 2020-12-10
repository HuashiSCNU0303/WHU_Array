<template>
  <div>
    <big-title><p>作业列表</p></big-title>
    <center-loading v-if="isLoading == true" />
    <homework-list
      v-else-if="homeworkList.length > 0"
      :data="homeworkList"
    ></homework-list>
    <div v-else><empty-hint :hint="emptyHint" /></div>
  </div>
</template>

<script>
import HomeworkList from "@/components/lists/HomeworkList.vue";
import BigTitle from "@/components/BigTitle";
import CenterLoading from "@/components/CenterLoading.vue";
import EmptyHint from "@/components/EmptyHint.vue";

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
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未完成的作业",
    };
  },
  components: {
    HomeworkList,
    BigTitle,
    CenterLoading,
    EmptyHint,
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.homeworkList = [];
        this.isLoading = false;
      }, 2000);
    },
  },
};
</script>

<style scoped>
.loading {
  text-align: center;
  margin: 40px 0;
}
</style>
