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
            <a-avatar :size="128" :src="updateStudent.userFace" />
          </a-row>
          <a-row>
            <!-- <a-upload
              :name="genFileName()"
              :multiple="false"
              :action="url"
              :headers="headers_"
              :showUploadList="false"
              :before-upload="beforeUpload"
              :customRequest="uploadImage"
              @change="handleAvatarChange"
            > -->
            <a-upload
              :name="genFileName()"
              :multiple="false"
              :showUploadList="false"
              :before-upload="beforeUpload"
              :customRequest="uploadImage"
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
const URL = "http://39.106.97.180:8009";

import { mapState } from "vuex";
import axios from "axios";
export default {
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
      headers_() {
        return {
          Authorization: localStorage.getItem("token"),
        };
      },
    }),
  },
  data() {
    return {
      selectedKey: "userSetting",
      updateStudent: {
        userId: "",
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
      url: URL + "/file/api",
    };
  },
  mounted() {
    this.getStudent();
  },
  methods: {
    getStudent() {
      var _this = this;
      axios
        .get(URL + "/student/findStudentByName", {
          params: {
            name: this.user.username,
          },
          headers: {
            Authorization: localStorage.getItem("token"),
          },
        })
        .then((res) => {
          // console.log(res.data);
          _this.updateStudent = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
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

    uploadImage(file) {
      const formData = new FormData();
      formData.append("file", file.file);
      var _this = this;
      this.api.student.uploadImage(formData, this.headers_).then((res) => {
        var response = res.data;
        console.log(response);
        _this.updateStudent.userFace = response;
        _this.submit();
      });
    },

    /*handleAvatarChange(info) {
      // 上传成功，更改用户头像url，这里逻辑还没写
      if (info.file.status === "done") {
        var response = info.file.response; // 服务器端响应内容
        // ...TODO，获取url
        this.$info({
          title: "注意",
          content: "上传头像后需要提交更改了才能记住哦",
        });
      }
    },*/

    genFileName() {
      return new Date().valueOf() + "_" + this.user.id;
    },

    submit() {
      this.updateStudent.role = "ROLE_" + this.updateStudent.role;
      // console.log(this.updateStudent.role);
      console.log(this.updateStudent);
      var _this = this;
      var headers = {
        Authorization: localStorage.getItem("token"),
      };
      this.api.student
        .updateStudent(this.updateStudent, headers)
        .then((res) => {
          console.log(res);
          // 更新当前用户
          var item = {
            username: _this.user.username,
            id: _this.updateStudent.userId,
            role: "student",
            nickname: _this.updateStudent.nickname,
            userFace: _this.updateStudent.userFace, // 更新URL
          };
          _this.$store.dispatch("setCurrentUser", item);
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
