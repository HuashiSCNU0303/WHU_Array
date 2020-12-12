export default {
  state: {
    problem: {
      id: -1,
      name: "",
      course: "",
      work: "",
      tag: [],
      isDone: false,
      score: -1,
    }
  },
  mutations: {
    setCurrentProblem (state, value) {
      let newObj = Object.assign({}, state.problem, value);
      state.problem = newObj;
      // console.log(state.homework);
    },
  },
  actions: {
    setCurrentProblem (context, value) {
      context.commit("setCurrentProblem", value);
    },
  }
}