<template>
  <a-breadcrumb>
    <a-breadcrumb-item v-for="item in items" :key="item.name">
      <router-link v-if="typeof item.href !== 'undefined'" :to="item.href">
        {{ item.name }}
      </router-link>
      <a v-else-if="item.type != ''" @click="handleSwitch(item)">{{ item.name }}</a>
      <div v-else>{{ item.name }}</div>
    </a-breadcrumb-item>
  </a-breadcrumb>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {};
  },
  props: {
    items: {
      type: Array,
    },
  },
  computed: {
    ...mapState({
      user: (state) => state.curObj.user.user,
    }),
  },
  methods: {
    handleSwitch(item) {
      const type = item.type;
      if (type == "Course") {
        this.utils.toggle.handleCourseSwitch(this, this.user.role, item.id);
      } else {
        this.utils.toggle.handleWorkSwitch(this, this.user.role, item.id);
      }
    },
  },
};
</script>

<style scoped></style>
