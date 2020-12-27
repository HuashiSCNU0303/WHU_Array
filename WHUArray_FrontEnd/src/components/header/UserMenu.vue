<template>
  <div class="user-wrapper">
    <div class="content-box">
      <a-dropdown>
        <span class="action ant-dropdown-link user-dropdown-menu">
          <span v-if="user.role == 'teacher'">
            <a-avatar style="background-color: #1890ff">老师</a-avatar>
            <span>&nbsp;{{ user.username }}</span>
          </span>
          <span v-else>
            <a-avatar class="avatar" :src="user.userFace" />
            <span>&nbsp;{{ user.nickname }}</span>
          </span>
        </span>
        <a-menu slot="overlay" class="user-dropdown-menu-wrapper">
          <a-menu-item v-if="user.role == 'student'" key="userSetting">
            <a href="javascript:;" @click="handleMgmtSwitch('userSetting')">
              <a-icon type="setting" />
              <span>账户设置</span>
            </a>
          </a-menu-item>
          <a-menu-divider v-if="user.role == 'student'" />
          <a-menu-item v-if="user.role == 'student'" key="msg">
            <a href="javascript:;" @click="handleMgmtSwitch('msg')">
              <a-icon type="message" />
              <span>消息提醒</span>
            </a>
          </a-menu-item>
          <a-menu-divider v-if="user.role == 'student'" />
          <a-menu-item key="logOut">
            <a href="javascript:;" @click="handleLogout">
              <a-icon type="logout" />
              <span>退出登录</span>
            </a>
          </a-menu-item>
        </a-menu>
      </a-dropdown>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
  },
  methods: {
    handleMgmtSwitch(key) {
      this.$emit("clearSelection");
      this.$router.push({
        path: "/" + this.user.role + "/mgmt/" + key.toLowerCase(),
      });
    },
    handleLogout() {
      const _this = this;

      this.$confirm({
        title: "提示",
        content: "真的要注销登录吗 ?",
        okText: "确定",
        cancelText: "取消",
        onOk() {
          var user = {};
          localStorage.removeItem("token");
          _this.$store.dispatch("setCurrentUser", user);
          _this.$router.push({
            path: "/",
          });
        },
        onCancel() {},
      });
    },
  },
};
</script>

<style scoped>
.content-box {
  float: right;
}
</style>
