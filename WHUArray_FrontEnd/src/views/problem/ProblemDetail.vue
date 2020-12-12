<template>
  <div>
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <!--题目详情-->
      <big-title>题目详情</big-title>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isLoading: true,
    };
  },
  mounted() {
    this.getProblemDetail();
  },
  methods: {
    getProblemDetail() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.isLoading = false;
      }, 1000);
    },
  }
};
</script>

<style scoped></style>
