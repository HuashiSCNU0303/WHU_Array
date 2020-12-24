<template>
  <div>
    <a-table :columns="columns" :data-source="data" :row-key="getRecordId">
      <div
        slot="filterDropdown"
        slot-scope="{ setSelectedKeys, selectedKeys, confirm, clearFilters, column }"
        style="padding: 8px"
      >
        <a-input
          v-ant-ref="(c) => (searchInput = c)"
          :value="selectedKeys[0]"
          style="width: 188px; margin-bottom: 8px; display: block"
          @change="(e) => setSelectedKeys(e.target.value ? [e.target.value] : [])"
          @pressEnter="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        />
        <a-button
          type="primary"
          icon="search"
          size="small"
          style="width: 90px; margin-right: 8px"
          @click="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        >
          搜索
        </a-button>
        <a-button
          size="small"
          style="width: 90px"
          @click="() => handleReset(clearFilters)"
        >
          重置
        </a-button>
      </div>
      <a-icon
        slot="filterIcon"
        slot-scope="filtered"
        type="search"
        :style="{ color: filtered ? '#108ee9' : undefined }"
      />
      <template slot="customRender" slot-scope="text, record, index, column">
        <span v-if="searchText && searchedColumn === column.dataIndex">
          <template
            v-for="(fragment, i) in text
              .toString()
              .split(new RegExp(`(?<=${searchText})|(?=${searchText})`, 'i'))"
          >
            <mark
              v-if="fragment.toLowerCase() === searchText.toLowerCase()"
              :key="i"
              class="highlight"
              >{{ fragment }}</mark
            >
            <template v-else>{{ fragment }}</template>
          </template>
        </span>
        <template v-else>
          {{ text }}
        </template>
      </template>

      <span slot="courseName" slot-scope="text, record">
        <a @click="handleCourseSwitch(record)">{{ text }}</a>
      </span>
      <span slot="statusTags" slot-scope="statusTags">
        <a-tag
          :key="statusTags"
          :color="
            statusTags === '准备开始'
              ? 'volcano'
              : statusTags === '已结束'
              ? 'green'
              : 'geekblue'
          "
        >
          {{ statusTags }}
        </a-tag>
      </span>
      <span slot="score" slot-scope="score">
        <div v-if="score != -1">{{ score }}</div>
        <div v-else>0</div>
      </span>
      <span slot="remainingTime" slot-scope="remainingTime">
        <span
          v-if="remainingTime >= 0"
          v-html="utils.countdown.getSimpleLeftTime(remainingTime)"
        />
      </span>
    </a-table>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      searchText: "",
      searchInput: null,
      searchedColumn: "",
      columns: [{}],
    };
  },
  props: {
    data: {
      type: Array, //指定传入的类型
    },
    currentPage: {
      type: String,
      default: "TodoList",
    },
  },
  computed: {
    ...mapState({
      examListCol: (state) => state.tableProto.student.examListCol,
    }),
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.examListCol));
    this.setFilters();
    this.setColumns();
  },
  methods: {
    setFilters() {
      this.columns[6].sorter = (a, b) => {
        return a.remainingTime - b.remainingTime;
      };
      this.columns[7].sorter = (a, b) => {
        return a.score - b.score;
      };
    },

    setColumns() {
      switch (this.currentPage) {
        case "CourseExam": {
          this.columns.splice(1, 2);
          this.columns.splice(4, 1);
          break;
        }
        case "CourseTodoExam": {
          this.columns.splice(1, 2);
          this.columns.splice(5, 1);
          break;
        }
        case "TodoList": {
          this.columns.splice(7, 1);
          break;
        }
      }
    },

    handleSearch(selectedKeys, confirm, dataIndex) {
      confirm();
      this.searchText = selectedKeys[0];
      this.searchedColumn = dataIndex;
    },

    handleReset(clearFilters) {
      clearFilters();
      this.searchText = "";
    },

    onFilterDropdownVisibleChange(visible) {
      if (visible) {
        setTimeout(() => {
          this.searchInput.focus();
        });
      }
    },

    handleCourseSwitch(record) {
      // 跳转到id对应的课程，待后端接口完成以后再做
      var item = {
        id: record.courseId,
        name: record.courseName,
        teacher: record.teacher,
        time: "2019-2020",
        description: "",
      };
      this.utils.toggle.handleCourseSwitch(this, item);
    },
    getRecordId(record) {
      return record.id;
    },
  },
};
</script>

<style scoped></style>
