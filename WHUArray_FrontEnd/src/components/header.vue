<template>
  <div>
  <a-layout-header >
      <a-menu
        theme="light"
        class="header"
        mode="horizontal"
        :default-selected-keys="['1']"
        :style="{ lineHeight: '64px' }"
      >
        <a-menu-item id="logo-wrapper"> <img id="logo" src="../assets/logo.png" /> </a-menu-item>
        <a-menu-item key="1"> 首页 </a-menu-item>
        <a-menu-item key="2"> 作业 </a-menu-item>
        <a-menu-item key="3"> 考试 </a-menu-item>
        <a-menu-item key="4"> 题库 </a-menu-item>
      </a-menu>
    </a-layout-header>
  </div>
</template>

<script>
</script>

<style scoped>
#logo {
  height: 32px;
}

#logo-wrapper {
  border-bottom: 2px solid transparent !important;
}

.ant-layout-header {
  background-color: #fff !important;
  padding: 0px;
}

.header {
  padding: 0 20px;
}
</style>
