<template>
  <div>
    <!--<center-loading
      v-if="isLoading == 1"
      :style="{ height: '100%', paddingTop: '64px' }"
    />-->
    <a-layout-content :style="{ height: '100%', paddingTop: '64px' }">
      <page-header>
        <slot slot="title" name="title">
          <h1>{{ header.pageTitle }}</h1>
        </slot>
        <slot slot="content" name="headerContent"></slot>
        <div slot="content" v-if="!this.$slots.headerContent && header.description" v-html="header.description">
        </div>
        <slot slot="extra" name="extra">
          <div class="extra-img">
            <img
              v-if="typeof header.extraImage !== 'undefined'"
              :src="header.extraImage"
            />
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
import PageHeader from "@/components/PageHeader";
import CenterLoading from "@/components/CenterLoading.vue";
import { mapState } from "vuex";

export default {
  components: {
    PageHeader,
    CenterLoading,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState({
      header: (state) => state.currentPageHeader.header,
    }),
  },

  watch: {
    header: {
      handler: function () {
        console.log("InCommonPageView");
        console.log(this.header);
      },
      deep: true,
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

.description {
  font-size: 14px;
  color: rgba(0, 0, 0, 0.65);
  white-space: pre-wrap;
}
</style>
