<template>
  <div>
    <a-modal
      v-model="visible"
      :title="title"
      ok-text="提交"
      cancel-text="取消"
      @ok="handleOk(course)"
      @cancel="handleCancel"
    >
      <a-form>
        <a-form-item
          v-if="typeof item !== 'undefined'"
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="课程号"
        >
          {{ item.id }}
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="课程名"
        >
          <a-input
            v-model="course.name"
            :default-value="typeof item !== 'undefined' ? item.name : ''"
          />
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="年级"
        >
          <a-select
            v-model="course.grade"
            :default-value="typeof item !== 'undefined' ? item.grade : ''"
          >
            <a-select-option v-for="grade in gradeOptions" :key="grade" :value="grade">
              {{ grade }}
            </a-select-option>
          </a-select>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "",
      formItemLayout: {
        labelCol: { span: 4 },
        wrapperCol: { span: 20 },
      },
      course: {
        name: "",
        
      },
      gradeOptions: [],
    };
  },
  props: {
    visible: {
      type: Boolean,
    },
    item: {
      type: Object,
    },
    handleOk: {
      type: Function,
    },
    handleCancel: {
      type: Function,
    },
  },
  mounted() {
    this.setModal();
    this.setGradeOptions();
  },
  methods: {
    setModal() {
      this.title = typeof this.item === "undefined" ? "添加课程" : "修改课程信息";
      if (typeof this.item !== "undefined") {
        this.course = this.item;
      }
    },
    setGradeOptions() {
      var date = new Date();
      var latestGrade = date.getMonth() < 8 ? date.getFullYear() - 1 : date.getFullYear();
      for (var i = 0; i < 8; i++) {
        this.gradeOptions.push(latestGrade - i + "级");
      }
    },
  },
};
</script>

<style scoped></style>
