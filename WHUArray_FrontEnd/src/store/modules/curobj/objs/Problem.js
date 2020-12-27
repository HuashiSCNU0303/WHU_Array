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
    },
    setCurrentProblemScore (state, value) {
      state.problem.score = value;
    },
  },
  actions: {
    setCurrentProblem (context, value) {
      context.commit("setCurrentProblem", value);
    },
    setCurrentProblemScore (context, value) {
      context.commit("setCurrentProblemScore", value);
    },
  }
}