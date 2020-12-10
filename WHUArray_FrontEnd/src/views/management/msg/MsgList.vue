<template>
  <div>
    <a-list item-layout="horizontal" :data-source="data">
      <a-list-item slot="renderItem" slot-scope="item" @click="handleShowModal(item)">
        <a-list-item-meta :description="item.description" :title="item.title">
          <a-avatar
            slot="avatar"
            src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
          />
        </a-list-item-meta>
      </a-list-item>
    </a-list>
    <a-modal
      v-model="modalVisible"
      :title="currentItem.title"
      ok-text="去看看"
      cancel-text="知道了"
      @ok="handleViewDetail"
    >
      {{ currentItem.description }}
    </a-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      modalVisible: false,
      currentItem: {
        title: "",
        description: "",
      },
    };
  },
  props: {
    data: {
      type: Array, //指定传入的类型
    },
  },
  methods: {
    handleShowModal(item) {
      this.modalVisible = true;
      this.currentItem = item;
    },
    handleViewDetail() {
      this.modalVisible = false;
      // 基于currentItem做一些跳转操作
    },
  },
};
</script>

<style scoped></style>
