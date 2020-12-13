export default {
  state: {
    header: {
      pageTitle: "",
      description: "测试",
      extraImage: "",
    },
    breadCrumb: null,
    type: "",
  },
  mutations: {
    setCurrentPageHeader (state, value) {
      state.header = value;
    },
    setCurrentBreadCrumb (state, value) {
      state.breadCrumb = value;
    },
    setCurrentPageType (state, value) {
      state.type = value;
    },
  },
  actions: {
    setCurrentPageHeader (context, value) {
      context.commit("setCurrentPageHeader", value);
    },
    setCurrentBreadCrumb (context, value) {
      context.commit("setCurrentBreadCrumb", value);
    },
    setCurrentPageType (context, value) {
      context.commit("setCurrentPageType", value);
    },
  },
};
