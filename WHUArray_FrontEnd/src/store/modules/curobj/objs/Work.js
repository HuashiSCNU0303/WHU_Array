export default {
  state: {
    work: {
      id: -1,
      name: "",
      status: "",
      startTime: "",
      endTime: "",
      score: -1,
      type: "Homework",
    }
  },
  mutations: {
    setCurrentWork (state, value) {
      let newObj = Object.assign({}, state.work, value);
      state.work = newObj;
    },
  },
  actions: {
    setCurrentWork (context, value) {
      context.commit("setCurrentWork", value);
    },
  }
}