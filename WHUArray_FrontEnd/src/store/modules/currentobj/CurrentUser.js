export default {
  state: {
    user: {
      username: "",
      role: -1,
    },
  },
  mutations: {
    setCurrentUser(state, value) {
      state.user = value;
    },
  },
  actions: {
    setCurrentUser(context, value) {
      context.commit("setCurrentUser", value);
    },
  },
};
