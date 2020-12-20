<template>
  <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
    <a-menu
      theme="light"
      class="header"
      mode="horizontal"
      ref="header"
      @click="handleTabSwitch"
      :default-selected-keys="['course']"
      :selected-keys="[currentKey]"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item id="unselectable-wrapper" key="logo">
        <img id="logo" src="@/assets/logo_teacher.png" />
      </a-menu-item>
      <a-menu-item key="course"> <a-icon type="home" />&nbsp;我的课程 </a-menu-item>
      <a-menu-item key="problemRepos"> <a-icon type="home" />&nbsp;真题仓库 </a-menu-item>
      <a-menu-item id="unselectable-wrapper" class="header-index-right" key="userMenu">
        <user-menu @clearSelection="handleClearSelection"></user-menu>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import UserMenu from "@/components/header/UserMenu";
import { mapState } from "vuex";

export default {
  components: {
    UserMenu,
  },
  data() {
    return {
      currentKey: "course",
    };
  },
  methods: {
    handleClearSelection() {
      this.currentKey = "";
    },
    handleTabSwitch: function (obj) {
      const key = obj.key;
      if (key == "logo" || key == "course") {
        this.currentKey = "course";
        this.$router.push({
          path: "/teacher",
        });
      } else if (key == "problemRepos") {
        this.currentKey = key;
        this.$router.push({
          path: "/teacher/repos",
        });
      } else {
      }
    },
  },
  computed: {
    ...mapState({
      pageType: (state) => state.curObj.page.type,
    }),
  },
  watch: {
    pageType: function (val) {
      if (val == "ProblemRepos" || val == "ProblemInRepos") {
        this.currentKey = "problemRepos";
      } else if (val == "MgmtCenter") {
        this.currentKey = "";
      } else {
        this.currentKey = "course";
      }
    },
  },
};
</script>

<style scoped>
#logo {
  height: 32px;
}

#unselectable-wrapper {
  color: rgba(0, 0, 0, 0.65);
  border-bottom: 2px solid transparent !important;
}

.ant-layout-header {
  background-color: #fff !important;
  padding: 0px;
}

.header {
  padding: 0 20px;
}

.header-index-right {
  flex: 1 0 auto;
  height: 64px;
  overflow: hidden;
  float: right;
}
</style>
