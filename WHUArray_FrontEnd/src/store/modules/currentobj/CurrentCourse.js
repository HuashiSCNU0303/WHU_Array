export default {
  state: {
    course: {
      id: -1,
      name: "",
      teacher: "",
      time: "",
      description: "",
    },
  },
  mutations: {
    setCurrentCourse (state, value) {
      let newObj = Object.assign({}, state.course, value);
      state.course = newObj;
      // console.log(state.course);
    },
  },
  actions: {
    setCurrentCourse (context, value) {
      context.commit("setCurrentCourse", value);
    },
  },
};
