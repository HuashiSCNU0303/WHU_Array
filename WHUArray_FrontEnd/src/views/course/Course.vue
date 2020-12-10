<template>
  <side-menu-panel :items="items" />
</template>

<script>
import SideMenuPanel from "@/components/SideMenuPanel.vue";
import CenterLoading from "@/components/CenterLoading.vue";
import { mapState } from "vuex";

export default {
  components: { SideMenuPanel, CenterLoading },
  data() {
    return {
      items: [
        {
          key: "homework",
          iconType: "setting",
          text: "待完成作业",
        },
        {
          key: "exam",
          iconType: "message",
          text: "待完成考试",
        },
      ],
      pageTitle: "",
      description: "",
      extraImage: "https://gw.alipayobjects.com/zos/rmsportal/RzwpdLnhmvDJToTdfDPe.png",
    };
  },
  computed: {
    ...mapState({
      isLoading: (state) => state.courseHeader.isLoading,
    }),
  },
  mounted() {
    if (this.$store.state.courseHeader.isLoading == 2) {
      this.getCourseInfo();
    }
  },
  methods: {
    getCourseInfo() {
      // 获取课程信息，下面只是模拟一下请求后端获得结果而已.
      this.$store.dispatch("setCourseHeader", 1);
      const courseId = this.$route.params.id;
      const promise = new Promise(function (resolve, reject) {
        resolve("hello");
      });
      promise.then((res) => {
        this.pageTitle = "计算机组成与设计";
        this.description = "2019-2020学年，由赵小刚老师授课\n课程号：" + courseId;
        console.log(this.pageTitle);
        this.$store.dispatch("setCourseHeader", 3);
      });
    },
  },
};
</script>

<style scoped></style>
