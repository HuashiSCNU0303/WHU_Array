<template>
  <div>
    <big-title
      ><p>{{ typeName }}列表</p></big-title
    >
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <a-row>
        <a-button
          v-if="course.status == 'on'"
          type="primary"
          icon="file-add"
          @click="
            () => {
              this.modalVisible = true;
            }
          "
          style="float: left"
          >添加{{ typeName }}</a-button
        >
        <div style="float: right">
          <a-select style="width: 120px" v-model="curFilterStatus">
            <a-select-option
              v-for="option in statusOptions"
              :value="option"
              :key="option"
            >
              {{ option }}
            </a-select-option>
          </a-select>
          <a-button
            type="primary"
            icon="sync"
            @click="
              () => {
                refreshKey = 1;
                curFilterStatus = '';
              }
            "
            ghost
            >重置</a-button
          >
        </div>
      </a-row>
      <tea-work-card-list
        v-if="workList.length > 0"
        :data="workList"
        :key="refreshKey"
        style="margin: 16px 0"
      >
        <div slot="content" slot-scope="props" @click="switchToWork(props.item)">
          <work-card :item="props.item" />
        </div>
      </tea-work-card-list>
      <icon-hint v-else :hint="emptyHint" />
    </div>
    <work-info-modal
      :visible="modalVisible"
      :type="typeName"
      :handleOk="addWork"
      :handleCancel="
        () => {
          this.modalVisible = false;
        }
      "
    />
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState({
      course: (state) => state.curObj.course.course,
    }),
    type() {
      return this.$route.meta.type;
    },
    typeName() {
      return this.type == "Homework" ? "作业" : "考试";
    },
    headers() {
      return {
        Authorization: localStorage.getItem("token"),
      };
    },
  },
  watch: {
    type: function () {
      this.isLoading = true;
      this.getWorks();
    },
    curFilterStatus: function () {
      if (this.curFilterStatus == "") {
        for (var i = 0; i < this.workList.length; i++) {
          this.workList[i].visible = true;
        }
      } else {
        for (var i = 0; i < this.workList.length; i++) {
          this.workList[i].visible =
            this.workList[i].status == this.curFilterStatus ? true : false;
        }
      }
      this.refreshKey++;
    },
  },
  data() {
    return {
      modalVisible: false,
      workList: [],
      statusOptions: ["未发布", "已发布", "已结束"],
      curFilterStatus: "",
      refreshKey: 1,
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未发布的作业",
    };
  },
  mounted() {
    this.getWorks();
  },
  methods: {
    getWorks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      this.workList = [];
      var _this = this;
      var data = {
        courseId: this.course.id,
      };
      this.api.teacher.getCourseWork(data, this.headers).then((res) => {
        var response = res.data;
        // 对response进行处理，变成下面的works
        var works = response;
        console.log(works);
        for (var i = 0; i < works.length; i++) {
          var work_ = works[i];

          if (
            (this.type == "Homework" && work_.isExam == 1) ||
            (this.type == "Exam" && work_.isExam == 0)
          ) {
            continue;
          }

          var work = {
            id: work_.homeworkId,
            status: work_.status,
            name: work_.homeworkName,
            startTime: work_.startTime,
            endTime: work_.endTime,
            type: work_.isExam == 0 ? "Homework" : "Exam",
          };

          console.log(work);

          _this.utils.statusHandler.handleTeacherWork(_this, work);
          work["visible"] = true;
          _this.workList.push(work);
        }
        _this.isLoading = false;
      });
    },
    switchToWork(item) {
      // 跳转到作业
      this.utils.toggle.handleWorkSwitch(this, "teacher", item.id);
    },
    addWork(work) {
      var work_ = {
        homeworkName: work.name,
        courseId: this.course.id,
        startTime: this.utils.countdown.transPickerToString(work.startTime),
        endTime: this.utils.countdown.transPickerToString(work.endTime),
        status: "unpublished",
        isExam: this.type == "Homework" ? 0 : 1,
      };

      console.log(work);
      // 把组装好的work_发送给后台，添加作业/考试，给我返回一个作业/考试号！！
      var _this = this;
      this.api.teacher.addWork(work_, this.headers).then((res) => {
        var response = res.data;
        // 对response进行处理，取得作业/考试号
        work["id"] = response;
        work["status"] = "未发布";
        work.startTime = work_.startTime;
        work.endTime = work_.endTime;
        _this.workList.push(work);
        _this.modalVisible = false;
        _this.$success({
          title: "添加成功",
        });
      });
    },
  },
};
</script>

<style scoped></style>
