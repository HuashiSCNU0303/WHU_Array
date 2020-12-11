<template>
  <div>
    <big-title><p>课程管理</p></big-title>
  </div>
</template>

<script>
import BigTitle from "@/components/widgets/BigTitle.vue";
export default {
  data () {
    return {
      selectedKey: "courseMgmt"
    }
  },
  components: {
    BigTitle
  },
}
</script>

<style scoped>
</style>