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
    setCurrentWorkScore (state, value) {
      state.work.score = value;
    }
  },
  actions: {
    setCurrentWork (context, value) {
      context.commit("setCurrentWork", value);
    },
    setCurrentWorkScore (context, value) {
      context.commit("setCurrentWorkScore", value);
    }
  }
}