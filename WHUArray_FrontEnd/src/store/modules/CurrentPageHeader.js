export default {
  state: {
    header: {
      pageTitle: "",
      description: "测试",
      extraImage: "",
    },
  },
  mutations: {
    setCurrentPageHeader (state, value) {
      state.header = value;
      console.log(state.header);
    },
  },
  actions: {
    setCurrentPageHeader (context, value) {
      context.commit("setCurrentPageHeader", value);
    },
  },
};
