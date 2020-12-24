<template>
  <div>
    <a-layout-content :style="{ height: '100%', paddingTop: '64px' }">
      <page-header>
        <slot v-if="page.breadCrumb != null" slot="breadcrumb" name="breadcrumb">
          <bread-crumb-nav :items="page.breadCrumb" :key="refreshKey" />
        </slot>
        <slot slot="title" name="title">
          <h1 v-html="page.header.pageTitle" />
        </slot>
        <slot slot="content" name="headerContent"></slot>
        <div
          slot="content"
          v-if="!this.$slots.headerContent && page.header.description"
          v-html="page.header.description"
        ></div>
        <slot slot="extra" name="extra">
          <div
            v-if="page.header.extraType == 'image'"
            class="extra-img"
            :key="refreshKey"
          >
            <img :src="page.header.extraImage" />
          </div>
          <div
            v-else-if="page.header.extraType == 'countdown'"
            class="countdown"
            :key="refreshKey"
          >
            <countdown :time="countdownTime" />
            <score-display
              class="score-display"
              :score="score"
              :time="countdownTime"
              :type="page.type"
            />
          </div>
          <div v-else-if="page.header.extraType == 'score'">
            <score-display
              class="score-display score"
              :score="score"
              :time="countdownTime"
              :type="page.type"
            />
          </div>
          <div v-else-if="page.header.extraType == 'courseOperation'" class="op-buttons">
            <a-button
              v-if="page.header.editStatus == true"
              type="primary"
              icon="edit"
              @click="page.header.editInfo"
              >编辑信息</a-button
            >
            <a-button v-else type="primary" icon="edit" disabled>不可编辑</a-button>

            <a-button
              type="danger"
              icon="delete"
              @click="page.header.delCourse"
              style="margin-left: 16px"
              >删除课程</a-button
            >

            <a-button
              v-if="page.header.editStatus == true"
              type="primary"
              icon="carry-out"
              @click="page.header.endCourse"
              style="margin-left: 16px"
              ghost
              >结束课程</a-button
            >
            <a-button
              v-else
              type="primary"
              icon="carry-out"
              style="margin-left: 16px"
              disabled
              >已结束</a-button
            >
          </div>
          <div v-else-if="page.header.extraType == 'workOperation'" class="op-buttons">
            <a-button
              v-if="page.header.editStatus == true"
              type="primary"
              icon="edit"
              @click="page.header.editInfo"
              >编辑信息</a-button
            >
            <a-button v-else type="primary" icon="edit" disabled>不可编辑</a-button>

            <a-button
              type="danger"
              icon="delete"
              @click="page.header.delWork"
              style="margin-left: 16px"
              >删除{{ page.type == "Homework" ? "作业" : "考试" }}</a-button
            >

            <a-button
              v-if="page.header.editStatus == true"
              type="primary"
              icon="carry-out"
              @click="page.header.publishWork"
              style="margin-left: 16px"
              ghost
              >发布{{ page.type == "Homework" ? "作业" : "考试" }}</a-button
            >
            <a-button
              v-else
              type="primary"
              icon="carry-out"
              style="margin-left: 16px"
              disabled
              >已发布</a-button
            >
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
      page: (state) => state.curObj.page,
      work: (state) => state.curObj.work.work,
      problem: (state) => state.curObj.problem.problem,
    }),
    countdownTime: function () {
      if (this.page.header.extraType != "countdown") {
        return null;
      }
      return this.work.endTime;
    },
    score: function () {
      if (this.page.header.extraType == "image") {
        return null;
      }
      return this.page.type.indexOf("Problem") == 0
        ? this.problem.score
        : this.work.score;
      // 以后好像还有课程的分数？
    },
  },
  methods: {
    updateComponents() {
      this.refreshKey = this.refreshKey ? false : true;
    },
  },
  watch: {
    "page.header": function () {
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

.op-buttons {
  margin-right: 48px;
  margin-bottom: 48px;
}
</style>
