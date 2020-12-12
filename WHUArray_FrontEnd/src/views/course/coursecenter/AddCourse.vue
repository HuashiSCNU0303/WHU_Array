<template>
  <div>
    <big-title><p>加入课程</p></big-title>
    <center-loading v-if="isLoading == true" />
    <div v-else-if="courseList.length > 0">
      <!--搜索模块-->
      <course-list :data="courseList" />
    </div>
    <icon-hint v-else :hint="emptyHint" />
  </div>
</template>

<script>
const courseList_temp = [
  {
    courseId: "11",
    courseName: "数字逻辑与数字电路",
    teacher: "涂卫平",
    grade: "2018级",
    courseDescription: "数字电路",
  },
  {
    courseId: "12",
    courseName: "离散数学",
    teacher: "吴志健",
    grade: "2018级",
    courseDescription: "离散数学",
  },
  {
    courseId: "13",
    courseName: "高等数学B2",
    teacher: "刘伟安",
    grade: "2018级",
    courseDescription: "高数",
  },
  {
    courseId: "14",
    courseName: "线性代数B",
    teacher: "黄学英",
    grade: "2019级",
    courseDescription: "线代",
  },
  {
    courseId: "15",
    courseName: "面向对象程序设计",
    teacher: "桂浩",
    grade: "2019级",
    courseDescription: "JAVA",
  },
];

export default {
  data() {
    return {
      courseList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有可以选择的课程",
    };
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.courseList = courseList_temp;
        this.isLoading = false;
      }, 1000);
    },
  },
};
</script>
