<template>
  <div>
    <a-modal v-model="visible" :title="title">
      <slot slot="footer" name="footer" />
      <a-form>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="课程号"
        >
          1111
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="课程名"
        >
          <a-input v-if="role == teacher" placeholder="给自己起个名字" />
          <span v-else></span>
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="年级"
        >
          2018级
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="课程描述"
        >
          <a-textarea rows="4" placeholder="You are not alone." />
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
    };
  },
  props: {
    role: {
      type: String,
    },
    visible: {
      type: Boolean,
    },
    item: {
      type: Object,
    },
  },
  mounted() {
    this.setModal();
  },
  methods: {
    setModal() {
      this.title = this.role == "teacher" ? "修改课程信息" : "课程信息";
    },
  },
};
</script>

<style scoped></style>
