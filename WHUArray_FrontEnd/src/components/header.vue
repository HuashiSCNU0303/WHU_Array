<template>
  <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
    <a-menu
      theme="light"
      class="header"
      mode="horizontal"
      :default-selected-keys="['1']"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item id="unselectable-wrapper">
        <img id="logo" src="../assets/logo.png" />
      </a-menu-item>
      <a-menu-item key="1"> <a-icon type="home" />&nbsp;首页 </a-menu-item>
      <a-menu-item key="2"> <a-icon type="home" />&nbsp;课程 </a-menu-item>
      <a-menu-item key="3"> <a-icon type="home" />&nbsp;题库 </a-menu-item>
      <a-menu-item id="unselectable-wrapper" class="header-index-right"
        ><user-menu></user-menu>
      </a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import UserMenu from "@/components/usermenu";
export default {
  components: {
    UserMenu,
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
