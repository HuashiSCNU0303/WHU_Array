<template>
  <div id="login">
    <div class="bg"></div>
    <a-row class="main-container">
      <a-col :span="12" :offset="0" type="flex" justify="center" align="middle">
        <a-row class="logo">
          <img src="../assets/logo.png" />
        </a-row>
        <a-row>
          <p class="header-text">
            <b>一个基于SpringCloud+Vue开发的在线编程作业/考试平台</b>
          </p>
        </a-row>
      </a-col>
      <a-col :span="7" :offset="1">
        <div class="bottom">
          <a-tabs class="container" default-active-key="1">
            <a-tab-pane key="1">
              <span slot="tab"><a-icon type="login" />&nbsp;登录</span>
              <a-form-model ref="loginUserInfo" :model="loginUserInfo">
                <a-form-model-item>
                  <a-input
                    v-model="loginUserInfo.username"
                    type="text"
                    placeholder="请输入帐户名"
                  >
                    <a-icon
                      slot="prefix"
                      type="user"
                      :style="{ color: 'rgba(0,0,0,.25)' }"
                    />
                  </a-input>
                </a-form-model-item>

                <a-form-model-item>
                  <a-input
                    v-model="loginUserInfo.password"
                    type="password"
                    autocomplete="false"
                    placeholder="请输入密码"
                  >
                    <a-icon
                      slot="prefix"
                      type="lock"
                      :style="{ color: 'rgba(0,0,0,.25)' }"
                    />
                  </a-input>
                </a-form-model-item>
                <a-form-model-item
                  label="你的身份"
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                >
                  <a-radio-group v-model="role">
                    <a-radio value="1">老师</a-radio>
                    <a-radio value="2">学生</a-radio>
                  </a-radio-group>
                </a-form-model-item>
                <div class="submit">
                  <a-button type="primary" @click="login()">登录</a-button>
                </div>
              </a-form-model>
            </a-tab-pane>
            <a-tab-pane key="2">
              <span slot="tab"><a-icon type="user-add" />&nbsp;注册</span>
              <a-form-model ref="registerUserInfo" :model="registerUserInfo">
                <a-form-model-item>
                  <a-input
                    v-model="registerUserInfo.name"
                    type="text"
                    placeholder="请输入帐户名"
                  >
                    <a-icon
                      slot="prefix"
                      type="user"
                      :style="{ color: 'rgba(0,0,0,.25)' }"
                    />
                  </a-input>
                </a-form-model-item>

                <a-form-model-item>
                  <a-input
                    v-model="registerUserInfo.password"
                    type="password"
                    autocomplete="false"
                    placeholder="请输入密码"
                  >
                    <a-icon
                      slot="prefix"
                      type="lock"
                      :style="{ color: 'rgba(0,0,0,.25)' }"
                    />
                  </a-input>
                </a-form-model-item>
                <a-form-model-item
                  label="你的身份"
                  :label-col="labelCol"
                  :wrapper-col="wrapperCol"
                >
                  <a-radio-group v-model="role">
                    <a-radio value="1">老师</a-radio>
                    <a-radio value="2">学生</a-radio>
                  </a-radio-group>
                </a-form-model-item>
                <div class="submit">
                  <a-button type="primary" @click="register()">注册</a-button>
                </div>
              </a-form-model>
            </a-tab-pane>
          </a-tabs>
        </div>
      </a-col>
    </a-row>
    <a-row class="footer">
      <a-col>
        <p class="msg2">版权所有&nbsp;©2020&nbsp;武汉大学计算机学院</p>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  name: "login",
  computed: {
    ...mapState({
      user: (state) => state.currentUser.user,
    }),
  },
  data() {
    return {
      labelPosition: "left",
      formLayout: "horizontal",
      labelCol: {
        span: "8",
        offset: "2",
      },
      wrapperCol: {
        span: "12",
      },
      loginUserInfo: {
        username: "",
        password: "",
      },
      registerUserInfo: {
        name: "",
        password: "",
      },
      role: "2",
    };
  },
  methods: {
    login() {
      var user = {
        username: this.loginUserInfo.username,
        role: this.role == "1" ? "teacher" : "student",
      };
      var data = {
        ...this.loginUserInfo,
        rememberMe: true,
      };
      var _this = this;
      this.api.user
        .login(data)
        .then((res) => {
          let token = res.headers.authorization;
          localStorage.setItem("token", token);
          var headers = {
            Authorization: localStorage.getItem("token"),
          };
          console.log(headers);
          _this.getCurrentUser(headers);
        })
        .catch((error) => {
          console.log(error);
          if (error.response.status === 401) {
            alert("用户名或密码错误");
          }
        });
    },

    register() {
      // 请求后台注册接口
      var _this = this;
      this.api.user
        .register(this.registerUserInfo, this.role == "1" ? "teacher" : "student")
        .then((res) => {
          console.log(res);
          _this.$success({
            title: "注册成功！",
            content: "登录以使用系统的功能",
          });
          _this.loginUserInfo = {
            username: _this.registerUserInfo.name,
            password: _this.registerUserInfo.password,
          };
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getCurrentUser(headers) {
      var _this = this;
      var axios =
        _this.role == "2"
          ? this.api.student.getCurrentStudent(headers)
          : this.api.teacher.getCurrentTeacher(headers);
      axios.then((res) => {
        var response = res.data;
        console.log(response);

        var user = {};
        if (this.role == "2") {
          user = {
            username: this.loginUserInfo.username,
            id: response,
            role: "student",
            nickname: "",
            userFace: "",
          };
        } else {
          user = {
            username: this.loginUserInfo.username,
            id: response.userId,
            role: "teacher",
          };
        }
        _this.$store.dispatch("setCurrentUser", user);
        _this.$router.push({
          path: "/" + (_this.role == "1" ? "teacher" : "student"),
        });
      });
    },
  },

  mounted() {},
};
</script>

<style scoped>
.container {
  margin-bottom: 32px;
  margin-top: 32px;
  width: 80%;
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
  margin-top: 60px;
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
  transform: scale(0.6);
  float: center;
}
#login .header-text {
  text-align: center;
  font-size: 25px;
  color: #fff;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande", "Lucida Sans",
    Arial, sans-serif;
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
