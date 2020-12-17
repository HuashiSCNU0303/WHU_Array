export default {
  state: {
    homework: {
      homeworkId: -1,
      homeworkName: "",
      // status: "",
      homeworkTime: "",
      homrworlContent: "",
      score: -1,
    }
  },
  mutations: {
    setCurrentHomework (state, value) {
      let newObj = Object.assign({}, state.homework, value);
      state.homework = newObj;
      console.log(state.homework);
    },
    setHomeworkEndTime (state, value) {
      state.homeworkTime = value;
    }
  },
  actions: {
    setCurrentHomework (context, value) {
      context.commit("setCurrentHomework", value);
    },
    setHomeworkEndTime (context, value) {
      context.commit("setHomeworkEndTime", value);
    }
  }
}