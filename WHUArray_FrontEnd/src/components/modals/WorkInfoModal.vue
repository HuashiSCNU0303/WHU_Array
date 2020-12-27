<template>
  <div>
    <a-modal
      v-model="visible"
      :title="title"
      ok-text="提交"
      cancel-text="取消"
      @ok="handleOk(work, true)"
      @cancel="handleCancel"
    >
      <a-form>
        <a-form-item
          v-if="typeof item !== 'undefined'"
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          :label="type + '号'"
        >
          {{ work.id }}
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          :label="type + '名'"
        >
          <a-input
            v-model="work.name"
            :default-value="typeof item !== 'undefined' ? item.name : ''"
          />
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="开始时间"
        >
          <a-date-picker v-model="work.startTime.date" />
          <a-time-picker v-model="work.startTime.time" style="margin-left: 16px" />
        </a-form-item>
        <a-form-item
          :label-col="formItemLayout.labelCol"
          :wrapper-col="formItemLayout.wrapperCol"
          label="截止时间"
        >
          <a-date-picker v-model="work.endTime.date" />
          <a-time-picker v-model="work.endTime.time" style="margin-left: 16px" />
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
      work: {
        name: "",
        startTime: {
          date: "",
          time: this.$moment("00:00:00", "HH:mm:ss"),
        },
        endTime: {
          date: "",
          time: this.$moment("00:00:00", "HH:mm:ss"),
        },
      },
    };
  },
  props: {
    visible: {
      type: Boolean,
    },
    item: {
      type: Object,
    },
    type: {
      type: String,
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
  },

  watch: {
    visible: function (newVal) {
      if (this.visible) {
        this.setModal();
      }
    },
  },

  methods: {
    setModal() {
      this.title =
        typeof this.item === "undefined"
          ? "添加" + this.type
          : "修改" + this.type + "信息";
      if (typeof this.item !== "undefined") {
        var item = {
          id: this.item.id,
          name: this.item.name,
          startTime: this.utils.countdown.transStringToPicker(this, this.item.startTime),
          endTime: this.utils.countdown.transStringToPicker(this, this.item.endTime),
        };
        this.work = item;
      }
    },
  },
};
</script>

<style scoped></style>
