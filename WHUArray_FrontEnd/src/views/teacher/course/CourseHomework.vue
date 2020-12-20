<template>
  <div>
    <big-title><p>作业列表</p></big-title>
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
          >添加作业</a-button
        >
      </a-row>
      <tea-work-card-list
        v-if="homeworkList.length > 0"
        :data="homeworkList"
        style="margin: 16px 0"
      >
        <div slot="content" slot-scope="props" @click="switchToHomework(props.item)">
          <work-card :item="props.item" />
        </div>
      </tea-work-card-list>
      <icon-hint v-else :hint="emptyHint" />
    </div>
    <work-info-modal
      :visible="modalVisible"
      :type="'作业'"
      :handleOk="addHomework"
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
  },
  data() {
    return {
      modalVisible: false,
      homeworkList: [],
      isLoading: true, // 标识一下正在加载的状态
      emptyHint: "当前没有未完成的作业",
    };
  },
  mounted() {
    this.getHomeworks();
  },
  methods: {
    getHomeworks() {
      // 获取作业列表，下面只是模拟一下请求后端获得结果而已
      setTimeout(() => {
        this.homeworkList = this.$store.state.tempData.homeworkList.homeworkList;
        this.isLoading = false;
      }, 1000);
    },
    switchToHomework(item) {
      // 跳转到作业
      this.utils.toggle.handleHomeworkSwitch(this, "teacher", item);
    },
    addHomework(work) {
      var work_ = {
        name: work.name,
        startTime: this.utils.countdown.transPickerToString(work.startTime),
        endTime: this.utils.countdown.transPickerToString(work.endTime),
        status: "unpublished",
        type: "homework",
      };
      // 把组装好的work_发送给后台
      this.modalVisible = false;
    },
  },
};
</script>

<style scoped></style>
