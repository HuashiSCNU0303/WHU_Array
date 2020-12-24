<template>
  <a-table
    :columns="columns"
    :data-source="data"
    :custom-row="handleProblemSwitch"
    :row-key="getRecordId"
  >
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
      <a-button size="small" style="width: 90px" @click="() => handleReset(clearFilters)">
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
      <div v-if="score != -1">{{ score }}</div>
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
      default: "Work",
    },
  },
  computed: {
    ...mapState({
      problemListCol: (state) => state.tableProto.student.problemListCol,
    }),
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.problemListCol));
    this.setFilters(); // 处理0（题目号）/1（题目名）/2（课程名）/3（作业名）/4（标签）/5（是否已做）
    if (this.currentPage != "ProblemRepos") {
      this.columns.splice(2, 2);
    }
  },
  methods: {
    setFilters() {
      this.columns[0].onFilter = (text, record) => {
        return record.id.toString().includes(text);
      };
      this.columns[0].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.columns[1].onFilter = (text, record) => {
        return record.name.includes(text);
      };
      this.columns[1].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.columns[2].onFilter = (text, record) => {
        return record.courseName.includes(text);
      };
      this.columns[2].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.columns[3].onFilter = (text, record) => {
        return record.workName.includes(text);
      };
      this.columns[3].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.setTagOptions(this.columns[4].filters);
      this.columns[4].onFilter = (text, record) => {
        return record.tags.indexOf(text) != -1;
      };

      this.columns[5].onFilter = (text, record) => {
        return record.isDone.toString() == text;
      };
    },

    setTagOptions(filters) {
      let obj = {};
      for (let element of this.data) {
        for (let i of element.tags) {
          if (!obj[i]) {
            filters.push({
              text: i,
              value: i,
            });
            obj[i] = 1;
          }
        }
      }
    },

    onFilterDropdownVisibleChange(visible) {
      if (visible) {
        setTimeout(() => {
          this.searchInput.focus();
        });
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

    handleProblemSwitch(record, index) {
      return {
        on: {
          click: () => {
            var source = this.getSource();
            this.utils.toggle.handleProblemSwitch(this, record, source);
          },
        },
      };
    },
    getSource() {
      return this.currentPage == "ProblemRepos" ? "repos" : "work";
    },
    getRecordId(record) {
      return record.id;
    },
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
