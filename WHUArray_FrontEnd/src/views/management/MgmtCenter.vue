<template>
  <a-layout :style="{ height: '100%', paddingTop: '64px' }">
    <a-layout-content style="padding: 30px 50px">
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-sider width="200" style="background: #fff">
          <a-menu
            mode="inline"
            :default-selected-keys="['userSetting']"
            :selected-keys="[selectedKey]"
            :default-open-keys="['bgManagement']"
            @click="handleMgmtSwitch"
            style="height: 100%"
          >
            <a-menu-item key="userSetting">
              <a-icon type="setting" />
              <span>账户设置</span>
            </a-menu-item>
            <a-menu-item key="msgNotification">
              <a-icon type="message" />
              <span>消息通知</span>
            </a-menu-item>
            <a-sub-menu key="bgManagement">
              <span slot="title">
                <a-icon type="notification" />教师后台管理
              </span>
              <a-menu-item key="courseMgmt">课程管理</a-menu-item>
              <a-menu-item key="homeworkMgmt">作业管理</a-menu-item>
              <a-menu-item key="examMgmt">考试管理</a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout-content :style="{ padding: '0 24px', minHeight: '580px' }">
          <router-view ref="content"/>
        </a-layout-content>
      </a-layout>
    </a-layout-content>
    <a-layout-footer style="text-align: center">Ant Design ©2018 Created by Ant UED</a-layout-footer>
  </a-layout>
</template>

<script>
export default {
  data () {
    return {
      selectedKey: '',
    };
  },
  mounted() {
    this.getSelectedKey();
  },
  updated() {
    this.getSelectedKey();
  },
  methods: {
    getSelectedKey () {
      const content = this.$refs.content
      this.selectedKey = content.selectedKey;
      console.log(content);
    },
    handleMgmtSwitch: function (obj) {
      const key = obj.key
      this.$router.push({
          name: key
      })
    }
  },
};
</script>

<style scoped>
</style>