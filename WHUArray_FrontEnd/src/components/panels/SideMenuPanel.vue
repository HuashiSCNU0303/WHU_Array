<template>
  <div>
    <a-layout-content :style="{ padding: '0 24px', minHeight: '380px' }">
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-sider width="150" style="background: #fff">
          <a-menu
            mode="inline"
            :default-selected-keys="[items[0].key]"
            @select="handleSwitch"
            style="height: 100%"
          >
            <a-menu-item v-for="item in items" :key="item.key">
              <a-icon v-if="typeof item.iconType !== 'undefined'" :type="item.iconType" />
              <slot v-else name="icon" />
              <span>{{ item.text }}</span>
            </a-menu-item>
          </a-menu>
        </a-layout-sider>
        <a-layout-content :style="{ padding: '0 24px' }">
          <router-view ref="content" />
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </div>
</template>

<script>
export default {
  props: {
    items: {
      type: Array,
      default: [
        {
          key: "",
        },
      ],
    },
  },
  methods: {
    handleSwitch: function (obj) {
      const key = obj.key;
      this.$router.push({
        path: "./" + key.toLowerCase(),
      });
    },
  },
};
</script>

<style scoped></style>
