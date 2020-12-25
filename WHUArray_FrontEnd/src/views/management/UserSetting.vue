<template>
  <div>
    <big-title><p>账户设置</p></big-title>
    <div>
      <a-row :gutter="16">
        <a-col :span="16">
          <a-form layout="vertical" :model="updateStudent">
            <a-form-item label="昵称">
              <a-input
                :placeholder="updateStudent.nickname"
                v-model="updateStudent.nickname"
              />
            </a-form-item>

            <a-form-item label="学号">
              <a-input
                :placeholder="updateStudent.studentId"
                v-model="updateStudent.studentId"
              />
            </a-form-item>

            <a-form-item label="联系电话">
              <a-input
                :placeholder="updateStudent.telephone"
                v-model="updateStudent.telephone"
              />
            </a-form-item>

            <a-form-item label="电子邮件" :required="false">
              <a-input :placeholder="updateStudent.mail" v-model="updateStudent.mail" />
            </a-form-item>

            <a-form-item>
              <a-button type="primary" @click="submit">提交</a-button>
            </a-form-item>
          </a-form>
        </a-col>
        <a-col :span="6" justify="center" align="middle">
          <a-row>
            <a-avatar
              :size="128"
              src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
            />
          </a-row>
          <a-row>
            <a-upload
              :name="genFileName()"
              :multiple="false"
              :action="url"
              :data="data"
              :headers="headers"
              :showUploadList="false"
              :before-upload="beforeUpload"
              @change="handleAvatarChange"
            >
              <a-button type="primary" icon="upload" ghost style="margin-top: 16px"
                >更改头像</a-button
              >
            </a-upload>
          </a-row>
        </a-col>
      </a-row>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
  },
  data() {
    return {
      selectedKey: "userSetting",
      updateStudent: {
        id: "",
        name: "",
        password: "",
        role: "",
        studentId: "",
        nickname: "",
        mail: "",
        telephone: "",
        userFace: "",
      },

      // 上传头像需要的参数，组件要求只能在这里写，就不搞到api里面去了
      url: "",
      data: {},
      headers: {},
    };
  },
  mounted() {
    this.getStudent();
  },
  methods: {
    getStudent() {
      console.log(this.user.role);
      let _this = this;
      switch (this.user.role) {
        case 1: //老师，不知道是不是在这写
          break;
        case 2:
          // 有一说一，这里界面刷新的时候会把前端的currentUser刷掉，建议老师和学生各做一个界面，用前端存的数据只有在界面没刷新之前有用，或者在mounted设置用户的角色
          // 感觉最好把两个角色分开
          axios
            .get("http://localhost:8009/student/findStudentByName", {
              params: {
                name: this.user.username,
              },
              headers: {
                Authorization: localStorage.getItem("token"),
              },
            })
            .then((res) => {
              console.log(res.data);
              _this.updateStudent = res.data;
            })
            .catch((error) => {
              console.log(error);
            });
      }
    },

    beforeUpload(file) {
      const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
      if (!isJpgOrPng) {
        this.$message.error("只能上传.jpg或.png文件！");
      }
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error("上传文件过大，只能上传小于2MB的文件！");
      }
      return isJpgOrPng && isLt2M;
    },

    handleAvatarChange(info) {
      // 上传成功，更改用户头像url，这里逻辑还没写
      if (info.file.status === "done") {
        var response = info.file.response; // 服务器端响应内容
        // ...TODO，获取url
        this.$info({
          title: "注意",
          content: "上传头像后需要提交更改了才能记住哦",
        });
      }
    },

    genFileName() {
      return new Date().valueOf() + "_" + this.user.id;
    },

    submit() {
      this.updateStudent.role = "ROLE_" + this.updateStudent.role;
      // console.log(this.updateStudent.role);
      var _this = this;
      var headers = {
        Authorization: localStorage.getItem("token"),
      };
      this.api.student
        .updateStudent(this.updateStudent, headers)
        .then((res) => {
          console.log(res);
          // 更新当前用户
          _this.dispatch("setCurrentUser", _this.updateStudent);
          _this.$success({
            title: "修改成功",
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style lang="less" scoped></style>
