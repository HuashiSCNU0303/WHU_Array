export default {
  state: {
    user: {
      username: "",
      role: "",
    },
  },
  mutations: {
    setCurrentUser (state, value) {
      state.user = value;
    },
  },
  actions: {
    setCurrentUser (context, value) {
      context.commit("setCurrentUser", value);
    },
  },
};
