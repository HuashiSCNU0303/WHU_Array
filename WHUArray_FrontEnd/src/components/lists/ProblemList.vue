<template>
  <a-table :columns="columns" :data-source="data" :customRow="handleProblemSwitch">
    <span slot="problemName" slot-scope="problemName">
      <a>{{ problemName }}</a>
    </span>
    <span slot="isDone" slot-scope="isDone">
      <div v-if="isDone == true" class="icon-text-wrap-cell">
        <a-icon
          type="check-circle"
          theme="twoTone"
          class="icon"
          two-tone-color="#ff4d4f"
        />
        <span id="done">&nbsp;已做</span>
      </div>
    </span>
    <span slot="score" slot-scope="score">
      <div v-if="score != 0">{{ score }}</div>
    </span>
    <span slot="problemTags" slot-scope="problemTags">
      <a-tag v-for="problemTag in problemTags" :key="problemTag" :color="'volcano'">
        {{ problemTag }}
      </a-tag>
    </span>
  </a-table>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      columns: [{}],
    };
  },
  props: {
    data: {
      type: Array, //指定传入的类型
    },
    currentPage: {
      type: String,
    },
  },
  computed: {
    ...mapState({
      problemListCol: (state) => state.tableColProto.problemListCol,
    }),
  },
  methods: {
    handleProblemSwitch(record, index) {
      return {
        on: {
          click: () => {
            var item = {
              id: record.problemId,
              name: record.problemName,
              course: record.problemCourse,
              work: record.problemWork,
              tag: record.problemTags,
              isDone: record.isDone,
              score: record.score,
            };
            this.utils.toggle.handleProblemSwitch(this, item);
          },
        },
      };
    },
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.problemListCol));
    if (typeof this.currentPage !== "undefined" && this.currentPage != "ProblemRepos") {
      this.columns.splice(2, 2);
    }
  },
};
</script>

<style scoped>
.icon {
  font-size: 24px;
}
#done {
  color: #ff4d4f;
}
.icon-text-wrap-cell {
  display: flex;
}
</style>
