export default {
  state: {
    homework: {
      id: -1,
      name: "",
      status: "",
      endTime: "",
      score: -1,
    }
  },
  mutations: {
    setCurrentHomework (state, value) {
      let newObj = Object.assign({}, state.homework, value);
      state.homework = newObj;
      console.log(state.homework);
    },
  },
  actions: {
    setCurrentHomework (context, value) {
      context.commit("setCurrentHomework", value);
    },
  }
}