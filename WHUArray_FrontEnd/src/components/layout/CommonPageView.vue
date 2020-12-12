<template>
  <div>
    <!--<center-loading
      v-if="isLoading == 1"
      :style="{ height: '100%', paddingTop: '64px' }"
    />-->
    <a-layout-content :style="{ height: '100%', paddingTop: '64px' }">
      <page-header>
        <slot
          v-if="typeof header.breadCrumbLayer !== 'undefined'"
          slot="breadcrumb"
          name="breadcrumb"
        >
          <bread-crumb-nav :currentLayer="header.breadCrumbLayer" :key="refreshKey" />
        </slot>
        <slot slot="title" name="title">
          <h1>{{ header.pageTitle }}</h1>
        </slot>
        <slot slot="content" name="headerContent"></slot>
        <div
          slot="content"
          v-if="!this.$slots.headerContent && header.description"
          v-html="header.description"
        ></div>
        <slot slot="extra" name="extra">
          <div
            v-if="typeof header.extraImage !== 'undefined'"
            class="extra-img"
            :key="refreshKey"
          >
            <img :src="header.extraImage" />
          </div>
          <div
            v-else-if="typeof header.countdownIndicator !== 'undefined'"
            class="countdown"
            :key="refreshKey"
          >
            <countdown :currentAnchorTime="1607704233000" />
          </div>
        </slot>
      </page-header>
      <a-layout-content style="padding: 24px">
        <a-layout style="padding: 12px 0; background: #fff">
          <router-view ref="content" />
        </a-layout>
      </a-layout-content>
      <a-layout-footer style="text-align: center"
        >Ant Design ©2018 Created by Ant UED</a-layout-footer
      >
    </a-layout-content>
  </div>
</template>

<script>
import PageHeader from "@/components/layout/PageHeader";
import BreadCrumbNav from "@/components/layout/BreadCrumbNav.vue";

import { mapState } from "vuex";

export default {
  data() {
    return {
      refreshKey: false,
    };
  },
  components: {
    PageHeader,
    BreadCrumbNav,
  },
  computed: {
    ...mapState({
      header: (state) => state.currentPageHeader.header,
    }),
  },
  methods: {
    updateComponents() {
      this.refreshKey = this.refreshKey ? false : true;
    },
  },
  watch: {
    header: function () {
      this.updateComponents();
    },
  },
};
</script>

<style lang="less" scoped>
.extra-img {
  margin-top: -40px;
  text-align: center;
  width: 195px;

  img {
    width: 100%;
  }
}

.countdown {
  margin-top: -20px;
  padding-bottom: 16px;
  margin-left: -100px;
  text-align: center;
  width: 195px;
}
</style>
