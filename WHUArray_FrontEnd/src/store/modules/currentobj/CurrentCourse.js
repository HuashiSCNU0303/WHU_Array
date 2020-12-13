export default {
  state: {
    course: {
      id: -1,
      name: "",
      teacher: "",
      time: "",
      description: "",
      isSelected: "",
    },
  },
  mutations: {
    setCurrentCourse (state, value) {
      let newObj = Object.assign({}, state.course, value);
      state.course = newObj;
      // console.log(state.course);
    },
    setCourseEndTime (state, value) {
      state.time = value;
    }
  },
  actions: {
    setCurrentCourse (context, value) {
      context.commit("setCurrentCourse", value);
    },
    setCourseEndTime (context, value) {
      context.commit("setCourseEndTime", value);
    }
  },
};
