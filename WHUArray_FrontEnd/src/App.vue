<template>
  <div id="app">
    <router-view v-if="isRouterAlive" />
  </div>
</template>

<script>
export default {
  name: "App",
  provide() {
    return {
      reload: this.reload,
    };
  },
  data() {
    return {
      isRouterAlive: true,
    };
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
  },
};
</script>

<style>
#app {
  height: 100%;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  line-height: inherit !important;
  font-size: initial !important;
  font-weight: initial !important;
}

.page-header .breadcrumb {
  background-color: transparent !important;
  padding: inherit !important;
}

.row {
  margin-left: inherit !important;
}

a:hover {
  text-decoration: none !important;
  line-height: inherit !important;
}

i > svg {
  vertical-align: initial !important;
}

ul {
  margin-bottom: inherit !important;
}
</style>
