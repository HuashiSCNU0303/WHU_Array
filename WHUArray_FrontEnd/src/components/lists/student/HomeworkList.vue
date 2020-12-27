<template>
  <div>
    <a-table :row-key="getRecordId" :columns="columns" :data-source="data">
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
          icon="sync"
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
      <span slot="homeworkName" slot-scope="text, record">
        <a @click="handleHomeworkSwitch(record)">{{ text }}</a>
      </span>
      <!--status要先算完再往里面放了，不然筛选没法做-->
      <span slot="statusTags" slot-scope="statusTags">
        <a-tag
          :key="statusTags"
          :color="
            statusTags === '未提交'
              ? 'volcano'
              : statusTags === '已提交'
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
    currentPage: {
      type: String,
      default: "TodoList",
    },
    data: {
      type: Array,
    },
  },
  computed: {
    ...mapState({
      homeworkListCol: (state) => state.tableProto.student.homeworkListCol,
    }),
  },
  mounted() {
    this.columns = JSON.parse(JSON.stringify(this.homeworkListCol));
    this.setFilters(); // 0/1/5需要设置
    this.setColumns();
  },
  methods: {
    setFilters() {
      this.columns[1].onFilter = (text, record) => {
        return record.courseName.includes(text);
      };
      this.columns[1].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.columns[4].sorter = (a, b) => {
        return a.remainingTime - b.remainingTime;
      };
      this.columns[5].sorter = (a, b) => {
        return a.score - b.score;
      };
    },

    setColumns() {
      switch (this.currentPage) {
        case "CourseHomework": {
          this.columns.splice(1, 1);
          this.columns.splice(3, 1);
          this.columns[0]["filters"] = [
            {
              text: "已提交",
              value: "已提交",
            },
            {
              text: "已结束",
              value: "已结束",
            },
          ];
          this.columns[0]["onFilter"] = (value, record) => {
            return record.status == value;
          };
          break;
        }
        case "CourseTodoHomework": {
          this.columns.splice(1, 1);
          this.columns.splice(4, 1);
          break;
        }
        case "TodoList": {
          this.columns.splice(5, 1);
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

    handleHomeworkSwitch(record) {
      // 跳转到具体作业
      if (this.currentPage == "TodoList") {
        console.log(record.courseName);
        this.utils.toggle.handleWorkSwitch(this, "student", record.id, record.courseName);
      } else {
        this.utils.toggle.handleWorkSwitchByItem(this, "student", record);
      }
    },
    handleCourseSwitch(record) {
      this.utils.toggle.handleCourseSwitch(this, "student", record.courseId);
    },
    getRecordId(record) {
      return record.id;
    },
  },
};
</script>

<style scoped></style>
