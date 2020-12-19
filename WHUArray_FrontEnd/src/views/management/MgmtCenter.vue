<template>
  <a-layout :style="{ height: '100%', paddingTop: '64px' }">
    <a-layout-content style="padding: 30px 50px">
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-sider width="200" style="background: #fff">
          <a-menu
            mode="inline"
            :default-selected-keys="['userSetting']"
            :selected-keys="[selectedKey]"
            @click="handleMgmtSwitch"
            style="height: 100%"
          >
            <a-menu-item key="userSetting">
              <a-icon type="setting" />
              <span>账户设置</span>
            </a-menu-item>
            <a-menu-item key="msg">
              <a-icon type="message" />
              <span>消息提醒</span>
            </a-menu-item>
          </a-menu>
        </a-layout-sider>
        <a-layout-content :style="{ padding: '0 24px', minHeight: '380px' }">
          <router-view ref="content" />
        </a-layout-content>
      </a-layout>
    </a-layout-content>
    <a-layout-footer style="text-align: center"
      >Ant Design ©2018 Created by Ant UED</a-layout-footer
    >
  </a-layout>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      selectedKey: "",
    };
  },
  computed: {
    ...mapState({
      user: (state) => state.currentUser.user,
    }),
  },
  mounted() {
    this.getSelectedKey();
    this.$store.dispatch("setCurrentPageHeader", {});
    this.$store.dispatch("setCurrentPageType", "MgmtCenter");
  },
  updated() {
    this.getSelectedKey();
  },
  methods: {
    getSelectedKey() {
      const content = this.$refs.content;
      this.selectedKey = content.selectedKey;
    },
    handleMgmtSwitch: function (obj) {
      const key = obj.key;
      this.$router.push({
        path: "./" + key.toLowerCase(),
      });
    },
  },
};
</script>

<style scoped></style>
