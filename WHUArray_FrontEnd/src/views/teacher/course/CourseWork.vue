<template>
  <div>
    <big-title
      ><p>{{ typeName }}列表</p></big-title
    >
    <center-loading v-if="isLoading == true" />
    <div v-else>
      <a-row>
        <a-button
          type="primary"
          icon="user"
          @click="
            () => {
              this.modalVisible = true;
            }
          "
          style="float: left"
          >添加{{ typeName }}</a-button
        >
      </a-row>
      <tea-work-card-list
        v-if="workList.length > 0"
        :data="workList"
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
  },
  data() {
    return {
      modalVisible: false,
      workList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未完成的作业",
    };
  },
  mounted() {
    this.getWorks();
  },
  methods: {
    getWorks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        // 根据type判断一下，获取作业列表还是考试列表
        this.workList = this.$store.state.tempData.homeworkList.homeworkList;
        this.isLoading = false;
      }, 1000);
    },
    switchToWork(item) {
      // 跳转到作业
      if (this.type == "Homework") {
        this.utils.toggle.handleHomeworkSwitch(this, "teacher", item);
      } else {
        this.utils.toggle.handleExamSwitch(this, "teacher", item);
      }
    },
    addWork(work) {
      var work_ = {
        name: work.name,
        startTime: this.utils.countdown.transPickerToString(work.startTime),
        endTime: this.utils.countdown.transPickerToString(work.endTime),
        status: "unpublished",
        type: this.type,
      };
      // 把组装好的work_发送给后台，添加作业/考试，给我返回一个课程号！！

      work_["id"] = 10005;
      this.workList.push(work_);
      this.modalVisible = false;
      this.$success({
        title: "添加成功",
        onOk() {
          // 添加成功以后页面reload一下
        },
      });
    },
  },
};
</script>

<style scoped></style>
