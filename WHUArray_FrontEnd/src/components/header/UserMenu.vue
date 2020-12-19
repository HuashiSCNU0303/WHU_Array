<template>
  <div class="user-wrapper">
    <div class="content-box">
      <a-dropdown>
        <span class="action ant-dropdown-link user-dropdown-menu">
          <a-avatar class="avatar" size="small" />
          <span>&nbsp;西门吹雪</span>
        </span>
        <a-menu slot="overlay" class="user-dropdown-menu-wrapper">
          <a-menu-item key="userSetting">
            <a href="javascript:;" @click="handleMgmtSwitch('userSetting')">
              <a-icon type="setting" />
              <span>账户设置</span>
            </a>
          </a-menu-item>
          <a-menu-divider />
          <a-menu-item key="msg">
            <a href="javascript:;" @click="handleMgmtSwitch('msg')">
              <a-icon type="message" />
              <span>消息提醒</span>
            </a>
          </a-menu-item>
          <a-menu-divider />
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
      const that = this;

      this.$confirm({
        title: "提示",
        content: "真的要注销登录吗 ?",
        onOk() {
          return that
            .Logout({})
            .then(() => {
              window.location.reload();
            })
            .catch((err) => {
              that.$message.error({
                title: "错误",
                description: err.message,
              });
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
