<template>
  <div>
    <a-layout-content :style="{ padding: '0 24px', minHeight: '380px' }">
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-sider width="150" style="background: #fff">
          <a-menu
            mode="inline"
            :default-selected-keys="['homework']"
            @select="handleWorkSwitch"
            style="height: 100%"
          >
            <a-menu-item key="homework">
              <a-icon type="setting" />
              <span>待完成作业</span>
            </a-menu-item>
            <a-menu-item key="exam">
              <a-icon type="message" />
              <span>待完成考试</span>
            </a-menu-item>
          </a-menu>
        </a-layout-sider>
        <a-layout-content :style="{ padding: '0 24px' }">
          <router-view ref="content" />
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </div>
</template>

<script>
export default {
  methods: {
    handleWorkSwitch: function (obj) {
      const key = obj.key;
      this.$router.push({
        path: "./" + key,
      });
    },
  },
};
</script>

<style scoped></style>
