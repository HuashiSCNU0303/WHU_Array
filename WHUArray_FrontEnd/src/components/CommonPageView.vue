<template>
  <div>
    <a-layout-content :style="{ height: '100%', paddingTop: '64px' }">
      <page-header>
        <slot slot="action" name="action"></slot>
        <slot slot="content" name="headerContent"></slot>
        <div slot="content" v-if="!this.$slots.headerContent && description">
          <p style="font-size: 14px; color: rgba(0, 0, 0, 0.65)">
            {{ description }}
          </p>
        </div>
        <slot slot="extra" name="extra">
          <div class="extra-img">
            <img v-if="typeof extraImage !== 'undefined'" :src="extraImage" />
          </div>
        </slot>
      </page-header>
      <a-layout-content style="padding: 30px 50px">
        <a-layout style="padding: 24px 0; background: #fff">
          <a-layout-content :style="{ padding: '0 24px', minHeight: '580px' }">
            <router-view ref="content" />
          </a-layout-content>
        </a-layout>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout-content>
  </div>
</template>

<script>
import PageHeader from "@/components/pageheader";

export default {
  components: {
    PageHeader,
  },
  props: {
    avatar: {
      type: String,
      default: null,
    },
    title: {
      type: [String, Boolean],
      default: true,
    },
    logo: {
      type: String,
      default: null,
    },
  },
  data() {
    return {
      pageTitle: "",
      description: "",
      extraImage: "",
    };
  },
  created() {
    this.getPageMeta();
  },
    mounted () {
    this.getPageMeta()
  },
  updated () {
    this.getPageMeta()
  },
  methods: {
    getPageMeta () {
      // eslint-disable-next-line
      const content = this.$refs.content
      if (content) {
        if (content.pageMeta) {
          Object.assign(this, content.pageMeta)
        } else {
          this.description = content.description
          this.extraImage = content.extraImage
        }
      }
    },
  },
};
</script>

<style lang="less" scoped>
.content {
  margin: 24px 24px 0;
  .link {
    margin-top: 16px;
    &:not(:empty) {
      margin-bottom: 16px;
    }
    a {
      margin-right: 32px;
      height: 24px;
      line-height: 24px;
      display: inline-block;
      i {
        font-size: 24px;
        margin-right: 8px;
        vertical-align: middle;
      }
      span {
        height: 24px;
        line-height: 24px;
        display: inline-block;
        vertical-align: middle;
      }
    }
  }
}

.extra-img {
  text-align: center;
  width: 195px;

  img {
    width: 100%;
  }
}
</style>