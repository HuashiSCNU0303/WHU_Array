<template>
  <div id="login">
    <div class="bg"></div>
    <el-row class="main-container">
      <el-col :span="12" :offset="0">
        <el-row class="logo">
          <img src="../assets/logo.png" />
        </el-row>
        <el-row class="header">
          <h2>一个基于SpringBoot+Vue开发的在线编程作业/考试平台</h2>
        </el-row>
      </el-col>
      <el-col :span="7" :offset="1">
        <div class="bottom">
          <el-tabs class="container">
            <el-tab-pane>
              <span slot="label"><i class="el-icon-user-solid"></i>&nbsp;登录</span>
              <el-form
                :label-position="labelPosition"
                label-width="80px"
                ref="loginUserInfo"
                :model="loginUserInfo"
              >
                <el-form-item label="用户名">
                  <el-input
                    v-model="loginUserInfo.username"
                    placeholder="请输入用户名"
                  ></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input
                    v-model="loginUserInfo.password"
                    placeholder="请输入密码"
                    type="password"
                  ></el-input>
                </el-form-item>
                <el-form-item label="你的身份" prop="role">
                  <el-radio-group v-model="loginUserInfo.role">
                    <el-radio label="学生" ></el-radio>
                    <el-radio label="老师" ></el-radio>
                  </el-radio-group>
                </el-form-item>
                <div class="submit">
                  <el-button type="primary" @click="login()">登录</el-button>
                </div>
              </el-form>
            </el-tab-pane>
            <el-tab-pane>
              <span slot="label"><i class="el-icon-circle-plus"></i>&nbsp;注册</span>
              <el-form
                :label-position="labelPosition"
                label-width="80px"
                :model="registerUserInfo"
              >
                <el-form-item label="用户名">
                  <el-input
                    v-model="registerUserInfo.username"
                    placeholder="请输入用户名"
                  ></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input
                    v-model="registerUserInfo.password"
                    placeholder="请输入密码"
                    type="password"
                  ></el-input>
                </el-form-item>
                <el-form-item label="你的身份">
                  <el-radio-group v-model="registerUserInfo.role">
                    <el-radio label="学生" key="2"></el-radio>
                    <el-radio label="老师" key="1"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <div class="submit">
                  <el-button type="primary" @click="register()">登录</el-button>
                </div>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
    <el-row class="footer">
      <el-col>
        <p class="msg2">版权所有&nbsp;©2019&nbsp;武汉大学计算机学院</p>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      labelPosition: "left",
      loginUserInfo: {
        username: "",
        password: "",
        role: "学生",
      },
      registerUserInfo: {
        username: "1",
        password: "2",
        role: "老师",
      },
    };
  },
  methods: {
    login() {
      // 请求后台登录接口，下面是原项目的旧代码，没有删
      console.log(JSON.stringify(this.loginUserInfo));
      /*this.$axios({
        url: `/api/login`,
        method: "post",
        data: {
          ...this.userInfo,
        },
      }).then((res) => {
        let resData = res.data.data;
        if (resData != null) {
          switch (resData.role) {
            case "1": //教师
              this.$cookies.set("cname", resData.teacherName);
              this.$cookies.set("cid", resData.teacherId);
              this.$cookies.set("role", 1);
              this.$router.push({ path: "/index" }); //跳转到教师用户
              break;
            case "2": //学生
              this.$cookies.set("cname", resData.studentName);
              this.$cookies.set("cid", resData.studentId);
              this.$router.push({ path: "/student" });
              break;
          }
        }
        if (resData == null) {
          //错误提示
          this.$message({
            showClose: true,
            type: "error",
            message: "用户名或者密码错误",
          });
        }
      });*/
    },
    register() {
      // 请求后台注册接口
    },
  },
  mounted() {},
};
</script>

<style lang="scss" scoped>
.container {
  margin-bottom: 32px;
  margin-top: 32px;
}
#login {
  font-size: 14px;
  color: #000;
  background-color: #fff;
}
#login .bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  overflow-y: auto;
  height: 100%;
  background: url("../assets/img/login_bg.png") center top / cover no-repeat;
  background-color: #b6bccdd1 !important;
}
#login .main-container {
  display: flex;
  justify-content: center;
  align-items: center;
}
#login .bottom {
  display: flex;
  margin-top: 40px;
  justify-content: center;
  align-items: center;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
#login .logo {
  margin-top: 40px;
  transform: scale(0.8);
}
#login .header {
  margin-top: -10px;
  text-align: center;
  font-size: 120%;
  color: #fff;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
.bottom .submit {
  display: flex;
  justify-content: center;
}
.footer {
  margin-top: 50px;
  text-align: center;
}
.footer .msg2 {
  font-size: 14px;
  color: #e3e3e3;
  margin-top: 70px;
}
</style>
