<template>
  <div class="hint">
    <!--<p><a-avatar :icon="icon" /> &nbsp; {{ hint }}</p>-->
    <a-empty
      image="https://gw.alipayobjects.com/mdn/miniapp_social/afts/img/A*pevERLJC9v0AAAAAAAAAAABjAQAAAQ/original"
      :image-style="{
        height: '60px',
      }"
      :description="false"
    />
    <div>{{ hint }}</div>
  </div>
</template>

<script>
export default {
  props: {
    icon: {
      type: String,
    },
    hint: {
      type: String,
    },
  },
};
</script>

<style scoped>
.hint {
  color: rgba(0, 0, 0, 0.65);
  font-size: 16px;
  text-align: center;
  /*margin: 30px 0;*/
  align-self: center;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 100px;
}
</style>
