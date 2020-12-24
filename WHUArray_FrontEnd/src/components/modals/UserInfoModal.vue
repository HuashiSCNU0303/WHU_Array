<template>
  <div>
    <a-modal
      v-model="visible"
      :title="studentInfo.name + ' 做题记录'"
      okText="好的"
      cancelText="取消"
      @ok="closeModal"
      @cancel="closeModal"
    >
      <div>
        <p>学号：{{ studentInfo.studentId }}</p>
      </div>
      <a-divider />
      <center-loading v-if="isLoading" />
      <div v-else>
        <div>
          作业状态：
          <a-tag
            :color="
              statusTag === '未提交'
                ? 'volcano'
                : statusTag === '已提交'
                ? 'green'
                : 'geekblue'
            "
          >
            {{ statusTag }}
          </a-tag>
        </div>
        <br />
        <a-table
          :columns="columns"
          :data-source="data"
          size="small"
          :row-key="getRecordId"
        >
          <span slot="score" slot-scope="score">
            <span v-if="score != -1">{{ score }}</span>
            <span v-else>0</span>
          </span>
        </a-table>
      </div>
    </a-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      isLoading: true,
      data: [],
      statusTag: "已提交",
    };
  },
  props: {
    visible: {
      type: Boolean,
    },
    studentInfo: {
      type: Object,
    },
    closeModal: {
      type: Function,
    },
  },
  mounted() {
    this.getRecords();
  },
  computed: {
    ...mapState({
      columns: (state) => state.tableProto.teacher.submitRecordCol,
    }),
  },
  watch: {
    "studentInfo.id": function (newVal) {
      this.isLoading = true;
      this.getRecords();
    },
  },
  methods: {
    getRecords() {
      // 获取这一作业的所有题目，学生做题记录（与学生查看作业调相同的后端接口）
      setTimeout(() => {
        // 传一个学生id和一个作业id，首先获取学生是否提交了这个作业。若作业分数为-1表示未提交，设置statusTag；
        // 然后再获取这个作业下的所有题目，以及每个题目学生的分数；
        // 在这里处理：如果statusTag为未提交，每道题目的分数全部设为0；
        this.data = [
          {
            id: 10001,
            name: "两数之和",
            score: 95,
          },
          {
            id: 10002,
            name: "两数之差",
            score: 40,
          },
        ];
        this.isLoading = false;
        clearInterval();
      }, 1000);
    },

    getRecordId(record) {
      return record.id;
    },
  },
};
</script>

<style scoped></style>
