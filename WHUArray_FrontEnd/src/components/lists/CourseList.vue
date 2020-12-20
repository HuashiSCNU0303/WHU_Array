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
          :placeholder="`Search ${column.dataIndex}`"
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
      <span slot="isSelected" slot-scope="text, record">
        <div v-if="record.isSelected == true" class="icon-text-wrap-cell">
          <a-icon
            type="check-circle"
            theme="twoTone"
            class="icon"
            two-tone-color="#ff4d4f"
          />
          <span id="selected">&nbsp;已选</span>
        </div>
      </span>
      <span slot="operation" slot-scope="text, record">
        <a-button v-if="record.isSelected" type="danger" @click="dropCourse(record)"
          >撤课</a-button
        >
        <a-button v-else type="primary" @click="addCourse(record)">选课</a-button>
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
      buttonText: "选课",
      columns: [{}],
    };
  },
  computed: {
    ...mapState({
      courseListCol: (state) => state.tableProto.student.courseListCol,
    }),
  },
  props: {
    data: {
      type: Array, //指定传入的类型
    },
    currentPage: {
      type: String,
      default: "AddCourse",
    },
  },
  mounted() {
    // 深度复制，不能简单用赋值号，不然只复制引用过去
    this.columns = JSON.parse(JSON.stringify(this.courseListCol));
    this.setFilters();

    if (this.currentPage == "TeaPreCourse") {
      this.columns.splice(2, 1);
      this.columns.splice(4, 1);
    }
  },
  methods: {
    getRecordId(record) {
      return record.id;
    },

    setFilters() {
      this.columns[1].onFilter = (text, record) => {
        return record.name.includes(text);
      };
      this.columns[1].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.columns[2].onFilter = (text, record) => {
        return record.teacher.includes(text);
      };
      this.columns[2].onFilterDropdownVisibleChange = this.onFilterDropdownVisibleChange();

      this.setGradeOptions(this.columns[3].filters);
      this.columns[3].onFilter = (text, record) => {
        return record.grade == text;
      };

      this.columns[5].onFilter = (text, record) => {
        return record.isSelected.toString() == text;
      };
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

    setGradeOptions(filters) {
      var date = new Date();
      var latestGrade = date.getMonth() < 8 ? date.getFullYear() - 1 : date.getFullYear();
      for (var i = 0; i < 8; i++) {
        filters.push({
          text: latestGrade - i + "级",
          value: latestGrade - i + "级",
        });
      }
    },

    dropCourse(record) {
      var _this = this;
      this.$confirm({
        title: "你确定要撤课吗？",
        content: "你在这门课下的所有作业记录将会被删除",
        okText: "确定",
        okType: "danger",
        cancelText: "取消",
        onOk() {
          // 发撤课请求
          _this.$success({
            title: "操作成功",
            onOk() {
              record.isSelected = false;
            },
          });
        },
        onCancel() {},
      });
    },

    addCourse(record) {
      // 发选课请求
      this.$success({
        title: "操作成功",
        onOk() {
          record.isSelected = true;
        },
      });
    },
  },
};
</script>

<style scoped>
.icon {
  font-size: 24px;
}
#selected {
  color: #ff4d4f;
}
.icon-text-wrap-cell {
  display: flex;
}
</style>
