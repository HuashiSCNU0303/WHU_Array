<template>
  <div>
    <!--<center-loading
      v-if="isLoading == 1"
      :style="{ height: '100%', paddingTop: '64px' }"
    />-->
    <a-layout-content :style="{ height: '100%', paddingTop: '64px' }">
      <page-header>
        <slot v-if="breadCrumb != null" slot="breadcrumb" name="breadcrumb">
          <bread-crumb-nav :items="breadCrumb" :key="refreshKey" />
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
          <div v-if="header.extraType == 'image'" class="extra-img" :key="refreshKey">
            <img :src="header.extraImage" />
          </div>
          <div
            v-else-if="header.extraType == 'countdown'"
            class="countdown"
            :key="refreshKey"
          >
            <countdown :time="countdownTime" />
            <score-display
              class="score-display"
              :score="score"
              :time="countdownTime"
              :type="pageType"
            />
          </div>
          <div v-else-if="header.extraType == 'score'">
            <score-display
              class="score-display score"
              :score="score"
              :time="countdownTime"
              :type="pageType"
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
        >版权所有&nbsp;©2020&nbsp;武汉大学计算机学院</a-layout-footer
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
      breadCrumb: (state) => state.curObj.page.breadCrumb,
      pageType: (state) => state.curObj.page.type,
      header: (state) => state.curObj.page.header,
      homework: (state) => state.curObj.homework.homework,
      exam: (state) => state.curObj.exam.exam,
      problem: (state) => state.curObj.problem.problem,
    }),
    countdownTime: function () {
      if (this.header.extraType != "countdown") {
        return null;
      }
      switch (this.pageType) {
        case "Homework": {
          return this.homework.endTime;
        }
        case "Exam": {
          return this.exam.endTime;
        }
      }
    },
    score: function () {
      if (this.header.extraType == "image") {
        return null;
      }
      switch (this.pageType) {
        case "Homework": {
          return this.homework.score;
        }
        case "Exam": {
          return this.exam.score;
        }
        default: {
          return this.problem.score;
        }
      }
    },
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
  margin-top: -64px;
  padding-bottom: 16px;
  margin-left: -200px;
  text-align: center;
  width: 350px;
}

.score-display {
  margin-top: 16px;
}

.score {
  margin-top: -32px;
  padding-bottom: 48px;
  margin-left: -300px;
  text-align: center;
}
</style>
