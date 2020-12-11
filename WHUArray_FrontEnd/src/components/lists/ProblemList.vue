<template>
  <a-table :columns="columns" :data-source="data">
    <span slot="isDone" slot-scope="isDone">
      <div v-if="isDone == true" class="icon-text-wrap-cell">
        <a-icon type="check-circle" theme="twoTone" class="icon" />
        <span id="done">&nbsp;已做</span>
      </div>
      <div v-else class="icon-text-wrap-cell">
        <a-icon
          type="close-circle"
          theme="twoTone"
          class="icon"
          two-tone-color="#ff4d4f"
        />
        <span id="undone">&nbsp;未做</span>
      </div>
    </span>
    <span slot="score" slot-scope="score">
      <div v-if="score != 0">{{ score }}</div>
      <div v-else>/</div>
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
  color: #1890ff;
}
#undone {
  color: #ff4d4f;
}
.icon-text-wrap-cell {
  display: flex;
}
</style>
