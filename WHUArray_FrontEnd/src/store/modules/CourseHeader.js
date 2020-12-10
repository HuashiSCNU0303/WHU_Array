export default {
  state: {
    isLoading: 2,
  },
  mutations: {
    setCourseHeader (state, value) {
      state.isLoading = value;
    },
  },
  actions: {
    setCourseHeader (context, value) {
      context.commit('setCourseHeader', value);
    },
  },
};
