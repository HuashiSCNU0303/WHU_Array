<template>
  <div>
    <a-comment>
      <a-avatar
        slot="avatar"
        src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
        alt="Han Solo"
      />
      <div slot="content">
        <a-form-item>
          <a-textarea :rows="3" />
        </a-form-item>
        <a-form-item>
          <a-button html-type="submit" type="primary"> Add Comment </a-button>
        </a-form-item>
      </div>
    </a-comment>
    <a-divider>最新评论</a-divider>
    <comment-list style="max-height: 472px; overflow: auto" />
  </div>
</template>

<script>
import CommentList from "@/components/lists/CommentList.vue";
export default {
  components: { CommentList },
};
</script>

<style scoped>
.ant-divider-with-text-center {
  margin: 0;
}
.comment-list {
  overflow: auto;
}
</style>
