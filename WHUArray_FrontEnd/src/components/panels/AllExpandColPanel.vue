<template>
  <div>
    <a-collapse :default-active-key="['1', '2']" :bordered="false">
      <template #expandIcon="props">
        <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
      </template>
      <a-collapse-panel key="1" :header="headers[0]" :style="customStyle">
        <div class="content">
          <center-loading v-if="isLoading == true" />
          <div v-else>
            <slot name="panel1_content" />
          </div>
        </div>
      </a-collapse-panel>
      <a-collapse-panel key="2" :header="headers[1]" :style="customStyle">
        <div class="content">
          <center-loading v-if="isLoading == true" />
          <div v-else>
            <slot name="panel2_content" />
          </div>
        </div>
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
export default {
  data() {
    return {
      customStyle:
        "background: #fff; padding-bottom: 18px; border-bottom: 1px solid #e8e8e8; overflow: hidden",
    };
  },
  props: {
    headers: {
      type: Array,
    },
    isLoading: {
      type: Boolean,
    },
  },
  methods: {},
};
</script>

<style scoped>
.content {
  margin-left: -16px !important;
  margin-right: -16px !important;
}

.ant-collapse > .ant-collapse-item > .ant-collapse-header .ant-collapse-arrow {
  left: 0px !important;
}
</style>
