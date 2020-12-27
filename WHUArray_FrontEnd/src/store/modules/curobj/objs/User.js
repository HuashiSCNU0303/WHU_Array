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
    setCurrentUserNickname (state, value) {
      state.user.nickname = value;
    },
    setCurrentUserFace (state, value) {
      state.user.userFace = value;
    },
  },
  actions: {
    setCurrentUser (context, value) {
      context.commit("setCurrentUser", value);
    },
    setCurrentUserNickname (context, value) {
      context.commit("setCurrentUserNickname", value);
    },
    setCurrentUserFace (context, value) {
      context.commit("setCurrentUserFace", value);
    },
  },
};
