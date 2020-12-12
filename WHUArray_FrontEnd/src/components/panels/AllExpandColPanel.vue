<template>
  <div>
    <a-collapse :default-active-key="['1', '2']" :bordered="false">
      <template #expandIcon="props">
        <a-icon type="caret-right" :rotate="props.isActive ? 90 : 0" />
      </template>
      <a-collapse-panel key="1" :header="items[0].header" :style="customStyle">
        <div class="content">
          <center-loading v-if="isLoading == true" />
          <div v-else>
            <div v-if="type == 'exam'">
              <exam-card
                v-if="items[0].hasCurrentExam == true"
                :currentExamData="items[0].currentExamData"
              />
              <div v-else><icon-hint :hint="items[0].emptyHint" /></div>
            </div>
            <div v-else>
              <course-card-list
                v-if="items[0].currentCourseList.length > 0"
                :data="items[0].currentCourseList"
              ></course-card-list>
              <div v-else><icon-hint :hint="items[0].emptyHint" /></div>
            </div>
          </div>
        </div>
      </a-collapse-panel>
      <a-collapse-panel key="2" :header="items[1].header" :style="customStyle">
        <div class="content">
          <center-loading v-if="isLoading == true" />
          <div v-else>
            <div v-if="type == 'exam'">
              <exam-list
                v-if="items[1].examList.length > 0"
                :data="items[1].examList"
                :currentPage="currentPage"
              ></exam-list>
              <div v-else><icon-hint :hint="items[1].emptyHint" /></div>
            </div>
            <div v-else>
              <course-card-list
                v-if="items[1].endCourseList.length > 0"
                :data="items[1].endCourseList"
              ></course-card-list>
              <div v-else><icon-hint :hint="items[1].emptyHint" /></div>
            </div>
          </div>
        </div>
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
import ExamCard from "@/components/cards/ExamCard.vue";
import CourseCardList from "@/components/lists/CourseCardList.vue";

export default {
  data() {
    return {
      customStyle:
        "background: #fff; padding-bottom: 18px; border-bottom: 1px solid #e8e8e8; overflow: hidden",
    };
  },
  props: {
    items: {
      type: Array,
    },
    type: {
      type: String,
    },
    isLoading: {
      type: Boolean,
    },
    currentPage: {
      type: String,
    },
  },
  components: {
    ExamCard,
    CourseCardList,
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
