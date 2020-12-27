<template>
  <div>
    <a-list item-layout="horizontal" :data-source="data">
      <a-list-item slot="renderItem" slot-scope="item" @click="handleShowModal(item)">
        <a-list-item-meta :description="item.description" :title="item.title">
          <!--<img slot="avatar" class="icon" :src="getImgUrl(item)" />-->
          <a-avatar slot="avatar" :src="getImgUrl(item)" />
        </a-list-item-meta>
      </a-list-item>
    </a-list>
    <a-modal
      v-model="modalVisible"
      :title="currentItem.title"
      ok-text="去看看"
      cancel-text="知道了"
      @ok="handleViewDetail"
    >
      {{ currentItem.description }}
    </a-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modalVisible: false,
      currentItem: {
        title: "",
        description: "",
      },
    };
  },
  props: {
    data: {
      type: Array, //指定传入的类型
    },
  },
  methods: {
    handleShowModal(item) {
      this.modalVisible = true;
      this.currentItem = item;
    },
    getImgUrl(item) {
      if (item.type == 0) {
        return require("../../../assets/img/homework_start.png");
      } else if (item.type == 1) {
        return require("../../../assets/img/exam_create.png");
      } else if (item.type == 2) {
        return require("../../../assets/img/exam_start.png");
      }
    },
    handleViewDetail() {
      this.modalVisible = false;
      switch (this.currentItem.type) {
        case 0: {
          // 跳转到作业
          this.utils.toggle.handleWorkSwitch(
            this,
            "student",
            this.currentItem.workId,
            this.currentItem.courseName
          );
        }
        case 1: {
          // 跳转到课程
          this.utils.toggle.handleCourseSwitch(
            this,
            "student",
            this.currentItem.courseId
          );
        }
        case 2: {
          // 跳转到考试
          this.utils.toggle.handleWorkSwitch(
            this,
            "student",
            this.currentItem.workId,
            this.currentItem.courseName
          );
        }
      }
    },
  },
};
</script>

<style scoped>
.icon {
  height: 60px;
  width: 60px;
  transform: scale(0.3);
}
</style>
