<template>
  <div>
    <center-loading
      v-if="isLoading == 1"
      :style="{ height: '100%', paddingTop: '64px' }"
    />
    <a-layout-content v-else :style="{ height: '100%', paddingTop: '64px' }">
      <page-header>
        <slot slot="title" name="title">
          <h1>{{ pageTitle }}</h1>
        </slot>
        <slot slot="content" name="headerContent"></slot>
        <div slot="content" v-if="!this.$slots.headerContent && description">
          <p style="font-size: 14px; color: rgba(0, 0, 0, 0.65); white-space: pre-wrap">
            {{ description }}
          </p>
        </div>
        <slot slot="extra" name="extra">
          <div class="extra-img">
            <img v-if="typeof extraImage !== 'undefined'" :src="extraImage" />
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
    return {
      pageTitle: "",
      description: "",
      extraImage: "",
    };
  },
  watch: {
    isLoading: function () {
      console.log("watch " + this.isLoading);
      if (this.isLoading == 3) {
        this.getPageMeta();
        console.log("watch " + this.pageTitle);
      }
    },
  },
  computed: {
    ...mapState({
      isLoading: (state) => state.courseHeader.isLoading,
    }),
  },
  created() {
    this.getPageMeta();
  },
  mounted() {
    this.getPageMeta();
  },
  updated() {
    this.getPageMeta();
  },
  methods: {
    getPageMeta() {
      // eslint-disable-next-line
      const content = this.$refs.content;
      console.log(content);
      if (content) {
        if (content.pageMeta) {
          Object.assign(this, content.pageMeta);
        } else {
          this.pageTitle = content.pageTitle;
          this.description = content.description;
          this.extraImage = content.extraImage;
        }
      }
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
</style>
